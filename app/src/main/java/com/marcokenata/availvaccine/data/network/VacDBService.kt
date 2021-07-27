package com.marcokenata.availvaccine.data.network

import com.marcokenata.availvaccine.data.network.response.Lokasi
import retrofit2.Call
import retrofit2.http.GET

interface VacDBService {

    @GET("/")
    fun apiData() : Call<ArrayList<Lokasi>>

}