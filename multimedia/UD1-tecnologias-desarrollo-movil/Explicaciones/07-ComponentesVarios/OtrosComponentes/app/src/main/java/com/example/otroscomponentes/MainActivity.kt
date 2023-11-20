@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.otroscomponentes

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.otroscomponentes.ui.theme.OtrosComponentesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtrosComponentesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyCard()
                    MyBadgeBox()
                    MyDivider()
                    MyDropDownMenu()
                }
            }
        }
    }
}

/**************************************************************************************************/
/*************************************** CARD *****************************************************/
/**************************************************************************************************/

@Composable
fun MyCard(){
    var isSelected by rememberSaveable { mutableStateOf(false)}
    Card(
        modifier = Modifier
            .size(width = 240.dp, height = 150.dp)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(100.dp),
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(Color.Green),
        border = BorderStroke(1.dp, Color.Red),
    ) {
        Column {
            Text(text = "Lunes")
            Text(text = "Martes")
            Text(text = "Miercoles")
            RadioButton(
                selected = isSelected,
                onClick = { isSelected = !isSelected},
                enabled = true,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Red,
                    unselectedColor = Color.Red,
                    disabledSelectedColor = Color.Yellow,
                    disabledUnselectedColor = Color.Blue
                )
            )
        }
    }
}
/**************************************************************************************************/
/*************************************** SURFACE **************************************************/
/**************************************************************************************************/

/*OLVIDARSE*/

/**************************************************************************************************/
/*************************************** BADGEBOX *************************************************/
/**************************************************************************************************/
@Composable
fun MyBadgeBox(){
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(100.dp)) {
        BadgedBox(
            badge = { Badge{ Text("MIERCOLES") }}
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = "Corazón")
        }
    }

}


/**************************************************************************************************/
/**************************************** DIVIDER *************************************************/
/**************************************************************************************************/

@Composable
fun MyDivider(){
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(10.dp), color = Color.Red, thickness = 10.dp)
}

/**************************************************************************************************/
/************************************** DROPDOWNMENU **********************************************/
/**************************************************************************************************/

@Composable
fun MyDropDownMenu(){

    var seleccion by rememberSaveable { mutableStateOf("Lunes")}
    var expanded by rememberSaveable{ mutableStateOf(false) }
    val dias = listOf("Lunes","Martes","Miercoles","Jueves","Viernes")
    
    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = seleccion,
            onValueChange = { seleccion = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier.clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = { expanded = false }
        ) {
            dias.forEach { dia ->
                DropdownMenuItem(
                    text = { Text(text = dia) },
                    onClick = {
                        expanded = false
                        seleccion = dia
                    }
                )
            }
        }
    }
}


/**************************************************************************************************/
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OtrosComponentesTheme {
        MyCard()
        //MyBadgeBox()
        //MyDivider()
    }
}