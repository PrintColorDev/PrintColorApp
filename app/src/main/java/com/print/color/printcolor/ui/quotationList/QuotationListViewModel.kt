package com.print.color.printcolor.ui.quotationList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.print.color.printcolor.data.network.FirebaseDataBaseService
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.domain.model.QuotationSteps
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class QuotationListViewModel @Inject constructor(private val firebaseDataBaseService: FirebaseDataBaseService) :
    ViewModel() {

    private var _uiState: MutableStateFlow<QuotationListUIState> =
        MutableStateFlow(QuotationListUIState())
    val uiState: StateFlow<QuotationListUIState> = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val response = withContext(Dispatchers.IO) {
                firebaseDataBaseService.getAllProducts()
            }
            _uiState.update { it.copy(quotations = response) }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun getQuotationSteps(quotationStepsId: String) {
        viewModelScope.launch {
            //_uiState.update { it.copy(isLoading = true) }
            val response = withContext(Dispatchers.IO) {
                firebaseDataBaseService.getQuotationSteps(quotationStepsId)
            }
            _uiState.update { it.copy(quotationSteps = response) }
        }
    }

    /** Fun to update a stepValur in the stepList */
    fun updateQuotationStep(quotationStepId: String?, stepKey: String, newValue: Boolean) {
        val correctStepKey = when (stepKey) {
            "step_one" -> "step1"
            "step_two" -> "step2"
            "step_three" -> "step3"
            "step_four" -> "step4"
            "step_five" -> "step5"
            "step_six" -> "step6"
            else -> stepKey
        } //TODO update this logic in a utils fun

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                firebaseDataBaseService.updateStep(quotationStepId, correctStepKey, newValue)
                firebaseDataBaseService.updateCurrentStep(
                    quotationStepId.toString(),
                    correctStepKey
                )
            }
            _uiState.update { currentState ->
                currentState.copy(
                    quotationSteps = currentState.quotationSteps?.copy(
                        steps = currentState.quotationSteps?.steps?.map {
                            if (it.stepKey == stepKey) it.copy(stepValue = newValue) else it
                        } ?: emptyList()
                    )
                )
            }
        }
    }

    /** Update current step validation */
    fun updateQuotationStepValidation(stepKey: String): Boolean {
        val steps = _uiState.value.quotationSteps?.steps ?: return false

        val currentIndex = steps.indexOfFirst { it.stepKey == stepKey }
        if (currentIndex == -1) return false

        if (currentIndex == 0) return true

        val previousStep = steps.getOrNull(currentIndex - 1) ?: return false

        return previousStep.stepValue
    }
}

data class QuotationListUIState(
    val isLoading: Boolean = false,
    val quotations: List<Quotation> = emptyList(),
    val quotationSteps: QuotationSteps? = null
)