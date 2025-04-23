package com.print.color.printcolor.ui.quotationList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.domain.model.QuotationStep
import com.print.color.printcolor.ui.components.AlertDialog.PcSAlertDialog
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData.AlertDialogType
import com.print.color.printcolor.ui.components.ButtonTheme.PcsButton
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonThemeDefaultVariants
import com.print.color.printcolor.ui.components.IconClickButton.PcsIconClickButton
import com.print.color.printcolor.ui.components.IconClickButton.model.IconClickButtonDefaultVariants
import com.print.color.printcolor.utils.formatPhoneNumber
import com.print.color.printcolor.utils.getIconForStep

@Composable
fun QuotationDetailsContent(
    modifier: Modifier = Modifier,
    quotation: Quotation?,
    quotationListViewModel: QuotationListViewModel
) {
    /** uiState of the viewModel. */
    val uiState by quotationListViewModel.uiState.collectAsState()

    /** Val to handle the show alertDialog state. */
    var showDeleteAlertDialog by remember { mutableStateOf(false) }

    /** Val to handle the quotationList of the QuotationStep component. */
    val quotationStepList = uiState.quotationSteps?.steps?.map { step ->
        QuotationStep(
            id = step.stepKey,
            stepKey = step.stepKey,
            stepValue = step.stepValue,
            quotationIcon = getIconForStep(step.stepKey)
        )
    } ?: emptyList()

    Column(modifier = Modifier.then(modifier)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            QuotationDetailsLeftContent(
                quotationId = quotation?.id.orEmpty(),
                quotationCustomerName = quotation?.customerName.orEmpty(),
                quotationClientName = quotation?.clientName.orEmpty()
            )
            QuotationDetailsRightContent(quotationContact = quotation?.contact.orEmpty())
        }
        /** BillRequired. */
        if (quotation?.isBillRequired == true) {
            Text(
                fontWeight = FontWeight.SemiBold,
                text = stringResource(R.string.quotation_list_bottom_sheet_details_bill_details_text)
            )
            BillRequiredContent(quotation = quotation)
        }
        /** ExtraData content text. */
        ExtraDataContent(quotationExtraData = quotation?.extraData.orEmpty())

        /** QuotationList Steps. */
        QuotationListSteps(
            quotationStepList = quotationStepList,
            quotationSteps = uiState.quotationSteps,
            quotationListViewModel = quotationListViewModel,
            isEditable = true
        )

        /** Delete Quotation Button. */
        PcsButton(
            onClick = {
                showDeleteAlertDialog = true
            },
            data = ButtonThemeDefaultVariants.buttonDataWithIcon(
                label = stringResource(R.string.quotation_list_bottom_sheet_details_delete_quotation_button),
                type = ButtonType.OUTLINED,
                contentDescription = stringResource(R.string.quotation_list_bottom_sheet_details_delete_quotation_button),
                icon = painterResource(R.drawable.ic_pcs_delete)
            ),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        )
    }
    /** Delete Quotation Alert Dialog Confirmation. */
    if (showDeleteAlertDialog) {
        PcSAlertDialog(
            data = AlertDialogData(
                title = stringResource(R.string.quotation_list_bottom_sheet_details_delete_alert_dialog_title),
                message = stringResource(R.string.quotation_list_bottom_sheet_details_delete_alert_dialog_message),
                confirmButtonText = stringResource(R.string.alert_dialog_confirm_button_text),
                dismissButtonText = stringResource(R.string.alert_dialog_dismiss_button_text),
                onConfirm = {
                    quotationListViewModel.deleteQuotation(quotation?.id.orEmpty())
                    showDeleteAlertDialog = false
                },
                onDismiss = { showDeleteAlertDialog = false },
                dismissOnClickOutside = true,
                type = AlertDialogType.CONFIRMATION,
                lottieAnimation = R.raw.pcs_success_anim,
            ),
            modifier = Modifier,
            autoPlayAnimation = true,
            animationRepeatCount = 1,
        )
    }
}

/** Private fun to handle details of the Quotation as a id, customer and client. */
@Composable
private fun RowScope.QuotationDetailsLeftContent(
    quotationId: String,
    quotationCustomerName: String,
    quotationClientName: String
) {
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            fontWeight = FontWeight.SemiBold,
            text = stringResource(
                R.string.quotation_item_list_annotation_id_text,
                quotationId
            )
        )
        Text(
            text = stringResource(
                R.string.quotation_item_list_annotation_customer_name_text,
                quotationCustomerName
            )
        )
        Text(
            text = stringResource(
                R.string.quotation_item_list_annotation_client_text,
                quotationClientName
            )
        )
    }
}

/** Private fun to handle details of the Quotation as a contact, date and avatar. */
@Composable
private fun RowScope.QuotationDetailsRightContent(
    quotationContact: String,
) {
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_pcs_phone),
                contentDescription = "phone icon",
                tint = MaterialTheme.colorScheme.onTertiary
            )
            Text(formatPhoneNumber(quotationContact))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_pcs_calendar),
                contentDescription = "calendar icon",
                tint = MaterialTheme.colorScheme.onTertiary
            )
            Text(text = stringResource(R.string.quotation_item_list_annotation_created_date_text, "01/01/2025"))
        }
        Avatar() // TODO make this a reusable component
    }
}

/** Private fun to handle extra data content. */
@Composable
private fun ExtraDataContent(quotationExtraData: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(R.drawable.ic_pcs_extra_data),
            contentDescription = "extra data icon",
            tint = MaterialTheme.colorScheme.onTertiary
        )
        Text(
            text = stringResource(
                R.string.quotation_item_list_annotation_extra_data_text,
                quotationExtraData
            )
        )
    }
}

@Preview(showBackground = true) // TODO move this in a reusable component
@Composable
fun Avatar(modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "Cretated by: David Glez"
        )
        PcsIconClickButton(
            modifier = Modifier,
            data = IconClickButtonDefaultVariants.iconClickButtonDefault(
                icon = painterResource(R.drawable.ic_pc_logo),
                contentDescription = "Close Bottom Sheet"
            ), onClick = { })
    }
}