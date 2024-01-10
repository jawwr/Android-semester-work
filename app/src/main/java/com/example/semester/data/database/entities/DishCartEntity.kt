package com.example.semester.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.semester.data.models.Dish
import com.example.semester.data.models.DishCart

@Entity(tableName = "cart_dishes")
data class DishCartEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val dishId: Int,
    val title: String,
    val price: Double,
    val count: Int,
    val photoUrl: String
) {
    companion object {
        fun fromModel(dish: Dish): DishCartEntity =
            DishCartEntity(
                dishId = dish.id,
                title = dish.title,
                price = dish.price,
                count = 1,
                photoUrl = dish.photoUrl
            )

        fun fromDishCart(dishCart: DishCart) =
            DishCartEntity(
                id = dishCart.id,
                dishId = dishCart.dishId,
                title = dishCart.title,
                price = dishCart.price,
                count = dishCart.count,
                photoUrl = dishCart.photoUrl
            )
    }
}