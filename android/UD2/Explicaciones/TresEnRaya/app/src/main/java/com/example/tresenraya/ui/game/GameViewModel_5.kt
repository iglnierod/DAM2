package com.example.tresenraya.ui.game

import androidx.lifecycle.ViewModel
import com.example.tresenraya.data.network.FirebaseService_5
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*5.7-Inyectamos el firebaseService*/
@HiltViewModel
class GameViewModel_5 @Inject constructor(private val firebaseService: FirebaseService_5): ViewModel(){

}