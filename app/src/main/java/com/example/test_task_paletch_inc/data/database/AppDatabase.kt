package com.example.test_task_paletch_inc.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test_task_paletch_inc.data.dao.BooksDao
import com.example.test_task_paletch_inc.data.dao.CategoriesDao
import com.example.test_task_paletch_inc.data.dao.entity.Books
import com.example.test_task_paletch_inc.data.dao.entity.Categories

@Database(entities = [Categories::class, Books::class], version = 1, exportSchema = false)
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

        fun getDatabase(): AppDatabase?{
            return INSTANCE
        }
    }
}




