package com.example.test_task_paletch_inc.data.repository.Book

import com.example.test_task_paletch_inc.domain.models.Book

interface BookRepository {
    suspend fun getAllBooksFromDB(): List<Book>
    suspend fun insertAllBooksIntoDB(books: List<Book>)
    suspend fun getAllBooksFromNetwork(apiKey: String): List<Book>

}
