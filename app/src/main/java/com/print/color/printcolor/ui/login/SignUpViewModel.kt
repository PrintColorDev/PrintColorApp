package com.print.color.printcolor.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {

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

    data class SignUpUiState(
        val firstName: String = "",
        val lastName: String = "",
        val phoneNumber: String = "",
        val userDate: String = "",
        val pin: String = "",
        val pinConfirmation: String = "",
    ) {
        fun isValidFields(): Boolean =
            firstName.isNotBlank() && lastName.isNotBlank() && phoneNumber.isNotBlank()
                    && userDate.isNotBlank() && pin.isNotBlank() && pinConfirmation.isNotBlank()
    }
}