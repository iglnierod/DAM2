package com.example.navegacionventanas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navegacionventanas.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EjemploScreen(drawerState: DrawerState){

    Scaffold(
        topBar = { CustomAppBar(
            drawerState = drawerState,
            title = "Ejemplo"
        )},
        bottomBar = { CustomBottomBar()}
    ) { paddingValues ->
        LazyColumn(Modifier.background(MaterialTheme.colorScheme.onBackground) .padding(paddingValues)) {
            item {
                Spacer(modifier = Modifier.padding(top = 126.dp))
                Divider(color = Color.DarkGray)
                Row(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(top = 8.dp)
                        .padding(start = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.width(50.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.vergil
                            ),
                            contentDescription = "vergil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(7.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Son of Sparda",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                            Spacer(modifier = Modifier.padding(1.dp))
                            Image(
                                painter = painterResource(id = R.drawable.verified),
                                contentDescription = "jaja paga el blue",
                                modifier = Modifier
                                    .height(10.dp)
                                    .width(10.dp)
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                text = "@VergilSparda . 5min",
                                color = Color.Gray,
                                fontSize = 10.sp
                            )
                        }

                        Text(text = "I need more power", color = Color.White)
                    }

                }
                Divider(color = Color.DarkGray)

                Row(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(top = 8.dp)
                        .padding(start = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.width(50.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.ayato
                            ),
                            contentDescription = "ayato",
                            modifier = Modifier
                                .clip(CircleShape)
                                .height(50.dp)
                                .width(50.dp)

                        )
                    }
                    Spacer(modifier = Modifier.padding(7.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Leader of Kamisato clan",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                            Spacer(modifier = Modifier.padding(1.dp))
                            Image(
                                painter = painterResource(id = R.drawable.verified),
                                contentDescription = "jaja paga el blue",
                                modifier = Modifier
                                    .height(10.dp)
                                    .width(10.dp)
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                text = "@BubbleTeaEnjoyer      15min",
                                color = Color.Gray,
                                fontSize = 10.sp
                            )

                        }


                        Text(
                            text = "Supe que era ser humilde al compartir banner con Cyno",
                            color = Color.White
                        )

                    }
                }


                Divider(color = Color.DarkGray)


                Row(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(top = 8.dp)
                        .padding(start = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.width(50.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.neuvillete
                            ),
                            contentDescription = "neuvillete",
                            modifier = Modifier
                                .clip(CircleShape)
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(7.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Neuvillete",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                            Spacer(modifier = Modifier.padding(1.dp))
                            Image(
                                painter = painterResource(id = R.drawable.verified),
                                contentDescription = "jaja paga el blue",
                                modifier = Modifier
                                    .height(10.dp)
                                    .width(10.dp)
                            )
                            Spacer(modifier = Modifier.padding(1.dp))

                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                text = "@NeuvilletteFontaine . 4h",
                                color = Color.White,
                                fontSize = 10.sp
                            )

                        }

                        Text(text = "Oretryce mecanic danalise cardinale", color = Color.White)
                    }

                }
                Divider(color = Color.DarkGray)

                Row(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(top = 8.dp)
                        .padding(start = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.width(50.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.gato
                            ),
                            contentDescription = "Lau",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(7.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Seren1224",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                            Spacer(modifier = Modifier.padding(1.dp))

                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                text = "@LauBlancoLe . 5h",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }

                        Text(
                            text = "No puedo mas con este sufriendo llamado Universidad de Vigo",
                            color = Color.White
                        )
                    }

                }

                Divider(color = Color.DarkGray)

                Row(
                    modifier = Modifier
                        .height(90.dp)
                        .padding(top = 8.dp)
                        .padding(start = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.width(50.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.fer
                            ),
                            contentDescription = "Fernandouu",
                            modifier = Modifier
                                .clip(CircleShape)
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(7.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Silksongn't",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                            Spacer(modifier = Modifier.padding(1.dp))

                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                text = "@FSP95 . 5h",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }

                        Text(
                            text = "En serio, a quen de From Software se lle ocurreu o dos putos cálices, que quero falar",
                            color = Color.White
                        )
                    }

                }

                Divider(color = Color.DarkGray)
            }}
    }
}