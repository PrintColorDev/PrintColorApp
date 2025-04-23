package com.print.color.printcolor.ui.components.AlertDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.print.color.printcolor.ui.theme.PrintColorTheme
import com.print.color.printcolor.R
import androidx.compose.runtime.getValue
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogData.AlertDialogType
import com.print.color.printcolor.ui.components.AlertDialog.model.AlertDialogDefaultVariants
import com.print.color.printcolor.ui.components.ButtonTheme.PcsButton
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonThemeDefaultVariants

@Composable
fun PcSAlertDialog(
    data: AlertDialogData,
    modifier: Modifier = Modifier,
    autoPlayAnimation: Boolean = true,
    animationRepeatCount: Int = LottieConstants.IterateForever,
) {
    PrintColorTheme {
        when (data.type) {
            AlertDialogType.CONFIRMATION -> {
                AlertDialog(
                    onDismissRequest = {
                        if (data.dismissOnClickOutside) data.onDismiss()
                    },
                    title = {
                        Text(
                            text = data.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    text = {
                        Text(
                            text = data.message,
                            fontSize = 16.sp
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = data.onConfirm) {
                            Text(
                                text = data.confirmButtonText,
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = data.onDismiss) {
                            Text(
                                text = data.dismissButtonText,
                            )
                        }
                    }
                )
            }

            AlertDialogType.ANIMATION -> {
                AlertDialog(
                    modifier = modifier.wrapContentWidth(),
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    onDismissRequest = {
                        if (data.dismissOnClickOutside) data.onDismiss()
                    },
                    title = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = data.title,
                                fontSize = 20.sp
                            )
                        }
                    },
                    text = {
                        // Lottie Animation
                        data.lottieAnimation?.let {
                            val composition by rememberLottieComposition(
                                LottieCompositionSpec.RawRes(
                                    data.lottieAnimation
                                )
                            )
                            val progress by animateLottieCompositionAsState(
                                composition = composition,
                                iterations = animationRepeatCount,
                                isPlaying = autoPlayAnimation
                            )

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                LottieAnimation(
                                    composition = composition,
                                    progress = progress,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .size(120.dp)
                                )
                            }
                        }
                    },
                    confirmButton = {
                        PcsButton(
                            onClick = {
                                data.onConfirm()
                            },
                            data = ButtonThemeDefaultVariants.buttonDefaultData(
                                label = data.confirmButtonText,
                                type = ButtonType.TEXT,
                                contentDescription = "Content Description",
                            ),
                            modifier = Modifier
                        )
                    }
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PcSAlertDialogPreview() {
    PrintColorTheme {
        var showDialog = remember { mutableStateOf(false) }
        var showDialogAnimation = remember { mutableStateOf(false) }

        val dataSample1 = AlertDialogDefaultVariants.alertDialogDefault(
            title = "Alert Dialog Title",
            message = "This is an alert dialog message",
            confirmButtonText = "OK",
            dismissButtonText = "Cancel",
            onConfirm = {},
            onDismiss = {},
            type = AlertDialogType.CONFIRMATION,
            dismissOnClickOutside = true
        )

        val dataSample2 = AlertDialogDefaultVariants.alertDialogAnimation(
            title = "Alert Dialog Title",
            message = "This is an alert dialog message",
            confirmButtonText = "OK",
            dismissButtonText = "Cancel",
            onConfirm = {},
            onDismiss = {},
            type = AlertDialogType.CONFIRMATION,
            dismissOnClickOutside = true,
            lottieAnimation = R.raw.pcs_success_anim
        )

        Column {
            Button(onClick = { showDialog.value = true }) {
                Text(text = "Show Dialog")
            }
            Button(onClick = { showDialog.value = true }) {
                Text(text = "Show Dialog Animation")
            }

            if (showDialog.value) {
                PcSAlertDialog(
                    data = dataSample1,
                    autoPlayAnimation = true,
                    animationRepeatCount = 1,
                )
            }

            if (showDialogAnimation.value) {
                PcSAlertDialog(
                    data = dataSample2,
                    autoPlayAnimation = true,
                    animationRepeatCount = 1,
                )
            }
        }
    }
}