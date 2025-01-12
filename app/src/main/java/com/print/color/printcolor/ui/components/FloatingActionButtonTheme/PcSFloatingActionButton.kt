package com.print.color.printcolor.ui.components.FloatingActionButtonTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.FloatingActionButtonData
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.TypeFAB.SMALL_FAB
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.TypeFAB.NORMAL_FAB
import com.print.color.printcolor.ui.theme.PrintColorTheme

@Composable
fun PcSFloatingActionButton(
    modifier: Modifier = Modifier,
    data: FloatingActionButtonData,
    onClick: () -> Unit
) {
    when (data.typeFAB) {

        SMALL_FAB -> {
            SmallFloatingActionButton(
                onClick = { onClick() },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary,
            ) {
                data.leadingIcon?.let {
                    Icon(imageVector = it, contentDescription = data.contentDescription)
                }
            }
        }

        NORMAL_FAB -> {
            FloatingActionButton(onClick = { onClick() }) {
                data.leadingIcon?.let {
                    Icon(imageVector = it, contentDescription = data.contentDescription)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PcSFloatingActionButtonPreview(modifier: Modifier = Modifier) {
    val data = FloatingActionButtonData(
        leadingIcon = Icons.Default.Add,
        typeFAB = SMALL_FAB,
        contentDescription = "Small Floating action button."
    )

    val data1 = FloatingActionButtonData(
        leadingIcon = Icons.Default.Add,
        typeFAB = NORMAL_FAB,
        contentDescription = "Floating action button."
    )
    PrintColorTheme {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            PcSFloatingActionButton(data = data, onClick = {})
            PcSFloatingActionButton(data = data1, onClick = {})
        }
    }
}