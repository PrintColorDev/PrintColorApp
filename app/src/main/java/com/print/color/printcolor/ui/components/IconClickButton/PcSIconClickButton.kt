package com.print.color.printcolor.ui.components.IconClickButton

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.IconClickButton.model.IconClickButtonData
import com.print.color.printcolor.ui.theme.PrintColorTheme

@Composable
fun PcsIconClickButton(
    modifier: Modifier = Modifier,
    data: IconClickButtonData,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.then(modifier)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onPrimary)
            .clickable{
                onClick()
            }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = data.icon,
                contentDescription = null,
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(LocalContentColor.current)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PcsIconClickButtonPreview() {
    PrintColorTheme {
        val context: Context = LocalContext.current
        val data = IconClickButtonData(
            icon = painterResource(R.drawable.ic_pcs_close),
            contentDescription = "",
        )
        PcsIconClickButton(
            data = data,
            onClick = { Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show() })
    }
}