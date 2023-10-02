package com.example.test_task_paletch_inc.data.database.mapper

import com.example.test_task_paletch_inc.data.database.entity.DbBooks
import com.example.test_task_paletch_inc.domain.models.Book

object BookDbMapper {
    fun toDbEntity(book: Book, id: Int, category: String) = DbBooks(
        id = id,
        name = book.name,
        category = category,
        description = book.description,
        author = book.author,
        publisher = book.publisher,
        imageUrl = book.imageUrl,
        rank = book.rank,
        link = book.linkToBuy
    )

    fun fromDbEntity(book: DbBooks) = Book(
        name = book.name,
        category = book.category,
        description = book.description,
        author = book.author,
        publisher = book.publisher,
        imageUrl = book.imageUrl,
        rank = book.rank,
        linkToBuy = book.link
    )
}