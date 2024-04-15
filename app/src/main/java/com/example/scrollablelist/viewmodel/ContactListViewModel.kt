package com.example.scrollablelist.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.scrollablelist.R
import com.example.scrollablelist.data.createContactsList
import com.example.scrollablelist.models.Contact
import com.example.scrollablelist.ui.views.AppScreens
import com.example.scrollablelist.ui.views.ContactListUiState
import com.example.scrollablelist.ui.views.InsertContactUiState
import com.example.scrollablelist.ui.views.MainScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContactListViewModel : ViewModel() {

    private val _contactListUiState: MutableStateFlow<ContactListUiState> =
        MutableStateFlow(ContactListUiState(createContactsList()))

    val contactListUiState: StateFlow<ContactListUiState> =
        _contactListUiState.asStateFlow()

    private val _insertContactUiState: MutableStateFlow<InsertContactUiState> =
        MutableStateFlow(InsertContactUiState())

    val insertContactUiState: StateFlow<InsertContactUiState> =
        _insertContactUiState.asStateFlow()

    private val _mainScreenUiState: MutableStateFlow<MainScreenUiState> =
        MutableStateFlow(
            MainScreenUiState(
                "Contact List",
                R.drawable.baseline_add_24,
                "Insert Contact"
            )
        )

    val mainScreenUiState: StateFlow<MainScreenUiState> =
        _mainScreenUiState.asStateFlow()

    private var editContact: Boolean = false
    private var contactToEdit: Contact = Contact(
        R.drawable.baseline_face_24,
        "",
        "",
    )

    fun fabAction(navController: NavController) {
        if (_mainScreenUiState.value.screenName == "Contact List") {
            _mainScreenUiState.update { currentState ->
                currentState.copy(
                    screenName = "Insert Contact",
                    icon = R.drawable.baseline_check_24,
                    iconContentDescription = "Confirm"
                )
            }
            navController.navigate(AppScreens.InsertContact.name)
        } else {
            if (editContact) {
                val allContacts = _contactListUiState.value.contactList.toMutableList()
                val pos = allContacts.indexOf(contactToEdit)
                allContacts.remove(contactToEdit)
                allContacts.add(
                    pos, contactToEdit.copy(
                        picture = _insertContactUiState.value.picture,
                        name = _insertContactUiState.value.name,
                        status = _insertContactUiState.value.status,
                    )
                )
                _contactListUiState.update { currentState ->
                    currentState.copy(
                        contactList = allContacts.toList()
                    )
                }
                editContact = false
                contactToEdit = Contact(R.drawable.baseline_face_24, "", "")
            } else {
                _contactListUiState.update { currentState ->
                    currentState.copy(
                        contactList = currentState.contactList + Contact(
                            _insertContactUiState.value.picture,
                            _insertContactUiState.value.name,
                            _insertContactUiState.value.status,
                        )
                    )
                }
            }
            _insertContactUiState.update {
                InsertContactUiState()
            }
            _mainScreenUiState.update { currentState ->
                currentState.copy(
                    screenName = "Contact List",
                    icon = R.drawable.baseline_add_24,
                    iconContentDescription = "Insert Contact"
                )
            }
            navController.navigate(AppScreens.ContactList.name) {
                popUpTo(AppScreens.ContactList.name) {
                    inclusive = true
                }
            }
        }
    }

    fun navigateBack(navController: NavController) {
        editContact = false
        contactToEdit = Contact(R.drawable.baseline_face_24, "", "")
        _insertContactUiState.update { InsertContactUiState() }
        _mainScreenUiState.update {
            MainScreenUiState(
                screenName = "Contact List",
                icon = R.drawable.baseline_add_24,
                iconContentDescription = "Insert Contact"
            )
        }
        navController.popBackStack()
    }

    fun updateContact(contact: Contact, navController: NavController) {
        editContact = true
        contactToEdit = contact
        _insertContactUiState.update {currentState->
            currentState.copy(
                picture = contact.picture,
                name = contact.name,
                status = contact.status
            )
        }
        _mainScreenUiState.update {
            MainScreenUiState(
                screenName = "Edit Contact",
                icon = R.drawable.baseline_check_24,
                iconContentDescription = "Confirm"
            )
        }
        navController.navigate(AppScreens.InsertContact.name)
    }

    fun updatePicture(@DrawableRes newPicture: Int) {
        _insertContactUiState.value = _insertContactUiState.value.copy(
            picture = newPicture
        )
    }

    fun updateName(newName: String) {
        _insertContactUiState.value = _insertContactUiState.value.copy(
            name = newName
        )
    }

    fun updateStatus(newStatus: String) {
        _insertContactUiState.value = _insertContactUiState.value.copy(
            status = newStatus
        )
    }

    fun deleteContact(contact: Contact) {
        val contacts = _contactListUiState.value.contactList.toMutableList()
        contacts.remove(contact)
        _contactListUiState.value = _contactListUiState.value.copy(
            contactList = contacts.toList()
        )
    }
}