package com.example.semester.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester.data.models.Dish
import com.example.semester.domain.GetAllDishesUseCase
import com.example.semester.domain.GetDishByIdUseCase
import com.example.semester.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishViewModel @Inject constructor(
    private val getAllDishesUseCase: GetAllDishesUseCase,
    private val getDishByIdUseCase: GetDishByIdUseCase
) : ViewModel() {
    private val _dish: MutableLiveData<UiState<Dish>> = MutableLiveData(UiState.Loading)
    val dish: LiveData<UiState<Dish>>
        get() = _dish

    fun getDishById(id: Int) {
        viewModelScope.launch {
            _dish.postResult(getDishByIdUseCase(id))
        }
    }
}