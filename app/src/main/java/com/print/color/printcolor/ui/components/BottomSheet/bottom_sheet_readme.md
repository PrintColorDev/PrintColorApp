# PcsBottomSheet - Reusable Modal Bottom Sheet Component

## Overview

`PcsBottomSheet` is a reusable modal bottom sheet component built using Jetpack Compose. It provides a standard Material 3 bottom sheet structure with a customizable title, dismiss action, and flexible content area.

## Features

- Built on top of **Material 3**'s `ModalBottomSheet`.
- Supports a **customizable title**.
- Includes a **close button** with accessible `contentDescription`.
- Accepts any **composable content** inside the sheet.
- Fully **customizable layout** using `Modifier`.
- Easily controlled via `showBottomSheet` state and `onDismiss` callback.

## Usage

### Basic Usage

```kotlin
var showBottomSheet by remember { mutableStateOf(true) }

PcsBottomSheet(
    modalBottomSheetData = ModalBottomSheetThemeDefaultVariants.modalBottomSheetData(
        title = "Details"
    ),
    sheetContent = {
        Text("This is the content inside the bottom sheet.")
    },
    showBottomSheet = showBottomSheet,
    onDismiss = { showBottomSheet = false }
)
```

### Custom Modifier

```kotlin
PcsBottomSheet(
    modifier = Modifier.height(300.dp),
    modalBottomSheetData = ModalBottomSheetThemeDefaultVariants.modalBottomSheetData(
        title = "Custom Height"
    ),
    sheetContent = {
        Text("Custom height content here.")
    },
    showBottomSheet = true,
    onDismiss = {}
)
```

## Component Parameters

| Parameter              | Type                      | Description                                                         |
|------------------------|---------------------------|---------------------------------------------------------------------|
| `modifier`             | `Modifier`                | Modifier to apply to the `ModalBottomSheet`.                        |
| `sheetContent`         | `@Composable () -> Unit`  | Composable content displayed inside the bottom sheet.              |
| `modalBottomSheetData` | `ModalBottomSheetData`    | Object that provides the sheet’s title.                            |
| `showBottomSheet`      | `Boolean`                 | Controls the visibility of the bottom sheet.                        |
| `onDismiss`            | `() -> Unit`              | Callback invoked when the sheet is dismissed.                       |

## Data Model

### `ModalBottomSheetData`

```kotlin
data class ModalBottomSheetData(
    val title: String
)
```

Holds the text title displayed at the top of the bottom sheet.

## Default Variant Factory

```kotlin
object ModalBottomSheetThemeDefaultVariants {
    fun modalBottomSheetData(title: String): ModalBottomSheetData
}
```

Provides an easy way to create `ModalBottomSheetData` with a default structure.

## Preview

```kotlin
@Preview(showBackground = true)
@Composable
fun PreviewPcsBottomSheet() {
    var showBottomSheet by remember { mutableStateOf(true) }

    PcsBottomSheet(
        modalBottomSheetData = ModalBottomSheetThemeDefaultVariants.modalBottomSheetData(
            title = "Quotation Details"
        ),
        sheetContent = {
            Text("Preview content inside the bottom sheet.")
        },
        showBottomSheet = showBottomSheet,
        onDismiss = { showBottomSheet = false }
    )
}
```

## Dependencies

Make sure you have the required dependencies in your `build.gradle`:

```gradle
dependencies {
    implementation "androidx.compose.material3:material3:1.2.0"
}
```

