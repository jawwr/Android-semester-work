package com.example.semester.data.repository

import com.example.semester.data.database.dao.DishCartDao
import com.example.semester.data.database.entities.DishCartEntity
import com.example.semester.data.models.Dish
import com.example.semester.data.models.DishCart
import javax.inject.Inject

interface DishCartRepository {
    suspend fun upsertDishToCart(dish: Dish)
    suspend fun getAllDishInCart(): List<DishCart>
}

class DishCartRepositoryImpl @Inject constructor(
    private val dao: DishCartDao
) : DishCartRepository {
    override suspend fun upsertDishToCart(dish: Dish) =
        dao.upsertDishToCart(DishCartEntity.fromModel(dish))

    override suspend fun getAllDishInCart(): List<DishCart> =
        dao.getAllDishesInCart().map { DishCart.fromEntity(it) }
}