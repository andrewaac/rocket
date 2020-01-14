package com.andrewaac.rocket.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

object RetrofitFactory {


    val instance: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .baseUrl("https://api.spacexdata.com/v3/")
        .build()
}