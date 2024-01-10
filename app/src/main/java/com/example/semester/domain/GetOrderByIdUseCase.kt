package com.example.semester.domain

import com.example.semester.data.models.Order
import com.example.semester.data.repository.OrderRepository
import javax.inject.Inject

interface GetOrderByIdUseCase {
    suspend operator fun invoke(id: Int): Result<Order>
}

class GetOrderByIdUseCaseImpl @Inject constructor(
    private val repository: OrderRepository
) : GetOrderByIdUseCase {
    override suspend fun invoke(id: Int): Result<Order> = repository.getOrderById(id)
}