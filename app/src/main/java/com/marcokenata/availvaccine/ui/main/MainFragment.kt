package com.marcokenata.availvaccine.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.marcokenata.availvaccine.R
import com.marcokenata.availvaccine.data.network.response.CountryData
import com.marcokenata.availvaccine.data.network.response.Lokasi
import com.marcokenata.availvaccine.ui.adapter.CovidDataAdapter
import com.marcokenata.availvaccine.ui.adapter.VacDataAdapter
import com.marcokenata.availvaccine.util.observe
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    var adapter: VacDataAdapter? = null

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sv_test.isIconified = true
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        viewModel.invokeData()
        observeData()
        super.onStart()
    }

    private fun observeData() {
        observe(viewModel.loadingData, ::loadingData)
        observe(viewModel.covidDataFetch, ::covidNumHandler)
        observe(viewModel.apiDataFetch, ::vaccineObserve)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.invokeData()
        observeData()

        sv_test.isIconified = true

        sv_test.setOnSearchClickListener {
            tv_covid_last_update.visibility = View.GONE
            vp_card_views.visibility = View.GONE
            tv_text_title.visibility = View.GONE
            tv_vac_site_title.visibility = View.GONE
            tv_covid_title.visibility = View.GONE
            tl_dots.visibility = View.GONE
        }

        sv_test.setOnCloseListener {
            tv_covid_last_update.visibility = View.VISIBLE
            vp_card_views.visibility = View.VISIBLE
            tv_text_title.visibility = View.VISIBLE
            tv_vac_site_title.visibility = View.VISIBLE
            tv_covid_title.visibility = View.VISIBLE
            tl_dots.visibility = View.VISIBLE

            return@setOnCloseListener false
        }

        sv_test.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("TEXT", p0.toString())
                adapter?.filter?.filter(p0.toString())
                return false
            }

        })
    }

    override fun onResume() {
        sv_test.isIconified = true
        viewModel.invokeData()
        observeData()
        super.onResume()
    }

    private fun loadingData(load: Boolean) {
        if (load) {
            tv_covid_last_update.visibility = View.GONE
            vp_card_views.visibility = View.GONE
            tv_text_title.visibility = View.GONE
            tv_vac_site_title.visibility = View.GONE
            tv_covid_title.visibility = View.GONE
            sv_test.visibility = View.GONE
            rv_vaccine_list.visibility = View.GONE
            tl_dots.visibility = View.GONE
            pb_loading.visibility = View.VISIBLE
        } else {
            tv_covid_last_update.visibility = View.VISIBLE
            vp_card_views.visibility = View.VISIBLE
            tv_text_title.visibility = View.VISIBLE
            tv_vac_site_title.visibility = View.VISIBLE
            sv_test.visibility = View.VISIBLE
            tv_covid_title.visibility = View.VISIBLE
            rv_vaccine_list.visibility = View.VISIBLE
            tl_dots.visibility = View.VISIBLE
            pb_loading.visibility = View.GONE
        }
    }

    private fun covidNumHandler(array: ArrayList<CountryData>) {

        vp_card_views.adapter = CovidDataAdapter(context, viewModel.covidArray)
        TabLayoutMediator(tl_dots, vp_card_views) { tab, position ->
            // PLACEHOLDER
        }.attach()

        with(vp_card_views) {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
        }

        val lastUpdate = array[0].lastUpdate

        tv_covid_last_update.text =
            "Last updated at " + Date(lastUpdate.toLong() * 1000).toString()
    }

    private fun vaccineObserve(array: ArrayList<Lokasi>) {
        adapter = VacDataAdapter(context, array)
        rv_vaccine_list.adapter = adapter
        rv_vaccine_list.layoutManager = LinearLayoutManager(context)
    }

}
