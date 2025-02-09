package com.print.color.printcolor.ui.quotationList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldType.OUTLINED
import com.print.color.printcolor.ui.components.ToolTip.PcSRichTooltip
import com.print.color.printcolor.ui.components.ToolTip.model.ToolTipData
import com.print.color.printcolor.ui.theme.PrintColorTheme


/*@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=1280dp,height=800dp,dpi=240",
    locale = "es"
)*/
@Composable
fun QuotationListScreen(
    quotationListViewModel: QuotationListViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by quotationListViewModel.uiState.collectAsState()
    var searchBarText by remember { mutableStateOf("") }

    PrintColorTheme {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PcsTextField(
                    data = TextFieldData(
                        textFieldType = OUTLINED,
                        label = "",
                        placeHolder = stringResource(R.string.quotation_list_screen_search_bar),
                        keyboardType = KeyboardType.Text,
                        leadingIcon = Icons.Rounded.Search
                    ),
                    modifier = Modifier.weight(1f),
                    onValueChange = { searchBarText = it },
                    value = searchBarText
                )
                IconButton(onClick = {}) {
                    Icon(
                        modifier = modifier,
                        painter = painterResource(R.drawable.ic_pcs_mode_list),
                        contentDescription = "view mode list",
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        modifier = modifier,
                        painter = painterResource(R.drawable.ic_pcs_filter),
                        contentDescription = "Filter",
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
            PcSSteps()
            QuotationList(uiState.isLoading, uiState.quotations, searchBarText)
        }
    }
}

//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PcSSteps(modifier: Modifier = Modifier) {
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


@Composable
fun QuotationList(isLoading: Boolean, quotations: List<Quotation>, searchBarText: String) {
    val filteredData = quotations.filter { it.id.contains(searchBarText, ignoreCase = true) }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            items(filteredData.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowItems.forEach { quotation ->
                        Card(
                            modifier = Modifier
                                .padding(all = 8.dp)
                                .border(
                                    BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .weight(1f),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 16.dp)) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("ID: ${quotation.id}", fontWeight = FontWeight.Bold)
                                    Text("Contact: ${quotation.contact}")
                                    Text("Client: ${quotation.clientName}")
                                }
                                PcSStepImage(
                                    icon = R.drawable.ic_pcs_survey,
                                    contentDescription = "",
                                    modifier = Modifier.align(
                                        Alignment.CenterVertically
                                    )
                                )
                            }
                        }
                    }
                    if (rowItems.size < 2) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}