package com.example.recuperacion.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM foods_table")
    fun getFoods(): Flow<List<FoodEntity>>

    @Insert
    suspend fun addFood(item: FoodEntity)
}