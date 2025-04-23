package com.print.color.printcolor.ui.quotationList

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.domain.model.Quotation
import com.print.color.printcolor.ui.theme.PrintColorTheme
import com.print.color.printcolor.utils.CONST_QUOTATION_STEP_KEY6
import com.print.color.printcolor.utils.formatPhoneNumber
import com.print.color.printcolor.utils.getCorrectStepDescriptionByStepKey
import com.print.color.printcolor.utils.getIconForStep

/** region ItemList View */
/** Quotation list item view */
@Composable
fun RowScope.QuotationListItem(
    quotation: Quotation,
    onQuotationClick: () -> Unit = {}
) {
    val context = LocalContext.current

    PrintColorTheme {
        Card(
            modifier = Modifier
                .padding(all = 8.dp)
                .clickable {
                    onQuotationClick()
                }
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(all = 16.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {

                    /** Quotation item list left content */
                    QuotationItemListLeftContent(
                        quotationId = quotation.id,
                        quotationCustomerName = quotation.customerName,
                        quotationClientName = quotation.clientName
                    )

                    /** Quotation item list right content */
                    QuotationItemRightContent(quotationContact = quotation.contact)
                }

                /** Quotation created date content */
                QuotationCreatedDateContent()

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) // TODO use horizontal divider utils
                /** Quotation current step content */
                QuotationCurrentStepContent(
                    currentStep = quotation.currentStep,
                    context = context
                )
                if (quotation.currentStep == CONST_QUOTATION_STEP_KEY6)
                    QuotationCompletedAnnotation(modifier = Modifier.align(Alignment.End))
                if (quotation.deleted)
                    QuotationDeletedAnnotation(modifier = Modifier.align(Alignment.End))
            }
        }
    }
}

/** Private fun handle the quotation completed annotation view*/
@Preview(showBackground = true)
@Composable
private fun QuotationCompletedAnnotation(modifier: Modifier = Modifier) {
    PrintColorTheme {
        Row(
            modifier = Modifier.then(modifier),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .align(Alignment.CenterVertically),
                text = stringResource(R.string.quotation_item_list_completed_annotation)
            )
            Icon(
                painter = painterResource(R.drawable.ic_pcs_verified),
                contentDescription = "quotation completed",
                tint = MaterialTheme.colorScheme.surfaceTint
            )
        }
    }
}

@Composable
private fun RowScope.QuotationItemListLeftContent(
    quotationId: String,
    quotationCustomerName: String,
    quotationClientName: String
) {
    Column(modifier = Modifier.weight(1f)) {
        Text(
            text = stringResource(
                R.string.quotation_item_list_annotation_id_text,
                quotationId
            )
        )
        Text(
            text = stringResource(
                R.string.quotation_item_list_annotation_customer_name_text,
                quotationCustomerName
            )
        )
        Text(
            text = stringResource(
                R.string.quotation_item_list_annotation_client_text,
                quotationClientName
            )
        )
    }
}

@Composable
fun RowScope.QuotationItemRightContent(
    quotationContact: String,
) {
    Column(modifier = Modifier.weight(1f)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_pcs_phone),
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.onTertiary
            )
            Text(formatPhoneNumber(quotationContact))
        }
        Avatar()
    }
}

@Composable
fun QuotationCurrentStepContent(currentStep: String, context: Context) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PcSStepImage(
            icon = getIconForStep(currentStep),
            contentDescription = "",
            modifier = Modifier.align(
                Alignment.CenterVertically
            )
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = getCorrectStepDescriptionByStepKey(
                context = context,
                stepKey = currentStep
            )
        )
    }
}

@Composable
private fun QuotationCreatedDateContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(R.drawable.ic_pcs_calendar),
            contentDescription = "Filter",
            tint = MaterialTheme.colorScheme.onTertiary
        )
        Text(text = stringResource(R.string.quotation_item_list_annotation_created_date_text, "01/01/2025"))
    }
}

/** region ItemList Preview */
/** Private fun handle the quotation deleted annotation view*/
@Preview(showBackground = true)
@Composable
private fun QuotationDeletedAnnotation(modifier: Modifier = Modifier) {
    PrintColorTheme {
        Row(
            modifier = Modifier.then(modifier),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .align(Alignment.CenterVertically),
                text = stringResource(R.string.quotation_item_list_deleted_annotation)
            )
            Icon(
                painter = painterResource(R.drawable.ic_pcs_close),
                contentDescription = "quotation deleted",
                tint = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}