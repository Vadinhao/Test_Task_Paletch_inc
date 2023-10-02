package com.example.test_task_paletch_inc.di

import com.example.test_task_paletch_inc.data.repository.Book.BookRepository
import com.example.test_task_paletch_inc.data.repository.Book.BookRepositoryImpl
import com.example.test_task_paletch_inc.data.repository.Category.CategoryRepository
import com.example.test_task_paletch_inc.data.repository.Category.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun provideBookRepository(bookRepositoryImpl: BookRepositoryImpl): BookRepository

    @Binds
    fun provideCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

}
