package com.example.currencyconverter.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R
import com.example.currencyconverter.data.AppUiState
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


private val _uiState = MutableStateFlow(AppUiState(topClicked = true))
val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainScreen(
    viewModel: AppViewModel,
    onNextButtonClicked: () -> Unit,
    onCryptoButtonClicked: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val topCurrency = uiState.topCurrency
    val bottomCurrency = uiState.bottomCurrency
    val quantityBottomCurrency = uiState.quantityBottomCurrency
    var textValue by remember { mutableStateOf("") }
    var doubleValue by remember { mutableDoubleStateOf(0.0) }
    Row (modifier = Modifier.size(600.dp)){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.size(600.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 25.dp, end = 25.dp, bottom = 25.dp),
                onClick = {
                    onNextButtonClicked()
                    viewModel.setTopClickedToTrue()
                }
            ) {
                    if (topCurrency != null) {
                        Text(
                            text = topCurrency.Ticker,
                            fontSize = 20.sp
                        )
                    }

            }
            Row (verticalAlignment = Alignment.CenterVertically){
                val imageSource = when (isSystemInDarkTheme()) {
                    true -> R.drawable.moneydark
                    else -> R.drawable.money
                }
                Image(
                    painter = painterResource(id = imageSource), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier
                    .width(60.dp)
                    .padding(10.dp)
                )
                OutlinedTextField(
                    value = textValue,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { newValue ->
                        textValue = newValue
                        doubleValue = newValue.toDoubleOrNull() ?: 0.0
                        viewModel.setQuantityTopCurrency(doubleValue)
                        println(doubleValue)
                    }
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 25.dp, end = 25.dp, bottom = 25.dp),
                onClick = {
                    onNextButtonClicked()
                    viewModel.setTopClickedToFalse()
                }
            ) {
                if (bottomCurrency != null) {
                    Text(
                        text = bottomCurrency.Ticker,
                        fontSize = 20.sp
                    )
                }
            }
            Row (verticalAlignment = Alignment.CenterVertically){
                val imageSource = when (isSystemInDarkTheme()) {
                    true -> R.drawable.moneydark
                    else -> R.drawable.money
                }
                Image(
                    painter = painterResource( id = imageSource), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier
                    .width(60.dp)
                    .padding(10.dp)
                )
                OutlinedTextField(readOnly = true, value = quantityBottomCurrency.toString(), onValueChange = { } )
            }
            var toggled by remember {
                mutableStateOf(false)
            }
            var toggled2 by remember {
                mutableStateOf(false)
            }
            val animatedPadding by animateDpAsState(
                if (toggled) {
                    8.dp
                } else {
                    25.dp
                },
                label = "shape"
            )
            val animatedPadding2 by animateDpAsState(
                if (toggled2) {
                    8.dp
                } else {
                    25.dp
                },
                label = "shape"
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    modifier = Modifier
                        .width(180.dp)
                        .height(130.dp)
                        .padding(top = 60.dp, start = 10.dp, end = 10.dp),
                    onClick = { toggled2 = !toggled2 },
                    shape = RoundedCornerShape(animatedPadding2)
                ) {
                    Text(
                        text = "Money",
                        fontSize = 35.sp
                    )
                }

                OutlinedButton(
                    onClick = {onCryptoButtonClicked(); toggled = !toggled},
                    shape = RoundedCornerShape(animatedPadding),
                    modifier = Modifier
                        .width(180.dp)
                        .height(130.dp)
                        .padding(top = 60.dp, start = 10.dp, end = 2.dp)
                ) {
                    Text(
                        text = "Crypto",
                        fontSize = 35.sp
                    )
                }
            }
        }
    }

}
val viewModel = AppViewModel()
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CurrencyConverterTheme {
        MainScreen(viewModel = viewModel, onNextButtonClicked = {}, onCryptoButtonClicked = {})
    }
}
