package com.example.semester.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester.data.models.Dish
import com.example.semester.domain.GetAllDishesUseCase
import com.example.semester.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainDishCardViewModel @Inject constructor(
    private val getAllDishesUseCase: GetAllDishesUseCase
) : ViewModel() {
    private val _dishesInMainScreen = MutableLiveData<UiState<List<Dish>>>(UiState.Loading)
    val dishesInMainScreen: LiveData<UiState<List<Dish>>>
        get() = _dishesInMainScreen

    fun getAllDish() {
        viewModelScope.launch {
            _dishesInMainScreen.postResult(getAllDishesUseCase())
        }
    }
}