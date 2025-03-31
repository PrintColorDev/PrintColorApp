package com.print.color.printcolor.ui.components.ChipsTheme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.ChipsTheme.model.ChipData
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.ChipsTheme.model.ChipsDefaultVariants

@Composable
fun rememberCheckState(default: Boolean = false): MutableState<Boolean> =
    remember { mutableStateOf(default) }

//TODO complete this component
@Composable
fun PcSChip(
    modifier: Modifier = Modifier,
    data: ChipData,
    onClick: () -> Unit,
    isSelected: Boolean = false
) {
    when (data.type) {
        ChipData.ChipType.ASSIST_CHIP -> {
            AssistChip(
                modifier = Modifier.then(modifier),
                onClick = { onClick() },
                label = { Text(data.text) },
                leadingIcon = {
                    data.icon?.let {
                        Image(
                            painter = it,
                            contentDescription = data.contentDescription,
                            modifier = Modifier.size(18.dp),
                            colorFilter = ColorFilter.tint(LocalContentColor.current)
                        )
                    }
                },
            )
        }

        ChipData.ChipType.FILTER_CHIP -> {
            FilterChip(
                modifier = Modifier.then(modifier),
                onClick = {
                    onClick()
                },
                label = {
                    Text(data.text)
                },
                selected = isSelected,
                leadingIcon = {
                    data.icon?.let {
                        AnimatedVisibility(
                            visible = isSelected,
                            enter = fadeIn() + scaleIn(),
                            exit = fadeOut() + scaleOut()
                        ) {
                            Image(
                                painter = it,
                                contentDescription = data.contentDescription,
                                modifier = Modifier.size(18.dp),
                                colorFilter = ColorFilter.tint(LocalContentColor.current)
                            )
                        }
                    }
                }
            )
        }

        ChipData.ChipType.INPUT_CHIP -> {
            // TODO: Add input chip
        }

        ChipData.ChipType.SUGGESTION_CHIP -> {
            SuggestionChip(
                onClick = { onClick() },
                label = { Text("Suggestion chip") },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PcSChipPreview() {
    val chipAssistData = ChipsDefaultVariants.chipAssist(
        text = "Assist Chip",
        icon = painterResource(R.drawable.ic_pcs_search),
        contentDescription = "Assist Chip",
        type = ChipData.ChipType.ASSIST_CHIP
    )

    val chipFilterData = ChipsDefaultVariants.chipFilter(
        text = "Filter Chip",
        icon = painterResource(R.drawable.ic_pcs_check),
        contentDescription = "Filter Chip",
        type = ChipData.ChipType.FILTER_CHIP,
        isSelected = true
    )

    val chipSuggestionData = ChipsDefaultVariants.chipSuggestion(
        text = "Suggestion Chip",
        contentDescription = "Filter Chip",
        type = ChipData.ChipType.SUGGESTION_CHIP,
    )
    Column {
        PcSChip(data = chipAssistData, onClick = {})
        PcSChip(data = chipFilterData, onClick = {})
        PcSChip(data = chipSuggestionData, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PcSChipFilterListPreview() {
    var selectedChipIndex by remember { mutableStateOf<Int?>(null) }
    val checkState: MutableState<Boolean> = rememberCheckState()

    val chips = listOf(
        ChipsDefaultVariants.chipFilter(
            text = "Chip 1",
            icon = painterResource(R.drawable.ic_pcs_check),
            contentDescription = "Filter Chip 1",
            type = ChipData.ChipType.FILTER_CHIP,
            isSelected = checkState.value
        ),
        ChipsDefaultVariants.chipFilter(
            text = "Chip 2",
            icon = painterResource(R.drawable.ic_pcs_check),
            contentDescription = "Filter Chip 2",
            type = ChipData.ChipType.FILTER_CHIP,
            isSelected = checkState.value
        ),
        ChipsDefaultVariants.chipFilter(
            text = "Chip 3",
            icon = painterResource(R.drawable.ic_pcs_check),
            contentDescription = "Filter Chip 3",
            type = ChipData.ChipType.FILTER_CHIP,
            isSelected = checkState.value
        )
    )

    Column {
        chips.forEachIndexed { index, chipData ->
            PcSChip(
                data = chipData,
                isSelected = selectedChipIndex == index,
                onClick = { selectedChipIndex = if (selectedChipIndex == index) null else index }
            )
        }
    }
}