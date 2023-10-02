package com.example.test_task_paletch_inc.data.network.mapper

import com.example.test_task_paletch_inc.data.network.entity.ListCategory
import com.example.test_task_paletch_inc.domain.models.Category

object CategoryNetworkMapper {
    fun fromNetworkEntity(listCategory: ListCategory): List<Category> {
        val tempList = mutableListOf<Category>()
        for (category in listCategory.result) {
            tempList.add(
                Category(
                    name = category.listName,
                    publishedDate = category.newestPublishedDate
                )
            )
        }
        return tempList
    }
}
