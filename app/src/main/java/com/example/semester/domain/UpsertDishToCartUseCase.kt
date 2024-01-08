package com.example.semester.domain

import com.example.semester.data.models.Dish
import com.example.semester.data.repository.DishCartRepository
import javax.inject.Inject

interface UpsertDishToCartUseCase {
    suspend operator fun invoke(dish: Dish)
}

class UpsertDishToCartUseCaseImpl @Inject constructor(
    private val repository: DishCartRepository
) : UpsertDishToCartUseCase {
    override suspend fun invoke(dish: Dish) = repository.upsertDishToCart(dish)
}