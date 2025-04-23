package com.print.color.printcolor.ui.components.IconClickButton

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.print.color.printcolor.utils.showToast

/** region principal component */
/**
 * @param modifier: The modifier to be applied to the button.
 * @param data: The data to be displayed in the button.
 * @param onClick: The action to be performed when the button is clicked.*/
@Composable
fun PcsIconClickButton(
    modifier: Modifier = Modifier,
    data: IconClickButtonData,
    onClick: () -> Unit,
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
            data.icon?.let {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = it,
                    contentDescription = data.contentDescription,
                    contentScale = ContentScale.Inside,
                    colorFilter = ColorFilter.tint(LocalContentColor.current)
                )
            }
        }
    }
}
/** endregion principal component */

/** region preview component */
@Preview(showBackground = true)
@Composable
fun PcsIconClickButtonPreview() {
    PrintColorTheme {
        val context: Context = LocalContext.current
        val data = IconClickButtonData(
            icon = painterResource(R.drawable.ic_pc_logo),
            contentDescription = "Some test content description",
        )
        PcsIconClickButton(
            data = data,
            onClick = { showToast(context, "Button click") })
    }
}
/** endregion preview component */