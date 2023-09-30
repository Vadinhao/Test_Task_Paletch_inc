package com.example.test_task_paletch_inc.presentation.screens.books.recycler

import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.domain.models.Book

class BooksAdapter (
    private val booksData : LiveData<List<Book>>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<BooksHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksHolder {
        return BooksHolder.create(parent, onItemClicked)
    }

    override fun onBindViewHolder(holder: BooksHolder, position: Int) {
        if (booksData.isInitialized)
            holder.bind(booksData.value!![position])
        else
            Log.e("MyTag", "LifeData book not initialized")
    }

    override fun getItemCount(): Int {
        return booksData.value?.size ?: Constants.EMPTY_LIST_SIZE
    }

}