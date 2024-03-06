package com.example.firebase_1.data

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlin.random.Random

class FirebaseInstance(context: Context) {

    private val database = Firebase.database

    init {
        FirebaseApp.initializeApp(context)
    }

    fun writeFirebase(){
        val myRef = database.reference
        val randomValue: String = Random.nextInt(1, 200).toString()
        myRef.setValue("Último valor= $randomValue")
    }

    fun setupDatabaseListener(postListener: ValueEventListener){
        database.reference.addValueEventListener(postListener)
    }
}