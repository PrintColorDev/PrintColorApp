package com.print.color.printcolor.ui.components.SwitchTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.ui.components.SwitchTheme.model.SwitchData
import com.print.color.printcolor.ui.components.SwitchTheme.model.SwitchDefaultVariants
import com.print.color.printcolor.ui.theme.PrintColorTheme

@Composable
fun PcSSwitch(modifier: Modifier = Modifier, data: SwitchData) {
    Row(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = data.switchString)
        Spacer(modifier = Modifier.weight(1f))
        Switch(onCheckedChange = { data.onCheckedChange(it) }, checked = data.isChecked,
            thumbContent = {
                if (data.isChecked) {
                    Icon(
                        modifier = modifier.size(16.dp),
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Checked",
                        tint = Color.Black
                    )
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun PcSSwitchPreview(modifier: Modifier = Modifier) {
    var switchValue by remember { mutableStateOf(false) }
    val switchData =
        SwitchDefaultVariants.switchDefault(
            switchString = "Switch State: $switchValue",
            isChecked = switchValue,
            onCheckedChange = { switchValue = it },
            contentDescription = "Switch $switchValue")
    PrintColorTheme {
        PcSSwitch(data = switchData, modifier = modifier)
    }
}