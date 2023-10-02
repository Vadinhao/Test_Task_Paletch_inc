package com.example.test_task_paletch_inc.data.repository.Book

import com.example.test_task_paletch_inc.domain.models.Book

interface BookRepository {
    suspend fun getBooksForCategoryFromDB(categoryName: String): List<Book>
    suspend fun insertAllBookIntoDB(books: List<Book>, category: String)
    suspend fun getAllBooksFromNetwork(apiKey: String): List<Book>

}
