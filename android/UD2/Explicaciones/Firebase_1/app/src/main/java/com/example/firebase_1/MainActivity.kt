package com.example.firebase_1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.firebase_1.data.FirebaseInstance
import com.example.firebase_1.ui.theme.Firebase_1Theme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import java.lang.ref.Reference

class MainActivity : ComponentActivity() {

    private lateinit var firebaseInstance: FirebaseInstance
    private var lectura: String by remember() mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            Firebase_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    firebaseInstance = FirebaseInstance(this)
                    Screen(firebaseInstance,lectura)
                }
            }
        }
    }
}

@Composable
fun Screen(firebaseInstance: FirebaseInstance, lectura: String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = lectura)
        Button(onClick = { firebaseInstance.writeFirebase() }) {
            Text(text = "Actualizar Información")
        }
    }
    SetupListeners(firebaseInstance, lectura)
}

fun SetupListeners(firebaseInstance: FirebaseInstance,lectura: String){
    val postListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            var data: String? = snapshot.getValue(String::class.java).toString()
            if (data != null){
                lectura = data
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.i("Brais encontré un eror por aquí", error.details)
        }
    }
    firebaseInstance.setupDatabaseListener(postListener)
}