package com.print.color.printcolor.ui.quotationList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldData
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldType.OUTLINED
import com.print.color.printcolor.ui.theme.PrintColorTheme

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=1280dp,height=800dp,dpi=240",
    locale = "es"
)
@Composable
fun QuotationListScreen(modifier: Modifier = Modifier) {
    PrintColorTheme {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PcsTextField(
                    data = TextFieldData(
                        textFieldType = OUTLINED,
                        label = "",
                        placeHolder = stringResource(R.string.quotation_list_screen_search_bar),
                        keyboardType = KeyboardType.Text,
                        leadingIcon = Icons.Rounded.Search
                    ),
                    modifier = Modifier.weight(1f),
                    onValueChange = { },
                    value = ""
                )
                IconButton(onClick = {}) {
                    Icon(
                        modifier = modifier,
                        painter = painterResource(R.drawable.ic_pcs_mode_list),
                        contentDescription = "view mode list",
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        modifier = modifier,
                        painter = painterResource(R.drawable.ic_pcs_filter),
                        contentDescription = "Filter",
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
            PcSSteps()
            QuotationList()
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun PcSSteps(modifier: Modifier = Modifier) {
    PrintColorTheme {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PcSCompleteStepImage()
                PcsStepDivider()
                PcSCompleteStepImage()
                PcsStepDivider()
                PcSCompleteStepImage()
                PcsStepDivider()
                PcSCompleteStepImage()
                PcsStepDivider()
                PcSUnCompleteStepImage()
                PcsStepDivider()
                PcSUnCompleteStepImage()
            }
        }
    }
}

@Composable
fun PcsStepDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .size(50.dp),
        color = Color.Black
    )
}

//@Preview()
@Composable
fun PcSCompleteStepImage(modifier: Modifier = Modifier) {
    PrintColorTheme {
        Icon(
            modifier = modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFF8BC34A))
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                    shape = CircleShape
                ),
            imageVector = Icons.Rounded.ShoppingCart,
            contentDescription = "Checked",
            tint = Color.White
        )
    }
}

@Preview()
@Composable
fun PcSUnCompleteStepImage(modifier: Modifier = Modifier) {
    PrintColorTheme {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .border(
                    BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary),
                    shape = CircleShape
                )
            ,
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pcs_survey),
                contentDescription = "Icono personalizado",
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}


@Composable
fun QuotationList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier
        .then(modifier)
        .fillMaxSize()) {
        items(100) {
            Text("Item $it")
        }
    }
}