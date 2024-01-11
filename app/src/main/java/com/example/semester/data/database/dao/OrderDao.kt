package com.example.semester.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.semester.data.database.entities.OrderEntity

@Dao
interface OrderDao {
    @Upsert
    suspend fun upsertOrder(entity: OrderEntity)

    @Query("SELECT * FROM order_entities")
    suspend fun findAll(): List<OrderEntity>
}