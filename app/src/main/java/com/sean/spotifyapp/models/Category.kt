package com.sean.spotifyapp.models

class Category(
    val href: String,
    val items: List<CategoryItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: String,
    val total: Int
)