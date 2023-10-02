package com.example.test_task_paletch_inc.data.repository.Book

import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.data.database.dao.BooksDao
import com.example.test_task_paletch_inc.data.database.mapper.BookDbMapper
import com.example.test_task_paletch_inc.data.network.NYTimesApi
import com.example.test_task_paletch_inc.data.network.mapper.BookNetworkMapper
import com.example.test_task_paletch_inc.domain.models.Book
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val booksDao: BooksDao
) : BookRepository {
    override suspend fun getAllBooksFromDB(): List<Book> {
        return booksDao.getAllBooks().map { BookDbMapper.fromDbEntity(it) }
    }

    override suspend fun insertAllBooksIntoDB(books: List<Book>) {
        booksDao.removeAllBooks()
        booksDao.insertAllBooks(books.mapIndexed { index, book ->
            BookDbMapper.toDbEntity(book, index)
        })
    }

    override suspend fun getAllBooksFromNetwork(apiKey: String): List<Book> {
        return BookNetworkMapper.fromNetworkEntity(NYTimesApi.retrofitService.getBooks(Constants.API_KEY))
    }
}