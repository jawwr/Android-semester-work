package com.example.semester.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.semester.data.database.entities.DishCartEntity

@Dao
interface DishCartDao {
    @Upsert
    suspend fun upsertDishToCart(dishCartEntity: DishCartEntity)

    @Query("SELECT * FROM cart_dishes")
    suspend fun getAllDishesInCart(): List<DishCartEntity>

    @Query("SELECT * FROM cart_dishes WHERE dishId=:id")
    suspend fun getDishInCartById(id: Int): DishCartEntity?

    @Delete
    suspend fun deleteDishFromCart(dishCartEntity: DishCartEntity)

    @Update
    suspend fun updateDishCart(dishCartEntity: DishCartEntity)
}