package com.print.color.printcolor.ui.productQuotation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldType.OUTLINED
import com.print.color.printcolor.R


@Composable
fun BillingScreenFields(productQuotationViewModel: ProductQuotationViewModel) {

    var taxRegime by remember { mutableStateOf("") }
    var rfc by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var municipality by remember { mutableStateOf("") }
    var cfdi by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("") }

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
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                productQuotationViewModel.onTaxRegimeChanged(it)
                taxRegime = it
            },
            value = taxRegime
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
            onValueChange = {
                productQuotationViewModel.onRFCChanged(it)
                rfc = it
            },
            value = rfc
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
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Outlined.Place
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                productQuotationViewModel.onZipCodeChanged(it)
                zipCode = it
            },
            value = zipCode
        )
        /** TextField State */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_zip_code),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                productQuotationViewModel.onStateChanged(it)
                state = it
            },
            value = state
        )
        /** TextField CFDI */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_zip_code),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                productQuotationViewModel.onCFDIChanged(it)
                cfdi = it
            },
            value = cfdi
        )
        /** TextField Payment Method */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_payment_method),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                productQuotationViewModel.onPaymentMethodChanged(it)
                paymentMethod = it
            },
            value = paymentMethod
        )
        /** TextField Municipality */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_municipality),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                productQuotationViewModel.onMunicipalityChanged(it)
                municipality = it
            },
            value = municipality
        )

        /** TextField Email */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_email),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Outlined.Email
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                productQuotationViewModel.onEmailChanged(it)
                email = it
            },
            value = email
        )
    }
}