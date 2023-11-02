package com.example.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview
fun MyConstraintLayout() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxTop, boxLeft, boxRight, boxBottom) = createRefs()
        // ARRIBA
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxSize(0.35f)
                .background(Color.Yellow)
                .constrainAs(boxTop) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Estoy arriba")
        }

        // IZQUIERDA
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxSize(0.33f)
                .background(Color.Red)
                .constrainAs(boxLeft) {
                    top.linkTo(boxTop.bottom)
                    start.linkTo(parent.start)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Izquierda")
        }

        // DERECHA
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxSize(0.33f)
                .background(Color.Green)
                .constrainAs(boxRight) {
                    top.linkTo(boxTop.bottom)
                    start.linkTo(boxLeft.end)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Derecha")
        }

        // ABAJO
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxSize(0.35f)
                .background(Color.Blue)
                .constrainAs(boxBottom) {
                    top.linkTo(boxLeft.bottom)
                    start.linkTo(parent.start)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Estoy abajo")
        }
    }
}