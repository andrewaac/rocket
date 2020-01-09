package com.andrewaac.rocket.api

import com.andrewaac.rocket.launches.Launch
import retrofit2.Call
import retrofit2.http.GET

interface SpaceXService {

    @GET("launches")
    fun getLaunches(): Call<List<Launch>>
}