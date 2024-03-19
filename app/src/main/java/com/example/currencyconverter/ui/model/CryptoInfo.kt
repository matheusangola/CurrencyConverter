package com.example.currencyconverter.ui.model

import com.example.currencyconverter.R

object CryptosRepository {
    val cryptos = listOf(
        Crypto(
            nameRes = R.string.BTC,
            descriptionRes = R.string.BTC1,
            //imageRes = R.drawable.android_superhero1
        ),
        Crypto(
            nameRes = R.string.ADA,
            descriptionRes = R.string.ADA1,
            //imageRes = R.drawable.android_superhero2
        ),
        Crypto(
            nameRes = R.string.ETH,
            descriptionRes = R.string.ETH1,
            //imageRes = R.drawable.android_superhero3
        ),
        Crypto(
            nameRes = R.string.BNB,
            descriptionRes = R.string.BNB1,
            //imageRes = R.drawable.android_superhero4
        ),
        Crypto(
            nameRes = R.string.SOL,
            descriptionRes = R.string.SOL1,
            //imageRes = R.drawable.android_superhero5
        ),
        Crypto(
            nameRes = R.string.DOT,
            descriptionRes = R.string.DOT1,
            //imageRes = R.drawable.android_superhero6
        )
    )
}