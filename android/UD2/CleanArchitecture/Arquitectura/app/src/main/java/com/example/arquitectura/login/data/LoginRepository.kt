package com.example.arquitectura.login.data

import com.example.arquitectura.login.data.network.LoginService

class LoginRepository {
    val api = LoginService()

    suspend fun doLogin(user:String, password:String): String{

        return api.doLogin(user, password)

    }
}