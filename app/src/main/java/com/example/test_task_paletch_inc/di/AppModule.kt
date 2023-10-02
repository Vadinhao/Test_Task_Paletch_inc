package com.example.test_task_paletch_inc.di

import android.content.Context
import com.example.test_task_paletch_inc.data.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

}