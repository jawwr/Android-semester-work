package com.example.semester.domain

import com.example.semester.data.models.Dish
import com.example.semester.data.repository.DishRepository
import javax.inject.Inject

interface GetDishByIdUseCase {
    suspend operator fun invoke(id: Int): Result<Dish>
}

class GetDishByIdUseCaseImpl @Inject constructor(
    private val repository: DishRepository
) : GetDishByIdUseCase {
    override suspend fun invoke(id: Int): Result<Dish> = repository.getDishById(id)
}