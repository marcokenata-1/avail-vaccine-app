package com.marcokenata.availvaccine.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.marcokenata.availvaccine.R
import com.marcokenata.availvaccine.data.network.response.Lokasi
import com.marcokenata.availvaccine.ui.main.MainFragmentDirections
import kotlinx.android.synthetic.main.vac_ticket.view.*
import java.util.*
import kotlin.collections.ArrayList

class VacDataAdapter(var context: Context?, private var list: List<Lokasi>) :
    RecyclerView.Adapter<VacDataAdapter.VacDataViewHolder>(), Filterable {

    var listOfVacData = ArrayList<Lokasi>()
    var tempList = ArrayList<Lokasi>()

    init {
        this.listOfVacData = list as ArrayList<Lokasi>
        tempList.addAll(list)
    }

    class VacDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(lokasi: Lokasi) {
            itemView.tv_vac_ticket.text = lokasi.nama_lokasi_vaksinasi
            itemView.tv_covid_regency.text = lokasi.wilayah
            itemView.tv_covid_vac_address.text = "Kel ${lokasi.kelurahan} Kec ${lokasi.kecamatan}"

            itemView.cv_vac_ticket.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToDetailsFragment()
                action.arguments.putInt("bunld", lokasi.kode_lokasi_vaksinasi)
                itemView.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacDataViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.vac_ticket, parent, false)

        return VacDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacDataViewHolder, position: Int) {
        return holder.bind(listOfVacData[position])
    }

    override fun getItemCount(): Int {
        return listOfVacData.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val constraint = p0.toString()
                if (constraint.isEmpty()) {
                    listOfVacData = list as ArrayList<Lokasi>
                } else {
                    val resultList = ArrayList<Lokasi>()
                    for (row in tempList) {
                        if (row.nama_lokasi_vaksinasi.lowercase(Locale.ROOT)
                                .contains(constraint.lowercase(Locale.ROOT)) ||
                            row.wilayah.lowercase(Locale.ROOT)
                                .contains(constraint.lowercase(Locale.ROOT)) ||
                            row.kelurahan.lowercase(Locale.ROOT)
                                .contains(constraint.lowercase(Locale.ROOT)) ||
                            row.kecamatan.lowercase(Locale.ROOT)
                                .contains(constraint.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    listOfVacData = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listOfVacData
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                listOfVacData = p1?.values as ArrayList<Lokasi>
                notifyDataSetChanged()
            }

        }
    }
}