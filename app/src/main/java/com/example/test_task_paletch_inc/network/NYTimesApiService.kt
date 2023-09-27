package com.example.test_task_paletch_inc.network

import com.example.test_task_paletch_inc.constants.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface NYTimesApiService {
    @GET(Constants.URL_CATEGORIES_PATH)
    suspend fun getCategories(@Query("api-key") apiKey: String): ListCategory

    @GET(Constants.URL_BOOKS_PATH)
    suspend fun getBooks(@Query("api-key") apiKey: String): ListCategoryWithBooks
}

object NYTimesApi {
    val retrofitService: NYTimesApiService by lazy { retrofit.create(NYTimesApiService::class.java) }
}