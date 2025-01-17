package com.print.color.printcolor.ui.productQuotation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.print.color.printcolor.ui.components.ButtonTheme.ButtonData
import com.print.color.printcolor.ui.components.ButtonTheme.ButtonType
import com.print.color.printcolor.ui.components.ButtonTheme.PcsButton
import com.print.color.printcolor.ui.components.SwitchTheme.PcSSwitch
import com.print.color.printcolor.ui.components.SwitchTheme.model.SwitchData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldType.OUTLINED

@Composable
fun QuotationScreen() {
    /** Quotation Fields */
    var nameValue by remember { mutableStateOf("") }
    var clientNameValue by remember { mutableStateOf("") }
    var contactValue by remember { mutableStateOf("") }
    var extraDataValue by remember { mutableStateOf("") }
    val context = LocalContext.current

    /** Switch Value */
    var switchValue by remember { mutableStateOf(false) }
    val switchData =
        SwitchData(
            switchString = stringResource(R.string.quotation_screen_switch_label),
            isChecked = switchValue,
            onCheckedChange = { switchValue = it },
            contentDescription = "Switch billing $switchValue"
        )
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(all = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        /** Top Screen */
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                modifier = Modifier.background(Color.Black),
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Logo"
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleLarge,
                text = stringResource(R.string.quotation_screen_title, "David")
            )
        }
        /** Quotation Fields */
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "",
                placeHolder = "Customer Name",
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { nameValue = it },
            value = nameValue
        )
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "",
                placeHolder = "Client Name",
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { clientNameValue = it },
            value = clientNameValue
        )
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "",
                placeHolder = "Contact",
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { contactValue = it },
            value = contactValue
        )
        PcsTextField(
            data = TextFieldData(
                textFieldType = OUTLINED,
                label = "",
                placeHolder = "Extra Data",
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Filled.AccountCircle
            ),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { extraDataValue = it },
            value = extraDataValue
        )
        /** Switch Billing validation */
        PcSSwitch(data = switchData, modifier = Modifier.fillMaxWidth())
        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp))
        AnimatedVisibility(switchValue) {
            BillingScreenFields()
        }
        PcsButton(
            onClick = {
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
            },
            data = ButtonData(
                label = "Save Quotation",
                type = ButtonType.FILLED,
                contentDescription = "Content Description"
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=1280dp,height=800dp,dpi=240",
    locale = "es"
)
@Composable
fun QuotationScreenPreview() {
    QuotationScreen()
}