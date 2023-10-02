package com.example.test_task_paletch_inc.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test_task_paletch_inc.data.database.entity.DbBooks

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBooks(books: List<DbBooks>)

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<DbBooks>

    @Query("DELETE FROM books")
    suspend fun removeAllBooks()
}