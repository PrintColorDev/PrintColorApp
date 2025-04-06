package com.print.color.printcolor.ui.components.BottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.BottomSheet.model.ModalBottomSheetData
import com.print.color.printcolor.ui.components.BottomSheet.model.ModalBottomSheetThemeDefaultVariants
import com.print.color.printcolor.ui.components.IconClickButton.PcsIconClickButton
import com.print.color.printcolor.ui.components.IconClickButton.model.IconClickButtonDefaultVariants

/** region principal component*/
/**
 * @param modifier: The modifier to apply to the ModalBottomSheet.
 * @param sheetContent: The content of the ModalBottomSheet.
 * @param modalBottomSheetData: The data to be displayed in the ModalBottomSheet.
 * @param showBottomSheet: The state of the ModalBottomSheet.
 * @param onDismiss: The callback to be invoked when the ModalBottomSheet is dismissed.
 * */
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
                    PcsIconClickButton(
                        data = IconClickButtonDefaultVariants.iconClickButtonDefault(
                            icon = painterResource(R.drawable.ic_pcs_close),
                            contentDescription = "Close Bottom Sheet"
                        ), onClick = { onDismiss() })
                }
                sheetContent()
            }
        }
    }
}
/** endregion principal component*/


/** region component preview*/
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PcsBottomSheetPreview(modifier: Modifier = Modifier) {
    var showBottomSheet by remember { mutableStateOf(true) }

    PcsBottomSheet(
        modifier = modifier,
        sheetContent = {
        },
        modalBottomSheetData = ModalBottomSheetThemeDefaultVariants.modalBottomSheetData(title = "Quotation Details"),
        showBottomSheet = showBottomSheet,
        onDismiss = { showBottomSheet = false }
    )
}
/** endregion component preview*/