package com.example.examen_2a_room.domain

import com.example.examen_2a_room.data.ContactRepository
import com.example.examen_2a_room.ui.model.ContactModel
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {
    suspend operator fun invoke(contactModel: ContactModel){
        contactRepository.delete(contactModel)
    }
}