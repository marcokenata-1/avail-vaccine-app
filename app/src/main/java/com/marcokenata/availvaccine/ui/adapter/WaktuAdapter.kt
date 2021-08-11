package com.marcokenata.availvaccine.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.gson.Gson
import com.marcokenata.availvaccine.R
import com.marcokenata.availvaccine.data.network.response.Waktu
import kotlinx.android.synthetic.main.waktu_ticket.view.*

class WaktuAdapter(var context: Context?, private var listOfWaktu: ArrayList<Waktu>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return listOfWaktu.size
    }

    override fun getItem(p0: Int): Any {
        return listOfWaktu[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.waktu_ticket,p2,false)

        val waktu = listOfWaktu[p0]

        view.tv_waktu.text = waktu.id
        val json = Gson().toJson(waktu)

        if (json.contains("totalKuota")){
            view.tv_jumlah_kuota.text = waktu.kuota.totalKuota.toString()
        }

        if (json.contains("sisaKuota")){
            view.tv_sisa_kuota.text = waktu.kuota.sisaKuota.toString()
        }


        return view
    }
}