package com.example.twitterfirebase.ui.login

import androidx.lifecycle.ViewModel
import com.example.twitterfirebase.data.network.FirebaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseService: FirebaseService
) : ViewModel() {

    private var _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    private var _password = MutableStateFlow<String?>(null)
    val password: StateFlow<String?> = _password

    fun checkUser(user: String, pwd: String): Boolean {
        return firebaseService.login(user, pwd)
    }
}