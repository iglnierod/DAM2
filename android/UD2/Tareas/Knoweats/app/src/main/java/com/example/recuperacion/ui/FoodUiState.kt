package com.example.recuperacion.ui

import com.example.recuperacion.ui.model.FoodModel

sealed interface FoodUiState {

    object Loading : FoodUiState

    data class Error(val throwable: Throwable) : FoodUiState

    data class Success(val foods: List<FoodModel>) : FoodUiState
}