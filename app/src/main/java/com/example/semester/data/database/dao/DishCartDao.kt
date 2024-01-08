package com.example.semester.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.semester.data.database.entities.DishCartEntity

@Dao
interface DishCartDao {
    @Upsert
    suspend fun upsertDishToCart(dishCartEntity: DishCartEntity)

    @Query("SELECT * FROM cart_dishes")
    suspend fun getAllDishesInCart(): List<DishCartEntity>
}