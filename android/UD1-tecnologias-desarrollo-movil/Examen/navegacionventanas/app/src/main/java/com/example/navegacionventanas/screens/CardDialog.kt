package com.example.navegacionventanas.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.navegacionventanas.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDialogScreen(drawerState: DrawerState){
    Scaffold(
        topBar = {CustomAppBar(drawerState = drawerState, title = "Card Dialog" )},
        content = { CardDialog()}


    )


}


@Composable
fun CardDialog(){

    var show by rememberSaveable { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(
            onClick = { show = true },
            colors = ButtonDefaults.buttonColors(Color(44, 213, 221, 255))
        ) {
            Text(text = "MyDialog")
        }
    }
    BrightnessDialog(show)
}

@Composable
fun BrightnessDialog(show: Boolean) {
    if (show) {
        Dialog(onDismissRequest = { }) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp)
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(modifier = Modifier.background(color = Color.Black)) {
                    Title()
                    Divider(thickness = 2.dp, color = Color(44, 213, 221, 255))
                    CheckBox()
                    SliderOjea()
                    Divider(thickness = 1.dp)
                    Buttons()
                }

            }
        }
    }

}

@Composable
fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.brillo),
            contentDescription = "Icon Brightness",
            tint = Color.White
        )
        Text(
            text = "Brightness",
            color = Color.Cyan,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun CheckBox() {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isChecked, onCheckedChange = { newValue -> isChecked = newValue })
        Text(text = "Automatic brightness", modifier = Modifier.padding(4.dp), color = Color.White)
    }

}

@Composable
fun SliderOjea() {
    var sliderValue by remember { mutableStateOf(0f) }

    androidx.compose.material3.Slider(
        value = sliderValue,
        onValueChange = { sliderValue = it },
        modifier = Modifier
            .padding(6.dp),
        enabled = true,
        valueRange = 0f..10f,
        colors = SliderDefaults.colors(
            thumbColor = Color(44, 213, 221, 255),
            activeTrackColor = Color(44, 213, 221, 255),
            inactiveTrackColor = Color.Gray
        )
    )
}

@Composable
fun Buttons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { },
            modifier = Modifier.padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(text = "Cancel")
        }

        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Button(
            onClick = { },
            modifier = Modifier.padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(text = "OK")
        }
    }


}