package com.example.currencyconverter.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CurrencyApiService {
    @GET("photos")
    suspend fun getPhotos(): String
}

object CurrencyApi {
    val retrofitService : CurrencyApiService by lazy {
        retrofit.create(CurrencyApiService::class.java)
    }
}