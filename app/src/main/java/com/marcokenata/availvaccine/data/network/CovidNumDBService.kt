package com.marcokenata.availvaccine.data.network

import com.marcokenata.availvaccine.data.network.response.CountryData
import retrofit2.Call
import retrofit2.http.GET

interface CovidNumDBService {

    @GET("ID/confirmed/")
    fun covidNumData(): Call<ArrayList<CountryData>>
}