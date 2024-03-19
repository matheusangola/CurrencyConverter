package com.example.currencyconverter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.Greeting
import com.example.currencyconverter.R
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme

@Composable
fun MainScreen(
    onNextButtonClicked: () -> Unit,
    onCryptoButtonClicked: () -> Unit,
) {
    var enteredValue1 by remember { mutableStateOf("") }
    var enteredValue2 by remember { mutableStateOf("") }
    Row {
        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                modifier = Modifier.fillMaxWidth() .padding(10.dp),
                onClick = onNextButtonClicked
            ) {
                Text(
                    text = "USD",
                    fontSize = 16.sp
                )
            }
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(painter = painterResource(id = R.drawable.crypto), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.width(40.dp) .padding(8.dp))
                OutlinedTextField(value = enteredValue1, onValueChange = { newValue -> enteredValue1 = newValue } )
            }
            Button(
                modifier = Modifier.fillMaxWidth() .padding(10.dp),
                onClick = onNextButtonClicked
            ) {
                Text(
                    text = "CAD",
                    fontSize = 16.sp
                )
            }
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(painter = painterResource(id = R.drawable.money), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.width(40.dp) .padding(8.dp))
                OutlinedTextField(value = enteredValue2, onValueChange = { newValue -> enteredValue2 = newValue } )
            }
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Button(
                    modifier = Modifier,
                    onClick = {  }
                ) {
                    Text(
                        text = "Dollar",
                        fontSize = 16.sp
                    )
                }

                OutlinedButton(
                    onClick = onCryptoButtonClicked,
                    modifier = Modifier,
                ) {
                    Text(
                        text = "Crypto",
                        fontSize = 16.sp
                    )
                }
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CurrencyConverterTheme {
        MainScreen(onNextButtonClicked = {}, onCryptoButtonClicked = {})
    }
}
