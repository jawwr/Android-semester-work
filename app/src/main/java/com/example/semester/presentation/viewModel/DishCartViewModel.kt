package com.example.semester.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester.data.models.Dish
import com.example.semester.data.models.DishCart
import com.example.semester.domain.DeleteDishFromCartUseCase
import com.example.semester.domain.DeleteDishesFromCartByIdInUseCase
import com.example.semester.domain.GetAllCartDishUseCase
import com.example.semester.domain.UpdateDishInCartUseCase
import com.example.semester.domain.UpsertDishToCartUseCase
import com.example.semester.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishCartViewModel @Inject constructor(
    private val getAllCartDishUseCase: GetAllCartDishUseCase,
    private val deleteDishFromCartUseCase: DeleteDishFromCartUseCase,
    private val updateDishInCartUseCase: UpdateDishInCartUseCase,
    private val deleteDishesFromCartByIdInUseCase: DeleteDishesFromCartByIdInUseCase
) : ViewModel() {
    private val _dishesInCart = MutableLiveData<UiState<List<DishCart>>>(UiState.Loading)
    val dishInCart: LiveData<UiState<List<DishCart>>>
        get() = _dishesInCart

    fun getAllDishInCart() {
        viewModelScope.launch {
            _dishesInCart.post(getAllCartDishUseCase())
        }
    }

    fun deleteDishFromCart(dishCart: DishCart) {
        viewModelScope.launch {
            deleteDishFromCartUseCase(dishCart)
        }
    }

    fun updateDishCart(dishCart: DishCart) {
        viewModelScope.launch {
            updateDishInCartUseCase(dishCart)
        }
    }

    fun deleteDishesByIdIn(ids: Collection<Int>) {
        viewModelScope.launch {
            deleteDishesFromCartByIdInUseCase(ids)
            _dishesInCart.post(listOf())
        }
    }
}
