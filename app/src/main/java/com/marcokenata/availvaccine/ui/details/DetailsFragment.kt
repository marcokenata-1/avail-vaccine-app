package com.marcokenata.availvaccine.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.marcokenata.availvaccine.R
import com.marcokenata.availvaccine.data.network.response.Lokasi
import com.marcokenata.availvaccine.ui.main.MainFragmentArgs
import com.marcokenata.availvaccine.util.observe
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory

    private lateinit var viewModel: DetailsViewModel

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

        val x : MainFragmentArgs by navArgs()
        val data = x.bundler
        Log.d("DATA2",data.toString())
        viewModel.invData(data)
        observeData()
    }

    private fun observeData(){
        observe(viewModel.vacCodeFetch,::lokasiHandler)
    }

    private fun lokasiHandler(lokasi: Lokasi){
        tv_test.text = lokasi.nama_lokasi_vaksinasi
    }

}