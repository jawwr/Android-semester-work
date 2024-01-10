package com.example.semester.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.semester.utils.UiState

fun <T> MutableLiveData<UiState<T>>.postResult(value: Result<T?>) {
    this.postValue(if (value.isSuccess) {
        value.getOrNull()?.let { UiState.Success(it) } ?: UiState.Failure("Failure value")
    } else {
        UiState.Failure("Something went wrong")
    })
}

fun <T> MutableLiveData<UiState<T>>.post(value: T?) {
    this.postValue(value?.let { UiState.Success(it) } ?: UiState.Failure("Failure value"))
}