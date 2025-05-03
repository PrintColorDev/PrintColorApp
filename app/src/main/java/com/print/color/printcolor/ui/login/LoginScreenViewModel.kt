package com.print.color.printcolor.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState

    fun onUserNameChanged(userName: String) {
        _uiState.update { it.copy(userName = userName.toString()) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password.toString()) }
    }

    data class LoginUIState(
        val userName: String = "",
        val password: String = "",
        val isLoggedIn: Boolean = false,
        val error: String? = null
    ) {
        fun isValidLogin(): Boolean = userName.isNotBlank() && password.isNotBlank()
    }
}