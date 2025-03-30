# PcsIconClickButton

A composable button component that displays an icon and provides a clickable interaction.

## Features
- Circular shape with customizable border
- Icon display with tint support
- Clickable with ripple effect

## Usage

### Data Model
```kotlin
/**
 * Data class representing the icon button properties.
 * @param icon The icon to be displayed in the button.
 * @param contentDescription The content description of the icon.
 */
data class IconClickButtonData(
    val icon: Painter,
    val contentDescription: String,
)
```

### Default Variants
```kotlin
/**
 * Object that contains default variants for the IconClickButton.
 */
object IconClickButtonDefaultVariants {
    fun iconClickButtonDefault(
        icon: Painter,
        contentDescription: String,
    ) = IconClickButtonData(
        icon = icon,
        contentDescription = contentDescription,
    )
}
```

### Composable Function
```kotlin
@Composable
fun PcsIconClickButton(
    modifier: Modifier = Modifier,
    data: IconClickButtonData,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.then(modifier)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onPrimary)
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = data.icon,
                contentDescription = null,
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(LocalContentColor.current)
            )
        }
    }
}
```

### Preview
```kotlin
@Preview(showBackground = true)
@Composable
fun PcsIconClickButtonPreview() {
    PrintColorTheme {
        val context: Context = LocalContext.current
        val data = IconClickButtonData(
            icon = painterResource(R.drawable.ic_pcs_close),
            contentDescription = "Close Button",
        )
        PcsIconClickButton(
            data = data,
            onClick = { Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show() }
        )
    }
}
```

## License
This project is licensed under the MIT License - see the LICENSE file for details.

