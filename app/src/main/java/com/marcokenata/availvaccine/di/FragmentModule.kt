package com.marcokenata.availvaccine.di

import com.marcokenata.availvaccine.ui.details.DetailsFragment
import com.marcokenata.availvaccine.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun MainFragment() : MainFragment

    @ContributesAndroidInjector
    internal abstract fun DetailsFragment() : DetailsFragment
}