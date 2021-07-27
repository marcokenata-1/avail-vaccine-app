package com.marcokenata.availvaccine.data.network.response

data class Lokasi (
    val kode_lokasi_vaksinasi: Int,
    val nama_lokasi_vaksinasi: String,
    val alamat_lokasi_vaksinasi: String,
    val wilayah: String,
    val kecamatan: String,
    val kelurahan: String,
    val rt: String,
    val rw: String,
    val kodepos: String,
    val jenis_faskes: String,
    val jumlah_tim_vaksinator: String,
    val nama_faskes: String,
    val created_at: String,
    val updated_at: String,
    val open_regis: String,
    val jadwal: ArrayList<Jadwal>,
    val detail_lokasi: ArrayList<DetailLokasi>,
    val last_updated_at: String
)