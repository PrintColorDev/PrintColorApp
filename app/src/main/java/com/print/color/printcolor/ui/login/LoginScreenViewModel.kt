package com.print.color.printcolor.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.print.color.printcolor.data.network.FirebaseDataBaseService
import com.print.color.printcolor.utils.PRINT_COLOR_EMAIL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(private val firebaseDataBaseService: FirebaseDataBaseService) :
    ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState

    private var _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun onUserNameChanged(userName: String) {
        _uiState.update { it.copy(userName = userName.toString()) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password.toString()) }
    }

    fun onLoginClicked(userName: String, password: String, navigateToHome: () -> Unit) {
        val userEmail = userName + PRINT_COLOR_EMAIL
        viewModelScope.launch {
            _isLoading.value = true

            try {
                val result = withContext(Dispatchers.IO) {
                    Log.d("LoginScreenViewModel", "onLoginClicked: $userEmail, $password")
                    firebaseDataBaseService.login("david.pc.0310025@printcolor.com", "11177")
                }

                if (result != null) {
                    navigateToHome()
                } else {
                    _uiState.update { it.copy(error = "Invalid username or password") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            } finally {
                _isLoading.value = false
            }

        }
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