package com.print.color.printcolor.ui.quotationList

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.ui.theme.PrintColorTheme
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY6
import com.print.color.printcolor.utils.getIconForStep

@Composable
fun RowScope.QuotationListItem(
    quotation: Quotation,
    onQuotationClick: () -> Unit = {}
) {
    PrintColorTheme {
        Card(
            modifier = Modifier
                .padding(all = 8.dp)
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    Log.i("click", "click quotation")
                    onQuotationClick()
                }
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text("ID: ${quotation.id}", fontWeight = FontWeight.Bold)
                    Text("Contact: ${quotation.contact}")
                    Text("Client: ${quotation.clientName}")
                    Text("Customer name: ${quotation.customerName}")
                    if (quotation.currentStep == CONST_QUOTATION_STEP_KEY6)
                        Row(
                            //modifier = Modifier.padding(all = 4.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                modifier = Modifier.padding(end = 4.dp),
                                text = "Quotation completed"
                            )
                            Icon(
                                painter = painterResource(R.drawable.ic_pcs_verified),
                                contentDescription = "Filter",
                                tint = MaterialTheme.colorScheme.surfaceTint
                            )
                        }
                        /*Card(
                            modifier = Modifier
                                .padding(all = 6.dp)
                                .align(Alignment.End)
                                *//*.border(
                                    BorderStroke(0.5f.dp, MaterialTheme.colorScheme.onBackground),
                                    shape = RoundedCornerShape(6.dp)
                                )*//*,
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                        ) {

                        }*/
                }
                PcSStepImage(
                    icon = getIconForStep(quotation.currentStep),
                    contentDescription = "",
                    modifier = Modifier.align(
                        Alignment.CenterVertically
                    )
                )
            }
        }
    }
}