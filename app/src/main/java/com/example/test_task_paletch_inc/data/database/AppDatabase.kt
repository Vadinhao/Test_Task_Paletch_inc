package com.example.test_task_paletch_inc.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test_task_paletch_inc.data.database.dao.BooksDao
import com.example.test_task_paletch_inc.data.database.dao.CategoriesDao
import com.example.test_task_paletch_inc.data.database.entity.DbBooks
import com.example.test_task_paletch_inc.data.database.entity.DbCategories

@Database(entities = [DbCategories::class, DbBooks::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoriesDao

    abstract fun booksDao(): BooksDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ny_times_app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}




