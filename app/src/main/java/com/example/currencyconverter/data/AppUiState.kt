package com.example.currencyconverter.data

data class AppUiState(

    val quantityTopCurrency: Double = 0.0,

    val quantityBottomCurrency: Double = 0.0,

    val topCurrency: Currency? = null,

    val bottomCurrency: Currency? = null


)
