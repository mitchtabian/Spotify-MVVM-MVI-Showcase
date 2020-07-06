package com.sean.spotifyapp.screens.categories.views

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sean.spotifyapp.models.CategoryItem

class CategoriesAdapter(val data: List<CategoryItem>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {


    class CategoryViewHolder(val view: CategoryResultView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(CategoryResultView(parent.context))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.view.setCategory(data[position])
    }


}