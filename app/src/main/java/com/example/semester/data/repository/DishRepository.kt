package com.example.semester.data.repository

import com.example.semester.data.api.DishService
import com.example.semester.data.models.Dish
import javax.inject.Inject

interface DishRepository {
    suspend fun getAllDishes(): Result<List<Dish>>
    suspend fun getDishById(id: Int): Result<Dish>
}

class DishRepositoryImpl @Inject constructor(
    private val service: DishService
) : DishRepository {
    override suspend fun getAllDishes(): Result<List<Dish>> =
        convertToResult { service.getAllDishes() }

    override suspend fun getDishById(id: Int): Result<Dish> =
        convertToResult { service.getDishById(id) }
}