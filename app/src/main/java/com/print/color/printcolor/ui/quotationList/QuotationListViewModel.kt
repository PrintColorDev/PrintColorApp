package com.print.color.printcolor.ui.quotationList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.print.color.printcolor.data.network.FirebaseDataBaseService
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.domain.model.QuotationSteps
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
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
        getAllQuotations()
    }

    fun getQuotations() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            delay(800)
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun getAllQuotations() {
        viewModelScope.launch {
            firebaseDataBaseService.getAllQuotations()
                .onStart { _uiState.update { it.copy(isLoading = true) } }
                .catch { error ->
                    Log.e("QuotationListViewModel", "Error al obtener cotizaciones", error)
                    _uiState.update { it.copy(isLoading = false) }
                }
                .collect { quotations ->
                    _uiState.update { it.copy(quotations = quotations, isLoading = false) }
                }
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

                val newCurrentStepKey = if (!newValue) {
                    val steps = _uiState.value.quotationSteps?.steps ?: emptyList()
                    val updatedSteps = steps.map {
                        if (it.stepKey == stepKey) it.copy(stepValue = false) else it
                    }
                    updatedSteps
                        .filter { it.stepValue }
                        .maxByOrNull { stepIndexOrder(it.stepKey) }
                        ?.let { lastCompleted ->
                            stepKeyToDbKey(lastCompleted.stepKey)
                        }
                } else {
                    correctStepKey
                }

                newCurrentStepKey?.let {
                    firebaseDataBaseService.updateCurrentStep(
                        quotationStepId.toString(),
                        it
                    )
                }
            }

            _uiState.update { currentState ->
                val updatedSteps = currentState.quotationSteps?.steps?.map {
                    if (it.stepKey == stepKey) it.copy(stepValue = newValue) else it
                } ?: emptyList()
                currentState.copy(
                    quotationSteps = currentState.quotationSteps?.copy(
                        steps = updatedSteps
                    )
                )
            }
        }
    }

    private fun stepIndexOrder(stepKey: String): Int {
        return when (stepKey) {
            "step_one" -> 1
            "step_two" -> 2
            "step_three" -> 3
            "step_four" -> 4
            "step_five" -> 5
            "step_six" -> 6
            else -> 0
        }
    }

    private fun stepKeyToDbKey(stepKey: String): String {
        return when (stepKey) {
            "step_one" -> "step1"
            "step_two" -> "step2"
            "step_three" -> "step3"
            "step_four" -> "step4"
            "step_five" -> "step5"
            "step_six" -> "step6"
            else -> stepKey
        }
    }

    /** Update current step validation */
    fun updateQuotationStepValidation(stepKey: String): Boolean {
        val steps = _uiState.value.quotationSteps?.steps ?: return false

        val currentIndex = steps.indexOfFirst { it.stepKey == stepKey }
        if (currentIndex == -1) return false

        val selectedStep = steps[currentIndex]

        // If it is already completed and you want to uncheck, validate the next step
        if (selectedStep.stepValue) {
            val nextStep = steps.getOrNull(currentIndex + 1)
            return nextStep?.stepValue != true // Si el siguiente ya está completo, no se puede retroceder
        } else {
            // Trying to move forward
            if (currentIndex == 0) return true
            val previousStep = steps.getOrNull(currentIndex - 1) ?: return false
            return previousStep.stepValue
        }
    }


    /** Fun to delete a quotation */
    fun deleteQuotation(quotationId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                firebaseDataBaseService.deleteQuotation(quotationId)
            }
        }
    }

}

data class QuotationListUIState(
    val isLoading: Boolean = false,
    val quotations: List<Quotation> = emptyList(),
    val quotationSteps: QuotationSteps? = null
)