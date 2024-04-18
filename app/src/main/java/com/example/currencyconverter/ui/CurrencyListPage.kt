package com.example.currencyconverter.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.data.Currency
import com.example.currencyconverter.data.DataSource
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme

@Composable
fun CurrencyListPage(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onCardClicked: (Currency) -> Unit,
) {
    val currencies = DataSource.currencies
    LazyColumn(contentPadding = contentPadding) {
        itemsIndexed(currencies) { _, currency ->
            CurrencyListItems(
                currency = currency,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onCardClicked = { onCardClicked(currency) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyListItems(
    currency: Currency,
    modifier: Modifier = Modifier,
    onCardClicked: () -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier,
        onClick = onCardClicked
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = currency.Ticker,
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = currency.Description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyListPagePreview() {
    CurrencyConverterTheme {
        CurrencyListPage(onCardClicked = {})
    }
}
