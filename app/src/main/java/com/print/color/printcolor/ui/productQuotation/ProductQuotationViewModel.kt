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
class ProductQuotationViewModel @Inject constructor(val firebaseDataBaseService: FirebaseDataBaseService) :
    ViewModel() {

    private val _uiState = MutableStateFlow(AddQuotationUIState())
    val uiState: StateFlow<AddQuotationUIState> = _uiState

    fun onClientNameChanged(name: String) {
        _uiState.update { it.copy(clientName = name.toString()) }
    }

    fun onCustomerNameChanged(customerName: String) {
        _uiState.update { it.copy(customerName = customerName.toString()) }
    }

    fun onContactChanged(contact: String) {
        _uiState.update { it.copy(contact = contact.toString()) }
    }

    fun onExtraDataChanged(extraData: String) {
        _uiState.update { it.copy(extraData = extraData.toString()) }
    }

    fun isBillingRequired(isBillingEnabled: Boolean) {
        _uiState.update { it.copy(isBillingRequired = isBillingEnabled) }
    }

    fun onTaxRegimeChanged(taxRegime: String) {
        _uiState.update { it.copy(taxRegime = taxRegime.toString()) }
    }

    fun onRFCChanged(rfc: String) {
        _uiState.update { it.copy(rfc = rfc.toString()) }
    }

    fun onAddressChanged(address: String) {
        _uiState.update { it.copy(address = address.toString()) }
    }

    fun onZipCodeChanged(zipCode: String) {
        _uiState.update { it.copy(zipCode = zipCode.toString()) }
    }

    fun onStateChanged(state: String) {
        _uiState.update { it.copy(state = state.toString()) }
    }

    fun onMunicipalityChanged(municipality: String) {
        _uiState.update { it.copy(municipality = municipality.toString()) }
    }

    fun onCFDIChanged(cfdi: String) {
        _uiState.update { it.copy(cfdi = cfdi.toString()) }
    }

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email.toString()) }
    }

    fun onPaymentMethodChanged(paymentMethod: String) {
        _uiState.update { it.copy(paymentMethod = paymentMethod.toString()) }
    }

    private fun isQuotationSaved(show: Boolean) {
        _uiState.update { it.copy(isQuotationSaved = show) }
    }

    fun onAddQuotation(onSuccessQuotation: () -> Unit) {
        viewModelScope.launch {
            isQuotationSaved(true)
            val result = withContext(Dispatchers.IO) {
                firebaseDataBaseService.newQuotation(
                    clientName = _uiState.value.clientName,
                    customerName = _uiState.value.customerName,
                    contact = _uiState.value.contact,
                    extraData = _uiState.value.extraData,
                    isBillRequired = _uiState.value.isBillingRequired,
                    taxRegime = _uiState.value.taxRegime,
                    rfc = _uiState.value.rfc,
                    address = _uiState.value.address,
                    zipCode = _uiState.value.zipCode,
                    state = _uiState.value.state,
                    municipality = _uiState.value.municipality,
                    cfdi = _uiState.value.cfdi,
                    email = _uiState.value.email,
                    paymentMethod = _uiState.value.paymentMethod
                )
            }
            if (result) {
                onSuccessQuotation()
            } else {
                _uiState.update {
                    it.copy(error = "An error has been occurred")
                }
                isQuotationSaved(false)
            }
            isQuotationSaved(false)
        }
    }

    fun clearFields() {
        _uiState.update {
            it.copy(
                clientName = "",
                customerName = "",
                contact = "",
                extraData = "",
                isBillingRequired = false,
                taxRegime = "",
                rfc = "",
                address = "",
                zipCode = "",
                state = "",
                municipality = "",
                cfdi = "",
                email = "",
                paymentMethod = ""
            )
        }
    }

    data class AddQuotationUIState(
        val clientName: String = "",
        val customerName: String = "",
        val contact: String = "",
        val extraData: String = "",
        val isBillingRequired: Boolean = false,
        val taxRegime: String = "",
        val rfc: String = "",
        val address: String = "",
        val zipCode: String = "",
        val state: String = "",
        val municipality: String = "",
        val cfdi: String = "",
        val email: String = "",
        val paymentMethod: String = "",
        val isQuotationSaved: Boolean = false,
        val error: String? = null
    ) {
        fun isValidQuotation() =
            if (!isBillingRequired) {
                clientName.isNotBlank() && customerName.isNotBlank() && contact.isNotBlank() && extraData.isNotBlank()
            } else {
                taxRegime.isNotBlank() && rfc.isNotBlank() && address.isNotBlank()
                        && zipCode.isNotBlank() && state.isNotBlank() && municipality.isNotBlank()
                        && cfdi.isNotBlank() && email.isNotBlank() && paymentMethod.isNotBlank()
            }
    }
}