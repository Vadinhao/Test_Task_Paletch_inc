package com.example.test_task_paletch_inc.presentation.screens.books.recycler

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_paletch_inc.R
import com.example.test_task_paletch_inc.databinding.RecyclerViewBookItemBinding
import com.example.test_task_paletch_inc.domain.models.Book

class BooksHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {

    private val binding = RecyclerViewBookItemBinding.bind(view)

    fun bind(
        bookItem: Book,
        context: Context
    ) {
        binding.book = bookItem
        binding.executePendingBindings()
        decorate(binding, bookItem, context)
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

        @SuppressLint("SetTextI18n")
        private fun decorate(
            binding: RecyclerViewBookItemBinding,
            bookItem: Book,
            context: Context
        ) {
            binding.nameTextView.text = capitalizeWords(bookItem.name)
            binding.rankTextView.text =
                context.resources.getString(R.string.rank) + ": " + bookItem.rank
            binding.authorTextView.text =
                context.resources.getString(R.string.author) + ": " + bookItem.author
            binding.publisherTextView.text =
                context.resources.getString(R.string.publisher) + ": " + bookItem.publisher
            binding.descriptionTextView.text =
                context.resources.getString(R.string.description) + ": " + bookItem.description
        }

        private fun capitalizeWords(input: String): String {
            val words = input.split(" ").toMutableList()

            for (i in words.indices) {
                val word = words[i]
                if (word.isNotEmpty()) {
                    words[i] = word.substring(0, 1).uppercase() + word.substring(1)
                        .lowercase()
                }
            }

            return words.joinToString(" ")
        }

    }
}