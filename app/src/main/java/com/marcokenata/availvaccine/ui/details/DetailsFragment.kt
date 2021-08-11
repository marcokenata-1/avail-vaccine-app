package com.marcokenata.availvaccine.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcokenata.availvaccine.R
import com.marcokenata.availvaccine.data.network.response.Lokasi
import com.marcokenata.availvaccine.ui.adapter.JadwalAdapter
import com.marcokenata.availvaccine.ui.main.MainFragmentArgs
import com.marcokenata.availvaccine.util.observe
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory

    private lateinit var viewModel: DetailsViewModel

    var adapter: JadwalAdapter? = null

    var lokasiUri : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)

        val x: MainFragmentArgs by navArgs()
        val data = x.bundler
        Log.d("DATA2", data.toString())
        viewModel.invData(data)
        observeData()

        bt_go_map.setOnClickListener{
            val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(lokasiUri))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }

    private fun loadingData(boolean: Boolean) {
        if (boolean) {
            tv_test.visibility = View.GONE
            cv_place.visibility = View.GONE
            cv_schedule.visibility = View.GONE
            pb_loading.visibility = View.VISIBLE
        } else {
            tv_test.visibility = View.VISIBLE
            cv_place.visibility = View.VISIBLE
            cv_schedule.visibility = View.VISIBLE
            pb_loading.visibility = View.GONE
        }
    }

    private fun observeData() {
        observe(viewModel.vacCodeFetch, ::lokasiHandler)
        observe(viewModel.loadingData, ::loadingData)
    }

    private fun lokasiHandler(lokasi: Lokasi) {
        tv_test.text = lokasi.nama_lokasi_vaksinasi
        lokasiUri = lokasi.nama_lokasi_vaksinasi
        tv_alamat_vaksin.text = lokasi.alamat_lokasi_vaksinasi
        tv_kec_kel.text = "Kel ${lokasi.kelurahan} Kec ${lokasi.kecamatan}".capitalize()
        tv_wilayah.text = lokasi.wilayah.capitalize()

        // setup adapter
        adapter = JadwalAdapter(context, lokasi.jadwal)
        rv_jadwal.adapter = adapter
    }

}