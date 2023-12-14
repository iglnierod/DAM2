package com.example.navegacionventanas.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.navegacionventanas.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogScreen(drawerState: DrawerState){
    Scaffold(
        topBar = { CustomAppBar(drawerState = drawerState, title = "Dialog") },
        bottomBar = { CustomBottomBar()},
        content = { botonDialog()}
    )
}


@Composable
fun botonDialog(){
    var show by rememberSaveable { mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Button(onClick = {show =true}) {
            Text(text = "Ajustar brillo")

        }
    }

    DialogBrillo(show,
        onDismiss = {show = false})
}

@Composable
fun DialogBrillo(
    show: Boolean,
    onDismiss: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column (modifier = Modifier.background(Color.DarkGray)){
                Brillo()
                Spacer(modifier = Modifier.padding(top = 5.dp))
                Divider(color = Color(0, 215, 235))
                CheckBoxBrillo()
                Slider()
                Divider(color = Color.DarkGray)
                Botones(onDismiss)

            }
        }
    }
}



@Composable
fun Brillo() {
    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(5.dp)){
        Image(painter = painterResource(id = R.drawable.brillo),
            contentDescription = "icono brillo")
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Brightness", color = Color(0,200,255))
    }
}


@Composable
fun CheckBoxBrillo(){
    var check by rememberSaveable { mutableStateOf(false) }
    Row (verticalAlignment = Alignment.CenterVertically){
        Checkbox(
            checked = check,
            onCheckedChange = {check = !check},
            enabled = true
        )
        Text(text = "Automatic brightness", color = Color.White)
    }

}


@Composable
fun Slider(){
    var position by remember { mutableStateOf(0.5f) }
    androidx.compose.material3.Slider(
        value = position,
        onValueChange = { position = it },
        colors = SliderDefaults.colors(
            activeTrackColor = Color(0, 200, 235),
            inactiveTrackColor = Color.DarkGray,
            thumbColor = Color(0, 200, 255)

        )
    )
}


@Composable
fun Botones(onDismiss: () -> Unit){
    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
        TextButton(onClick = { onDismiss() }) {
            Text(text = "Cancel", color = Color.White)
        }
        Divider(
            color = Color.White,
            modifier = Modifier
                .width(1.dp)
                .height(50.dp)
        )
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "OK", color = Color.White)
        }
    }
}