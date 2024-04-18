package com.example.currencyconverter.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
 "https://data.fixer.io/api/convert?access_key=fda015efe50b67be452866c61d71cc51&from=USD&to=JPY&amount=25"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()