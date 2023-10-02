package com.example.test_task_paletch_inc.data.repository.Book

import android.util.Log
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
    override suspend fun getBooksForCategoryFromDB(categoryName: String): List<Book> {
        return booksDao.getBooksForCategory(categoryName).map { BookDbMapper.fromDbEntity(it) }
    }


    //Короче, тут проблема, нужно найти
    override suspend fun insertAllBookIntoDB(books: List<Book>, category: String) {
        Log.d("MyTag", "7")
        booksDao.removeBooks()
        Log.d("MyTag", "8")
        booksDao.insertAll(books.mapIndexed { index, book ->
            BookDbMapper.toDbEntity(book, index, category)
        })
        Log.d("MyTag", "9")
    }

    override suspend fun getAllBooksFromNetwork(apiKey: String): List<Book> {
        Log.d("MyTag", "ЕБАТЬ ДОБРАЛСЯ ДО РЕПЫ, ТУПО В ГЕТАЛЛБКУК")
        val gg = BookNetworkMapper.fromNetworkEntity(NYTimesApi.retrofitService.getBooks(Constants.API_KEY))
        Log.d("MyTag", "Я АУХЕЛ, Я ПРОЛУЧИЛ ТУПО: " + gg[0].name)
        return BookNetworkMapper.fromNetworkEntity(NYTimesApi.retrofitService.getBooks(Constants.API_KEY))
    }
}