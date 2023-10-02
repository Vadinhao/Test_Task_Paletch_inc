package com.example.test_task_paletch_inc.data.repository.Category

import com.example.test_task_paletch_inc.domain.models.Category

interface CategoryRepository {

    suspend fun getAllCategoriesFromDB(): List<Category>
    suspend fun insertAllCategoryIntoDB(categories: List<Category>)
    suspend fun getAllCategoriesFromNetwork(apiKey: String): List<Category>
}
