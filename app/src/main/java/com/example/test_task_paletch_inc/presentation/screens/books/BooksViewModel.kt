package com.example.test_task_paletch_inc.presentation.screens.books

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.data.dao.BooksDao
import com.example.test_task_paletch_inc.data.dao.entity.Books
import com.example.test_task_paletch_inc.data.database.AppDatabase
import com.example.test_task_paletch_inc.domain.models.Book
import com.example.test_task_paletch_inc.data.network.ListCategoryWithBooks
import com.example.test_task_paletch_inc.data.network.NYTimesApi
import com.example.test_task_paletch_inc.data.network.NYTimesApiStatus
//import com.example.test_task_paletch_inc.data.repository.BookRepository
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {
    private val _status = MutableLiveData<NYTimesApiStatus>()
    val status: LiveData<NYTimesApiStatus> = _status

    private val _data = MutableLiveData<ListCategoryWithBooks>()
    val data: LiveData<ListCategoryWithBooks> = _data

    private val _booksData = MutableLiveData<List<Book>>()
    val booksData: LiveData<List<Book>> = _booksData

    private lateinit var category : String

    init {
        getCategoriesWithBooks()
    }

    private fun getCategoriesWithBooks() {
        viewModelScope.launch {
            _status.value = NYTimesApiStatus.LOADING
            try {
                _data.value = NYTimesApi.retrofitService.getBooks(Constants.API_KEY)
                _status.value = NYTimesApiStatus.DONE
                insertBooksToDataBase()
                getBookFromDB()
            } catch (e: Exception) {
                _status.value = NYTimesApiStatus.ERROR
            }
        }
    }

    private fun insertBooksToDataBase() {
        val db = AppDatabase.getDatabase()
        if (db != null) {
            val booksDao: BooksDao = db.booksDao()
            viewModelScope.launch {
                for ((iterator, book) in booksData.value!!.withIndex()) {
                    booksDao.insert(
                        Books(
                            iterator,
                            book.name,
                            book.category,
                            book.description,
                            book.author,
                            book.publisher,
                            book.imageUrl,
                            book.rank,
                            book.linkToBuy
                        )
                    )
                }
            }
        }
        Log.d("MyTag", "insertedBooks!")
    }

    private fun getBookFromDB(){
        val db = AppDatabase.getDatabase()
        if (db != null) {
            val booksDao: BooksDao = db.booksDao()
            viewModelScope.launch {
                val listBooks = booksDao.getBooksForCategory(category)
                for (book in listBooks) {
                    Log.d("MyTag",
                        book.name + " " +
                        book.category + " " +
                        book.author + " " +
                        book.rank + " " +
                        book.description + " " +
                        book.imageUrl + " " +
                        book.link + " " +
                        book.publisher + " "
                        )
                }
            }
        }
        Log.d("MyTag", "getBooks!")
    }

    fun getBooksList(category: String): LiveData<List<Book>> {
        this.category = category
        val tempMutableList: MutableList<Book> = mutableListOf()
        if (status.value == NYTimesApiStatus.DONE && data.isInitialized) {
            for (listElement in data.value!!.results.lists) {
                if (listElement.listName == category) {
                    for (book in listElement.books) {
                        tempMutableList.add(
                            Book(
                                book.title,
                                category,
                                book.description,
                                book.author,
                                book.publisher,
                                book.bookImage,
                                book.rank.toString(),
                                book.buyLinks[Constants.AMAZON_LINK_INDEX].url
                            )
                        )
                    }
                }
            }
            _booksData.value = tempMutableList
        }
        return booksData
    }

    fun getLinkById(id: Int): String {
        return booksData.value!![id].linkToBuy
    }

}