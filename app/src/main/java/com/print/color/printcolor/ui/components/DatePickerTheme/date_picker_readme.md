# PcSDatePicker Component Documentation

## Overview
The `PcSDatePicker` is a customizable Jetpack Compose component that displays an `OutlinedTextField` to show the selected date and opens a Material 3 `DatePickerDialog` when tapped. The selected date is stored as a `Long?` representing milliseconds since the Unix epoch, making it easy to persist and compare.

---

## `rememberSelectedDate` Helper

## PcSDatePicker Component
```kotlin
/**
 * @param default Optional initial date in milliseconds (epoch time)
 * @return A MutableState<Long?> to be used with PcSDatePicker
 *
@Composable
fun rememberSelectedDate(default: Long? = null): MutableState<Long?> {
    return remember { mutableStateOf(default) }
}
