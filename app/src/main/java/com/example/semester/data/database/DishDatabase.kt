package com.example.semester.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.semester.data.database.dao.DishCartDao
import com.example.semester.data.database.dao.OrderDao
import com.example.semester.data.database.entities.DishCartEntity
import com.example.semester.data.database.entities.OrderEntity

@Database(
    entities = [
        DishCartEntity::class,
        OrderEntity::class
    ],
    version = 1
)
abstract class DishDatabase : RoomDatabase() {
    abstract val dishCartDao: DishCartDao
    abstract val orderDao: OrderDao
}