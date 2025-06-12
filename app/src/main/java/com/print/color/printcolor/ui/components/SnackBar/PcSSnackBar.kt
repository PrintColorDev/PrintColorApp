package com.print.color.printcolor.ui.components.SnackBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarColors
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarData
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarData.SnackBarType
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarDefaultVariants
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarDefaults
import com.print.color.printcolor.ui.theme.PrintColorTheme
import kotlinx.coroutines.delay

class SnackBarController {
    private val _snackBarData = mutableStateOf<SnackBarData?>(null)
    val snackBarData: State<SnackBarData?> = _snackBarData

    fun show(data: SnackBarData) {
        _snackBarData.value = data
    }

    fun dismiss() {
        _snackBarData.value = null
    }
}

@Composable
fun rememberSnackBarController(): SnackBarController {
    return remember { SnackBarController() }
}

@Composable
fun PcSDSnackBar(
    modifier: Modifier = Modifier,
    colors: SnackBarColors = SnackBarDefaults.snackBarColors(),
    snackBarData: SnackBarData,
    onDismiss: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(3000)
        onDismiss()
    }

    when (snackBarData.type) {
        SnackBarType.SB_SUCCESS -> {
            SnackBarSection(
                modifier = modifier,
                colors = colors,
                snackBarType = snackBarData.type,
                snackBarMessage = snackBarData.message
            )
        }

        SnackBarType.SB_ERROR -> {
            SnackBarSection(
                modifier = modifier,
                colors = colors,
                snackBarType = snackBarData.type,
                snackBarMessage = snackBarData.message
            )
        }

        SnackBarType.SB_INFO -> {
            SnackBarSection(
                modifier = modifier,
                colors = colors,
                snackBarType = snackBarData.type,
                snackBarMessage = snackBarData.message
            )
        }

        SnackBarType.SB_ACTION -> {

        }
    }
}

@Composable
private fun SnackBarSection(
    modifier: Modifier,
    colors: SnackBarColors,
    snackBarType: SnackBarType,
    snackBarMessage: String
) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {
        Box(
            modifier = Modifier.then(modifier)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Surface(
                color = getBackgroundColor(snackBarType = snackBarType, colors = colors),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    2.dp,
                    getBorderColor(snackBarType = snackBarType, colors = colors)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            getBackgroundColor(
                                snackBarType = snackBarType,
                                colors = colors
                            )
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = when (snackBarType) {
                            SnackBarType.SB_SUCCESS -> Icons.Default.CheckCircle
                            SnackBarType.SB_ERROR -> Icons.Default.Edit
                            SnackBarType.SB_INFO -> Icons.Default.Info
                            SnackBarType.SB_ACTION -> Icons.Default.Info
                        },
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = snackBarMessage,
                        color = colors.textColor,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

/** Fun to get the border color of the snackBar*/
private fun getBorderColor(snackBarType: SnackBarType, colors: SnackBarColors): Color {
    return when (snackBarType) {
        SnackBarType.SB_SUCCESS -> colors.borderSuccessColor
        SnackBarType.SB_ERROR -> colors.borderErrorColor
        SnackBarType.SB_INFO -> colors.borderInfoColor
        SnackBarType.SB_ACTION -> colors.backgroundSuccessColor
    }
}

/** Fun to get the background color of the snackBar*/
private fun getBackgroundColor(snackBarType: SnackBarType, colors: SnackBarColors): Color {
    return when (snackBarType) {
        SnackBarType.SB_SUCCESS -> colors.backgroundSuccessColor
        SnackBarType.SB_ERROR -> colors.backgroundErrorColor
        SnackBarType.SB_INFO -> colors.backgroundInfoColor
        SnackBarType.SB_ACTION -> colors.backgroundSuccessColor
    }
}


@Preview(showBackground = true)
@Composable
fun MyScreen() {
    val snackBarController = rememberSnackBarController()
    PrintColorTheme {

        Box(modifier = Modifier.fillMaxSize()) {
            // Botón de prueba
            Button(onClick = {
                snackBarController.show(
                    SnackBarDefaultVariants.snackBarSuccess("Operación completada", icon = null)
                )
            }) {
                Text("Mostrar éxito")
            }

            // Mostrar Snackbar cuando haya uno disponible
            snackBarController.snackBarData.value?.let { data ->
                PcSDSnackBar(
                    snackBarData = data,
                    onDismiss = { snackBarController.dismiss() },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}


