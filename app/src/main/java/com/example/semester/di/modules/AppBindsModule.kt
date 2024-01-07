package com.example.semester.di.modules

import com.example.semester.data.repository.DishRepository
import com.example.semester.data.repository.DishRepositoryImpl
import com.example.semester.domain.GetAllDishesUseCase
import com.example.semester.domain.GetAllDishesUseCaseImpl
import com.example.semester.domain.GetDishByIdUseCase
import com.example.semester.domain.GetDishByIdUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {
    @Binds
    fun bindDishRepository(dishRepository: DishRepositoryImpl): DishRepository

    @Binds
    fun bindGetAllDishesUseCase(useCase: GetAllDishesUseCaseImpl): GetAllDishesUseCase

    @Binds
    fun bindGetDishByIdUseCase(useCase: GetDishByIdUseCaseImpl): GetDishByIdUseCase
}