# PcsButton - Reusable Button Component

## Overview

`PcsButton` is a reusable button component built using Jetpack Compose. It provides various button types following Material 3 design guidelines, with optional icon support and loading states.

## Features

- Supports multiple button styles: **Elevated, Outlined, Text, Filled, and Tonal**.
- Allows adding an **icon** to the button.
- Supports **loading state** using a `CircularProgressIndicator`.
- Fully **customizable** using `Modifier`.
- Includes **accessibility support** with `contentDescription`.

## Usage

### Basic Usage

```kotlin
val buttonData = ButtonThemeDefaultVariants.buttonDefaultData(
    label = "Click Me",
    type = ButtonType.FILLED,
    contentDescription = "A filled button"
)

PcsButton(
    onClick = { /* Handle click */ },
    data = buttonData
)
```

### Button with Icon

```kotlin
val buttonDataWithIcon = ButtonThemeDefaultVariants.buttonDataWithIcon(
    label = "Search",
    type = ButtonType.OUTLINED,
    contentDescription = "Outlined button with search icon",
    icon = painterResource(R.drawable.ic_pcs_search)
)

PcsButton(
    onClick = { /* Handle click */ },
    data = buttonDataWithIcon
)
```

### Button with Loading State

```kotlin
PcsButton(
    isVisible = true, // Shows CircularProgressIndicator instead of label
    onClick = { /* Handle click */ },
    data = buttonData
)
```

## Button Variants

| Type     | Description                                     |
| -------- | ----------------------------------------------- |
| FILLED   | A standard button with a filled background.     |
| OUTLINED | A button with an outlined border.               |
| TEXT     | A text-only button with no background.          |
| ELEVATED | A button with elevation for emphasis.           |
| TONAL    | A slightly filled button with tonal background. |

## Customization

You can customize the button using Jetpack Compose's `Modifier`:

```kotlin
PcsButton(
    onClick = { /* Handle click */ },
    data = buttonData,
    modifier = Modifier.size(200.dp)
)
```

## Preview

To preview the button in Android Studio:

```kotlin
@Preview(showBackground = true)
@Composable
fun PreviewPcsButton() {
    PrintColorTheme {
        PcsButton(
            onClick = {},
            data = ButtonThemeDefaultVariants.buttonDefaultData(
                label = "Preview",
                type = ButtonType.FILLED,
                contentDescription = "Preview Button"
            )
        )
    }
}
```

## Dependencies

Ensure you have Jetpack Compose and Material 3 set up in your `build.gradle`:

```gradle
dependencies {
    implementation "androidx.compose.material3:material3:1.1.0"
}
```