package com.print.color.printcolor.ui.productQuotation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.AlertDialog.PcSAlertDialog
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData.AlertDialogType
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogDefaultVariants
import com.print.color.printcolor.ui.components.ButtonTheme.PcsButton
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonThemeDefaultVariants
import com.print.color.printcolor.ui.components.SwitchTheme.PcSSwitch
import com.print.color.printcolor.ui.components.SwitchTheme.model.SwitchDefaultVariants
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldDefaultVariants

@Composable
fun QuotationScreen(
    productQuotationViewModel: ProductQuotationViewModel,
    onAddQuotationSave: () -> Unit
) {
    val uiState by productQuotationViewModel.uiState.collectAsState()
    val isButtonEnabled = uiState.isValidQuotation()

    /** Quotation Fields */
    var nameValue = uiState.clientName
    var clientNameValue = uiState.customerName
    var contactValue = uiState.contact
    var extraDataValue = uiState.extraData
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    /*LaunchedEffect(uiState.error) {
        uiState.error?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            productQuotationViewModel._uiState.update { it.copy(error = null) } // Limpia el error
        }
    }*/

    /** Switch Value */
    val switchData =
        SwitchDefaultVariants.switchDefault(
            switchString = stringResource(R.string.quotation_screen_switch_label),
            isChecked = uiState.isBillRequired,
            onCheckedChange = { productQuotationViewModel.isBillingRequired(it) },
            contentDescription = "Switch billing $uiState.isBillingRequired"
        )
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(all = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        /** Top Screen */
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                modifier = Modifier.size(width = 130.dp, height = 110.dp),
                painter = painterResource(R.drawable.ic_pc_logo),
                contentDescription = "Logo"
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleLarge,
                text = stringResource(R.string.quotation_screen_title, "David")
            )
        }
        /** Quotation Fields */
        /** TextField Customer Name*/
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "",
                placeHolder = stringResource(R.string.quotation_screen_company_name),
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(R.drawable.ic_pcs_business_center)
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onClientNameChanged(it) },
            value = nameValue,
            imeAction = ImeAction.Next
        )
        /** TextField Client Name*/
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "",
                placeHolder = stringResource(R.string.quotation_screen_client_name),
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(R.drawable.ic_pcs_client)
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onCustomerNameChanged(it) },
            value = clientNameValue,
            imeAction = ImeAction.Next
        )
        /** TextField Contact */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "",
                placeHolder = stringResource(R.string.quotation_screen_contact),
                keyboardType = KeyboardType.Phone,
                leadingIcon = painterResource(R.drawable.ic_pcs_phone),
                isTextCountRequired = true
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { productQuotationViewModel.onContactChanged(it) },
            value = contactValue,
            maxLength = 10,
            imeAction = ImeAction.Next
        )
        /** TextField Extra Data */
        PcsTextField(
            data = TextFieldDefaultVariants.textFieldOutlined(
                label = "",
                placeHolder = stringResource(R.string.quotation_screen_extra_data),
                keyboardType = KeyboardType.Text,
                leadingIcon = painterResource(R.drawable.ic_pcs_extra_data)
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                productQuotationViewModel.onExtraDataChanged(it)
            },
            maxLength = 200,
            value = extraDataValue,
            imeAction = if (uiState.isBillRequired) {
                ImeAction.Next
            } else {
                ImeAction.Done
            }
        )
        /** Switch Billing validation */
        PcSSwitch(data = switchData, modifier = Modifier.fillMaxWidth())
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        AnimatedVisibility(uiState.isBillRequired) {
            BillingScreenFields(productQuotationViewModel = productQuotationViewModel)
        }
        /** Button save quotation */
        PcsButton(
            isVisible = uiState.isQuotationSaved,
            onClick = {
                productQuotationViewModel.onAddQuotation {
                    productQuotationViewModel.clearFields()
                    onAddQuotationSave()
                    showDialog = true
                }
            },
            data = ButtonThemeDefaultVariants.buttonDefaultData(
                label = stringResource(R.string.quotation_screen_save_quotation),
                type = ButtonType.OUTLINED,
                contentDescription = "Content Description",
                isEnabled = isButtonEnabled
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        if (showDialog) {
            PcSAlertDialog(
                data = AlertDialogDefaultVariants.alertDialogAnimation(
                    title = stringResource(R.string.quotation_screen_alert_dialog_title),
                    message = "",
                    confirmButtonText = stringResource(R.string.alert_dialog_confirm_button_text),
                    dismissButtonText = "",
                    onConfirm = { showDialog = false },
                    dismissOnClickOutside = false,
                    type = AlertDialogType.ANIMATION,
                    onDismiss = {},
                    lottieAnimation = R.raw.pcs_success_anim,
                ),
                autoPlayAnimation = true,
                animationRepeatCount = 1
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=1280dp,height=800dp,dpi=240",
    locale = "es"
)
@Composable
fun QuotationScreenPreview() {
    //QuotationScreen(productQuotationViewModel)
}