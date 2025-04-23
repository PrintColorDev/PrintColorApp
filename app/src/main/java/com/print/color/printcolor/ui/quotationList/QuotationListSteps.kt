package com.print.color.printcolor.ui.quotationList

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.domain.model.QuotationSteps
import com.print.color.printcolor.ui.components.ToolTip.PcSRichTooltip
import com.print.color.printcolor.ui.components.ToolTip.model.ToolTipData
import com.print.color.printcolor.ui.theme.PrintColorTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.print.color.printcolor.domain.model.QuotationStep
import com.print.color.printcolor.ui.components.AlertDialog.PcSAlertDialog
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData.AlertDialogType
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY1
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY2
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY3
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY4
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY5
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY6
import com.print.color.printcolor.utils.PcsStepDivider
import com.print.color.printcolor.utils.getStringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotationListSteps(
    modifier: Modifier = Modifier,
    quotationStepList: List<QuotationStep> = emptyList(),
    quotationSteps: QuotationSteps? = null,
    quotationListViewModel: QuotationListViewModel,
    isEditable: Boolean = false
) {
    var showAlertDialog by remember { mutableStateOf(false) }
    var selectedStepKey by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    PrintColorTheme {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = modifier
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                quotationStepList.forEachIndexed { index, quotationStep ->
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PcSStepImage(
                            icon = quotationStep.quotationIcon,
                            contentDescription = "",
                            isCompleted = quotationStep.stepValue,
                            onClick = {
                                if (isEditable) {
                                    if (quotationListViewModel.updateQuotationStepValidation(
                                            quotationStep.stepKey
                                        )
                                    ) {
                                        selectedStepKey = quotationStep.stepKey
                                        showAlertDialog = true
                                    } else {
                                        Toast.makeText(
                                            context,
                                            R.string.quotation_item_list_step_validation_toast,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        )
                        Text(correctStepName(context = context, stepKey = quotationStep.stepKey))
                    }
                    if (index < quotationStepList.size - 1) {
                        PcsStepDivider()
                    }
                }
            }
        }
        if (showAlertDialog) {
            PcSAlertDialog(
                data = AlertDialogData(
                    title = stringResource(R.string.quotation_list_bottom_sheet_details_alert_dialog_title),
                    message = stringResource(R.string.quotation_list_bottom_sheet_details_alert_dialog_message),
                    confirmButtonText = stringResource(R.string.alert_dialog_confirm_button_text),
                    dismissButtonText = stringResource(R.string.alert_dialog_dismiss_button_text),
                    onConfirm = {
                        selectedStepKey?.let { stepKey ->
                            val step = quotationSteps?.steps?.find { it.stepKey == stepKey }
                            val newValue = step?.stepValue != true

                            quotationListViewModel.updateQuotationStep(
                                quotationStepId = quotationSteps?.id,
                                stepKey = stepKey,
                                newValue = newValue
                            )
                        }
                        showAlertDialog = false
                    },
                    onDismiss = { showAlertDialog = false },
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
}

/** Private fun to get the correct step description in base of step key */
private fun correctStepName(context: Context, stepKey: String) = when (stepKey) {
    CONST_QUOTATION_STEP_KEY1 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_title_step1
    )

    CONST_QUOTATION_STEP_KEY2 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_title_step2
    )

    CONST_QUOTATION_STEP_KEY3 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_title_step3
    )

    CONST_QUOTATION_STEP_KEY4 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_title_step4
    )

    CONST_QUOTATION_STEP_KEY5 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_title_step5
    )

    CONST_QUOTATION_STEP_KEY6 -> getStringResource(
        context = context,
        stringResId = R.string.quotation_list_screen_tooltip_title_step6
    )

    else -> stepKey
}