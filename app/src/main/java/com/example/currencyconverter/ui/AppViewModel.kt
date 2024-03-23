package com.example.currencyconverter.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.AppUiState
import com.example.currencyconverter.data.Currency
import com.example.currencyconverter.network.CurrencyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AppUiStateInt {
    data class Success(val photos: String) : AppUiStateInt
    object Error : AppUiStateInt
    object Loading : AppUiStateInt
}

class AppViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun calculateExchange(){

    }

    fun setTopCurrency(currency: Currency) {
        val topCurrency: Currency? = null

        val bottomCurrency: Currency? = null
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