package com.print.color.printcolor.ui.quotationList

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotationListSteps(
    modifier: Modifier = Modifier,
    quotationStepList: List<QuotationStep> = emptyList(),
    quotationSteps: QuotationSteps? = null,
    quotationListViewModel: QuotationListViewModel
) {
    /** region variables */
    var showAlertDialog by remember { mutableStateOf(false) }
    var selectedStepKey by remember { mutableStateOf<String?>(null) }
    var currentStepValue by remember { mutableStateOf<Boolean?>(null) }
    val context = LocalContext.current
    /** endregion variables */

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
                                if (quotationListViewModel.updateQuotationStepValidation(
                                        quotationStep.stepKey
                                    )
                                ) {
                                    selectedStepKey = quotationStep.stepKey
                                    showAlertDialog = true
                                } else {
                                    Toast.makeText(
                                        context,
                                        "No puedes actualizar este paso sin antes haber completado el paso anterior, revisa el flujo antes de continuar",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                        PcSRichTooltip(
                            modifier = modifier,
                            data = ToolTipData(
                                toolTipTitle = stringResource(
                                    R.string.quotation_list_bottom_sheet_details_text_step,
                                    quotationStep.stepValue
                                ),
                                toolTipDescription = stringResource(R.string.quotation_list_screen_tooltip_description_step1),
                                toolTipActionText = stringResource(
                                    R.string.quotation_list_bottom_sheet_details_text_step,
                                    index
                                )
                            )
                        )
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
                            quotationListViewModel.updateQuotationStep(
                                quotationStepId = quotationSteps?.id,
                                stepKey = stepKey,
                                newValue = true
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

@Composable
fun PcsStepDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .size(height = 2.dp, width = 50.dp),
        color = Color.Black
    )
}