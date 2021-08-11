package com.marcokenata.availvaccine.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.marcokenata.availvaccine.R
import com.marcokenata.availvaccine.data.network.response.Jadwal
import com.marcokenata.availvaccine.data.network.response.Waktu
import kotlinx.android.synthetic.main.jadwal_ticket.view.*
import kotlinx.android.synthetic.main.waktu_bottom_sheet.*

class JadwalAdapter(var context: Context?, list: ArrayList<Jadwal>) : RecyclerView.Adapter<JadwalAdapter.JadwalDataViewHolder>() {

    private var list = ArrayList<Jadwal>()


    init {
        this.list = list
    }

    class JadwalDataViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var waktuAdapter:WaktuAdapter? = null

        fun bind(jadwal: Jadwal){
            itemView.tv_tggl.text = jadwal.id

            itemView.setOnClickListener {
                bottomSheetDialog(jadwal.waktu)
            }

        }

        private fun bottomSheetDialog(array : ArrayList<Waktu>) {
            val btSheet = BottomSheetDialog(itemView.context)

            btSheet.setContentView(R.layout.waktu_bottom_sheet)
            waktuAdapter = WaktuAdapter(itemView.context,array)

            Log.d("DATA2",array.toString())
            btSheet.lv_jadwal.adapter = waktuAdapter

            btSheet.show()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalDataViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.jadwal_ticket,parent,false)
        return JadwalDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: JadwalDataViewHolder, position: Int) {
        return holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}