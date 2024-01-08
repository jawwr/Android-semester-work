package com.example.semester.di.modules

import android.content.Context
import androidx.room.Room
import com.example.semester.data.database.DishCartDatabase
import com.example.semester.data.database.dao.DishCartDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): DishCartDatabase =
        Room.databaseBuilder(
            context,
            DishCartDatabase::class.java,
            "dish_cart.db"
        ).build()

    @Provides
    fun provideDishCartDao(db: DishCartDatabase): DishCartDao = db.dishCartDao
}