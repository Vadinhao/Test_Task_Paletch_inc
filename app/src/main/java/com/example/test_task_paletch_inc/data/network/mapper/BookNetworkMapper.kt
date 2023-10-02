package com.example.test_task_paletch_inc.data.network.mapper

import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.data.network.entity.ListCategoryWithBooks
import com.example.test_task_paletch_inc.domain.models.Book

object BookNetworkMapper {
    fun fromNetworkEntity(listCategoryWithBooks: ListCategoryWithBooks): List<Book> {
        val tempList = mutableListOf<Book>()
        for (list in listCategoryWithBooks.results.lists) {
            val category = list.listName
            for (book in list.books) {
                tempList.add(
                    Book(
                        name = book.title,
                        category = category,
                        description = book.description,
                        author = book.author,
                        publisher = book.publisher,
                        imageUrl = book.bookImage,
                        rank = book.rank.toString(),
                        linkToBuy = book.buyLinks[Constants.AMAZON_LINK_INDEX].url
                    )
                )
            }
        }
        return tempList
    }

}
