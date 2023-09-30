package com.example.test_task_paletch_inc.presentation.screens.categories.recycler

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.domain.models.Category

class CategoriesAdapter(
    private val categoriesData: LiveData<List<Category>>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<CategoriesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        return CategoriesHolder.create(parent, onItemClicked)
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.bind(categoriesData.value!![position])
    }

    override fun getItemCount(): Int {
        return categoriesData.value?.size ?: Constants.EMPTY_LIST_SIZE
    }

}
