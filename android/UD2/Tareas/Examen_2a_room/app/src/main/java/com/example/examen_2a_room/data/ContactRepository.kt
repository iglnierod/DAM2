package com.example.examen_2a_room.data

import com.example.examen_2a_room.ui.model.ContactModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
) {
    suspend fun add(contactModel: ContactModel) {
        contactDao.addContact(
            ContactEntity(
                contactModel.codUsuario,
                contactModel.nombreUsuario,
                contactModel.apellidosUsuario,
                contactModel.tlfnUsuario
            )
        )
    }

    suspend fun delete(contactModel: ContactModel) {
        contactDao.deleteContact(contactModel.toData())
    }

    fun ContactModel.toData(): ContactEntity {
        return ContactEntity(
            this.codUsuario,
            this.nombreUsuario,
            this.apellidosUsuario,
            this.tlfnUsuario
        )
    }

    val contacts: Flow<List<ContactModel>> =
        contactDao.getContacts().map { items ->
            items.map {
                ContactModel(it.codUsuario, it.nombreUsuario, it.apellidosUsuario, it.tlfnUsuario)
            }
        }
}