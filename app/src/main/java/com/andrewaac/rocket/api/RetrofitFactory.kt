package com.andrewaac.rocket.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.Executors

object RetrofitFactory {

    val instance: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .baseUrl("https://api.spacexdata.com/v3/")
        .build()

    init {
//        val converter = JacksonConverterFactory.create(
//            ObjectMapper().configure(
//                DeserializationFeature.UNWRAP_ROOT_VALUE, false
//            )
//        )


    }
}