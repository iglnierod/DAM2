package com.example.imagenes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imagenes.ui.theme.ImagenesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImagenesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Capa()
                }
            }
        }
    }
}

@Composable
fun MyImagen(){
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription ="Imagen de ejemplo",
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, androidx.compose.ui.graphics.Color.Red, CircleShape),
        alpha = 1f
    )
}

@Composable
fun MyInternetImagen(){
    val imageModifier = Modifier
        .clip(CircleShape)
        .clip(RoundedCornerShape(50.dp))
        .size(200.dp)
        .border(2.dp, Color.Blue, CircleShape)

    AsyncImage(
        model = "https://www.clarin.com/img/2021/03/09/0x5gB2KxU_1256x620__1.jpg",
        contentDescription = "Translated description of what the image contains",
        contentScale = ContentScale.Crop,
        modifier = imageModifier
    )
}

@Composable
fun MyIcon(){
    Icon(
        imageVector = Icons.Rounded.Star,
        contentDescription ="Estrellita",
        modifier = Modifier.size(100.dp),
        tint = Color.Red
    )
}

@Composable
fun Capa(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyImagen()
        MyInternetImagen()
        MyIcon()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImagenesTheme {
        Capa()
    }
}