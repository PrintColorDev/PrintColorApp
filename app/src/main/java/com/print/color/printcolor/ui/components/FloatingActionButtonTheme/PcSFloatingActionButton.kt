package com.print.color.printcolor.ui.components.FloatingActionButtonTheme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.ButtonAnimationState.STATIC
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.FloatingActionButtonData
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.TypeFAB.ANIMATED_FAB
import com.print.color.printcolor.ui.components.FloatingActionButtonTheme.model.TypeFAB.SMALL_FAB

private val FABMinimumCollapsedWidth = 24.dp
private val FABMinimumContentHeight = 24.dp
private val FABRoundedCornerShapeSize = 32.dp
private val FABContentPadding = 20.dp
private val FABEndIconPadding = 12.dp
private val iconSize = 24.dp
private const val characterLimit = 25
private const val DurationShort = 100.0
private const val DurationShort2 = 200.0
private const val DurationLong = 500.0
private val CollapseAnimation = fadeOut(
    animationSpec = tween(
        durationMillis = DurationShort.toInt(),
    )
) + shrinkHorizontally(
    animationSpec = tween(
        durationMillis = DurationLong.toInt(),
    ),
    shrinkTowards = Alignment.End,
)
private val ExpandAnimation = fadeIn(
    animationSpec = tween(
        durationMillis = DurationShort2.toInt(),
        delayMillis = DurationShort.toInt(),
    ),
) + expandHorizontally(
    animationSpec = tween(
        durationMillis = DurationLong.toInt(),
    ),
    expandFrom = Alignment.End,
)

@Composable
fun rememberButtonState(default: ButtonAnimationState = STATIC): MutableState<ButtonAnimationState> =
    remember { mutableStateOf(default) }

enum class ButtonAnimationState {
    STATIC,
    EXPAND,
    COLLAPSE
}

@Composable
fun PcSFloatingActionButton(
    data: FloatingActionButtonData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    when (data.typeFAB) {
        SMALL_FAB -> {
            SmallFloatingActionButton(onClick = { onClick() }, modifier = modifier) {
                data.leadingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Leading Icon",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        ANIMATED_FAB -> {
            val buttonShape = RoundedCornerShape(size = FABRoundedCornerShapeSize)

            FloatingActionButton(
                onClick = onClick,
                modifier = modifier.elevation3(shape = buttonShape),
                shape = buttonShape,
                containerColor = colors.backgroundColor,
                contentColor = colors.contentColor,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
            ) {
                FloatingActionButtonContent(
                    buttonContent = buttonContent,
                    animationState = animationState,
                    colors = colors
                )
            }

        }
    }
}

@Composable
private fun FloatingActionButtonContent(
    buttonContent: FloatingActionButtonData,
    animationState: ButtonAnimationState,
    colors: FloatingActionButtonColors
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .sizeIn(
                    minWidth = FABMinimumCollapsedWidth,
                    minHeight = FABMinimumContentHeight
                )
                .padding(all = FABContentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (animationState == STATIC || buttonContent.text.isEmpty()) {
                StaticContent(buttonContent = buttonContent, colors = colors)
            } else {
                DynamicContent(
                    buttonContent = buttonContent,
                    state = animationState,
                    colors = colors
                )
            }
        }
    }
}

@Composable
private fun StaticContent(
    buttonContent: FloatingActionButtonData,
    colors: FloatingActionButtonColors
) {
    CclImage(
        modifier = Modifier.size(iconSize),
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(color = colors.contentColor),
        item = buttonContent.icon
    )
    if (buttonContent.text.isNotEmpty()) {
        TextSection(
            text = buttonContent.text,
            textReader = buttonContent.contentDescription,
            colors = colors
        )
    }
}

@Composable
private fun RowScope.DynamicContent(
    buttonContent: FloatingActionButtonData,
    state: ButtonAnimationState,
    colors: FloatingActionButtonColors
) {
    CclImage(
        modifier = Modifier.size(iconSize),
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(color = colors.contentColor),
        item = buttonContent.icon
    )
    AnimatedVisibility(
        visible = state == EXPAND,
        enter = ExpandAnimation,
        exit = CollapseAnimation,
    ) {
        TextSection(
            text = buttonContent.text,
            textReader = buttonContent.contentDescription,
            colors = colors
        )
    }
}

@Composable
private fun TextSection(
    text: String,
    textReader: String,
    colors: FloatingActionButtonColors
) {
    Row {
        Spacer(
            Modifier.width(
                FABEndIconPadding
            )
        )
        val truncatedText: String = text.take(n = characterLimit)

        Text(
            text = truncatedText,
            modifier = Modifier.semantics { contentDescription = textReader },
            style = floatingActionButtonTypography.primaryTextTextStyle,
            color = colors.contentColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PcSFloatingActionButtonPreview(modifier: Modifier = Modifier) {
    val data = FloatingActionButtonData(
        stringFAB = "FAB",
        leadingIcon = Icons.Default.Add,
        typeFAB = SMALL_FAB
    )
    PcSFloatingActionButton(data = data, onClick = {})
}