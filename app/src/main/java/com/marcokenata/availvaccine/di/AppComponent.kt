package com.marcokenata.availvaccine.di

import android.app.Application
import com.marcokenata.availvaccine.AvailVaccineApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class,AndroidSupportInjectionModule::class,FragmentModule::class,AppModule::class])
interface AppComponent : AndroidInjector<AvailVaccineApplication>{

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application) : Builder
        fun build() : AppComponent
    }
}