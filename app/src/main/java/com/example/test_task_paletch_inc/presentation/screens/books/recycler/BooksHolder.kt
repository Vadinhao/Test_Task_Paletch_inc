package com.example.test_task_paletch_inc.presentation.screens.books.recycler

import android.content.Intent
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.traceEventEnd
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_paletch_inc.R
import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.databinding.RecyclerViewBookItemBinding
import com.example.test_task_paletch_inc.databinding.RecyclerViewCategoryItemBinding
import com.example.test_task_paletch_inc.domain.models.Book
import com.example.test_task_paletch_inc.domain.models.Category
import com.example.test_task_paletch_inc.presentation.screens.categories.recycler.CategoriesHolder

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