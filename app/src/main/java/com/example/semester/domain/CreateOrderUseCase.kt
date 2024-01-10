package com.example.semester.domain

import com.example.semester.data.models.Order
import com.example.semester.data.repository.OrderRepository
import javax.inject.Inject

interface CreateOrderUseCase {
    suspend operator fun invoke(dishIds: Collection<Int>): Result<Order>
}

class CreateOrderUseCaseImpl @Inject constructor(
    private val repository: OrderRepository
) : CreateOrderUseCase {
    override suspend fun invoke(dishIds: Collection<Int>): Result<Order> = repository.createOrder(dishIds)
}