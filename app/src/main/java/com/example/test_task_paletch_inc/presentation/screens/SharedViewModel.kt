package com.example.test_task_paletch_inc.presentation.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_paletch_inc.AppTestTaskPaletchInc
import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.data.database.AppDatabase
import com.example.test_task_paletch_inc.data.network.NYTimesApi
import com.example.test_task_paletch_inc.data.network.NYTimesApiStatus
import com.example.test_task_paletch_inc.data.repository.Book.BookRepository
import com.example.test_task_paletch_inc.data.repository.Category.CategoryRepository
import com.example.test_task_paletch_inc.domain.models.Book
import com.example.test_task_paletch_inc.domain.models.Category
import kotlinx.coroutines.launch
import javax.inject.Inject

class SharedViewModel : ViewModel() {

    @Inject
    lateinit var bookRepository: BookRepository

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @SuppressLint("StaticFieldLeak")
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var dataBase: AppDatabase

    private val _statusForBook = MutableLiveData<NYTimesApiStatus>()
    val statusForBook: LiveData<NYTimesApiStatus> = _statusForBook

    private val _statusForCategory = MutableLiveData<NYTimesApiStatus>()
    val statusForCategory: LiveData<NYTimesApiStatus> = _statusForCategory

    private val _dataBooks = MutableLiveData<List<Book>>()
    val dataBooks: LiveData<List<Book>> = _dataBooks

    private val _dataBookByCategory = MutableLiveData<List<Book>>()
    val dataBookByCategory: LiveData<List<Book>> = _dataBookByCategory

    private val _dataCategories = MutableLiveData<List<Category>>()
    val dataCategories: LiveData<List<Category>> = _dataCategories

    init {
        AppTestTaskPaletchInc.appComponent.inject(this)
        getAllBooks()
        getAllCategories()
    }

    private fun getAllBooks() {
        viewModelScope.launch {
            _statusForBook.value = NYTimesApiStatus.LOADING
            try {
                if (NYTimesApi.checkForInternet(context)) {
                    _dataBooks.value = bookRepository.getAllBooksFromNetwork(Constants.API_KEY)
                    insertBooksToDataBase()
                } else
                    getBookFromDB()
                _statusForBook.value = NYTimesApiStatus.DONE
            } catch (e: Exception) {
                _statusForBook.value = NYTimesApiStatus.ERROR
                getBookFromDB()
            }
        }
    }

    private fun insertBooksToDataBase() {
        viewModelScope.launch {
            bookRepository.insertAllBooksIntoDB(dataBooks.value!!)
        }
    }

    private fun getBookFromDB() {
        viewModelScope.launch {
            _dataBooks.value = bookRepository.getAllBooksFromDB()
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            _statusForCategory.value = NYTimesApiStatus.LOADING
            try {
                if (NYTimesApi.checkForInternet(context)) {
                    _dataCategories.value =
                        categoryRepository.getAllCategoriesFromNetwork(Constants.API_KEY)
                    insertCategoriesToDataBase()
                } else
                    getCategoryFromDB()
                _statusForCategory.value = NYTimesApiStatus.DONE
            } catch (e: Exception) {
                _statusForCategory.value = NYTimesApiStatus.ERROR
                getCategoryFromDB()
            }
        }
    }

    private fun insertCategoriesToDataBase() {
        viewModelScope.launch {
            categoryRepository.insertAllCategoryIntoDB(dataCategories.value!!)
        }
    }

    private fun getCategoryFromDB() {
        viewModelScope.launch {
            _dataCategories.value = categoryRepository.getAllCategoriesFromDB()
        }
    }

    fun getLinkById(id: Int): String {
        return dataBooks.value!![id].linkToBuy
    }

    fun getCategoryNameById(id: Int): String {
        return dataCategories.value!![id].name
    }

    fun getBooksListByCategory(selectedCategory: String) {
        val mutableList = mutableListOf<Book>()
        for (element in dataBooks.value!!) {
            if (element.category == selectedCategory)
                mutableList.add(element)
        }
        _dataBookByCategory.value = mutableList
    }

}