package com.marcokenata.availvaccine.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcokenata.availvaccine.R
import com.marcokenata.availvaccine.data.network.response.CovidData
import kotlinx.android.synthetic.main.number_ticket.view.*

class CovidDataAdapter(var context: Context?, list: ArrayList<CovidData>) :
    RecyclerView.Adapter<CovidDataAdapter.CovidDataViewHolder>() {

    private var listOfCovidData = ArrayList<CovidData>()

    init {
        this.listOfCovidData = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidDataViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.number_ticket, parent, false)

        return CovidDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CovidDataViewHolder, position: Int) {
        return holder.bind(listOfCovidData[position])
    }

    override fun getItemCount(): Int {
        return listOfCovidData.size
    }

    class CovidDataViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        private fun tvDetector(tv: TextView, item : String){
            if (item.isNotEmpty()){
                tv.text = item
            } else {
                tv.visibility = View.GONE
            }
        }

        fun bind(covidData: CovidData) {
            tvDetector(itemView.tv_covid_cases_num,covidData.itemNum)
            tvDetector(itemView.tv_covid_ticket,covidData.itemDetail)
        }
    }
}