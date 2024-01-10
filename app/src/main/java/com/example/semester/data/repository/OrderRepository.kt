package com.example.semester.data.repository

import com.example.semester.data.api.OrderService
import com.example.semester.data.models.Order
import javax.inject.Inject

interface OrderRepository {
    suspend fun createOrder(dishIds: Collection<Int>): Result<Order>
    suspend fun getOrderById(id: Int): Result<Order>
}

class OrderRepositoryImpl @Inject constructor(
    private val service: OrderService
) : OrderRepository {
    override suspend fun createOrder(dishIds: Collection<Int>): Result<Order> =
        convertToResult { service.createOrder(dishIds) }

    override suspend fun getOrderById(id: Int): Result<Order> =
        convertToResult { service.getOrderById(id) }
}