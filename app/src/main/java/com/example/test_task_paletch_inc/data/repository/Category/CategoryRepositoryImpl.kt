package com.example.test_task_paletch_inc.data.repository.Category

import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.data.database.dao.CategoriesDao
import com.example.test_task_paletch_inc.data.database.mapper.CategoryDbMapper
import com.example.test_task_paletch_inc.data.network.NYTimesApi
import com.example.test_task_paletch_inc.data.network.mapper.CategoryNetworkMapper
import com.example.test_task_paletch_inc.domain.models.Category
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoriesDao: CategoriesDao
) : CategoryRepository {
    override suspend fun getAllCategoriesFromDB(): List<Category> {
        return categoriesDao.getAllCategories().map { CategoryDbMapper.fromDbEntity(it) }
    }

    override suspend fun insertAllCategoryIntoDB(categories: List<Category>) {
        categoriesDao.removeAllCategories()
        categoriesDao.insertAllCategories(categories.mapIndexed { index, category ->
            CategoryDbMapper.toDbEntity(category, index)
        })
    }

    override suspend fun getAllCategoriesFromNetwork(apiKey: String): List<Category> {
        return CategoryNetworkMapper.fromNetworkEntity(
            NYTimesApi.retrofitService.getCategories(
                Constants.API_KEY
            )
        )
    }

}