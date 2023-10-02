package com.example.test_task_paletch_inc.presentation.screens

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_paletch_inc.AppTestTaskPaletchInc
import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.data.database.AppDatabase
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

    @Inject
    lateinit var context: Context

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

    private lateinit var selectedCategory: String

    init {
        //(applicationContext as AppTestTaskPaletchInc).getComponent().inject(this)
        AppTestTaskPaletchInc.appComponent.inject(this)
        getAllBooks()
        getAllCategories()
    }

    private fun getAllBooks() {
        viewModelScope.launch {
            _statusForBook.value = NYTimesApiStatus.LOADING
            Log.d("MyTag", "Я ебал книгу " + _statusForBook.value!!)
            try {
                if (checkForInternet(context)) {
                    Log.d("MyTag", "ПРОБУЮ КНИГУ ПО СЕТИ")
                    _dataBooks.value = bookRepository.getAllBooksFromNetwork(Constants.API_KEY)
                    Log.d("MyTag", "Я ПОНЯЛ, Я НАЕБНУЛ БАЗУ КНИГОЙ")
                    insertBooksToDataBase()
                    Log.d("MyTag", "КНИГА НОРМ ПО СЕТИ")
                } else{
                    Log.d("MyTag", "ПРОБУЮ КНИГУ ПО БАЗЕ")
                    getBookFromDB()
                    Log.d("MyTag", "КНИГА НОРМ ПО БАЗЕ")
                }
                _statusForBook.value = NYTimesApiStatus.DONE
                Log.d("MyTag", "Я ебал книгу " + _statusForBook.value!!)
            } catch (e: Exception) {
                _statusForBook.value = NYTimesApiStatus.ERROR
                getBookFromDB()
            }
        }
    }

    private fun insertBooksToDataBase() {
        Log.d("MyTag", "1")
        val db = AppDatabase.getDatabase()
        Log.d("MyTag", "2")
        if (db != null)
            Log.d("MyTag", "3")
            viewModelScope.launch {
                Log.d("MyTag", "4")
                bookRepository.insertAllBookIntoDB(dataBooks.value!!, selectedCategory)
                Log.d("MyTag", "5")
            }
        Log.d("MyTag", "6")
    }

    private fun getBookFromDB() {
        val db = AppDatabase.getDatabase()
        if (db != null) {
            viewModelScope.launch {
                _dataBooks.value = bookRepository.getBooksForCategoryFromDB(selectedCategory)
            }
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            _statusForCategory.value = NYTimesApiStatus.LOADING
            Log.d("MyTag", "Я ебал Категорию " + _statusForBook.value!!)
            try {
                if (checkForInternet(context)) {
                    _dataCategories.value =
                        categoryRepository.getCategoriesAllFromNetwork(Constants.API_KEY)
                    insertCategoriesToDataBase()
                    Log.d("MyTag", "КАТЕГОРИЯ НОРМ ПО СЕТИ")
                } else{
                    getCategoryFromDB()
                    Log.d("MyTag", "КАТЕГОРИЯ НОРМ ПО БАЗЕ")
                }
                _statusForCategory.value = NYTimesApiStatus.DONE
                Log.d("MyTag", "Я ебал Категорию " + _statusForBook.value!!)
            } catch (e: Exception) {
                _statusForCategory.value = NYTimesApiStatus.ERROR
                getCategoryFromDB()
            }
        }
    }

    private fun insertCategoriesToDataBase() {
        val db = AppDatabase.getDatabase()
        if (db != null)
            viewModelScope.launch {
                categoryRepository.insertAllCategoryIntoDB(dataCategories.value!!)
            }
    }

    private fun getCategoryFromDB() {
        val db = AppDatabase.getDatabase()
        if (db != null) {
            viewModelScope.launch {
                _dataCategories.value = categoryRepository.getAllCategoriesFromDB()
            }
        }
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
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
            if (element.category.equals(selectedCategory))
                mutableList.add(element)
        }
        _dataBookByCategory.value = mutableList
    }

}