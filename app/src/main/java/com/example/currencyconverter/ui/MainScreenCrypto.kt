package com.example.currencyconverter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme

@Composable
fun MainScreenCrypto(
    viewModel: AppViewModel,
    onNextButtonClicked: () -> Unit,
    onCryptoButtonClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val topCrypto = uiState.topCrypto
    val bottomCrypto = uiState.bottomCrypto
    val quantityBottomCrypto = uiState.quantityBottomCrypto
    var textValue by remember { mutableStateOf("") }
    var doubleValue by remember { mutableDoubleStateOf(0.0) }
    Row (modifier = Modifier.size(600.dp)){
        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.size(600.dp)) {
            Button(
                modifier = Modifier.fillMaxWidth() .padding(top = 60.dp, start = 25.dp, end = 25.dp, bottom = 25.dp),
                onClick = {
                    onNextButtonClicked()
                    viewModel.setTopClickedToTrue()
                }
            ) {
                if (topCrypto != null) {
                    Text(
                        text = topCrypto.Ticker,
                        fontSize = 20.sp
                    )
                }
            }
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(painter = painterResource(id = R.drawable.crypto), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.width(60.dp) .padding(10.dp))
                OutlinedTextField(
                    value = textValue,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { newValue ->
                        textValue = newValue
                        doubleValue = newValue.toDoubleOrNull() ?: 0.0
                        viewModel.setQuantityTopCrypto(doubleValue)
                        println(doubleValue)
                    }
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth() .padding(top = 60.dp, start = 25.dp, end = 25.dp, bottom = 25.dp),
                onClick = {
                    onNextButtonClicked()
                    viewModel.setTopClickedToFalse()
                }
            ) {
                if (bottomCrypto != null) {
                    Text(
                        text = bottomCrypto.Ticker,
                        fontSize = 20.sp
                    )
                }
            }
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(painter = painterResource(id = R.drawable.crypto), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.width(60.dp) .padding(10.dp))
                OutlinedTextField(readOnly = true, value = quantityBottomCrypto.toString(), onValueChange = { } )
            }
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                OutlinedButton(
                    modifier = Modifier.width(180.dp).height(130.dp).padding(top = 60.dp, start = 10.dp, end = 10.dp),
                    onClick = onCryptoButtonClicked
                ) {
                    Text(
                        text = "Dollar",
                        fontSize = 40.sp
                    )
                }

                Button(
                    onClick = {  },
                    modifier = Modifier.width(180.dp).height(130.dp).padding(top = 60.dp, start = 10.dp, end = 2.dp),
                ) {
                    Text(
                        text = "Crypto",
                        fontSize = 40.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenCryptoPreview() {
    CurrencyConverterTheme {
        MainScreenCrypto(viewModel = viewModel, onNextButtonClicked = {}, onCryptoButtonClicked = {})
    }
}