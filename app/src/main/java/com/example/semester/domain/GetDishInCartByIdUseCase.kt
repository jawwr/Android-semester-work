package com.example.semester.domain

import com.example.semester.data.models.DishCart
import com.example.semester.data.repository.DishCartRepository
import javax.inject.Inject

interface GetDishInCartByIdUseCase {
    suspend operator fun invoke(id: Int): DishCart?
}

class GetDishInCartByIdUseCaseImpl @Inject constructor(
    private val repository: DishCartRepository
) : GetDishInCartByIdUseCase {
    override suspend fun invoke(id: Int): DishCart? = repository.getDishInCartById(id)
}