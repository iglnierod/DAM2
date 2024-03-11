package com.example.examen_2a.ui.nuevo

import androidx.lifecycle.ViewModel
import com.example.examen_2a.data.FirebaseService
import com.example.examen_2a.ui.model.ContactoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NuevoContactoViewModel @Inject constructor(private val firebaseService: FirebaseService) : ViewModel() {

    fun addContacto(contacto: ContactoModel) {
        firebaseService.addContacto(contacto)
    }
}