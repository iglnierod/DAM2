package com.example.examen_2a.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen_2a.data.FirebaseService
import com.example.examen_2a.ui.model.ContactoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactosViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {

    private val _contactos = MutableStateFlow<ContactoModel?>(null)
    val contactos: StateFlow<ContactoModel?> = _contactos

    fun getDatos() {
        viewModelScope.launch {
            /* Completa la coroutina llamando al firebaseService.obtenerContactos y trae los datos
            de realtime database convertido a el tipo ContactoModel
             */
            firebaseService.obtenerContactos().collect {
                var result = it
                if(result != null)
                    Log.i("obetenerContactos().result is null", result.nombreUsuario)
            }
        }

    }


}

