package com.example.semester.data.models

data class Order(
    val id: Int,
    val status: OrderStatus
)

enum class OrderStatus {
    COLLECTING,
    COOKING,
    SENDING,
    DELIVERY,
    DONE
}
