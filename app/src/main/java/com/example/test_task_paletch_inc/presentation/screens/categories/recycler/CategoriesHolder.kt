package com.example.test_task_paletch_inc.presentation.screens.categories.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_paletch_inc.R
import com.example.test_task_paletch_inc.databinding.RecyclerViewCategoryItemBinding
import com.example.test_task_paletch_inc.domain.models.Category

class CategoriesHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding = RecyclerViewCategoryItemBinding.bind(view)

    fun bind(
        categoryItem: Category
    ) {
        binding.category = categoryItem
        binding.executePendingBindings()
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (Int) -> Unit
        ): CategoriesHolder {

            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_category_item, parent, false
            )

            val viewHolder = CategoriesHolder(view = view)

            viewHolder.itemView.setOnClickListener {
                val position = viewHolder.adapterPosition
                onItemClicked(position)
            }

            return viewHolder

        }
    }

}