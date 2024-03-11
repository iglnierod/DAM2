package com.example.examen_2a_room.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactEntity::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    //DAO
    abstract fun contactDao(): ContactDao
}