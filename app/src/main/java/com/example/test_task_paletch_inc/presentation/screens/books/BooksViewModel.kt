package com.example.test_task_paletch_inc.presentation.screens.books

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.domain.models.Book
import com.example.test_task_paletch_inc.network.BookList
import com.example.test_task_paletch_inc.network.ListCategoryResult
import com.example.test_task_paletch_inc.network.ListCategoryWithBooks
import com.example.test_task_paletch_inc.network.NYTimesApi
import com.example.test_task_paletch_inc.network.NYTimesApiStatus
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {
    private val _status = MutableLiveData<NYTimesApiStatus>()
    val status: LiveData<NYTimesApiStatus> = _status

    private val _data = MutableLiveData<ListCategoryWithBooks>()
    val data: LiveData<ListCategoryWithBooks> = _data

    private val _booksData = MutableLiveData<List<Book>>()
    val booksData: LiveData<List<Book>> = _booksData

    init {
        getCategoriesWithBooks()
    }

    private fun getCategoriesWithBooks() {
        viewModelScope.launch {
            _status.value = NYTimesApiStatus.LOADING
            try {
                _data.value = NYTimesApi.retrofitService.getBooks(Constants.API_KEY)
                _status.value = NYTimesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = NYTimesApiStatus.ERROR
            }
        }
    }

    fun getBooksList(category: String): LiveData<List<Book>> {
        val tempMutableList: MutableList<Book> = mutableListOf()
        if (status.value == NYTimesApiStatus.DONE && data.isInitialized) {
            for (listElement in data.value!!.results.lists) {
                if (listElement.listName == category) {
                    for (book in listElement.books) {
                        tempMutableList.add(
                            Book(
                                book.title,
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