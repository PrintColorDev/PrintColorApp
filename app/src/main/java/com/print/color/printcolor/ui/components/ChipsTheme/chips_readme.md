# PcSChip Component Documentation

## Overview
The `PcSChip` component is a customizable chip widget designed for use in Jetpack Compose applications. It supports multiple chip types, including Assist, Filter, Input, and Suggestion chips.

## ChipData Structure
```kotlin
/**
 * @param text: The text of the chip.
 * @param isSelected: The state of the chip.
 * @param icon: The icon of the chip.
 * @param contentDescription: The content description of the chip.
 * @param type: The type of chip (Assist, Filter, Input, or Suggestion).
 */
data class ChipData(
    val text: String,
    val isSelected: Boolean?,
    val icon: Painter?,
    val contentDescription: String,
    val type: ChipType
) {
    enum class ChipType {
        ASSIST_CHIP,
        FILTER_CHIP,
        INPUT_CHIP,
        SUGGESTION_CHIP
    }
}
```

## Default Variants
To simplify the creation of different types of chips, the `ChipsDefaultVariants` object provides factory methods:

```kotlin
object ChipsDefaultVariants {
    fun chipAssist(text: String, icon: Painter, contentDescription: String, type: ChipType) = ChipData(
        text = text, isSelected = null, icon = icon, contentDescription = contentDescription, type = type
    )

    fun chipFilter(text: String, isSelected: Boolean, icon: Painter, contentDescription: String, type: ChipType) = ChipData(
        text = text, isSelected = isSelected, icon = icon, contentDescription = contentDescription, type = type
    )

    fun chipInput(text: String, isSelected: Boolean, icon: Painter, contentDescription: String, type: ChipType) = ChipData(
        text = text, isSelected = isSelected, icon = icon, contentDescription = contentDescription, type = type
    )

    fun chipSuggestion(text: String, isSelected: Boolean, contentDescription: String, type: ChipType) = ChipData(
        text = text, isSelected = isSelected, icon = null, contentDescription = contentDescription, type = type
    )
}
```

## PcSChip Component
```kotlin
@Composable
fun PcSChip(modifier: Modifier = Modifier, data: ChipData, onClick: () -> Unit) {
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
            var selected by remember { mutableStateOf(false) }

            FilterChip(
                modifier = Modifier.then(modifier),
                onClick = { selected = !selected },
                label = { Text(data.text) },
                selected = selected,
                leadingIcon = {
                    data.icon?.let {
                        AnimatedVisibility(
                            visible = selected,
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
            // TODO: Implement Input Chip
        }
        
        ChipData.ChipType.SUGGESTION_CHIP -> {
            SuggestionChip(
                onClick = { onClick() },
                label = { Text(data.text) },
            )
        }
    }
}
```

## Preview
```kotlin
@Preview(showBackground = true)
@Composable
fun PcSChipPreview(modifier: Modifier = Modifier) {
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
        contentDescription = "Suggestion Chip",
        type = ChipData.ChipType.SUGGESTION_CHIP,
        isSelected = true
    )
    
    Column {
        PcSChip(data = chipAssistData, onClick = {})
        PcSChip(data = chipFilterData, onClick = {})
        PcSChip(data = chipSuggestionData, onClick = {})
    }
}
```

## Usage
Use `PcSChip` in your Composable functions like this:
```kotlin
PcSChip(
    data = ChipsDefaultVariants.chipAssist(
        text = "Assist Chip",
        icon = painterResource(R.drawable.ic_pcs_search),
        contentDescription = "Search Icon",
        type = ChipData.ChipType.ASSIST_CHIP
    ),
    onClick = { /* Handle click */ }
)
```

## Summary
- `PcSChip` supports multiple chip types.
- Uses `ChipsDefaultVariants` for easy instantiation.
- Includes animation for `FilterChip` selection.
- Provides a clean and modular design for Jetpack Compose applications.