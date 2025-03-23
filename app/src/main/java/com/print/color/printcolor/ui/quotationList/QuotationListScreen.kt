package com.print.color.printcolor.ui.quotationList

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.domain.model.QuotationStep
import com.print.color.printcolor.ui.components.BottomSheet.PcsBottomSheet
import com.print.color.printcolor.ui.components.BottomSheet.model.ModalBottomSheetData
import com.print.color.printcolor.ui.components.ButtonTheme.ButtonData
import com.print.color.printcolor.ui.components.ButtonTheme.ButtonType
import com.print.color.printcolor.ui.components.ButtonTheme.PcsButton
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldType.OUTLINED
import com.print.color.printcolor.ui.theme.PrintColorTheme
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP1_ID
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY1
import com.print.color.printcolor.utils.getIconForStep
import com.print.color.printcolor.utils.getQuotationStepList
import kotlin.collections.chunked
import kotlin.collections.forEach


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

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedQuotation by remember { mutableStateOf<Quotation?>(null) }

    PrintColorTheme {
        Scaffold { contentPading ->
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        PcsTextField(
                            data = TextFieldData(
                                textFieldType = OUTLINED,
                                label = "",
                                placeHolder = stringResource(R.string.quotation_list_screen_search_bar),
                                keyboardType = KeyboardType.Text,
                                leadingIcon = painterResource(R.drawable.ic_pcs_search)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { searchBarText = it },
                            value = searchBarText,
                            imeAction = ImeAction.Search
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(
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
                /** Quotation List Steps */
                QuotationListSteps(
                    quotationListViewModel = quotationListViewModel,
                    quotationStepList = getQuotationStepList()
                )
                /** Quotation List */
                QuotationList(
                    uiState.isLoading,
                    uiState.quotations,
                    quotationIcon = R.drawable.ic_pc_logo,
                    searchBarText = searchBarText,
                    onQuotationClick = { quotation ->
                        selectedQuotation = quotation
                        showBottomSheet = true
                    })
                if (showBottomSheet && selectedQuotation != null) {
                    PcsBottomSheet(
                        modifier = modifier,
                        sheetContent = {
                            BottomSheetContent(
                                quotation = selectedQuotation,
                                quotationListViewModel = quotationListViewModel
                            )
                        },
                        modalBottomSheetData = ModalBottomSheetData(title = "Quotation Details"),
                        showBottomSheet = showBottomSheet,
                        onDismiss = { showBottomSheet = false }
                    )
                }
            }
        }
    }
}

/** Quotation List */
@Composable
fun QuotationList(
    isLoading: Boolean,
    quotations: List<Quotation>,
    searchBarText: String,
    quotationIcon: Int,
    onQuotationClick: (Quotation) -> Unit
) {
    val filteredData = quotations.filter { it.id.contains(searchBarText, ignoreCase = true) }

    if (isLoading) {
        Log.d("QuotationList", "Loading...")
        CircularProgressIndicator()
    } else {
        Log.d("QuotationList", "Loaded")
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
                        QuotationListItem(
                            quotation = quotation,
                            quotationIcon = quotationIcon,
                            onQuotationClick = {
                                onQuotationClick(quotation)
                            }
                        )
                    }
                    if (rowItems.size < 2) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

/** BottomSheet Content */
@Composable
fun BottomSheetContent(quotation: Quotation?, quotationListViewModel: QuotationListViewModel) {
    quotationListViewModel.getQuotationSteps(quotation?.quotationStepsId.orEmpty())

    val uiState by quotationListViewModel.uiState.collectAsState()

    LaunchedEffect(quotation?.quotationStepsId) {
        quotation?.quotationStepsId?.let { quotationListViewModel.getQuotationSteps(it) }
        Log.d("BottomSheetContent", "QuotationStepsId: ${quotation?.quotationStepsId}")
    }

    Log.d("BottomSheetContent", "QuotationSteps: ${uiState.quotationSteps}")

    PrintColorTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            if (quotation?.isBillRequired == true)
                ContentBillRequired(quotation = quotation)
            else
                NonContentBillRequired(quotation = quotation)
            val quotationStepList = uiState.quotationSteps?.steps?.map { step ->
                QuotationStep(
                    id = step.stepKey,
                    stepKey = step.stepKey,
                    stepValue = step.stepValue,
                    quotationIcon = getIconForStep(step.stepKey)
                )
            } ?: emptyList()

            QuotationListSteps(
                quotationStepList = quotationStepList,
                quotationSteps = uiState.quotationSteps,
                quotationListViewModel = quotationListViewModel
            )
            PcsButton(
                onClick = {
                },
                data = ButtonData(
                    label = "Delete Quotation",
                    type = ButtonType.OUTLINED,
                    contentDescription = "Content Description",
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}


@Composable
fun ContentBillRequired(quotation: Quotation?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_id,
                    quotation?.id.orEmpty(),
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_client,
                    quotation?.clientName.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_customer,
                    quotation?.customerName.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_is_bill_required,
                    quotation?.isBillRequired ?: false
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_tax_regime,
                    quotation?.taxRegime.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_rfc,
                    quotation?.rfc.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_address,
                    quotation?.address.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_zip_code,
                    quotation?.zipCode.orEmpty()
                )
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_state,
                    quotation?.state.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_municipality,
                    quotation?.municipality.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_cfdi,
                    quotation?.cfdi.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_email,
                    quotation?.email.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_payment_method,
                    quotation?.paymentMethod.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_contact,
                    quotation?.contact.orEmpty()
                )
            )
            Text(
                text = stringResource(
                    R.string.quotation_list_bottom_sheet_details_extra_data,
                    quotation?.extraData.orEmpty()
                )
            )
        }
    }
}

@Composable
fun NonContentBillRequired(quotation: Quotation?) {
    Text(
        text = stringResource(
            R.string.quotation_list_bottom_sheet_details_id,
            quotation?.id.orEmpty()
        )
    )
    Text(
        text = stringResource(
            R.string.quotation_list_bottom_sheet_details_client,
            quotation?.clientName.orEmpty()
        )
    )
    Text(
        text = stringResource(
            R.string.quotation_list_bottom_sheet_details_customer,
            quotation?.customerName.orEmpty()
        )
    )
    Text(
        text = stringResource(
            R.string.quotation_list_bottom_sheet_details_contact,
            quotation?.contact.orEmpty()
        )
    )
    Text(
        text = stringResource(
            R.string.quotation_list_bottom_sheet_details_extra_data,
            quotation?.extraData.orEmpty()
        )
    )
}