package com.example.recuperacion.domain

import com.example.recuperacion.data.FoodRepository
import com.example.recuperacion.ui.model.FoodModel
import javax.inject.Inject

class AddFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(foodModel: FoodModel) {
        foodRepository.add(foodModel)
    }
}