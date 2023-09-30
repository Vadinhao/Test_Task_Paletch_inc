package com.example.test_task_paletch_inc.data.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "categories")
data class Categories (
    @PrimaryKey val id: Int,
    @NotNull @ColumnInfo(name = "name") val name: String,
    @NotNull @ColumnInfo(name = "published_date") val publishedDate: String
)
