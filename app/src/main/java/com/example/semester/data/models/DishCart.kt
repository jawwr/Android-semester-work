package com.example.semester.data.models

import com.example.semester.data.database.entities.DishCartEntity

data class DishCart(
    var id: Int = 0,
    val dishId: Int,
    val title: String,
    val price: Double,
    val count: Int,
    val photoUrl: String
) {
    companion object {
        fun fromEntity(entity: DishCartEntity): DishCart =
            DishCart(
                entity.id,
                entity.dishId,
                entity.title,
                entity.price,
                entity.count,
                entity.photoUrl
            )

        fun fromDish(dish: Dish, count: Int = 1) =
            DishCart(
                dishId = dish.id,
                title = dish.title,
                price = dish.price,
                count = count,
                photoUrl = dish.photoUrl
            )
    }
}
