package com.sean.spotifyapp.screens.categories.views

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.sean.spotifyapp.R
import com.sean.spotifyapp.models.CategoryItem

class CategoryResultView(context: Context) : FrameLayout(context) {

    init {
        View.inflate(context, R.layout.view_category_result, this)
    }

    fun setCategory(category: CategoryItem) {
        findViewById<TextView>(R.id.TextView_category_result_name).text = category.name
    }

}