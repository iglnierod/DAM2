package com.example.twitterfirebase.data.network

import android.util.Log
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseService @Inject constructor(
    private val reference: DatabaseReference
) {
    companion object {
        private const val PATH = "twitter"
    }

    fun login(username: String, password: String): Boolean {
        val userPwd: String = reference.child(PATH).child(username).child(password).get().toString()
        Log.i("FirebaseService.login()", userPwd)
        return password == userPwd;
    }

}