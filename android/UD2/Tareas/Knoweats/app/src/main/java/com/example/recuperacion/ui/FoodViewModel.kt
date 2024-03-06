package com.example.recuperacion.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recuperacion.domain.AddFoodUseCase
import com.example.recuperacion.domain.GetFoodUseCase
import com.example.recuperacion.ui.FoodUiState.Success
import com.example.recuperacion.ui.model.FoodModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val addFoodUseCase: AddFoodUseCase,
    getFoodUseCase: GetFoodUseCase
) : ViewModel() {

    private val _uiState: StateFlow<FoodUiState> = getFoodUseCase().map(::Success)
        .catch { FoodUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FoodUiState.Loading)
    val uiState: StateFlow<FoodUiState> = _uiState

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _foods = mutableStateListOf<FoodModel>()
    val foods: List<FoodModel> = _foods

    fun getFoods(foodUiState: FoodUiState): List<FoodModel> {
        return when (foodUiState) {
            is FoodUiState.Error -> emptyList()
            FoodUiState.Loading -> emptyList()
            is Success -> {
                foodUiState.foods
            }

            //else -> {}
        }
    }

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onFoodCreated(foodName: String, foodDescription: String, foodPrice: Double) {
        viewModelScope.launch {
            addFoodUseCase(
                FoodModel(
                    name = foodName,
                    description = foodDescription,
                    price = foodPrice
                )
            )
        }
        _showDialog.value = false
        //_foods.add(FoodModel(name = foodName, description = foodDescription, price = foodPrice))
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }
}