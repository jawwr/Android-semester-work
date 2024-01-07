package com.example.semester.data.api

import com.example.semester.data.models.Dish
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DishService {
    @GET("dishes")
    suspend fun getAllDishes(): Response<List<Dish>>

    @GET("dishes/{id}")
    suspend fun getDishById(@Path("id") id: Int): Response<Dish>
}