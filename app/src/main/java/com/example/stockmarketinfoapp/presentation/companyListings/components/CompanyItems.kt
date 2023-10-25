package com.example.stockmarketinfoapp.presentation.companyListings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarketinfoapp.domain.model.CompanyListing
import com.example.stockmarketinfoapp.ui.theme.StockMarketInfoAppTheme

@Composable
fun CompanyItem(
    company: CompanyListing,
    modifier: Modifier = Modifier,
    onClick:  () -> Unit
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = modifier.clickable {
                onClick.invoke()
        }
    ) {
        Box(
            modifier = modifier.padding(8.dp)
        ){
            Row(
                modifier = modifier.padding(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = company.name,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = modifier.weight(1f)
                        )

                        Text(
                            text = company.exchange,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Spacer(modifier = modifier.height(6.dp))
                    Text(
                        text = "(${company.symbol})",
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CardPreview(){
    StockMarketInfoAppTheme {
        CompanyItem(
            company = CompanyListing(
                name = "Tesla",
                symbol = "AA",
                exchange = "TSLA"
            ),
            onClick = {}
        )
    }
}
