package com.example.semester.domain

import com.example.semester.data.models.DishCart
import com.example.semester.data.repository.DishCartRepository
import javax.inject.Inject

interface GetAllCartDishUseCase {
    suspend operator fun invoke(): List<DishCart>
}

class GetAllCartDishUseCaseImpl @Inject constructor(
    private val repository: DishCartRepository
) : GetAllCartDishUseCase {
    override suspend fun invoke(): List<DishCart> = repository.getAllDishInCart()
}