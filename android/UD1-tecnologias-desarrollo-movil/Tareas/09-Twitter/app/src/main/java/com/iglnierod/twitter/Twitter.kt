package com.iglnierod.twitter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwitterScaffold() {
    Scaffold(
        topBar = { MyTopAppBar() },
        floatingActionButton = { MyFloatingActionButton() },
        bottomBar = { MyBottomAppBar() }
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            MyContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    Column {
        CenterAlignedTopAppBar(
            title = { Text(text = "") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Black,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            actions = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user_image),
                        contentDescription = "user image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(0.dp, Color.Transparent, CircleShape)
                    )
                    Text(
                        text = "Messages",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.more_horiz_white_24dp),
                        contentDescription = "More",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(15.dp, 5.dp)
                .border(0.dp, Color.Transparent, RoundedCornerShape(100.dp)),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color(0XFF657786)
                )
            },
            value = "Search Direct Messages",
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0XFF99A1A8),
                containerColor = Color(0xFF202327),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(100.dp),
            onValueChange = {/* TODO */ },
            placeholder = {
                Text(text = "Search Direct Messages")
            }
        )
    }
}
@Composable
fun MyContent() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        MyDirectMessages(R.drawable.cat, "Lunnaris", "Lunnaris100", "4h", "Tengo sueño y tos")
        MyDirectMessages(R.drawable.cat1, "crampy", "craampyy", "1h", "Soy tonto y estoy triste")
        MyDirectMessages(R.drawable.cat2, "Xian", "TheMan1a", "12h", "No, domingo de almagv")
        MyDirectMessages(R.drawable.cat3, "Andres😈", "Andrupi", "10 días", "Hoy examen de conducir, deseadme suerte")
        MyDirectMessages(R.drawable.cat4, "Alma", "almagv", "3h", "Cerámica!")
    }
}

@Composable
fun MyDirectMessages(
    userIcon: Int,
    name: String,
    username: String,
    lastConnection: String,
    lastMessage: String
) {
    Row(
        modifier = Modifier
            .background(Color.Black)
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = userIcon),
            contentDescription = "user image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .size(64.dp)
                .clip(CircleShape)
                .border(0.dp, Color.Transparent, CircleShape)
        )
        MyUserName(name, username, lastConnection, lastMessage)
    }
}

@Composable
fun MyUserName(
    name: String,
    username: String,
    lastConnection: String,
    lastMessage: String
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp
            )
            Image(
                painter = painterResource(id = R.drawable.twitter_verified_badge),
                contentDescription = "Verified",
                modifier = Modifier
                    .padding(2.dp, 0.dp)
                    .size(20.dp)
            )
            Text(
                text = "@$username · $lastConnection",
                color = Color(0XFF99A1A8),
                fontSize = 20.sp
            )
        }
        Text(
            text = lastMessage,
            color = Color(0XFF99A1A8),
            fontSize = 18.sp,
            modifier = Modifier.height(50.dp)
        )
    }
}

@Composable
fun MyFloatingActionButton() {
    ExtendedFloatingActionButton(
        onClick = { /*TODO*/ },
        containerColor = Color(0XFF1DA1F2),
        contentColor = Color.White,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add",
            // TODO: full rounded button
            modifier = Modifier.padding(0.dp, 25.dp)
        )
    }
}

@Composable
fun MyBottomAppBar() {
    var index by remember { mutableStateOf(0) }
    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = index == 0,
            onClick = { index = 0 },
            icon = {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            icon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            icon = {
                Icon(imageVector = Icons.Filled.Call, contentDescription = "Voice")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = index == 3,
            onClick = { index = 3 },
            icon = {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notifications")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = index == 4,
            onClick = { index = 4 },
            icon = {
                Icon(imageVector = Icons.Filled.Email, contentDescription = "Direct Messages")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White,
                indicatorColor = Color.Transparent
            )
        )
    }
}