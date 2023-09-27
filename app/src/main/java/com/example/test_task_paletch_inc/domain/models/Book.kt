package com.example.test_task_paletch_inc.domain.models

import android.media.Image

class Book(
    private val name: String,
    private val description: String,
    private val author: String,
    private val publisher: String,
    private val image: Image,
    private val rank: Int,
    private val linkToBuy: String
)
