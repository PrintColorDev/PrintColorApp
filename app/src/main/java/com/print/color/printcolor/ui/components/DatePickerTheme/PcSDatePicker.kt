package com.print.color.printcolor.ui.components.DatePickerTheme

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.theme.PrintColorTheme
import com.print.color.printcolor.utils.convertMillisToDate


@Composable
fun rememberSelectedDate(default: Long? = null): MutableState<Long?> {
    return remember { mutableStateOf(default) }
}

/**
 * @param modifier Modifier
 * @param selectedDate MutableState<Long?>
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PcSDatePicker(
    modifier: Modifier = Modifier,
    selectedDate: MutableState<Long?>
) {
    var showModal by remember { mutableStateOf(false) }

    PrintColorTheme {
        OutlinedTextField(
            value = selectedDate.value?.let { convertMillisToDate(it) } ?: "",
            onValueChange = { },
            label = { Text(text = stringResource(R.string.date_picker_hint_text)) },
            placeholder = { Text(text = stringResource(R.string.date_picker_place_holder_text)) },
            trailingIcon = {
                Icon(Icons.Default.DateRange, contentDescription = stringResource(R.string.date_picker_hint_text))
            },
            modifier = modifier
                .fillMaxWidth()
                .pointerInput(Unit) { //Use Unit as a key to prevent recomposition
                    awaitEachGesture {
                        // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
                        // in the Initial pass to observe events before the text field consumes them
                        // in the Main pass.
                        awaitFirstDown(pass = PointerEventPass.Initial)
                        val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        if (upEvent != null) {
                            showModal = true
                        }
                    }
                }
        )

        if (showModal) {
            DatePickerModal(
                onDateSelected = {
                    selectedDate.value = it
                },
                onDismiss = { showModal = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

