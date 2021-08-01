package com.marcokenata.availvaccine.ui.details

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.marcokenata.availvaccine.data.network.response.Lokasi
import com.marcokenata.availvaccine.data.repository.VacRepository
import com.marcokenata.availvaccine.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DetailsViewModel(
    private val vacRepository: VacRepository
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    val fetch = MutableLiveData<ArrayList<Lokasi>>()
    var vacCodeFetch = MutableLiveData<Lokasi>()

    private suspend fun invokeData(vacCode: Int){
        return withContext(Dispatchers.Main) {
            Log.d("DATA2","RUN")
            fetch.postValue(vacRepository.fetchApiData().value)
            if (fetch.value == null){
                fetch.postValue(vacRepository.fetchApiData().value)
            }
            for (x in fetch.value!!){
                if (x.kode_lokasi_vaksinasi == vacCode){
                    vacCodeFetch.value = x
                }
            }
        }
    }

    fun invData(vacCode: Int){
        launch {
            invokeData(vacCode)
        }
    }

}