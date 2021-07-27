package com.marcokenata.availvaccine.data.network.response

data class Jadwal (
    val id: String,
    val label: String,
    val kode_lokasi_vaksinasi: String,
    val waktu: ArrayList<Waktu>
)