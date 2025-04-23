package com.print.color.printcolor.ui.quotationList

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
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
import com.print.color.printcolor.ui.components.BottomSheet.PcsBottomSheet
import com.print.color.printcolor.ui.components.BottomSheet.model.ModalBottomSheetData
import com.print.color.printcolor.ui.components.ChipsTheme.PcSChip
import com.print.color.printcolor.ui.components.ChipsTheme.model.ChipData
import com.print.color.printcolor.ui.components.ChipsTheme.model.ChipsDefaultVariants
import com.print.color.printcolor.ui.components.ChipsTheme.rememberCheckState
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldDefaultVariants
import com.print.color.printcolor.ui.theme.PrintColorTheme
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY6
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

    val context = LocalContext.current

    /** Val to handle the check state of the chip list. */
    val checkState: MutableState<Boolean> = rememberCheckState()
    var selectedChipIndex by remember { mutableStateOf<Int?>(null) }

    val chipList = getChipFilterList(isSelected = checkState.value)
    val defaultChipIndex = chipList.indexOfFirst {
        it.text == getStringResource(context, R.string.quotation_list_screen_chip_filter_current)
    }

    /** Val to handle the state of the chips selections to current every time that the screens was launched*/
    var filteredQuotations by remember { mutableStateOf<List<Quotation>>(emptyList()) }

    LaunchedEffect(uiState.quotations, selectedChipIndex) {
        val selectedText = chipList.getOrNull(selectedChipIndex ?: defaultChipIndex)?.text.orEmpty()
        filteredQuotations = handleChipClick(
            context = context,
            chipText = selectedText,
            quotations = uiState.quotations
        )
    }

    /** LauncherEffect to handle the chip current state selection */
    LaunchedEffect(uiState.quotations) {
        if (selectedChipIndex == null && defaultChipIndex != -1) {
            selectedChipIndex = defaultChipIndex
            filteredQuotations = handleChipClick(
                context = context,
                chipText = chipList[defaultChipIndex].text,
                quotations = uiState.quotations
            )
        }
    }

    PrintColorTheme {
        Scaffold { contentPading ->
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxSize()
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    chipList.forEachIndexed { index, chipData ->
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
                    }, isRefreshing = uiState.isLoading,
                    onRefresh = {
                        Log.d("onRefresh", "onRefresh")
                        quotationListViewModel.getQuotations()
                    }
                )
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotationList(
    isLoading: Boolean,
    quotations: List<Quotation>,
    searchBarText: String,
    onQuotationClick: (Quotation) -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    val pullRefreshState = rememberPullToRefreshState()
    val filteredData = quotations
        .filter { it.id.contains(searchBarText, ignoreCase = true) }
    /*PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onRefresh() },
        modifier = Modifier,
        state = pullRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                state = pullRefreshState
            )
        }
    ) {*/
    if (isLoading && quotations.isEmpty()) {
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
    //}
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
            QuotationDetailsContent(
                quotation = quotation,
                quotationListViewModel = quotationListViewModel
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
            quotations.filter { it.deleted }
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_completed
        ).lowercase() -> {
            quotations.filter { it.currentStep == CONST_QUOTATION_STEP_KEY6 }
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_by_date
        ).lowercase() -> {
            emptyList()
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_by_status
        ).lowercase() -> {
            quotations.sortedBy { it.status }
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_created_by
        ).lowercase() -> {
            quotations.filter { it.deleted }
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_all
        ).lowercase() -> {
            quotations
        }

        getStringResource(
            context,
            R.string.quotation_list_screen_chip_filter_current
        ).lowercase() -> {
            quotations.filter { !it.deleted && it.currentStep != CONST_QUOTATION_STEP_KEY6 }
        }

        else -> {
            emptyList()
        }
    }
    return filteredData
}