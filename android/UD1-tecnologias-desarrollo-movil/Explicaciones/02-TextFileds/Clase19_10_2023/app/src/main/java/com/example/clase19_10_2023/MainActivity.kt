@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.clase19_10_2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.clase19_10_2023.ui.theme.Clase19_10_2023Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Clase19_10_2023Theme {
                Layout()
            }
        }
    }
}

@Composable
fun MyText() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "¿Cómo estás?")
        Text(text = "¿Cómo estás?", fontWeight = FontWeight.ExtraBold)
        Text(text = "¿Cómo estás?", fontWeight = FontWeight.Light)
        Text(text = "¿Cómo estás?", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(text = "¿Cómo estás?", color = Color.Red)
        Text(
            text = "¿Cómo estás?",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "¿Cómo estás?",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "¿Cómo estás?",
            style = TextStyle(
                textDecoration = TextDecoration.combine(listOf(
                    TextDecoration.LineThrough,
                    TextDecoration.Underline)))
        )
    }
}

@Composable
fun MyTextField() {
    var myText by remember { mutableStateOf("") }
    TextField(
        value = myText,
        onValueChange = { myText = it},
        label = { Text("email") },
        maxLines = 2,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun MyTextFieldAdvanced(){
    var password by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Enter password") },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary, unfocusedBorderColor = Color.Black),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun MyTextFieldOutline(){
    var myText by remember { mutableStateOf("") }
    OutlinedTextField(
        value = myText ,
        onValueChange = { myText = it},
        label = { Text("Login") },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary, unfocusedBorderColor = Color.Black)
    )

}

@Composable
//Tratamiento de imagenes
fun MyImage(){
    Image(
        painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "Imagen de prueba",
        modifier = Modifier
            .padding(20.dp)
            .clip(CircleShape)
            .size(100.dp)
            .background(MaterialTheme.colorScheme.primary)
    )
}


@Composable
fun Layout(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (foto,login,password) = createRefs()
        val guiasup = createGuidelineFromTop(0.3f)

        Box(modifier = Modifier.constrainAs(foto){
            top.linkTo(guiasup)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        ){
            MyImage()
        }

        Box(modifier = Modifier.constrainAs(login) {
            top.linkTo(foto.bottom)
            start.linkTo(foto.start)
            end.linkTo(foto.end)
        })
        {
            MyTextFieldOutline()
        }

        Box(modifier = Modifier.constrainAs(password) {
            top.linkTo(login.bottom)
            start.linkTo(login.start)
            end.linkTo(login.end)
        })
        {
            MyTextFieldAdvanced()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Clase19_10_2023Theme {
        Layout()
    }
}