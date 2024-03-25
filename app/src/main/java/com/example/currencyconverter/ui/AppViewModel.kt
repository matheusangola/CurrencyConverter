package com.example.currencyconverter.ui

import androidx.lifecycle.ViewModel
import com.example.currencyconverter.data.AppUiState
import com.example.currencyconverter.data.Currency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface AppUiStateInt {
    data class Success(val photos: String) : AppUiStateInt
    object Error : AppUiStateInt
    object Loading : AppUiStateInt
}

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState(topClicked = true))
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

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
        println("settopcurrency: " + _uiState.value.topCurrency?.Ticker)
    }

    fun setBottomCurrency(currency: Currency) {
        _uiState.update { currentState ->
            currentState.copy(
                bottomCurrency = currency
            )
        }
        println("setbottomcurrency: " + _uiState.value.bottomCurrency?.Ticker)
    }

    fun setQuantityTopCurrency(enteredValue1: Double){
        _uiState.update { currentState ->
            currentState.copy(
                quantityTopCurrency = enteredValue1,
                quantityBottomCurrency = exchangeTopToBottom(uiState.value.topCurrency, uiState.value.bottomCurrency, enteredValue1)
            )
        }
    }

    fun exchangeTopToBottom (topCurrency: Currency?, bottomCurrency: Currency?, quantityTopCurrency: Double): Double {
        var newBottomValue = 0.0
        if (topCurrency!!.Ticker == "USD" && bottomCurrency!!.Ticker == "EUR") {
            newBottomValue = quantityTopCurrency * 0.92
        } else if (topCurrency.Ticker == "USD" && bottomCurrency!!.Ticker == "JPY") {
            newBottomValue = quantityTopCurrency * 150.86
        } else if (topCurrency.Ticker == "USD" && bottomCurrency!!.Ticker == "USD") {
            newBottomValue = quantityTopCurrency * 1
        } else if (topCurrency.Ticker == "USD" && bottomCurrency!!.Ticker == "AUD") {
            newBottomValue = quantityTopCurrency * 1.53
        } else if (topCurrency.Ticker == "USD" && bottomCurrency!!.Ticker == "CAD") {
            newBottomValue = quantityTopCurrency * 1.36
        } else if (topCurrency.Ticker == "USD" && bottomCurrency!!.Ticker == "GBP") {
            newBottomValue = quantityTopCurrency * 0.79
        } else if (topCurrency.Ticker == "EUR" && bottomCurrency!!.Ticker == "JPY") {
            newBottomValue = quantityTopCurrency * 163.93
        } else if (topCurrency.Ticker == "EUR" && bottomCurrency!!.Ticker == "USD") {
            newBottomValue = quantityTopCurrency * 1.09
        } else if (topCurrency.Ticker == "EUR" && bottomCurrency!!.Ticker == "AUD") {
            newBottomValue = quantityTopCurrency * 1.66
        } else if (topCurrency.Ticker == "EUR" && bottomCurrency!!.Ticker == "CAD") {
            newBottomValue = quantityTopCurrency * 1.47
        } else if (topCurrency.Ticker == "EUR" && bottomCurrency!!.Ticker == "GBP") {
            newBottomValue = quantityTopCurrency * 0.85
        } else if (topCurrency.Ticker == "EUR" && bottomCurrency!!.Ticker == "EUR") {
            newBottomValue = quantityTopCurrency * 1
        } else if (topCurrency.Ticker == "CAD" && bottomCurrency!!.Ticker == "CAD") {
            newBottomValue = quantityTopCurrency * 1
        } else if (topCurrency.Ticker == "CAD" && bottomCurrency!!.Ticker == "EUR") {
            newBottomValue = quantityTopCurrency * 0.68
        } else if (topCurrency.Ticker == "CAD" && bottomCurrency!!.Ticker == "USD") {
            newBottomValue = quantityTopCurrency * 0.74
        } else if (topCurrency.Ticker == "CAD" && bottomCurrency!!.Ticker == "JPY") {
            newBottomValue = quantityTopCurrency * 111.2
        } else if (topCurrency.Ticker == "CAD" && bottomCurrency!!.Ticker == "AUD") {
            newBottomValue = quantityTopCurrency * 1.13
        } else if (topCurrency.Ticker == "CAD" && bottomCurrency!!.Ticker == "GBP") {
            newBottomValue = quantityTopCurrency * 0.58
        } else if (topCurrency.Ticker == "GBP" && bottomCurrency!!.Ticker == "GBP") {
            newBottomValue = quantityTopCurrency * 1
        } else if (topCurrency.Ticker == "GBP" && bottomCurrency!!.Ticker == "EUR") {
            newBottomValue = quantityTopCurrency * 1.17
        } else if (topCurrency.Ticker == "GBP" && bottomCurrency!!.Ticker == "USD") {
            newBottomValue = quantityTopCurrency * 1.27
        } else if (topCurrency.Ticker == "GBP" && bottomCurrency!!.Ticker == "JPY") {
            newBottomValue = quantityTopCurrency * 191.93
        } else if (topCurrency.Ticker == "GBP" && bottomCurrency!!.Ticker == "AUD") {
            newBottomValue = quantityTopCurrency * 1.95
        } else if (topCurrency.Ticker == "GBP" && bottomCurrency!!.Ticker == "CAD") {
            newBottomValue = quantityTopCurrency * 1.73
        } else if (topCurrency.Ticker == "JPY" && bottomCurrency!!.Ticker == "JPY") {
            newBottomValue = quantityTopCurrency * 1
        } else if (topCurrency.Ticker == "JPY" && bottomCurrency!!.Ticker == "EUR") {
            newBottomValue = quantityTopCurrency * 0.0061
        } else if (topCurrency.Ticker == "JPY" && bottomCurrency!!.Ticker == "USD") {
            newBottomValue = quantityTopCurrency * 0.0066
        } else if (topCurrency.Ticker == "JPY" && bottomCurrency!!.Ticker == "AUD") {
            newBottomValue = quantityTopCurrency * 0.010
        } else if (topCurrency.Ticker == "JPY" && bottomCurrency!!.Ticker == "CAD") {
            newBottomValue = quantityTopCurrency * 0.009
        } else if (topCurrency.Ticker == "JPY" && bottomCurrency!!.Ticker == "GBP") {
            newBottomValue = quantityTopCurrency * 0.0052
        } else if (topCurrency.Ticker == "AUD" && bottomCurrency!!.Ticker == "AUD") {
            newBottomValue = quantityTopCurrency * 1
        } else if (topCurrency.Ticker == "AUD" && bottomCurrency!!.Ticker == "EUR") {
            newBottomValue = quantityTopCurrency * 0.6
        } else if (topCurrency.Ticker == "AUD" && bottomCurrency!!.Ticker == "USD") {
            newBottomValue = quantityTopCurrency * 0.65
        } else if (topCurrency.Ticker == "AUD" && bottomCurrency!!.Ticker == "JPY") {
            newBottomValue = quantityTopCurrency * 98.54
        } else if (topCurrency.Ticker == "AUD" && bottomCurrency!!.Ticker == "CAD") {
            newBottomValue = quantityTopCurrency * 0.89
        } else if (topCurrency.Ticker == "AUD" && bottomCurrency!!.Ticker == "GBP") {
            newBottomValue = quantityTopCurrency * 0.51
        }
        return newBottomValue
    }


//    var appUiState: String by mutableStateOf("")
//        private set
//var appUiState: AppUiStateInt by mutableStateOf(AppUiStateInt.Loading)
 //   private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
//    init {
//        getCurrency()
//    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
//    private fun getCurrency() {
//        viewModelScope.launch {
//            try {
////                val listResult = CurrencyApi.retrofitService.getPhotos()
////                appUiState = listResult
//                val listResult = CurrencyApi.retrofitService.getPhotos()
//                appUiState = AppUiStateInt.Success(listResult)
//            } catch (e: IOException) {
//                appUiState = AppUiStateInt.Error
//            }
//        }
//    }
}