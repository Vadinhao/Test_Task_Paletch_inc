package com.example.test_task_paletch_inc.di

import android.content.Context
import com.example.test_task_paletch_inc.data.database.AppDatabase
import com.example.test_task_paletch_inc.data.database.dao.BooksDao
import com.example.test_task_paletch_inc.data.database.dao.CategoriesDao
import com.example.test_task_paletch_inc.data.repository.Book.BookRepository
import com.example.test_task_paletch_inc.data.repository.Category.CategoryRepository
import com.example.test_task_paletch_inc.presentation.screens.SharedViewModel
import dagger.Component

@Component(modules = [AppModule::class, RepositoryModule::class, LocalModule::class])
interface AppComponent {

    fun getBookRepository(): BookRepository
    fun getCategoryRepository(): CategoryRepository
    fun getAppContext(): Context
    fun getDataBase(): AppDatabase
    fun getBooksDao(): BooksDao
    fun getCategoriesDao(): CategoriesDao
    fun inject(application: SharedViewModel)
}
