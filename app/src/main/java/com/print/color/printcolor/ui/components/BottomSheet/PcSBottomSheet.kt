package com.print.color.printcolor.ui.components.BottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.ui.components.BottomSheet.model.ModalBottomSheetData
import com.print.color.printcolor.ui.quotationList.BottomSheetContent
import com.print.color.printcolor.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PcsBottomSheet(
    modifier: Modifier = Modifier,
    sheetContent: @Composable () -> Unit,
    modalBottomSheetData: ModalBottomSheetData,
    showBottomSheet: Boolean,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            modifier = Modifier.then(modifier.fillMaxWidth()),
            sheetState = sheetState
        ) {
            Column(Modifier) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = modalBottomSheetData.title,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Box(
                        modifier = Modifier
                            .clickable {
                                onDismiss()
                            }
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.ic_pcs_close),
                            contentDescription = ""
                        )
                    }
                }
                sheetContent()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PcsBottomSheetPreview(modifier: Modifier = Modifier) {

    var showBottomSheet by remember { mutableStateOf(true) }
    var selectedQuotation by remember { mutableStateOf<Quotation?>(null) }

    //val

    PcsBottomSheet(
        modifier = modifier,
        sheetContent = {
            //BottomSheetContent(quotation = selectedQuotation)
        },
        modalBottomSheetData = ModalBottomSheetData(title = "Quotation Details"),
        showBottomSheet = showBottomSheet,
        onDismiss = { showBottomSheet = false }
    )
}