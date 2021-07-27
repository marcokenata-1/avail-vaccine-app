package com.marcokenata.availvaccine.di

import com.marcokenata.availvaccine.AvailVaccineApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun application() :AvailVaccineApplication {
        return AvailVaccineApplication()
    }
}