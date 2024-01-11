package com.example.semester.data.repository

import com.example.semester.data.api.OrderService
import com.example.semester.data.database.dao.OrderDao
import com.example.semester.data.database.entities.OrderEntity
import com.example.semester.data.models.Order
import javax.inject.Inject

interface OrderRepository {
    suspend fun createOrder(dishIds: Collection<Int>): Result<Order>
    suspend fun getOrderById(id: Int): Result<Order>
    suspend fun upsertOrder(order: Order)
    suspend fun findAllOrders(): List<Order>
    suspend fun getAllOrdersByIdIn(ids: Collection<Int>): List<Order>
}

class OrderRepositoryImpl @Inject constructor(
    private val service: OrderService,
    private val dao: OrderDao
) : OrderRepository {
    override suspend fun createOrder(dishIds: Collection<Int>): Result<Order> =
        convertToResult { service.createOrder(dishIds) }

    override suspend fun getOrderById(id: Int): Result<Order> =
        convertToResult { service.getOrderById(id) }

    override suspend fun upsertOrder(order: Order) =
        dao.upsertOrder(OrderEntity.fromOrder(order))

    override suspend fun findAllOrders(): List<Order> =
        dao.findAll().map { Order.fromEntity(it) }

    override suspend fun getAllOrdersByIdIn(ids: Collection<Int>): List<Order> =
        ids.map { requireNotNull(service.getOrderById(it).body()) }
}