package com.example.examen_2a_room.ui

import com.example.examen_2a_room.ui.model.ContactModel

sealed interface ContactUiState {
    object Loading : ContactUiState

    data class Error(val throwable: Throwable) : ContactUiState

    data class Success(val foods: List<ContactModel>) : ContactUiState
}