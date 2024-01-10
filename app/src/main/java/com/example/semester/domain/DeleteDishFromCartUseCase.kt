package com.example.semester.domain

import com.example.semester.data.models.DishCart
import com.example.semester.data.repository.DishCartRepository
import javax.inject.Inject

interface DeleteDishFromCartUseCase {
    suspend operator fun invoke(dishCart: DishCart)
}

class DeleteDishFromCartUseCaseImpl @Inject constructor(
    private val repository: DishCartRepository
) : DeleteDishFromCartUseCase {
    override suspend fun invoke(dishCart: DishCart) = repository.deleteDishFromCart(dishCart)
}