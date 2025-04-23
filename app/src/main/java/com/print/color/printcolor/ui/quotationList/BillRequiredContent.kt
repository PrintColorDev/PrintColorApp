package com.print.color.printcolor.ui.quotationList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.print.color.printcolor.R
import com.print.color.printcolor.domain.model.Quotation

@Composable
fun BillRequiredContent(
    modifier: Modifier = Modifier,
    quotation: Quotation?,
) {
    Column(modifier = Modifier.then(modifier)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(
                        R.string.quotation_list_bottom_sheet_details_tax_regime,
                        quotation?.taxRegime.orEmpty()
                    )
                )
                Text(
                    text = stringResource(
                        R.string.quotation_list_bottom_sheet_details_state,
                        quotation?.state.orEmpty()
                    )
                )
                Text(
                    text = stringResource(
                        R.string.quotation_list_bottom_sheet_details_municipality,
                        quotation?.municipality.orEmpty()
                    )
                )
                Text(
                    text = stringResource(
                        R.string.quotation_list_bottom_sheet_details_address,
                        quotation?.address.orEmpty()
                    )
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(
                        R.string.quotation_list_bottom_sheet_details_zip_code,
                        quotation?.zipCode.orEmpty()
                    )
                )
                Text(
                    text = stringResource(
                        R.string.quotation_list_bottom_sheet_details_cfdi,
                        quotation?.cfdi.orEmpty()
                    )
                )
                Text(
                    text = stringResource(
                        R.string.quotation_list_bottom_sheet_details_rfc,
                        quotation?.rfc.orEmpty()
                    )
                )
                Text(
                    text = stringResource(
                        R.string.quotation_list_bottom_sheet_details_payment_method,
                        quotation?.paymentMethod.orEmpty()
                    )
                )
            }
        }
    }
}