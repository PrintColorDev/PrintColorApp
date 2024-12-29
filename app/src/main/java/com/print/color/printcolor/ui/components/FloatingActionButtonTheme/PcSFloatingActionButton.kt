package com.print.color.printcolor.ui.components.FloatingActionButtonTheme

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.FloatingActionButtonData
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.TypeFAB.ANIMATED_FAB
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.TypeFAB.SMALL_FAB

@Composable
fun PcSFloatingActionButton(
    data: FloatingActionButtonData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    when (data.typeFAB) {
        SMALL_FAB -> {
            SmallFloatingActionButton(onClick = { onClick() }, modifier = modifier) {
                data.leadingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Leading Icon",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        ANIMATED_FAB -> {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PcSFloatingActionButtonPreview(modifier: Modifier = Modifier) {
    val data = FloatingActionButtonData(
        stringFAB = "FAB",
        leadingIcon = Icons.Default.Add,
        typeFAB = SMALL_FAB
    )
    PcSFloatingActionButton(data = data, onClick = {})
}