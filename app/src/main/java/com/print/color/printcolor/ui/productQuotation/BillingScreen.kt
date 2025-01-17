package com.print.color.printcolor.ui.productQuotation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
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
fun BillingScreenFields() {
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

        /** Tax Regime */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_tax_regime),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { taxRegime = it },
            value = taxRegime
        )
        /** RFC */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_rfc),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { rfc = it },
            value = rfc
        )
        /** Address */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_address),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { address = it },
            value = address
        )
        /** Zip Code */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_zip_code),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { zipCode = it },
            value = zipCode
        )
        /** State */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_zip_code),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { state = it },
            value = state
        )
        /** CFDI */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_zip_code),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { cfdi = it },
            value = cfdi
        )
        /** Payment Method */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_payment_method),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { paymentMethod = it },
            value = paymentMethod
        )
        /** Municipality */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_municipality),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { municipality = it },
            value = municipality
        )

        /** Email */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "Label",
                placeHolder = stringResource(R.string.quotation_screen_billing_email),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { email = it },
            value = email
        )
    }
}