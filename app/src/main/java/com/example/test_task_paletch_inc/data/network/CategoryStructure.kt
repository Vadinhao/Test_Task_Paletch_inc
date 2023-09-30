package com.example.test_task_paletch_inc.data.network

import com.squareup.moshi.Json

data class ListCategory(
    @Json(name = "status") val status: String,
    @Json(name = "copyright") val copyright: String,
    @Json(name = "num_results") val numResults: Int,
    @Json(name = "results") val result: List<CategoryInfo>
)

data class CategoryInfo(
    @Json(name = "list_name") val listName: String,
    @Json(name = "display_name") val displayName: String,
    @Json(name = "list_name_encoded") val listNameEncoded: String,
    @Json(name = "oldest_published_date") val oldestPublishedDate: String,
    @Json(name = "newest_published_date") val newestPublishedDate: String,
    @Json(name = "updated") val updated: String
)