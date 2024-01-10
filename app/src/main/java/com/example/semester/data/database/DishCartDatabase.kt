package com.example.semester.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.semester.data.database.dao.DishCartDao
import com.example.semester.data.database.entities.DishCartEntity

@Database(
    entities = [
        DishCartEntity::class
    ],
    version = 1
)
abstract class DishCartDatabase : RoomDatabase() {
    abstract val dishCartDao: DishCartDao
}