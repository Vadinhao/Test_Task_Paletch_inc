package com.example.test_task_paletch_inc.presentation.screens.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.network.ListCategory
import com.example.test_task_paletch_inc.network.NYTimesApi
import com.example.test_task_paletch_inc.network.NYTimesApiStatus
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {
    private val _status = MutableLiveData<NYTimesApiStatus>()
    val status: LiveData<NYTimesApiStatus> = _status

    private val _data = MutableLiveData<ListCategory>()
    val data: LiveData<ListCategory> = _data

    init {
        getCategoriesWithBooks()
    }

    private fun getCategoriesWithBooks() {
        viewModelScope.launch {
            _status.value = NYTimesApiStatus.LOADING
            try {
                _data.value = NYTimesApi.retrofitService.getCategories(Constants.API_KEY)
                _status.value = NYTimesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = NYTimesApiStatus.ERROR
            }
        }
    }
}