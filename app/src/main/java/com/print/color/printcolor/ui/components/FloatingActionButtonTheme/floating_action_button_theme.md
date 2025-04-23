# Floating Action Button (FAB) Component

## Overview
The `PcSFloatingActionButton` component is a customizable Floating Action Button (FAB) for Jetpack Compose. It supports different FAB types and icons while maintaining a consistent Material Design appearance.

## Data Model
The `FloatingActionButtonData` data class holds the properties for configuring the FAB.

### Properties
- **icon** (`Painter?`): The leading icon of the FAB.
- **typeFAB** (`TypeFAB`): The type of FAB (either `SMALL_FAB` or `NORMAL_FAB`).
- **contentDescription** (`String`): The content description for accessibility.

```kotlin
// Data class for FAB configuration
data class FloatingActionButtonData(
    val icon: Painter?,
    val typeFAB: TypeFAB,
    val contentDescription: String
) {
    enum class TypeFAB {
        SMALL_FAB,
        NORMAL_FAB
    }
}
```

## Default Variants
The `FloatingActionButtonDefaultVariants` object provides predefined configurations for FAB types.

### Variants
- **smallFAB**: Creates a `SmallFloatingActionButton`.
- **normalFAB**: Creates a standard `FloatingActionButton`.

```kotlin
object FloatingActionButtonDefaultVariants {
    fun smallFAB(
        icon: Painter,
        contentDescription: String,
        typeFAB: TypeFAB
    ) = FloatingActionButtonData(
        icon = icon,
        typeFAB = typeFAB,
        contentDescription = contentDescription)

    fun normalFAB(
        icon: Painter,
        contentDescription: String,
        typeFAB: TypeFAB
    ) = FloatingActionButtonData(
        icon = icon,
        typeFAB = typeFAB,
        contentDescription = contentDescription)
}
```

## Composable Function
The `PcSFloatingActionButton` composable function displays a FAB based on the provided `FloatingActionButtonData`.

### Parameters
- **modifier** (`Modifier`): Modifier for styling and layout.
- **data** (`FloatingActionButtonData`): Configuration data for the FAB.
- **onClick** (`() -> Unit`): Click event callback.

### Implementation
```kotlin
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
```

## Preview Example
The following preview function demonstrates how to use `PcSFloatingActionButton`.

```kotlin
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
```

## Summary
- Supports **small and normal FAB** types.
- Customizable **icon** and **content description**.
- Easily integrated with Material Theme.
- Provides predefined variants for convenience.

This component ensures a flexible and accessible FAB implementation in Jetpack Compose applications.