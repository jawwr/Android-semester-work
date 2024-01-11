package com.example.semester.domain

import com.example.semester.data.models.Order
import com.example.semester.data.repository.OrderRepository
import javax.inject.Inject

interface GetOrdersByIdInUseCase {
    suspend operator fun invoke(ids: Collection<Int>): List<Order>
}

class GetOrdersByIdInUseCaseImpl @Inject constructor(
    private val repository: OrderRepository
) : GetOrdersByIdInUseCase {
    override suspend fun invoke(ids: Collection<Int>): List<Order> =
        repository.getAllOrdersByIdIn(ids)
}