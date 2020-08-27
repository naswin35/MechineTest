package com.example.codingtest.network

data class json_response(
    val categories: ArrayList<Category>,
    val msg: String,
    val status: Boolean
)

data class Category(
    val products: List<Product>,
    val title: String
)

data class Product(
    val description: String,
    val imageUrl: String,
    val price: Int,
    val title: String
)