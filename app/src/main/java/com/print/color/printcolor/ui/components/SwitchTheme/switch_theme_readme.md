# PcSSwitch Documentation

## Overview
The `PcSSwitch` component is a customizable switch component that allows users to toggle between two states (on/off). It displays a label and an optional icon when checked.

## Parameters

### `SwitchData`
- **`switchString`** (`String`): The text label displayed next to the switch.
- **`isChecked`** (`Boolean`): The current state of the switch (true for ON, false for OFF).
- **`onCheckedChange`** (`(Boolean) -> Unit`): Callback triggered when the switch state changes.
- **`contentDescription`** (`String`): Content description for accessibility purposes.

## Default Variants

### `SwitchDefaultVariants`
The `SwitchDefaultVariants` object provides a default variant for the switch.

#### `switchDefault`
```kotlin
fun switchDefault(
    switchString: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    contentDescription: String
) = SwitchData(
    switchString = switchString,
    isChecked = isChecked,
    onCheckedChange = onCheckedChange,
    contentDescription = contentDescription
)
```

## Usage Example

### Implementing `PcSSwitch`
```kotlin
@Composable
fun PcSSwitch(modifier: Modifier = Modifier, data: SwitchData) {
    Row(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = data.switchString)
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            onCheckedChange = { data.onCheckedChange(it) },
            checked = data.isChecked,
            thumbContent = {
                if (data.isChecked) {
                    Icon(
                        modifier = modifier.size(16.dp),
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Checked",
                        tint = Color.Black
                    )
                }
            }
        )
    }
}
```

### Preview Example
```kotlin
@Preview(showBackground = true)
@Composable
fun PcSSwitchPreview(modifier: Modifier = Modifier) {
    var switchValue by remember { mutableStateOf(false) }
    val switchData = SwitchDefaultVariants.switchDefault(
        switchString = "Switch State: $switchValue",
        isChecked = switchValue,
        onCheckedChange = { switchValue = it },
        contentDescription = "Switch $switchValue"
    )
    PrintColorTheme {
        PcSSwitch(data = switchData, modifier = modifier)
    }
}
```

## Summary
The `PcSSwitch` component provides an easy-to-use switch toggle with customizable text, state handling, and an optional icon when checked. It supports accessibility features through content descriptions.