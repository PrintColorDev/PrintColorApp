package com.print.color.printcolor.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.print.color.printcolor.data.network.FirebaseDataBaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val CONTACT_MAX_LENGTH = 10
const val DATE_MAX_LENGTH = 10
const val PIN_MAX_LENGTH = 6

@HiltViewModel
class SignUpViewModel @Inject constructor(val firebaseDataBaseService: FirebaseDataBaseService) :
    ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState

    fun onFirstNameChanged(firstName: String) {
        _uiState.update { it.copy(firstName = firstName.toString()) }
    }

    fun onLastNameChanged(lastName: String) {
        _uiState.update { it.copy(lastName = lastName.toString()) }
    }

    fun onPhoneChanged(phoneNumber: String) {
        _uiState.update { it.copy(phoneNumber = phoneNumber.toString()) }
    }

    fun onUserDateChanged(userDate: String) {
        _uiState.update { it.copy(userDate = userDate.toString()) }
    }

    fun onPINChanged(pin: String) {
        _uiState.update { it.copy(pin = pin.toString()) }
    }

    fun onPINConfirmationChanged(pinConfirmation: String) {
        _uiState.update { it.copy(pinConfirmation = pinConfirmation.toString()) }
    }

    private fun isUserSaved(show: Boolean) {
        _uiState.update { it.copy(isUserSaved = show) }
    }

    fun onSignUp(onSuccessSignUp: () -> Unit) {
        viewModelScope.launch {
            isUserSaved(show = true)
            val result = withContext(Dispatchers.IO) {
                firebaseDataBaseService.createNewUser(
                    firstName = _uiState.value.firstName,
                    lastName = _uiState.value.lastName,
                    phoneNumber = _uiState.value.phoneNumber,
                    userDate = _uiState.value.userDate,
                    pin = _uiState.value.pin,
                    userName = _uiState.value.firstName
                )
            }
            if (result) {
                onSuccessSignUp()
            } else {
                _uiState.update {
                    it.copy(error = "An error has been occurred")
                }
                isUserSaved(show = false)
            }
            isUserSaved(show = false)
        }
    }

    fun clearFields() {
        _uiState.update {
            it.copy(
                firstName = "",
                lastName = "",
                phoneNumber = "",
                userDate = "",
                pin = "",
                pinConfirmation = ""
            )
        }
    }

    data class SignUpUiState(
        val firstName: String = "",
        val lastName: String = "",
        val phoneNumber: String = "",
        val userDate: String = "",
        val pin: String = "",
        val pinConfirmation: String = "",
        val isUserSaved: Boolean = false,
        val error: String? = null
    ) {
        fun arePINEquals(): Boolean = pin == pinConfirmation
        private fun isValidPIN(): Boolean = pin == pinConfirmation && pin.length >= PIN_MAX_LENGTH

        fun isValidFields(): Boolean =
            firstName.isNotBlank() && lastName.isNotBlank() && phoneNumber.length >= CONTACT_MAX_LENGTH
                    && userDate.isNotBlank() && isValidPIN()
    }

}