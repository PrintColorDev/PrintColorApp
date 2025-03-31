package com.print.color.printcolor.ui.quotationList

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
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
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.domain.model.QuotationStep
import com.print.color.printcolor.ui.components.AlertDialog.PcSAlertDialog
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData.AlertDialogType
import com.print.color.printcolor.ui.components.BottomSheet.PcsBottomSheet
import com.print.color.printcolor.ui.components.BottomSheet.model.ModalBottomSheetData
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType
import com.print.color.printcolor.ui.components.ButtonTheme.PcsButton
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonThemeDefaultVariants
import com.print.color.printcolor.ui.components.ChipsTheme.PcSChip
import com.print.color.printcolor.ui.components.ChipsTheme.model.ChipData
import com.print.color.printcolor.ui.components.ChipsTheme.model.ChipsDefaultVariants
import com.print.color.printcolor.ui.components.ChipsTheme.rememberCheckState
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldDefaultVariants
import com.print.color.printcolor.ui.theme.PrintColorTheme
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY6
import com.print.color.printcolor.utils.getIconForStep
import com.print.color.printcolor.utils.getQuotationStepList
import com.print.color.printcolor.utils.getStringResource
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
    /** ViewModel call's */
    val uiState by quotationListViewModel.uiState.collectAsState()

    //TODO refac to internal variable
    var searchBarText by remember { mutableStateOf("") }

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedQuotation by remember { mutableStateOf<Quotation?>(null) }

    /** Val to handle the check state of the chip list. */
    val checkState: MutableState<Boolean> = rememberCheckState()
    var selectedChipIndex by remember { mutableStateOf<Int?>(null) }

    val context = LocalContext.current

    var filteredQuotations by remember { mutableStateOf(uiState.quotations) }

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
                            data = TextFieldDefaultVariants.textFieldOutlined(
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    getChipFilterList(
                        isSelected = checkState.value,
                    ).forEachIndexed { index, chipData ->
                        PcSChip(
                            data = chipData,
                            onClick = {
                                selectedChipIndex = index
                                filteredQuotations = handleChipClick(
                                    context = context,
                                    chipText = chipData.text,
                                    quotations = uiState.quotations
                                )
                            },
                            isSelected = selectedChipIndex == index
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
                    isLoading = uiState.isLoading,
                    quotations = filteredQuotations,
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

/** region Quotation List */
@Composable
fun QuotationList(
    isLoading: Boolean,
    quotations: List<Quotation>,
    searchBarText: String,
    onQuotationClick: (Quotation) -> Unit
) {
    val filteredData = quotations
        .filter { it.id.contains(searchBarText, ignoreCase = true) }

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

/** region BottomSheet Content */
@Composable
fun BottomSheetContent(quotation: Quotation?, quotationListViewModel: QuotationListViewModel) {
    quotationListViewModel.getQuotationSteps(quotation?.quotationStepsId.orEmpty())

    val uiState by quotationListViewModel.uiState.collectAsState()
    var showDeleteAlertDialog by remember { mutableStateOf(false) }

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
                    showDeleteAlertDialog = true
                },
                data = ButtonThemeDefaultVariants.buttonDataWithIcon(
                    label = "Delete Quotation",
                    type = ButtonType.TONAL,
                    contentDescription = "Content Description",
                    icon = painterResource(R.drawable.ic_pcs_delete)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            )
        }
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
}

/** Private fun to get chipFilter list. */
@Composable
private fun getChipFilterList(
    isSelected: Boolean
): List<ChipData> {

    val context = LocalContext.current
    val filterList =
        context.resources.getStringArray(R.array.quotation_list_screen_chip_filter_list)

    return filterList.map { filterName ->
        ChipsDefaultVariants.chipFilter(
            text = filterName,
            icon = painterResource(R.drawable.ic_pcs_check),
            contentDescription = "Filter Chip: $filterName",
            type = ChipData.ChipType.FILTER_CHIP,
            isSelected = isSelected
        )
    }
}

/** Private fun to handle chip click. */
private fun handleChipClick(
    context: Context, chipText: String,
    quotations: List<Quotation>
): List<Quotation> {

    val normalizedText = chipText.trim().lowercase()
    Log.d("ChipFilter", "Received chipText: '$chipText' | Normalized: '$normalizedText'")
    Log.d("deletedQuotations", quotations.toString())
    val filteredData = when (normalizedText) {
        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_deleted
        ).lowercase() -> {
            Toast.makeText(context, "Showing deleted items", Toast.LENGTH_SHORT).show()
            quotations.filter { it.deleted }
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_completed
        ).lowercase() -> {
            Toast.makeText(context, "Showing completed items", Toast.LENGTH_SHORT).show()
            quotations.filter { it.currentStep == CONST_QUOTATION_STEP_KEY6 }
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_by_date
        ).lowercase() -> {
            Toast.makeText(context, "Filtering by date", Toast.LENGTH_SHORT).show()
            //quotations.sortedBy { it.date }
            emptyList()
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_by_status
        ).lowercase() -> {
            Toast.makeText(context, "Filtering by status", Toast.LENGTH_SHORT).show()
            quotations.sortedBy { it.status }
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_created_by
        ).lowercase() -> {
            Toast.makeText(context, "Filtering by creator", Toast.LENGTH_SHORT).show()
            quotations.filter { it.deleted }
        }

        getStringResource(context, R.string.quotation_list_screen_chip_filter_all).lowercase() -> {
            Toast.makeText(context, "Filtering all", Toast.LENGTH_SHORT).show()
            quotations
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_current
        ).lowercase() -> {
            Toast.makeText(context, "Filtering current", Toast.LENGTH_SHORT).show()
            quotations.filter { !it.deleted }
        }

        else -> {
            Toast.makeText(context, "Unknown filter", Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }
    return filteredData
}


/** region Content Bill Required */
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

/** region Content Non Bill Required */
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