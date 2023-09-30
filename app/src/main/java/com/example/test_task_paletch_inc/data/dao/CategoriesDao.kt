package com.example.test_task_paletch_inc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test_task_paletch_inc.data.dao.entity.Categories

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categories: Categories)

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<Categories>
}