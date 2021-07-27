package com.marcokenata.availvaccine.data.network.response

data class CountryData(
    val provinceState: String,
    val countryRegion: String,
    val lastUpdate: Double,
    val lat: Double,
    val long: Double,
    val confirmed: Long,
    val recovered: Long,
    val deaths: Long
)