package com.example.menus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    Scaffold(
        topBar = { MyTopAppBar() },
        bottomBar = { MyBottomAppBar() },
        floatingActionButton = { MyFAB()},
        //floatingActionButtonPosition = FabPosition.Center
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) { /* ... */ }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text(text = "Título") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Magenta,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "BACK")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "SEARCH")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Send, contentDescription = "SEND")
            }
        }
    )
}

@Composable
fun MyBottomAppBar() {
    var index by remember { mutableStateOf(0)}
    NavigationBar(
        containerColor = Color.Magenta,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = index == 0,
            onClick = {index = 0 },
            icon = {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            },
            label = { Text(text = "Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            icon = {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
            },
            label = { Text(text = "Favorite") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            icon = {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "Person")
            },
            label = { Text(text = "Person") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            selected = index == 3,
            onClick = { index = 3 },
            icon = {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "Star")
            },
            label = { Text(text = "Star") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
    }
}

@Composable
fun MyFAB(){
    ExtendedFloatingActionButton(
        onClick = { /*TODO*/ },
        containerColor = Color.Magenta,
        contentColor = Color.White,
        shape = CircleShape
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}




