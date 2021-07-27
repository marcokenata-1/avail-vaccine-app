package com.marcokenata.availvaccine.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcokenata.availvaccine.data.network.response.Lokasi
import com.marcokenata.availvaccine.data.network.response.data
import dagger.Module
import dagger.Provides
import retrofit2.await
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@Module
class VacDataSource @Inject constructor(val vacDBService: VacDBService){

    private val _apiData = MutableLiveData<ArrayList<Lokasi>>()

    val apiData : LiveData<ArrayList<Lokasi>>
        get() = _apiData

    @Provides
    suspend fun getApiData(){
        try {
            val fetchApiData = vacDBService.apiData().await()
            _apiData.postValue(fetchApiData)
        } catch (e: IOException){
            Log.d("Connectivity","No Connectivity Exception for vacDB")
        }
    }

}