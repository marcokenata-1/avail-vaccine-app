package com.marcokenata.availvaccine.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcokenata.availvaccine.data.network.response.Lokasi
import com.marcokenata.availvaccine.data.network.response.data
import com.marcokenata.availvaccine.data.repository.VacRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailsViewModel(
    private val vacRepository: VacRepository
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    val apiDataFetch = MutableLiveData<ArrayList<Lokasi>>()

    init {
        launch {
            apiDataFetch.postValue(vacRepository.fetchApiData().value)
        }
    }
}