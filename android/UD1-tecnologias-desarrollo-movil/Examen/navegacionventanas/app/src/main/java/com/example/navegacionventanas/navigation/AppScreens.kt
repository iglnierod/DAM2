package com.example.navegacionventanas.navigation

sealed class AppScreens(val route: String){

    /*Sirve para almacenar las rutas y evitar que si queremos cambiar el nombre de una de ellas
    * se actualice en todos los ficheros existentes. También evitamos que crachee la APP*/

    object Articles: AppScreens("Articles")
    object About: AppScreens("About Us")
    object Settings: AppScreens("Settings")
    object Ejemplo: AppScreens("Ejemplo")
    object Login: AppScreens("Login")
    object Dialog : AppScreens("Dialog")
    object CardDialog : AppScreens("CardDialog")
}
