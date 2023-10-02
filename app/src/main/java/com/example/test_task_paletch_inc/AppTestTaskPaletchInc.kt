package com.example.test_task_paletch_inc

import android.app.Application
import android.util.Log
import com.example.test_task_paletch_inc.data.database.AppDatabase
import com.example.test_task_paletch_inc.di.AppComponent
import com.example.test_task_paletch_inc.di.AppModule
import com.example.test_task_paletch_inc.di.DaggerAppComponent
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class AppTestTaskPaletchInc : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDataBase()
        appComponent = buildComponent()
    }

    fun getComponent(): AppComponent = appComponent

    private fun buildComponent(): AppComponent {
        Log.d("MyTag", "Я ебал, вроде арр пашет ")
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    private fun initDataBase() {
        val context = this
        MainScope().launch {
            AppDatabase.getDatabase(context)
        }
    }
}
