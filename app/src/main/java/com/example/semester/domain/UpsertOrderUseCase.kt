package com.example.semester.domain

import com.example.semester.data.models.Order
import com.example.semester.data.repository.OrderRepository
import javax.inject.Inject

interface UpsertOrderUseCase {
    suspend operator fun invoke(order: Order)
}

class UpsertOrderUseCaseImpl @Inject constructor(
    private val repository: OrderRepository
) : UpsertOrderUseCase {
    override suspend fun invoke(order: Order) = repository.upsertOrder(order)
}