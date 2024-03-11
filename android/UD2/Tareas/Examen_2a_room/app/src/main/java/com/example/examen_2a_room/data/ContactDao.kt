package com.example.examen_2a_room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts_table")
    fun getContacts(): Flow<List<ContactEntity>>

    @Insert
    suspend fun addContact(item: ContactEntity)

    @Delete
    suspend fun deleteContact(item: ContactEntity)
}