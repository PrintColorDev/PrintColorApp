package com.print.color.printcolor.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.AlertDialog.PcSAlertDialog
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData.AlertDialogType
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogDefaultVariants
import com.print.color.printcolor.ui.components.ButtonTheme.PcsButton
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonThemeDefaultVariants
import com.print.color.printcolor.ui.components.DatePickerTheme.PcSDatePicker
import com.print.color.printcolor.ui.components.DatePickerTheme.rememberSelectedDate
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldDefaultVariants
import com.print.color.printcolor.ui.theme.PrintColorTheme

const val MAX_PIN_LENGTH = 6
const val MAX_PHONE_LENGTH = 10

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel) {
    Box {
        //ExtraSmoothWavyDiagonalBackground()
        Column(modifier = Modifier.padding(all = 16.dp)) {
            TopAppBarContent()
            TextFieldsContent(signUpViewModel = signUpViewModel)
        }
    }
}

@Composable
private fun ColumnScope.TopAppBarContent() {
    Row(
        modifier = Modifier
            .align(Alignment.Start)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            colorFilter = ColorFilter.tint(LocalContentColor.current),
            modifier = Modifier,
            painter = painterResource(R.drawable.ic_pcs_arrow_back),
            contentDescription = "back button"
        )
        Image(
            modifier = Modifier.size(width = 90.dp, height = 70.dp),
            painter = painterResource(R.drawable.ic_pc_logo),
            contentDescription = "Logo"
        )
        Text(
            text = stringResource(R.string.login_screen_create_account_title_text),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
private fun TextFieldsContent(signUpViewModel: SignUpViewModel) {
    val uiState by signUpViewModel.uiState.collectAsState()
    val isButtonEnabled = uiState.isValidFields()
    val arePINSame = uiState.arePINEquals()

    val firstNameValue = uiState.firstName
    val lastNameValue = uiState.lastName
    val phoneNumberValue = uiState.phoneNumber
    val userDateValue = uiState.userDate
    val pinValue = uiState.pin
    val pinConfirmationValue = uiState.pinConfirmation

    var showDialog by remember { mutableStateOf(false) }

    PrintColorTheme {
        Column(
            modifier = Modifier.padding(horizontal = 60.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PcsTextField(
                data = TextFieldDefaultVariants.textFieldOutlined(
                    label = "",
                    placeHolder = "First Name",
                    keyboardType = KeyboardType.Text,
                    leadingIcon = painterResource(R.drawable.ic_pcs_client)
                ),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    signUpViewModel.onFirstNameChanged(it)
                },
                value = firstNameValue,
                imeAction = ImeAction.Next
            )
            PcsTextField(
                data = TextFieldDefaultVariants.textFieldOutlined(
                    label = "",
                    placeHolder = "Last Name",
                    keyboardType = KeyboardType.Text,
                    leadingIcon = painterResource(R.drawable.ic_pcs_client)
                ),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    signUpViewModel.onLastNameChanged(it)
                },
                value = lastNameValue,
                imeAction = ImeAction.Next
            )

            PcsTextField(
                maxLength = MAX_PHONE_LENGTH,
                data = TextFieldDefaultVariants.textFieldOutlined(
                    label = "",
                    placeHolder = "Phone number",
                    keyboardType = KeyboardType.Number,
                    leadingIcon = painterResource(R.drawable.ic_pcs_phone),
                    isTextCountRequired = true
                ),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    signUpViewModel.onPhoneChanged(it)
                },
                value = phoneNumberValue,
                imeAction = ImeAction.Next
            )

            var selectedDate: MutableState<Long?> = rememberSelectedDate()
            PcSDatePicker(selectedDate = selectedDate)
            signUpViewModel.onUserDateChanged(selectedDate.value.toString())

            PcsTextField(
                maxLength = MAX_PIN_LENGTH,
                data = TextFieldDefaultVariants.textFieldOutlined(
                    label = "",
                    placeHolder = "PIN",
                    keyboardType = KeyboardType.Text,
                    leadingIcon = painterResource(R.drawable.ic_pcs_pin),
                    isTextCountRequired = true
                ),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    signUpViewModel.onPINChanged(it)
                },
                value = pinValue,
                imeAction = ImeAction.Next
            )

            PcsTextField(
                maxLength = MAX_PIN_LENGTH,
                data = TextFieldDefaultVariants.textFieldOutlined(
                    label = "",
                    placeHolder = "PIN confirmation",
                    keyboardType = KeyboardType.Text,
                    leadingIcon = painterResource(R.drawable.ic_pcs_pin),
                    isTextCountRequired = true
                ),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    signUpViewModel.onPINConfirmationChanged(it)
                },
                value = pinConfirmationValue,
                imeAction = ImeAction.Done
            )
            AnimatedVisibility(
                visible = !arePINSame,
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally()
            ) {
                Text(text = "PIN are not the same!")
            }
            PcsButton(
                isVisible = uiState.isUserSaved,
                onClick = {
                    signUpViewModel.onSignUp {
                        signUpViewModel.clearFields()
                        showDialog = true
                    }
                },
                data = ButtonThemeDefaultVariants.buttonDefaultData(
                    label = "Register me!",
                    type = ButtonType.OUTLINED,
                    contentDescription = "Content Description",
                    isEnabled = isButtonEnabled
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        if (showDialog) {
            PcSAlertDialog(
                data = AlertDialogDefaultVariants.alertDialogAnimation(
                    title = "User created successfully!",
                    message = "",
                    confirmButtonText = stringResource(R.string.alert_dialog_confirm_button_text),
                    dismissButtonText = "",
                    onConfirm = { showDialog = false },
                    dismissOnClickOutside = false,
                    type = AlertDialogType.ANIMATION,
                    onDismiss = {},
                    lottieAnimation = R.raw.pcs_success_anim,
                ),
                autoPlayAnimation = true,
                animationRepeatCount = 1
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=1280dp,height=800dp,dpi=240",
    locale = "es"
)
@Composable
fun SignUpScreenPreview(modifier: Modifier = Modifier) {
    //SignUpScreen()
}