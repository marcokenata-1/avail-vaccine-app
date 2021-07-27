package com.marcokenata.availvaccine.data.network.response

data class DetailLokasi(
    val place_id: Double,
    val licence: String,
    val osm_type: String,
    val osm_id: Double,
    val boundingbox: ArrayList<String>,
    val lat: String,
    val lon: String,
    val display_name: String,
    val place_rank: Double,
    val category: String,
    val type: String,
    val importance: Double,
    val icon: String
)