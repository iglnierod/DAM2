package com.iglnierod.simplefirebase.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHomeScreen() {
    // Write to firebase
    val database = Firebase.database
    val myRef = database.getReference("message")

    var message: String by remember { mutableStateOf("") }

    var lastMessage by remember { mutableStateOf("") }

    // Update on change last value
    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val post = snapshot.getValue()
            lastMessage = post.toString()
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    })

    Column(
        Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        TextField(value = message, onValueChange = { message = it })
        Button(
            onClick = {
                myRef.setValue(message)
                message = ""
            },
            content = {
                Text(text = "Send message")
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Last message: $lastMessage")
    }


}