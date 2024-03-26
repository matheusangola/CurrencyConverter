package com.example.currencyconverter.data

data class AppUiState(

    var topClicked: Boolean,

    val quantityTopCurrency: Double = 0.0,

    val quantityBottomCurrency: Double = 0.0,

    var topCurrency: Currency? = Currency("USD", "United States Dollar"),

    val bottomCurrency: Currency? = Currency("CAD", "Canadian Dollar"),

    val quantityTopCrypto: Double = 0.0,

    val quantityBottomCrypto: Double = 0.0,

    var topCrypto: Crypto? = Crypto("BTC", "Bitcoin"),

    val bottomCrypto: Crypto? = Crypto("ETH", "Ethereum")




)
