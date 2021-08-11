package com.marcokenata.availvaccine.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcokenata.availvaccine.data.network.response.CountryData
import dagger.Module
import dagger.Provides
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

@Module
class CovidNumDataSource @Inject constructor(val covidNumDBService: CovidNumDBService) {

    private val _apiDataCovid = MutableLiveData<ArrayList<CountryData>>()

    val apiData: LiveData<ArrayList<CountryData>>
        get() = _apiDataCovid

    @Provides
    suspend fun getApiDataCovid() {
        try {
            val fetchApiData = covidNumDBService.covidNumData().await()
            _apiDataCovid.postValue(fetchApiData)
        } catch (e: IOException) {
            Log.d("Connectivity", "No Connectivity Exception")
        }
    }
}