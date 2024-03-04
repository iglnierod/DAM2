package com.example.recuperacion.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recuperacion.data.FoodEntity

@Database(entities = [FoodEntity::class], version = 1)
abstract class KnoweatsDatabase: RoomDatabase() {
    // DAO
    abstract fun foodDao(): FoodDao
}