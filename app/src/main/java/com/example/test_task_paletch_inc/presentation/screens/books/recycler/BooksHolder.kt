package com.example.test_task_paletch_inc.presentation.screens.books.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_paletch_inc.R
import com.example.test_task_paletch_inc.databinding.RecyclerViewBookItemBinding
import com.example.test_task_paletch_inc.domain.models.Book

class BooksHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding = RecyclerViewBookItemBinding.bind(view)

    fun bind(
        bookItem: Book
    ) {
        binding.book = bookItem
        binding.executePendingBindings()
    }

    companion object {

        fun create(
            parent: ViewGroup,
            onItemClicked: (Int) -> Unit
        ): BooksHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_book_item, parent, false
            )

            val viewHolder = BooksHolder(view = view)

            viewHolder.binding.linkTextView.setOnClickListener {
                val position = viewHolder.adapterPosition
                onItemClicked(position)
            }

            return viewHolder
        }

    }
}