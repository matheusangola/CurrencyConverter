package com.example.currencyconverter.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.data.AppUiState
import com.example.currencyconverter.data.Crypto
import com.example.currencyconverter.data.CryptoRate
import com.example.currencyconverter.data.Currency
import com.example.currencyconverter.data.CurrencyRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState(topClicked = true))
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    //!!CURRENCY!!

    init{
        getRates()
        getCryptoRates()
    }

    fun setTopClickedToFalse(){
        _uiState.update { currentState ->
            currentState.copy(
                topClicked = false
            )
        }
    }

    fun setTopClickedToTrue(){
        _uiState.update { currentState ->
            currentState.copy(
                topClicked = true
            )
        }
    }

    fun setTopCurrency(currency: Currency) {
        _uiState.update { currentState ->
            currentState.copy(
                topCurrency = currency
            )
        }
    }

    fun setBottomCurrency(currency: Currency) {
        _uiState.update { currentState ->
            currentState.copy(
                bottomCurrency = currency
            )
        }
    }

    fun setQuantityTopCurrency(enteredValue1: Double){
        _uiState.update { currentState ->
            currentState.copy(
                quantityTopCurrency = enteredValue1,
                quantityBottomCurrency = setQuantityBottomCurrency(enteredValue1)
            )
        }
    }

    //!!CRYPTO!!

    fun setTopCrypto(crypto: Crypto) {
        _uiState.update { currentState ->
            currentState.copy(
                topCrypto = crypto
            )
        }
    }

    fun setBottomCrypto(crypto: Crypto) {
        _uiState.update { currentState ->
            currentState.copy(
                bottomCrypto = crypto
            )
        }
    }

    fun setQuantityTopCrypto(enteredValue1: Double){
        _uiState.update { currentState ->
            currentState.copy(
                quantityTopCrypto = enteredValue1,
                quantityBottomCrypto = setQuantityBottomCrypto(enteredValue1)
            )
        }
    }

    fun setQuantityBottomCurrency(enteredValue1: Double) : Double{
        var result : Double = 0.0
        val topCurrencyTicker = uiState.value.topCurrency?.Ticker
        val bottomCurrencyTicker = uiState.value.bottomCurrency?.Ticker
        val allRates = uiState.value.allRates
        var topCurrencyRate : Double = 0.0
        var bottomCurrencyRate : Double = 0.0
        for (item in allRates) {
            if (item.ticker == topCurrencyTicker){
                topCurrencyRate = item.rate
            }
            if (item.ticker == bottomCurrencyTicker){
                bottomCurrencyRate = item.rate
            }
        }
        result = enteredValue1*bottomCurrencyRate/topCurrencyRate

        return result
    }

    fun setQuantityBottomCrypto(enteredValue1: Double) : Double{
        var result : Double = 0.0
        val topCryptoTicker = uiState.value.topCrypto?.Ticker
        val bottomCryptoTicker = uiState.value.bottomCrypto?.Ticker
        val allCryptoRates = uiState.value.allCryptoRates
        var topCryptoRate : Double = 0.0
        var bottomCryptoRate : Double = 0.0
        for (item in allCryptoRates) {
            if (item.ticker == topCryptoTicker){
                topCryptoRate = item.rate.toDouble()
            }
            if (item.ticker == bottomCryptoTicker){
                bottomCryptoRate = item.rate.toDouble()
            }
        }
        result = enteredValue1*bottomCryptoRate/topCryptoRate

        return result
    }

    private fun getRates () {
        val ConversionURL =  "http://data.fixer.io/api/latest?access_key=fda015efe50b67be452866c61d71cc51"

        GlobalScope.launch( Dispatchers.IO ){
            try {
                val apiResult = URL(ConversionURL).readText()
                val jsonObject = JSONObject(apiResult)
                val conversionRates = jsonObject.getJSONObject("rates")
                val allRates : MutableList<CurrencyRate> = mutableListOf()

                for (key in conversionRates.keys()){
                    val newCurrencyRate = CurrencyRate(ticker = key, rate = conversionRates.get(key).toString().toDouble())
                    allRates.add(newCurrencyRate)
                }
                _uiState.update { currentState ->
                    currentState.copy(
                        allRates = allRates
                    )
                }
                Log.d("Main", "${uiState.value.allRates}")
                Log.d("Main", apiResult)
            }
            catch(e: Exception) {
                Log.e("Main", "$e")
            }
        }
    }

    private fun getCryptoRates () {
        val ConversionURL =  "https://api.coingecko.com/api/v3/exchange_rates"

        GlobalScope.launch( Dispatchers.IO ){
            try {
                val apiResult = URL(ConversionURL).readText()
                val jsonObject = JSONObject(apiResult)
                val conversionRates = jsonObject.getJSONObject("rates")
                val allCryptoRates : MutableList<CryptoRate> = mutableListOf()

                for (key in conversionRates.keys()){
                    var ticker: String = "Not Found"
                    var rate: String = "Not Found"
                    val item = conversionRates.getJSONObject(key)
                    for (itemKey in item.keys()){
                        if (itemKey == "unit"){
                            ticker = item.get(itemKey).toString()
                        } else if (itemKey == "value") {
                            rate = item.get(itemKey).toString()
                        }
                    }
                    val newCryptoRate =
                        CryptoRate(ticker = ticker, rate = rate)
                    allCryptoRates.add(newCryptoRate)
                }
                _uiState.update { currentState ->
                    currentState.copy(
                        allCryptoRates = allCryptoRates
                    )
                }
                Log.d("Main", "${uiState.value.allCryptoRates}")
                Log.d("Main", apiResult)
            }
            catch(e: Exception) {
                Log.e("Main", "$e")
            }
        }
    }
}