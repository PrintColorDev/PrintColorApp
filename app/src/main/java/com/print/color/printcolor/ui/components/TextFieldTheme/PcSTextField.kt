package com.print.color.printcolor.ui.components.TextFieldTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData.TextFieldType.FILLED
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData.TextFieldType.OUTLINED
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData.TextFieldType.OUTLINED_LIST
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData.TextFieldType.OUTLINED_PASSWORD
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldDefaultVariants
import com.print.color.printcolor.ui.theme.PrintColorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PcsTextField(
    data: TextFieldData,
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int = 100,
    imeAction: ImeAction = ImeAction.None,
    options: List<String> = emptyList(),
) {
    /** Password Val's */
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val passwordIcon = if (passwordVisible)
        painterResource(id = R.drawable.ic_pcs_visibility)
    else
        painterResource(id = R.drawable.ic_pcs_visibility_off)

    val trailingPasswordIcon: @Composable (() -> Unit) = {
        IconButton(onClick = {
            passwordVisible = !passwordVisible
        }) {
            Icon(
                painter = passwordIcon,
                contentDescription = "Visibility Icon"
            )
        }
    }

    val trailingIcon: @Composable (() -> Unit) = {
        if (value.isNotEmpty()) {
            IconButton(onClick = { onValueChange("") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pcs_cancel),
                    contentDescription = "Clear",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

    val leadingIcon: @Composable (() -> Unit)? = if (data.leadingIcon != null) {
        {
            Icon(
                painter = data.leadingIcon,
                contentDescription = "Leading Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    } else {
        null
    }

    PrintColorTheme {
        when (data.textFieldType) {
            FILLED -> {
                TextField(
                    value = value,
                    onValueChange = {
                        maxLength(it, 100)
                    },
                    modifier = modifier,
                    placeholder = {
                        Text(text = data.placeHolder)
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = data.keyboardType,
                        imeAction = imeAction
                    ),
                    trailingIcon = trailingIcon,
                    leadingIcon = leadingIcon
                )
            }

            OUTLINED -> {
                Column {
                    OutlinedTextField(
                        value = value,
                        onValueChange = { onValueChange(maxLength(it, maxLength)) },
                        modifier = modifier,
                        label = {
                            Text(text = data.placeHolder)
                        },
                        /*placeholder = {
                            Text(text = data.placeHolder)
                        },*/
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = data.keyboardType,
                            imeAction = imeAction
                        ),
                        trailingIcon = trailingIcon,
                        leadingIcon = leadingIcon,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedTextColor = MaterialTheme.colorScheme.error,
                            focusedTextColor = MaterialTheme.colorScheme.scrim,
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.scrim,
                        )
                    )
                    if (data.isTextCountRequired) {
                        Text(
                            text = "${value.length} / $maxLength",
                            modifier = Modifier.align(Alignment.End),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            OUTLINED_PASSWORD -> {
                Column {
                    OutlinedTextField(
                        value = value,
                        onValueChange = { onValueChange(maxLength(it, maxLength)) },
                        modifier = modifier,
                        placeholder = {
                            Text(text = data.placeHolder)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = data.keyboardType,
                            imeAction = imeAction
                        ),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = trailingPasswordIcon,
                        leadingIcon = leadingIcon,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedTextColor = MaterialTheme.colorScheme.error,
                            focusedTextColor = MaterialTheme.colorScheme.scrim,
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.scrim,
                        )
                    )
                    if (data.isTextCountRequired) {
                        Text(
                            text = "${value.length} / $maxLength",
                            modifier = Modifier.align(Alignment.End),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            OUTLINED_LIST -> {
                var expanded by remember { mutableStateOf(false) }

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = value,
                        onValueChange = {
                            onValueChange(maxLength(it, maxLength))
                            expanded = true
                        },
                        //TODO, future refact
                        modifier = modifier.menuAnchor(),
                        placeholder = {
                            Text(text = stringResource(R.string.quotation_screen_billing_payment_method))
                        },
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = if (expanded) "Collapse" else "Expand"
                            )
                        },
                        leadingIcon = leadingIcon,
                        readOnly = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.scrim
                        ),
                        keyboardOptions = KeyboardOptions.Default
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    onValueChange(option)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

/** fun to handle the max length of the textField */
private fun maxLength(text: String, maxLength: Int): String {
    return if (text.length > maxLength) {
        text.take(maxLength)
    } else {
        text
    }
}

@Preview(showBackground = true)
@Composable
private fun PcsTextFieldPreview(modifier: Modifier = Modifier) {
    var myPassword by remember { mutableStateOf("") }
    val textFieldData = TextFieldDefaultVariants.textFieldOutlined(
        label = "Label",
        placeHolder = "Placeholder",
        keyboardType = KeyboardType.Text,
        leadingIcon = painterResource(R.drawable.ic_pcs_verified)
    )
    val textFieldData1 = TextFieldDefaultVariants.textFieldFilled(
        label = "Label",
        placeHolder = "Placeholder",
        keyboardType = KeyboardType.Text,
        leadingIcon = painterResource(R.drawable.ic_pcs_verified)
    )

    val textFieldData2 = TextFieldDefaultVariants.textFieldOutlinedList(
        label = "Label",
        placeHolder = "Placeholder",
        keyboardType = KeyboardType.Text,
        leadingIcon = painterResource(R.drawable.ic_pcs_verified)
    )
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Another Option")

    PrintColorTheme {
        Column {
            PcsTextField(
                data = textFieldData,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                onValueChange = { myPassword = it },
                value = myPassword,
                imeAction = ImeAction.Next
            )

            PcsTextField(
                data = textFieldData1,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                onValueChange = { myPassword = it },
                value = myPassword,
                imeAction = ImeAction.Next
            )

            PcsTextField(
                data = textFieldData2,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                onValueChange = { myPassword = it },
                value = myPassword,
                options = options,
                imeAction = ImeAction.Next
            )
        }
    }
}