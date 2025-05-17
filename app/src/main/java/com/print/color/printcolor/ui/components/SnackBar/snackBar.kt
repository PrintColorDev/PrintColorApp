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
import kotlinx.coroutines.delay

enum class SnackbarType {
    SUCCESS, ERROR, INFO
}

data class CustomSnackbarData(
    val message: String,
    val type: SnackbarType = SnackbarType.INFO
)

class SnackbarController {
    private val _snackbarData = mutableStateOf<CustomSnackbarData?>(null)
    val snackbarData: State<CustomSnackbarData?> = _snackbarData

    fun show(message: String, type: SnackbarType = SnackbarType.INFO) {
        _snackbarData.value = CustomSnackbarData(message, type)
    }

    fun dismiss() {
        _snackbarData.value = null
    }
}

@Composable
fun rememberSnackbarController(): SnackbarController {
    return remember { SnackbarController() }
}

@Composable
fun CustomSnackbar(
    snackbarData: CustomSnackbarData,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = Color(0xAA5EB624)       // Verde fuerte para el borde
    val backgroundColor = Color(0x515FCE65)   // Verde con transparencia (~67%)

    LaunchedEffect(Unit) {
        delay(3000)
        onDismiss()
    }

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {
        Box(
            modifier = modifier
                //.background(bac)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Surface(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(2.dp, borderColor),
                shadowElevation = 6.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .background(backgroundColor)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = when (snackbarData.type) {
                            SnackbarType.SUCCESS -> Icons.Default.CheckCircle
                            SnackbarType.ERROR -> Icons.Default.Edit
                            SnackbarType.INFO -> Icons.Default.Info
                        },
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = snackbarData.message,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MyScreen() {
    val snackbarController = rememberSnackbarController()

    Box(modifier = Modifier.fillMaxSize()) {
        // Botón de prueba
        Button(
            onClick = {
                snackbarController.show("¡Operación exitosa!", SnackbarType.SUCCESS)
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Mostrar Snackbar")
        }

        // Mostrar Snackbar cuando haya uno disponible
        snackbarController.snackbarData.value?.let { data ->
            CustomSnackbar(
                snackbarData = data,
                onDismiss = { snackbarController.dismiss() },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}


