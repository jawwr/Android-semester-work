package com.example.semester.di.modules

import android.app.Application
import android.content.Context
import com.example.semester.data.repository.DishCartRepository
import com.example.semester.data.repository.DishCartRepositoryImpl
import com.example.semester.data.repository.DishRepository
import com.example.semester.data.repository.DishRepositoryImpl
import com.example.semester.domain.GetAllCartDishUseCase
import com.example.semester.domain.GetAllCartDishUseCaseImpl
import com.example.semester.domain.GetAllDishesUseCase
import com.example.semester.domain.GetAllDishesUseCaseImpl
import com.example.semester.domain.GetDishByIdUseCase
import com.example.semester.domain.GetDishByIdUseCaseImpl
import com.example.semester.domain.UpsertDishToCartUseCase
import com.example.semester.domain.UpsertDishToCartUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface AppBindsModule {
    @Binds
    fun bindDishRepository(dishRepository: DishRepositoryImpl): DishRepository

    @Binds
    fun bindGetAllDishesUseCase(useCase: GetAllDishesUseCaseImpl): GetAllDishesUseCase

    @Binds
    fun bindGetDishByIdUseCase(useCase: GetDishByIdUseCaseImpl): GetDishByIdUseCase

    @Binds
    fun bindDishCartRepository(dishCartRepository: DishCartRepositoryImpl): DishCartRepository

    @Binds
    fun bindUpsertDishToCartUseCase(useCase: UpsertDishToCartUseCaseImpl): UpsertDishToCartUseCase

    @Binds
    fun bindGetAllCartDishUseCase(useCase: GetAllCartDishUseCaseImpl): GetAllCartDishUseCase

    companion object {
        @Provides
        fun provideContext(app: Application): Context = app.applicationContext
    }
}