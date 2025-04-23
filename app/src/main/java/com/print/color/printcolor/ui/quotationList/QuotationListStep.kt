package com.print.color.printcolor.ui.quotationList

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.theme.PrintColorTheme

@Composable
fun PcSStepImage(
    @DrawableRes icon: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    isCompleted: Boolean? = false,
    onClick: () -> Unit = {}
) {
    PrintColorTheme {
        val isCompletedModifier: Modifier =
            if (isCompleted == true) Modifier.background(MaterialTheme.colorScheme.primaryContainer) else Modifier.background(
                MaterialTheme.colorScheme.onPrimary
            )
        Box(
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(if (isCompleted == true) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimary)
                .border(
                    BorderStroke(
                        1.dp,
                        if (isCompleted == true) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.tertiary
                    ),
                    shape = CircleShape
                )
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = contentDescription,
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PcSStepImagePreview() {
    Column {
        PcSStepImage(icon = R.drawable.ic_pcs_design, contentDescription = "", isCompleted = true)
        PcSStepImage(icon = R.drawable.ic_pcs_design, contentDescription = "", isCompleted = false)
    }
}