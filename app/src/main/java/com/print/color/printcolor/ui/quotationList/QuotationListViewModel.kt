package com.print.color.printcolor.ui.quotationList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.print.color.printcolor.data.network.FirebaseDataBaseService
import com.print.color.printcolor.domain.model.Quotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class QuotationListViewModel @Inject constructor(private val firebaseDataBaseService: FirebaseDataBaseService) :
    ViewModel() {

    private var _uiState: MutableStateFlow<QuotationListUIState> = MutableStateFlow(QuotationListUIState())
    val uiState: StateFlow<QuotationListUIState> = _uiState

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                firebaseDataBaseService.getAllProducts()
            }
            _uiState.update { it.copy(quotations = response) }
        }
    }
}

data class QuotationListUIState(
    val isLoading: Boolean = false,
    val quotations: List<Quotation> = emptyList()
)