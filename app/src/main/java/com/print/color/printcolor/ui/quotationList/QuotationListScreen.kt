package com.print.color.printcolor.ui.quotationList

import android.util.Log
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldType.OUTLINED
import com.print.color.printcolor.ui.theme.PrintColorTheme
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
                        leadingIcon = painterResource(R.drawable.ic_pcs_search)
                    ),
                    modifier = Modifier,
                    onValueChange = { searchBarText = it },
                    value = searchBarText,
                    imeAction = ImeAction.Search
                )
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
            QuotationListSteps() // Quotation List Steps
            /*val filteredData = uiState.quotations.filter { it.id.contains(searchBarText, ignoreCase = true) }
            if (uiState.isLoading) {
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
                                QuotationListItem(quotation = quotation)
                            }
                            if (rowItems.size < 2) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }*/
            QuotationList(uiState.isLoading, uiState.quotations, searchBarText) // Quotation List
        }
    }
}

/** Quotation List */
@Composable
fun QuotationList(isLoading: Boolean, quotations: List<Quotation>, searchBarText: String) {
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
                        QuotationListItem(quotation = quotation)
                    }
                    if (rowItems.size < 2) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}