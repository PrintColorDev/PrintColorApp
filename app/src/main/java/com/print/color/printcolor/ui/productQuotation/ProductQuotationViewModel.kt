package com.print.color.printcolor.ui.productQuotation

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

@HiltViewModel
class ProductQuotationViewModel @Inject constructor(val firebaseDataBaseService: FirebaseDataBaseService): ViewModel() {

    private val _uiState = MutableStateFlow(AddQuotationUIState())
    val uiState: StateFlow<AddQuotationUIState> = _uiState

    fun onClientNameChanged(name: String) {
        _uiState.update { it.copy(clientName = name.toString()) }
    }

    private fun showLoading(show: Boolean) {
        _uiState.update { it.copy(isLoading = show) }
    }

    fun onAddQuotation(onSuccessQuotation: () -> Unit) {
        viewModelScope.launch {
            showLoading(true)
            val result = withContext(Dispatchers.IO) {
                firebaseDataBaseService.newQuotation(clientName = _uiState.value.clientName)
            }
            if (result) {
                onSuccessQuotation()
            } else {
                _uiState.update {
                    it.copy(error = "An error has been occurred")
                }
                showLoading(false)
            }
        }
    }

    data class AddQuotationUIState(
        val clientName: String = "",
        val isLoading: Boolean = false,
        val error: String? = null
    ) {
        fun isValidQuotation() = clientName.isNotBlank()
    }
}