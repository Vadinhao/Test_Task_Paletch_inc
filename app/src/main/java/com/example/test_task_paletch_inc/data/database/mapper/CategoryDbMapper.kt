package com.example.test_task_paletch_inc.data.database.mapper

import com.example.test_task_paletch_inc.data.database.entity.DbCategories
import com.example.test_task_paletch_inc.domain.models.Category

object CategoryDbMapper {
    fun toDbEntity(category: Category, id: Int) = DbCategories(
        id = id,
        name = category.name,
        publishedDate = category.publishedDate
    )

    fun fromDbEntity(dbCategories: DbCategories) = Category(
        name = dbCategories.name,
        publishedDate = dbCategories.publishedDate
    )
}