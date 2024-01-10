package com.example.semester.domain

import com.example.semester.data.repository.DishCartRepository
import javax.inject.Inject

interface DeleteDishesFromCartByIdInUseCase {
    suspend operator fun invoke(ids: Collection<Int>)
}

class DeleteDishesFromCartByIdInUseCaseImpl @Inject constructor(
    private val repository: DishCartRepository
) : DeleteDishesFromCartByIdInUseCase {
    override suspend fun invoke(ids: Collection<Int>) = repository.deleteDishFromCartByIdIn(ids)
}