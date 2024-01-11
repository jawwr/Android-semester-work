package com.example.semester.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester.data.models.Order
import com.example.semester.data.models.OrderStatus
import com.example.semester.domain.CreateOrderUseCase
import com.example.semester.domain.FindAllOrdersUseCase
import com.example.semester.domain.GetOrdersByIdInUseCase
import com.example.semester.domain.UpsertOrderUseCase
import com.example.semester.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val createOrderUseCase: CreateOrderUseCase,
    private val upsertOrderUseCase: UpsertOrderUseCase,
    private val findAllOrdersUseCase: FindAllOrdersUseCase,
    private val getOrdersByIdInUseCase: GetOrdersByIdInUseCase
) : ViewModel() {
    private val _orders = MutableLiveData<UiState<List<Order>>>(UiState.Loading)
    val orders: LiveData<UiState<List<Order>>>
        get() = _orders

    fun createOrder(dishIds: Collection<Int>) {
        viewModelScope.launch {
            val result = createOrderUseCase(dishIds)
            if (result.isSuccess) {
                upsertOrder(requireNotNull(result.getOrNull()))
            }
        }
    }

    fun upsertOrder(order: Order) {
        viewModelScope.launch {
            upsertOrderUseCase(order)
        }
    }

    fun findAllOrders() {
        viewModelScope.launch {
            val allOrders = findAllOrdersUseCase()
            val notDelivered = allOrders.filterNot { it.status == OrderStatus.DONE }.map { it.id }
            val orders = getOrdersByIdInUseCase(notDelivered)
            _orders.post(orders)
            orders.forEach { upsertOrder(it) }
        }
    }
}