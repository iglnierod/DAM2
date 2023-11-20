package com.example.slider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slider.ui.theme.SliderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SliderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MySliderBasico()
                    //MySliderAdvanced()
                }
            }
        }
    }
}

@Composable
fun MySliderBasico(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
        var sliderPosition by remember{ mutableStateOf(0f) }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it},
            valueRange= 0f..10f,
            enabled = true,
            steps= 9,
            colors= SliderDefaults.colors()
        )
        Text(text = sliderPosition.toString())
    }
}

@Composable
fun MySliderAdvanced(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var completeValue by remember { mutableStateOf("") }
        var sliderPosition by remember{ mutableStateOf(0f) }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it},
            onValueChangeFinished = { completeValue = sliderPosition.toString()},
            valueRange = 0f..10f,
            steps = 9,
            enabled = true
        )
        Text(text = completeValue)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SliderTheme {
        MySliderAdvanced()
    }
}