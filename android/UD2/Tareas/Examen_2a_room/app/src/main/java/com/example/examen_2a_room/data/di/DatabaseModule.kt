package com.example.examen_2a_room.data.di

import android.content.Context
import androidx.room.Room
import com.example.examen_2a_room.data.ContactDao
import com.example.examen_2a_room.data.ContactsDatabase
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
    fun provideFoodDao(contactsDatabase: ContactsDatabase): ContactDao {
        return contactsDatabase.contactDao()
    }

    @Provides
    @Singleton
    fun provideContactsDatabase(@ApplicationContext appContext: Context): ContactsDatabase {
        return Room.databaseBuilder(
            appContext, ContactsDatabase::class.java, "ContactsDatabase"
        ).build()
    }
}