package com.example.recuperacion.data.di

import android.content.Context
import androidx.room.Room
import com.example.recuperacion.data.FoodDao
import com.example.recuperacion.data.KnoweatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideFoodDao(knoweatsDatabase: KnoweatsDatabase): FoodDao {
        return knoweatsDatabase.foodDao()
    }

    @Provides
    @Singleton
    fun provideKnoweatsDatabase(@ApplicationContext appContext: Context): KnoweatsDatabase {
        return Room.databaseBuilder(
            appContext, KnoweatsDatabase::class.java, "FoodDatabase"
        ).build()
    }
}