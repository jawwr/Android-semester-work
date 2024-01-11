package com.example.semester.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester.data.models.Dish
import com.example.semester.data.models.DishCart
import com.example.semester.domain.GetDishByIdUseCase
import com.example.semester.domain.GetDishInCartByIdUseCase
import com.example.semester.domain.UpdateDishInCartUseCase
import com.example.semester.domain.UpsertDishToCartUseCase
import com.example.semester.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishViewModel @Inject constructor(
    private val getDishByIdUseCase: GetDishByIdUseCase,
    private val getDishInCartByIdUseCase: GetDishInCartByIdUseCase,
    private val upsertDishToCartUseCase: UpsertDishToCartUseCase,
    private val updateDishInCartUseCase: UpdateDishInCartUseCase
) : ViewModel() {
    private val _dish: MutableLiveData<UiState<Dish>> = MutableLiveData(UiState.Loading)
    val dish: LiveData<UiState<Dish>>
        get() = _dish

    private val _dishInCart: MutableLiveData<UiState<DishCart>> = MutableLiveData(UiState.Loading)
    val dishInCart: LiveData<UiState<DishCart>>
        get() = _dishInCart

    fun getDishById(id: Int) {
        viewModelScope.launch {
            _dish.postResult(getDishByIdUseCase(id))
        }
    }

    fun upsertDishToCart(dish: Dish) {
        viewModelScope.launch {
            upsertDishToCartUseCase(dish)
        }
    }

    fun updateDishInCart(dish: DishCart) {
        viewModelScope.launch {
            updateDishInCartUseCase(dish)
        }
    }

    fun getDishInCartById(id: Int) {
        viewModelScope.launch {
            _dishInCart.post(getDishInCartByIdUseCase(id))
        }
    }
}