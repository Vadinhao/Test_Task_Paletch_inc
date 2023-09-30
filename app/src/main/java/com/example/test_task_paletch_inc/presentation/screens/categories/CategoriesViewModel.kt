package com.example.test_task_paletch_inc.presentation.screens.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.data.dao.CategoriesDao
import com.example.test_task_paletch_inc.data.dao.entity.Categories
import com.example.test_task_paletch_inc.data.database.AppDatabase
import com.example.test_task_paletch_inc.domain.models.Category
import com.example.test_task_paletch_inc.data.network.CategoryInfo
import com.example.test_task_paletch_inc.data.network.ListCategory
import com.example.test_task_paletch_inc.data.network.NYTimesApi
import com.example.test_task_paletch_inc.data.network.NYTimesApiStatus
//import com.example.test_task_paletch_inc.data.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val _status = MutableLiveData<NYTimesApiStatus>()
    val status: LiveData<NYTimesApiStatus> = _status

    private val _data = MutableLiveData<ListCategory>()
    val data: LiveData<ListCategory> = _data

    private val _categoriesData = MutableLiveData<List<Category>>()
    val categoriesData: LiveData<List<Category>> = _categoriesData

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            _status.value = NYTimesApiStatus.LOADING
            try {
                _data.value = NYTimesApi.retrofitService.getCategories(Constants.API_KEY)
                _status.value = NYTimesApiStatus.DONE
                getCategoriesList()
                Log.d("MyTag", "Eto pizdez")
                insertCategoryToDataBase()
            } catch (e: Exception) {
                _status.value = NYTimesApiStatus.ERROR
            }
        }
    }

    private fun insertCategoryToDataBase() {
        val db = AppDatabase.getDatabase()
        val data = categoriesData.value!!
        if (db != null) {
            val categoriesDao: CategoriesDao = db.categoryDao()
            viewModelScope.launch {
                for ((iterator, category) in data.withIndex()) {
                    categoriesDao.insert(
                        Categories(
                            iterator,
                            category.name,
                            category.publishedDate
                        )
                    )
                }
            }
        }
        Log.d("MyTag", "insertedCategories!")
    }

    private fun getCategoriesList() {
        _categoriesData.value = extractCategoryData(data.value!!.result)
    }

    private fun extractCategoryData(data: List<CategoryInfo>): MutableList<Category> {
        val tempMutableList: MutableList<Category> = mutableListOf()
        if (status.value == NYTimesApiStatus.DONE) {
            for (categoryInfo in data)
                tempMutableList.add(
                    Category(
                        categoryInfo.listName,
                        categoryInfo.newestPublishedDate
                    )
                )
        }
        return tempMutableList
    }

    fun getCategoryNameById(id: Int): String {
        return categoriesData.value!![id].name
    }
}