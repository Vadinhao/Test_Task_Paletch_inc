package com.example.test_task_paletch_inc.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test_task_paletch_inc.data.database.entity.DbCategories

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<DbCategories>)

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<DbCategories>

    @Query("DELETE FROM categories")
    suspend fun removeAllCategories()
}