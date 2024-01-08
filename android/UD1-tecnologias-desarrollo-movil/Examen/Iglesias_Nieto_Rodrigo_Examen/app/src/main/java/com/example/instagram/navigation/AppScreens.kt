package com.example.instagram.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen: AppScreens("login_screen")
    object FeedScreen: AppScreens("feed_screen")
}
