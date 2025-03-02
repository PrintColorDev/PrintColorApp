package com.print.color.printcolor.ui.quotationList

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.ToolTip.PcSRichTooltip
import com.print.color.printcolor.ui.components.ToolTip.model.ToolTipData
import com.print.color.printcolor.ui.theme.PrintColorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotationListSteps(modifier: Modifier = Modifier) {
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
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PcSStepImage(icon = R.drawable.ic_pcs_notes, contentDescription = "")
                    PcSRichTooltip(
                        modifier = modifier,
                        data = ToolTipData(
                            toolTipTitle = stringResource(R.string.quotation_list_screen_tooltip_title_step1),
                            toolTipDescription = stringResource(R.string.quotation_list_screen_tooltip_description_step1),
                            toolTipActionText = stringResource(R.string.quotation_list_screen_tooltip_action_step1)
                        )
                    )
                }
                PcsStepDivider()
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PcSStepImage(icon = R.drawable.ic_pcs_success, contentDescription = "")
                    PcSRichTooltip(
                        data = ToolTipData(
                            toolTipTitle = stringResource(R.string.quotation_list_screen_tooltip_title_step2),
                            toolTipDescription = stringResource(R.string.quotation_list_screen_tooltip_description_step2),
                            toolTipActionText = stringResource(R.string.quotation_list_screen_tooltip_action_step2)
                        )
                    )
                }
                PcsStepDivider()
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PcSStepImage(icon = R.drawable.ic_pcs_design, contentDescription = "")
                    PcSRichTooltip(
                        data = ToolTipData(
                            toolTipTitle = stringResource(R.string.quotation_list_screen_tooltip_title_step3),
                            toolTipDescription = stringResource(R.string.quotation_list_screen_tooltip_description_step3),
                            toolTipActionText = stringResource(R.string.quotation_list_screen_tooltip_action_step3)
                        )
                    )
                }
                PcsStepDivider()
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PcSStepImage(icon = R.drawable.ic_pcs_print, contentDescription = "")
                    PcSRichTooltip(
                        data = ToolTipData(
                            toolTipTitle = stringResource(R.string.quotation_list_screen_tooltip_title_step4),
                            toolTipDescription = stringResource(R.string.quotation_list_screen_tooltip_description_step4),
                            toolTipActionText = stringResource(R.string.quotation_list_screen_tooltip_action_step4)
                        )
                    )
                }
                PcsStepDivider()
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PcSStepImage(icon = R.drawable.ic_pcs_delivery, contentDescription = "")
                    PcSRichTooltip(
                        data = ToolTipData(
                            toolTipTitle = stringResource(R.string.quotation_list_screen_tooltip_title_step5),
                            toolTipDescription = stringResource(R.string.quotation_list_screen_tooltip_description_step5),
                            toolTipActionText = stringResource(R.string.quotation_list_screen_tooltip_action_step5)
                        )
                    )
                }
                PcsStepDivider()
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PcSStepImage(icon = R.drawable.ic_pcs_survey, contentDescription = "")
                    PcSRichTooltip(
                        data = ToolTipData(
                            toolTipTitle = stringResource(R.string.quotation_list_screen_tooltip_title_step6),
                            toolTipDescription = stringResource(R.string.quotation_list_screen_tooltip_description_step6),
                            toolTipActionText = stringResource(R.string.quotation_list_screen_tooltip_action_step6)
                        )
                    )
                }
            }
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