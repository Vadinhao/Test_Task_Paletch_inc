package com.example.test_task_paletch_inc.data.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "books")
data class Books(
    @PrimaryKey val id: Int,
    @NotNull @ColumnInfo(name = "name") val name: String,
    @NotNull @ColumnInfo(name = "category") val category: String,
    @NotNull @ColumnInfo(name = "description") val description: String,
    @NotNull @ColumnInfo(name = "author") val author: String,
    @NotNull @ColumnInfo(name = "publisher") val publisher: String,
    @NotNull @ColumnInfo(name = "image_url") val imageUrl: String,
    @NotNull @ColumnInfo(name = "rank") val rank: String,
    @NotNull @ColumnInfo(name = "link") val link: String
)
