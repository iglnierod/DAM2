package com.example.examen_2a_room.domain

import com.example.examen_2a_room.data.ContactRepository
import com.example.examen_2a_room.ui.model.ContactModel
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {
    operator fun invoke(): Flow<List<ContactModel>> = contactRepository.contacts
}