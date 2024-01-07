package com.example.semester.domain

import com.example.semester.data.models.Dish
import com.example.semester.data.repository.DishRepository
import javax.inject.Inject

interface GetAllDishesUseCase {
    suspend operator fun invoke(): Result<List<Dish>>
}

class GetAllDishesUseCaseImpl @Inject constructor(
    private val repository: DishRepository
) : GetAllDishesUseCase {
    override suspend fun invoke() = repository.getAllDishes()
}