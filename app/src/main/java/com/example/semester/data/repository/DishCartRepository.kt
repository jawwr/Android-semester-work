package com.example.semester.data.repository

import com.example.semester.data.database.dao.DishCartDao
import com.example.semester.data.database.entities.DishCartEntity
import com.example.semester.data.models.Dish
import com.example.semester.data.models.DishCart
import javax.inject.Inject

interface DishCartRepository {
    suspend fun upsertDishToCart(dish: Dish)
    suspend fun updateDishCart(dishCart: DishCart)
    suspend fun getAllDishInCart(): List<DishCart>

    suspend fun deleteDishFromCart(dishCart: DishCart)

    suspend fun getDishInCartById(id: Int): DishCart?
}

class DishCartRepositoryImpl @Inject constructor(
    private val dao: DishCartDao
) : DishCartRepository {
    override suspend fun upsertDishToCart(dish: Dish) =
        dao.upsertDishToCart(DishCartEntity.fromModel(dish))

    override suspend fun updateDishCart(dishCart: DishCart) =
        dao.updateDishCart(DishCartEntity.fromDishCart(dishCart))

    override suspend fun getAllDishInCart(): List<DishCart> =
        dao.getAllDishesInCart().map { DishCart.fromEntity(it) }

    override suspend fun deleteDishFromCart(dishCart: DishCart) =
        dao.deleteDishFromCart(DishCartEntity.fromDishCart(dishCart))

    override suspend fun getDishInCartById(id: Int): DishCart? =
        dao.getDishInCartById(id)?.let { DishCart.fromEntity(it) }
}