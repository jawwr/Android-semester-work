package com.example.semester.domain

import com.example.semester.data.models.DishCart
import com.example.semester.data.repository.DishCartRepository
import javax.inject.Inject

interface UpdateDishInCartUseCase {
    suspend operator fun invoke(dishCart: DishCart)
}

class UpdateDishInCartUseCaseImpl @Inject constructor(
    private val repository: DishCartRepository
) : UpdateDishInCartUseCase {
    override suspend fun invoke(dishCart: DishCart) = repository.updateDishCart(dishCart)
}