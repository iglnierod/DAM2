package com.example.arquitectura.login.data.network

import com.example.arquitectura.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {

    @GET("/v3/59165f5c-a0c8-4adb-a287-d3bc9b4bcb2c")

    suspend fun doLogin(): Response<LoginResponse>

}