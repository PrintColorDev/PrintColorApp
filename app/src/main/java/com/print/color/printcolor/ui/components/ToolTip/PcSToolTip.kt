package com.print.color.printcolor.ui.components.ToolTip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.ToolTip.model.ToolTipData
import com.print.color.printcolor.ui.theme.PrintColorTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PcSRichTooltip(
    modifier: Modifier = Modifier,
    data: ToolTipData
) {
    val tooltipPosition = TooltipDefaults.rememberRichTooltipPositionProvider()
    val tooltipState = rememberTooltipState(isPersistent = false)
    val scope = rememberCoroutineScope()
    PrintColorTheme {
        TooltipBox(
            positionProvider = tooltipPosition,
            tooltip = {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary),
                    modifier = modifier
                ) {
                    Column(modifier = Modifier.padding(all = 8.dp)) {
                        Text(textAlign = TextAlign.Center, text = data.toolTipTitle)
                        Text(data.toolTipDescription)
                    }
                }
            },
            state = tooltipState
        ) {
            Text(
                data.toolTipActionText,
                modifier = Modifier
                    .clickable {
                        scope.launch {
                            tooltipState.show()
                        }
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PcSRichTooltipPreview(modifier: Modifier = Modifier) {
    val data = ToolTipData(
        toolTipTitle = "Title",
        toolTipDescription = "Description DescriptionDescriptionDescriptionDescriptionDescription",
        toolTipActionText = "Step 1"
    )
    PcSRichTooltip(data = data)
}