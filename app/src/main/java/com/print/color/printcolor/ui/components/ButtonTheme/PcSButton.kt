package com.print.color.printcolor.ui.components.ButtonTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.print.color.printcolor.ui.components.ButtonTheme.ButtonType.ELEVATED
import com.print.color.printcolor.ui.components.ButtonTheme.ButtonType.FILLED
import com.print.color.printcolor.ui.components.ButtonTheme.ButtonType.OUTLINED
import com.print.color.printcolor.ui.components.ButtonTheme.ButtonType.TEXT
import com.print.color.printcolor.ui.components.ButtonTheme.ButtonType.TONAL
import com.print.color.printcolor.ui.theme.PrintColorTheme

data class ButtonData(
    val label: String,
    val contentDescription: String? = null,
    val type: ButtonType,
    val isEnabled: Boolean = true
)

enum class ButtonType {
    ELEVATED,
    OUTLINED,
    TEXT,
    FILLED,
    TONAL
}

@Composable
fun PcsButton(
    data: ButtonData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: Shape = MaterialTheme.shapes.medium,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit = { Text(text = data.label) }
) {
    when (data.type) {
        ELEVATED -> {
            ElevatedButton(
                onClick = { onClick() },
                modifier = modifier,
                content = content
            )
        }

        OUTLINED -> {
            OutlinedButton(
                onClick = { onClick() },
                modifier = modifier,
                content = content
            )
        }
        TEXT -> {
            TextButton(
                onClick = { onClick() },
                modifier = modifier,
                content = content
            )
        }
        FILLED -> {
            Button(
                onClick = { onClick() },
                modifier = modifier,
                content = content
            )
        }
        TONAL -> {
            FilledTonalButton(
                onClick = { onClick() },
                modifier = modifier,
                content = content
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PcsButtonPreview(modifier: Modifier = Modifier) {
    PrintColorTheme {
        Column {
            val buttonElevatedData = ButtonData(label = "Label", type = ELEVATED, contentDescription = "Content Description")
            PcsButton(onClick = {}, data = buttonElevatedData, modifier = Modifier)

            val buttonOutlinedData = ButtonData(label = "Label", type = OUTLINED, contentDescription = "Content Description")
            PcsButton(onClick = {}, data = buttonOutlinedData, modifier = Modifier)

            val buttonTextData = ButtonData(label = "Label", type = TEXT, contentDescription = "Content Description")
            PcsButton(onClick = {}, data = buttonTextData, modifier = Modifier)

            val buttonFilledData = ButtonData(label = "Label", type = FILLED, contentDescription = "Content Description")
            PcsButton(onClick = {}, data = buttonFilledData, modifier = Modifier)

            val buttonTonalData = ButtonData(label = "Label", type = TONAL, contentDescription = "Content Description")
            PcsButton(onClick = {}, data = buttonTonalData, modifier = Modifier)
        }
    }
}