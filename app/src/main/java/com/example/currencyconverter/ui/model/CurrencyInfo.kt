package com.example.currencyconverter.ui.model

import com.example.currencyconverter.R

object CurrencyRepository {
    val currencies = listOf(
        Currency(
            nameRes = R.string.USD,
            descriptionRes = R.string.USD1
            //imageRes = R.drawable.android_superhero1
        ),
        Currency(
            nameRes = R.string.EUR,
            descriptionRes = R.string.EUR1,
            //imageRes = R.drawable.android_superhero2
        ),
        Currency(
            nameRes = R.string.JPY,
            descriptionRes = R.string.JPY1,
            //imageRes = R.drawable.android_superhero3
        ),
        Currency(
            nameRes = R.string.AUD,
            descriptionRes = R.string.AUD1,
            //imageRes = R.drawable.android_superhero4
        ),
        Currency(
            nameRes = R.string.CAD,
            descriptionRes = R.string.CAD1,
            //imageRes = R.drawable.android_superhero5
        ),
        Currency(
            nameRes = R.string.GBP,
            descriptionRes = R.string.GBP1,
            //imageRes = R.drawable.android_superhero6
        )
    )
}