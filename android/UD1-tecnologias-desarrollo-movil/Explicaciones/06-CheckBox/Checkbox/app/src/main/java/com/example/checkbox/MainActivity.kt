package com.example.checkbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.checkbox.ui.theme.CheckboxTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.example.checkbox.ui.theme.CheckInfo


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckboxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val myOptions = getOptions(listOf("Lunes","Martes","Miercoles","Jueves","Viernes"))
                    Column {
                        MySwitch()
                        MyCheckBox()
                        MyCheckBoxWithTextSimple()
                        MyTriStatusCheckBox()
                        myOptions.forEach{ MyCheckBoxWithTextAdvanced(it)}
                    }
                }
            }
        }
    }
}

/**************************************************************************************************/
@Composable
fun MyTriStatusCheckBox(){
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off)}
    TriStateCheckbox(
        state = status,
        onClick = {
            status = when(status){
                ToggleableState.On -> ToggleableState.Off
                ToggleableState.Off->ToggleableState.Indeterminate
                ToggleableState.Indeterminate->ToggleableState.On
            }
        }
    )
}



@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {

    return titles.map{
        var status by rememberSaveable { mutableStateOf(false)}
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { myNewStatus->status = myNewStatus}
        )
    }
}



@Composable
fun MySwitch() {
    var checked by remember { mutableStateOf(false) }
    Switch(
        checked = checked,
        onCheckedChange = { checked = it },
        enabled = true,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            checkedThumbColor = Color.Yellow,
            uncheckedTrackColor = Color.Yellow,
            checkedTrackColor = Color.Black
        )
    )
}

@Composable
fun MyCheckBox(){
    var state by rememberSaveable { mutableStateOf(false)}
    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Red,
            checkmarkColor = Color.Green
        )
    )
    Text( "Viajo en tren ${if (state) "sentado" else "levantado"}")
}

@Composable
fun MyCheckBoxWithTextSimple(){
    var state by rememberSaveable{ mutableStateOf(false) }

    Row(Modifier.padding(5.dp)){
        Checkbox(checked = state, onCheckedChange = {state = !state})
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "Este es el simple")
    }
}

@Composable
fun MyCheckBoxWithTextAdvanced(checkInfo: CheckInfo){
    Row(Modifier.padding(5.dp)){
        Checkbox(checked = checkInfo.selected, onCheckedChange = {checkInfo.onCheckedChange(!checkInfo.selected)})
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = checkInfo.title)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CheckboxTheme {
        //MyCheckBoxWithTextAdvanced()
    }
}