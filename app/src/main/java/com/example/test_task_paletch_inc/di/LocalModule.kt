package com.example.test_task_paletch_inc.di

import com.example.test_task_paletch_inc.data.database.AppDatabase
import com.example.test_task_paletch_inc.data.database.dao.BooksDao
import com.example.test_task_paletch_inc.data.database.dao.CategoriesDao
import dagger.Module
import dagger.Provides

@Module
class LocalModule {

    @Provides
    fun provideBooksDao(database: AppDatabase): BooksDao {
        return database.booksDao()
    }

    @Provides
    fun provideCategoriesDao(database: AppDatabase): CategoriesDao {
        return database.categoryDao()
    }

}