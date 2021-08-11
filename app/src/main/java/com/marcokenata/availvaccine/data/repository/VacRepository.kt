package com.marcokenata.availvaccine.data.repository

import androidx.lifecycle.LiveData
import com.marcokenata.availvaccine.data.network.CovidNumDataSource
import com.marcokenata.availvaccine.data.network.VacDataSource
import com.marcokenata.availvaccine.data.network.response.CountryData
import com.marcokenata.availvaccine.data.network.response.Lokasi
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Module
class VacRepository @Inject constructor(private val vacDataSource: VacDataSource, private val covidNumDataSource: CovidNumDataSource) {

    @Provides
    suspend fun fetchApiData(): LiveData<ArrayList<Lokasi>> {
        return withContext(Dispatchers.IO){
            vacDataSource.getApiData()
            return@withContext vacDataSource.apiData
        }
    }

    @Provides
    suspend fun fetchCovidNum(): LiveData<ArrayList<CountryData>> {
        return withContext(Dispatchers.IO){
            covidNumDataSource.getApiDataCovid()
            return@withContext covidNumDataSource.apiData
        }
    }


}