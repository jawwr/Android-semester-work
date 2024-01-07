package com.example.semester.data.models

data class Dish(
    val id: Int,
    val title: String,
    val price: Double,
    val mass: Int,
    val kcal: Int,
    val description: String,
    val photoUrl: String
)