package com.example.test_task_paletch_inc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test_task_paletch_inc.data.dao.entity.Books

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(books: Books)

    @Query("SELECT * FROM books WHERE category = :categoryName")
    suspend fun getBooksForCategory(categoryName: String): List<Books>
}