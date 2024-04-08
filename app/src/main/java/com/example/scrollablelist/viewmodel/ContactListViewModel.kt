package com.example.scrollablelist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scrollablelist.data.createContactsList
import com.example.scrollablelist.models.Contact
import com.example.scrollablelist.ui.views.ContactListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactListViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<ContactListUiState> =
        MutableStateFlow(ContactListUiState(createContactsList()))

    val uiState: StateFlow<ContactListUiState> =
        _uiState.asStateFlow()

    fun deleteContact(contact: Contact){
        val contacts = _uiState.value.contactList.toMutableList()
        contacts.remove(contact)
        _uiState.value = _uiState.value.copy(
            contactList = contacts.toList()
        )
    }
}