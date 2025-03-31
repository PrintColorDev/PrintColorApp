package com.print.color.printcolor.ui.productQuotation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData.TextFieldType.OUTLINED
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData.TextFieldType.OUTLINED_LIST
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldDefaultVariants


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
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        /** TextField Tax Regime */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_tax_regime),
                keyboardType = KeyboardType.Number,
                leadingIcon = painterResource(R.drawable.ic_pcs_account_balance),
                isTextCountRequired = true
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onTaxRegimeChanged(it) },
            value = taxRegime,
            maxLength = 3,
            imeAction = ImeAction.Next
        )
        /** TextField RFC */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_rfc),
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(R.drawable.ic_pcs_verified),
                isTextCountRequired = true
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onRFCChanged(it) },
            value = rfc,
            maxLength = 13,
            imeAction = ImeAction.Next
        )
        /** TextField Address */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_address),
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(R.drawable.ic_pcs_home_filled)
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                productQuotationViewModel.onAddressChanged(it)
                address = it
            },
            value = address,
            imeAction = ImeAction.Next
        )
        /** TextField Zip Code */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_zip_code),
                keyboardType = KeyboardType.Number,
                leadingIcon = painterResource(R.drawable.ic_pcs_home_pin),
                isTextCountRequired = true
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onZipCodeChanged(it) },
            value = zipCode,
            maxLength = 5,
            imeAction = ImeAction.Next
        )
        /** TextField State */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_state),
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(R.drawable.ic_pcs_globe_location_pin)
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onStateChanged(it) },
            value = state,
            imeAction = ImeAction.Next
        )
        /** TextField CFDI */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_cfdi),
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(R.drawable.ic_pcs_receipt),
                isTextCountRequired = true
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onCFDIChanged(it) },
            value = cfdi,
            maxLength = 3,
            imeAction = ImeAction.Next
        )
        /** TextField Payment Method */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_payment_method),
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(R.drawable.ic_pcs_payments),
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onPaymentMethodChanged(it) },
            value = paymentMethod,
            options = stringArrayResource(R.array.quotation_screen_billing_payment_method_list).toList()
        )
        /** TextField Municipality */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_municipality),
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(R.drawable.ic_pcs_map)
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onMunicipalityChanged(it) },
            value = municipality,
            imeAction = ImeAction.Next
        )

        /** TextField Email */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_email),
                keyboardType = KeyboardType.Email,
                leadingIcon = painterResource(R.drawable.ic_pcs_mail)
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onEmailChanged(it) },
            value = email,
            imeAction = ImeAction.Done
        )
    }
}