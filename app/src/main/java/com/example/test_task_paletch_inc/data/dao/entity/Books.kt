package com.example.test_task_paletch_inc.data.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Books(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "publisher") val publisher: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "rank") val rank: String,
    @ColumnInfo(name = "link") val link: String
)
