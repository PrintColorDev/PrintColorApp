package com.print.color.printcolor.ui.components.SnackBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarColors
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarData
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarData.SnackBarType
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarDefaultVariants
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarDefaults
import com.print.color.printcolor.ui.theme.PrintColorTheme
import kotlinx.coroutines.delay

/** Class to handle the SnackBar controller to show or dismiss the snackBar.*/
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

/** SnackBar Controller to show or dismiss the snackBar.*/
@Composable
fun rememberSnackBarController(): SnackBarController {
    return remember { SnackBarController() }
}

/**
 * @param modifier Modifier
 * @param colors SnackBarColors
 * @param snackBarData SnackBarData to handle the snackBar variants
 * @param onDismiss Function to dismiss the snackBar
 * */

@Composable
fun PcSSnackBar(
    modifier: Modifier = Modifier,
    colors: SnackBarColors = SnackBarDefaults.snackBarColors(),
    snackBarData: SnackBarData,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(snackBarData) {
        isVisible = true
        delay(3000)
        isVisible = false
        delay(300)
        onDismiss()
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
    ) {
        SnackBarSectionContent(
            modifier = modifier,
            colors = colors,
            snackBarType = snackBarData.type,
            snackBarMessage = snackBarData.message
        )
    }
}

/** Private fun to handle the principal SnackBar section content.*/
@Composable
private fun SnackBarSectionContent(
    modifier: Modifier,
    colors: SnackBarColors,
    snackBarType: SnackBarType,
    snackBarMessage: String
) {
    Box(
        modifier = Modifier
            .then(modifier)
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
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = getSnackBarIcon(snackBarType = snackBarType),
                    contentDescription = null,
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

/** Fun to get SnackBar Icon.*/
@Composable
private fun getSnackBarIcon(snackBarType: SnackBarType): Painter {
    return when (snackBarType) {
        SnackBarType.SB_SUCCESS -> painterResource(R.drawable.ic_pcs_success_alert)
        SnackBarType.SB_ERROR -> painterResource(R.drawable.ic_pcs_error_alert)
        SnackBarType.SB_INFO -> painterResource(R.drawable.ic_pcs_info_alert)
        SnackBarType.SB_ACTION -> painterResource(R.drawable.ic_pcs_success_alert)
    }
}

/** Fun to get the border color of the snackBar.*/
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
fun SnackBarPreview() {
    PrintColorTheme {
        var snackBarData by remember { mutableStateOf<SnackBarData?>(null) }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    snackBarData = SnackBarDefaultVariants.snackBarSuccess("Success message")
                }) {
                    Text("Show Success SnackBar")
                }

                Button(onClick = {
                    snackBarData = SnackBarDefaultVariants.snackBarError("Error message")
                }) {
                    Text("Show Error SnackBar")
                }

                Button(onClick = {
                    snackBarData = SnackBarDefaultVariants.snackBarInfo("Info message")
                }) {
                    Text("Show Info SnackBar")
                }
            }

            snackBarData?.let { data ->
                PcSSnackBar(
                    snackBarData = data,
                    onDismiss = { snackBarData = null },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}