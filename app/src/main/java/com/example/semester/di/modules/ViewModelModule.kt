package com.example.semester.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.semester.di.viewModel.ViewModelFactory
import com.example.semester.di.viewModel.ViewModelKey
import com.example.semester.presentation.viewModel.DishCartViewModel
import com.example.semester.presentation.viewModel.DishViewModel
import com.example.semester.presentation.viewModel.OrderViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DishViewModel::class)
    fun bindDishViewModel(viewModel: DishViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DishCartViewModel::class)
    fun bindDishCartViewModel(viewModel: DishCartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    fun bindOrderViewModel(viewModel: OrderViewModel): ViewModel
}