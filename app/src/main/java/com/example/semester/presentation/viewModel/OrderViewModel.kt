package com.example.semester.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester.domain.CreateOrderUseCase
import com.example.semester.domain.GetOrderByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val createOrderUseCase: CreateOrderUseCase,
    private val getOrderByIdUseCase: GetOrderByIdUseCase
) : ViewModel() {
    fun createOrder(dishIds: Collection<Int>) {
        viewModelScope.launch {
            createOrderUseCase(dishIds)
        }
    }
}