package com.example.semester.di.modules

import android.content.Context
import androidx.room.Room
import com.example.semester.data.database.DishDatabase
import com.example.semester.data.database.dao.DishCartDao
import com.example.semester.data.database.dao.OrderDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): DishDatabase =
        Room.databaseBuilder(
            context,
            DishDatabase::class.java,
            "dish.db"
        ).build()

    @Provides
    fun provideDishCartDao(db: DishDatabase): DishCartDao = db.dishCartDao

    @Provides
    fun provideOrderDao(db: DishDatabase): OrderDao = db.orderDao
}