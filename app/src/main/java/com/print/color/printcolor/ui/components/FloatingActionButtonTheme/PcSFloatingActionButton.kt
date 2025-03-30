package com.print.color.printcolor.ui.components.FloatingActionButtonTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.FloatingActionButtonData
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.FloatingActionButtonData.TypeFAB.NORMAL_FAB
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.FloatingActionButtonData.TypeFAB.SMALL_FAB
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.FloatingActionButtonDefaultVariants
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
                data.icon?.let {
                    Image(painter = it, contentDescription = data.contentDescription)
                }
            }
        }

        NORMAL_FAB -> {
            FloatingActionButton(onClick = { onClick() }) {
                data.icon?.let {
                    Image(painter = it, contentDescription = data.contentDescription)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PcSFloatingActionButtonPreview() {
    val data = FloatingActionButtonDefaultVariants.smallFAB(
        icon = painterResource(R.drawable.ic_pcs_add),
        typeFAB = SMALL_FAB,
        contentDescription = "Small Floating action button."
    )

    val data1 = FloatingActionButtonDefaultVariants.normalFAB(
        icon = painterResource(R.drawable.ic_pcs_add),
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