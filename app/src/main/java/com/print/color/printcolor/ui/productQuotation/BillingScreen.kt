package com.print.color.printcolor.ui.productQuotation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldType.OUTLINED
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldType.OUTLINED_LIST
import com.print.color.printcolor.R


@Composable
fun BillingScreenFields(productQuotationViewModel: ProductQuotationViewModel) {

    val uiState by productQuotationViewModel.uiState.collectAsState()

    var taxRegime = uiState.taxRegime
    var rfc = uiState.rfc
    var address = uiState.address
    var zipCode = uiState.zipCode.toString()
    var state = uiState.state
    var municipality = uiState.municipality
    var cfdi = uiState.cfdi
    var email = uiState.email
    var paymentMethod = uiState.paymentMethod

    Column(
        modifier = Modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        /** TextField Tax Regime */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_tax_regime),
                keyboardType = KeyboardType.Number,
                leadingIcon = Icons.Filled.AccountCircle,
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onTaxRegimeChanged(it) },
            value = taxRegime,
            maxLength = 3
        )
        /** TextField RFC */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_rfc),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onRFCChanged(it) },
            value = rfc,
            maxLength = 13
        )
        /** TextField Address */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_address),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Outlined.Home
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                productQuotationViewModel.onAddressChanged(it)
                address = it
            },
            value = address
        )
        /** TextField Zip Code */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_zip_code),
                keyboardType = KeyboardType.Number,
                leadingIcon = Icons.Outlined.Place
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onZipCodeChanged(it) },
            value = zipCode,
            maxLength = 5
        )
        /** TextField State */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_state),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onStateChanged(it) },
            value = state
        )
        /** TextField CFDI */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_cfdi),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onCFDIChanged(it) },
            value = cfdi,
            maxLength = 3
        )
        /** TextField Payment Method */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED_LIST,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_payment_method),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle,
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onPaymentMethodChanged(it) },
            value = paymentMethod,
            options = stringArrayResource(R.array.quotation_screen_billing_payment_method_list).toList()
        )
        /** TextField Municipality */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_municipality),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.ShoppingCart
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onMunicipalityChanged(it) },
            value = municipality
        )

        /** TextField Email */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_email),
                keyboardType = KeyboardType.Email,
                leadingIcon = Icons.Outlined.Email
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onEmailChanged(it) },
            value = email
        )
    }
}