package com.example.semester.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.semester.data.models.Order
import com.example.semester.data.models.OrderStatus

@Entity(tableName = "order_entities")
data class OrderEntity(
    @PrimaryKey
    var id: Int = 0,
    val status: OrderStatus
) {
    companion object {
        fun fromOrder(order: Order) =
            OrderEntity(
                order.id,
                order.status
            )
    }
}