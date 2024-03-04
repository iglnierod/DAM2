package com.example.recuperacion.domain

import com.example.recuperacion.data.FoodRepository
import com.example.recuperacion.ui.model.FoodModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    operator fun invoke(): Flow<List<FoodModel>> = foodRepository.foods
}