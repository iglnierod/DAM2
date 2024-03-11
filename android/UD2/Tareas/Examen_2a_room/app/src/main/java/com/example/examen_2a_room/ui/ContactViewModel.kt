package com.example.examen_2a_room.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen_2a_room.domain.AddContactUseCase
import com.example.examen_2a_room.domain.DeleteContactUseCase
import com.example.examen_2a_room.domain.GetContactsUseCase
import com.example.examen_2a_room.ui.ContactUiState.Success
import com.example.examen_2a_room.ui.model.ContactModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContactViewModel @Inject constructor(
    private val addContactUseCase: AddContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
    getContactsUseCase: GetContactsUseCase
) : ViewModel() {
    private val _uiState: StateFlow<ContactUiState> = getContactsUseCase().map(::Success)
        .catch { ContactUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactUiState.Loading)
    val uiState: StateFlow<ContactUiState> = _uiState

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _foods = mutableStateListOf<ContactModel>()
    val foods: List<ContactModel> = _foods

    fun getContacts(foodUiState: ContactUiState): List<ContactModel> {
        return when (foodUiState) {
            is ContactUiState.Error -> emptyList()
            ContactUiState.Loading -> emptyList()
            is Success -> {
                foodUiState.foods
            }

            //else -> {}
        }
    }

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onContactCreated(userName: String, userApellidos: String, userTlf: String) {
        viewModelScope.launch {
            addContactUseCase(
                ContactModel(
                    nombreUsuario = userName,
                    apellidosUsuario = userApellidos,
                    tlfnUsuario = userTlf.toInt()
                )
            )
        }
        _showDialog.value = false
        //_foods.add(FoodModel(name = foodName, description = foodDescription, price = foodPrice))
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onItemRemoved(contactModel: ContactModel) {
        viewModelScope.launch {
            deleteContactUseCase(contactModel)
        }
    }
}