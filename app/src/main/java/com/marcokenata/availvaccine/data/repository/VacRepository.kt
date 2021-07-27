package com.marcokenata.availvaccine.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.marcokenata.availvaccine.data.network.CovidNumDataSource
import com.marcokenata.availvaccine.data.network.VacDataSource
import com.marcokenata.availvaccine.data.network.response.CountryData
import com.marcokenata.availvaccine.data.network.response.CountryDetail
import com.marcokenata.availvaccine.data.network.response.Lokasi
import com.marcokenata.availvaccine.data.network.response.data
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Module
class VacRepository @Inject constructor(val vacDataSource: VacDataSource, val covidNumDataSource: CovidNumDataSource) {

    @Provides
    suspend fun fetchApiData(): LiveData<ArrayList<Lokasi>> {
        return withContext(Dispatchers.IO){
            Log.d("debug","summon1")
            vacDataSource.getApiData()
            return@withContext vacDataSource.apiData
        }
    }

    @Provides
    suspend fun fetchCovidNum(): LiveData<ArrayList<CountryData>> {
        return withContext(Dispatchers.IO){
            Log.d("debug","summon2")
            covidNumDataSource.getApiDataCovid()
            Log.d("DATA",covidNumDataSource.apiData.toString())
            return@withContext covidNumDataSource.apiData
        }
    }
}