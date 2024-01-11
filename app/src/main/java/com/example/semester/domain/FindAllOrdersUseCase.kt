package com.example.semester.domain

import com.example.semester.data.models.Order
import com.example.semester.data.repository.OrderRepository
import javax.inject.Inject

interface FindAllOrdersUseCase {
    suspend operator fun invoke(): List<Order>
}

class FindAllOrdersUseCaseImpl @Inject constructor(
    private val repository: OrderRepository
) : FindAllOrdersUseCase {
    override suspend fun invoke(): List<Order> = repository.findAllOrders()
}