package com.print.color.printcolor.ui.components.TextFieldTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldType.FILLED
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldType.OUTLINED
import com.print.color.printcolor.ui.theme.PrintColorTheme

@Composable
fun PcsTextField(
    data: TextFieldData, modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val trailingIcon: @Composable (() -> Unit) = {
        if (value.isNotEmpty()) {
            IconButton(onClick = { onValueChange("") }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Clear",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
    val leadingIcon: @Composable (() -> Unit)? = if (data.leadingIcon != null) {
        {
            Icon(
                imageVector = data.leadingIcon,
                contentDescription = "Leading Icon",
                modifier = Modifier.size(16.dp)
            )
        }
    } else {
        null
    }

    when (data.textFieldType) {
        FILLED -> {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                label = { Text(text = data.label) },
                placeholder = {
                    Text(text = data.placeHolder)
                },
                keyboardOptions = KeyboardOptions(keyboardType = data.keyboardType),
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon
            )
        }

        OUTLINED -> {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                //label = { Text(text = data.label) },
                placeholder = {
                    Text(text = data.placeHolder)
                },
                keyboardOptions = KeyboardOptions(keyboardType = data.keyboardType),
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PcsTextFieldPreview(modifier: Modifier = Modifier) {
    var myPassword by remember { mutableStateOf("") }
    val textFieldData = TextFieldData(
        textFieldType = OUTLINED,
        label = "Label",
        placeHolder = "Placeholder",
        keyboardType = KeyboardType.Text,
        leadingIcon = Icons.Filled.Search
    )
    val textFieldData1 = TextFieldData(
        textFieldType = FILLED,
        label = "Label",
        placeHolder = "Placeholder",
        keyboardType = KeyboardType.Text,
        leadingIcon = Icons.Filled.Search
    )
    PrintColorTheme {
        Column {
            PcsTextField(
                data = textFieldData,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                onValueChange = { myPassword = it },
                value = myPassword
            )

            PcsTextField(
                data = textFieldData1,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                onValueChange = { myPassword = it },
                value = myPassword
            )
        }
    }
}