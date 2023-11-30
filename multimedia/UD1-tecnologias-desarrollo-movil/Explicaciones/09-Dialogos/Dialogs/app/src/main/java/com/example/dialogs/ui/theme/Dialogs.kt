package com.example.dialogs.ui.theme

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.dialogs.R


@Composable
fun MyDialogoAlertSimple(show: Boolean, onDismiss: () -> Unit) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Versión de Android 7.0",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun MyDialogoAlertAdvanced(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (show) {
        AlertDialog(onDismissRequest = { onDismiss() },
            title = { Text(text = "Titulo") },
            text = { Text(text = "Esto es un dialogo emergente") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "ACEPTAR")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "CANCELAR")
                }
            })
    }
}

@Composable
fun MyTitulo(text: String) {
    Icon(
        Icons.Filled.Star,
        contentDescription = null,
        modifier = Modifier.size(32.dp),
        tint = Color.White
    )

    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(8.dp),
        color = Color(0xFF32A7D3)
    )

}

@Composable
fun MyAccountItem(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "Avatar cuenta de correo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(15.dp)
                .size(40.dp)
                .clip(CircleShape)
        )

        Text(text = email, fontSize = 14.sp)
    }

}


/********************************** DIALOGO CON RADIOBUTTONS **************************************/
/**************************************************************************************************/
@Composable
fun MyRadioButtonAdvanced(name: String, onItemSelected: (String) -> Unit) {

    Column(Modifier.fillMaxWidth()) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Matemáticas",
                onClick = { onItemSelected("Matemáticas") })
            Text(text = "Matemáticas")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Geografía", onClick = { onItemSelected("Geografía") })
            Text(text = "Geografía")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Historia", onClick = { onItemSelected("Historia") })
            Text(text = "Historia")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ciencias Sociales",
                onClick = { onItemSelected("Ciencias Sociales") })
            Text(text = "Ciencias Sociales")
        }
    }
}

@Composable
fun MyConfirmationDialog(show: Boolean, onDismiss: () -> Unit) {
    if (show) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MyTitulo(text = "Brightness")
                }
                Divider(Modifier.fillMaxWidth(), color = Color(0xFF32A7D3))
                var status by remember { mutableStateOf("") }
                MyBrightnessCheckbox("Automatic brightness", false)
                Divider(Modifier.fillMaxWidth(), color = Color.Gray)
                Row(Modifier.align(Alignment.End)) {
                    TextButton(onClick = { }) {
                        Text(text = "ACEPTAR")
                    }
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "CANCELAR")
                    }
                }
            }
        }
    }
}

@Composable
fun MyBrightnessCheckbox(text: String, checked: Boolean) {
    var newChecked by remember { mutableStateOf(checked) }
    var sliderValue by remember { mutableStateOf(0.5f) }

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { newChecked = it },
                colors = CheckboxDefaults.colors(checkedColor = Color.Red)
            )
            Text(
                text = text,
                color = Color.White
            )
        }

        Slider(
            value = sliderValue,
            valueRange = 0f..1f,
            onValueChange = { newValue ->
                sliderValue = newValue
                // Update the brightness based on the slider value
            },
            modifier = Modifier.padding(10.dp,20.dp),
            colors = SliderDefaults.colors(activeTrackColor = Color.Cyan)
        )
    }
}

/**************************************************************************************************/
@Composable
fun MyDialogoCustom(show: Boolean, onDismiss: () -> Unit) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnBackPress = true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
                    .background(color = Color.LightGray)
            ) {
                MyTitulo(text = "Set Backup Account")
                MyAccountItem(email = "broncano@gmail.com", drawable = R.drawable.broncano)
                MyAccountItem(email = "ponce@gmail.com", drawable = R.drawable.ponce)
            }
        }
    }
}
