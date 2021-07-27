package com.marcokenata.availvaccine.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marcokenata.availvaccine.data.repository.VacRepository
import dagger.Module
import javax.inject.Inject

@Module
class DetailsViewModelFactory @Inject constructor(
    private val vacRepository: VacRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(vacRepository) as T
    }

}