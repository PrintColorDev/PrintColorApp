package com.print.color.printcolor.ui.components.ButtonTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType.OUTLINED
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType.ELEVATED
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType.FILLED
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType.TEXT
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType.TONAL
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonThemeDefaultVariants
import com.print.color.printcolor.ui.theme.PrintColorTheme
import com.print.color.printcolor.R

@Composable
fun PcsButton(
    isVisible: Boolean = false,
    data: ButtonData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: Shape = MaterialTheme.shapes.medium,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit = {
        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            data.icon?.let {
                Image(
                    painter = it,
                    contentDescription = data.contentDescription,
                    modifier = Modifier.size(18.dp),
                    colorFilter = ColorFilter.tint(LocalContentColor.current)
                )
            }

            if (!isVisible) {
                Text(text = data.label)
            }

            if (isVisible) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp
                )
            }
        }
    }
) {
    when (data.type) {
        ELEVATED -> {
            ElevatedButton(
                onClick = { onClick() },
                enabled = data.isEnabled,
                modifier = modifier,
                content = content
            )
        }

        OUTLINED -> {
            OutlinedButton(
                onClick = { onClick() },
                enabled = data.isEnabled,
                modifier = modifier,
                content = content,
            )
        }

        TEXT -> {
            TextButton(
                onClick = { onClick() },
                enabled = data.isEnabled,
                modifier = modifier,
                content = content
            )
        }

        FILLED -> {
            Button(
                onClick = { onClick() },
                enabled = data.isEnabled,
                modifier = modifier,
                content = content
            )
        }

        TONAL -> {
            FilledTonalButton(
                onClick = { onClick() },
                enabled = data.isEnabled,
                modifier = modifier,
                content = content
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PcsButtonPreview() {
    val indication = LocalIndication.current
    val interactionSource = remember { MutableInteractionSource() }
    PrintColorTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            /** region Button without icon */
            val buttonElevatedData = ButtonThemeDefaultVariants.buttonDefaultData(
                label = "Elevated",
                type = ELEVATED,
                contentDescription = "Content Description"
            )
            PcsButton(
                onClick = {}, data = buttonElevatedData, modifier = Modifier.indication(
                    indication = indication, interactionSource = interactionSource
                )
            )

            val buttonOutlinedData = ButtonThemeDefaultVariants.buttonDefaultData(
                label = "Outlined",
                type = OUTLINED,
                contentDescription = "Content Description"
            )
            PcsButton(onClick = {}, data = buttonOutlinedData, modifier = Modifier)

            val buttonTextData =
                ButtonThemeDefaultVariants.buttonDefaultData(
                    label = "Text", type = TEXT, contentDescription = "Content Description"
                )
            PcsButton(onClick = {}, data = buttonTextData, modifier = Modifier)

            val buttonFilledData = ButtonThemeDefaultVariants.buttonDefaultData(
                label = "Filled",
                type = FILLED,
                contentDescription = "Content Description"
            )
            PcsButton(onClick = {}, data = buttonFilledData, modifier = Modifier)

            val buttonTonalData = ButtonThemeDefaultVariants.buttonDefaultData(
                label = "Label",
                type = TONAL,
                contentDescription = "Content Description"
            )
            PcsButton(onClick = {}, data = buttonTonalData, modifier = Modifier)

            /** region Button with icon */
            val buttonElevatedDataWithIcon = ButtonThemeDefaultVariants.buttonDataWithIcon(
                label = "Elevated",
                type = ELEVATED,
                contentDescription = "Content Description",
                icon = painterResource(R.drawable.ic_pc_logo),
            )
            PcsButton(onClick = {}, data = buttonElevatedDataWithIcon, modifier = Modifier)

            val buttonOutlinedDataWithIcon = ButtonThemeDefaultVariants.buttonDataWithIcon(
                label = "Outlined",
                type = OUTLINED,
                contentDescription = "Content Description",
                icon = painterResource(R.drawable.ic_pc_logo),
            )
            PcsButton(onClick = {}, data = buttonOutlinedDataWithIcon, modifier = Modifier)

            val buttonTextDataWithIcon =
                ButtonThemeDefaultVariants.buttonDataWithIcon(
                    label = "Text",
                    type = TEXT,
                    contentDescription = "Content Description",
                    icon = painterResource(R.drawable.ic_pc_logo)
                )
            PcsButton(onClick = {}, data = buttonTextDataWithIcon, modifier = Modifier)

            val buttonFilledDataWithIcon = ButtonThemeDefaultVariants.buttonDataWithIcon(
                label = "Filled",
                type = FILLED,
                contentDescription = "Content Description",
                icon = painterResource(R.drawable.ic_pc_logo),

                )
            PcsButton(onClick = {}, data = buttonFilledDataWithIcon, modifier = Modifier)

            val buttonTonalDataWithIcon = ButtonThemeDefaultVariants.buttonDataWithIcon(
                label = "Tonal",
                type = TONAL,
                contentDescription = "Content Description",
                icon = painterResource(R.drawable.ic_pc_logo),
            )
            PcsButton(onClick = {}, data = buttonTonalDataWithIcon, modifier = Modifier)
        }
    }
}