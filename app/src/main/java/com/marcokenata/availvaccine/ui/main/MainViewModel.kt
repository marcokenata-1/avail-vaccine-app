package com.marcokenata.availvaccine.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcokenata.availvaccine.R
import com.marcokenata.availvaccine.data.network.response.CountryData
import com.marcokenata.availvaccine.data.network.response.CovidData
import com.marcokenata.availvaccine.data.network.response.Lokasi
import com.marcokenata.availvaccine.data.repository.VacRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val vacRepository: VacRepository
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    val apiDataFetch: MutableLiveData<ArrayList<Lokasi>> by lazy {
        MutableLiveData<ArrayList<Lokasi>>()
    }

    val covidArray = ArrayList<CovidData>()

    val covidDataFetch = MutableLiveData<ArrayList<CountryData>>()

    var loadingData = MutableLiveData<Boolean>()

    fun invokeData() {
        launch {
            //loading data is used for view gone and visible the loading round thing
            loadingData.postValue(true)
            covidDataFetch.postValue(vacRepository.fetchCovidNum().value)
            apiDataFetch.postValue(vacRepository.fetchApiData().value)

            if (covidArray.size < 3){
                covidArray.add(
                    CovidData(
                        "Total Cases",
                        covidDataFetch.value?.get(0)?.confirmed.toString(),
                        R.color.purple_200
                    )
                )
                covidArray.add(
                    CovidData(
                        "Deaths",
                        covidDataFetch.value?.get(0)?.deaths.toString(),
                        R.color.purple_500
                    )
                )
                covidArray.add(
                    CovidData(
                        "Recoveries",
                        covidDataFetch.value?.get(0)?.recovered.toString(),
                        R.color.purple_700
                    )
                )
            }

        }.invokeOnCompletion {
            loadingData.postValue(false)
            Log.d("debug", "summon1")
        }
    }
}