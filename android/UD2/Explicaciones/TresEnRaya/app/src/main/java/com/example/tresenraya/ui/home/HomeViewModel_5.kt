package com.example.tresenraya.ui.home

import androidx.lifecycle.ViewModel
import com.example.tresenraya.data.network.FirebaseService
import com.example.tresenraya.data.network.model.GameData
import com.example.tresenraya.data.network.model.PlayerData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

/*5.7-Inyectamos el firebaseService*/
@HiltViewModel
class HomeViewModel_5 @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {

    fun onCreateGame_4() {

    }

    fun onJoinGame_4(id: String) {

    }

}