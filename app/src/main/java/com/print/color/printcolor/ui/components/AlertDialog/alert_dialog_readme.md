# PcSAlertDialog

`PcSAlertDialog` is a customizable alert dialog component for Jetpack Compose, supporting both confirmation dialogs and animated dialogs using Lottie animations.

## Features
- Supports two types: `CONFIRMATION` and `ANIMATION`.
- Customizable title, message, and buttons.
- Optional Lottie animation support.
- Configurable dismissal behavior.

## Usage

### Basic Confirmation Dialog
```kotlin
val dataSample = AlertDialogDefaultVariants.alertDialogDefault(
    title = "Alert Dialog Title",
    message = "This is an alert dialog message",
    confirmButtonText = "OK",
    dismissButtonText = "Cancel",
    onConfirm = {},
    onDismiss = {},
    type = AlertDialogType.CONFIRMATION,
    dismissOnClickOutside = true
)

PcSAlertDialog(
    data = dataSample
)
```

### Animated Dialog with Lottie
```kotlin
val dataSample = AlertDialogDefaultVariants.alertDialogAnimation(
    title = "Success",
    message = "Operation completed successfully!",
    confirmButtonText = "OK",
    dismissButtonText = "Cancel",
    onConfirm = {},
    onDismiss = {},
    type = AlertDialogType.ANIMATION,
    dismissOnClickOutside = true,
    lottieAnimation = R.raw.success_animation
)

PcSAlertDialog(
    data = dataSample,
    autoPlayAnimation = true,
    animationRepeatCount = 1
)
```

## Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `title` | `String` | Title of the dialog |
| `message` | `String` | Message displayed inside the dialog |
| `confirmButtonText` | `String` | Text for the confirm button |
| `dismissButtonText` | `String` | Text for the dismiss button |
| `dismissOnClickOutside` | `Boolean` | Determines if clicking outside dismisses the dialog |
| `onConfirm` | `() -> Unit` | Callback for confirm button click |
| `onDismiss` | `() -> Unit` | Callback for dismiss button click |
| `type` | `AlertDialogType` | Type of the dialog (`CONFIRMATION`, `ANIMATION`) |
| `lottieAnimation` | `Int?` | Optional Lottie animation resource |

## Preview
You can preview the dialog using Android Studio's Compose Preview:

```kotlin
@Preview
@Composable
fun PcSAlertDialogPreview() {
    PrintColorTheme {
        var showDialog = remember { mutableStateOf(true) }
        val dataSample = AlertDialogDefaultVariants.alertDialogDefault(
            title = "Preview Dialog",
            message = "This is a preview message",
            confirmButtonText = "OK",
            dismissButtonText = "Cancel",
            onConfirm = {},
            onDismiss = {},
            type = AlertDialogType.CONFIRMATION,
            dismissOnClickOutside = true
        )

        if (showDialog.value) {
            PcSAlertDialog(data = dataSample)
        }
    }
}
```