package com.marcokenata.availvaccine.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marcokenata.availvaccine.data.repository.VacRepository
import dagger.Module
import javax.inject.Inject

@Module
class MainViewModelFactory @Inject constructor(private val vacRepository: VacRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(vacRepository) as T
    }
}