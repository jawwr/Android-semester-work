package com.example.semester.data.models

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.semester.data.database.entities.OrderEntity

data class Order(
    val id: Int,
    val status: OrderStatus
) {
    companion object {
        fun fromEntity(entity: OrderEntity): Order =
            Order(
                entity.id,
                entity.status
            )
    }
}

@TypeConverters(OrderStatus::class)
enum class OrderStatus(val value: String) {
    COLLECTING("Сборка"),
    COOKING("Готовится"),
    SENDING("Подготовка к доставке"),
    DELIVERY("Доставляется"),
    DONE("Доставлено");

    @TypeConverter
    fun fromStatus(value: String) = enumValueOf<OrderStatus>(value)

    @TypeConverter
    fun toStatus(value: OrderStatus) = value.name
}
