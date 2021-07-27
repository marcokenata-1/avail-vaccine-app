package com.marcokenata.availvaccine.di

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.marcokenata.availvaccine.data.network.CovidNumDBService
import com.marcokenata.availvaccine.data.network.httpUrl
import com.marcokenata.availvaccine.data.network.VacDBService
import com.marcokenata.availvaccine.data.network.httpCovidNum
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetModule {

    private val requestInterceptor = Interceptor { chain ->

        var url = chain.request()
            .url
            .newBuilder()
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request)

    }

    private val interceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun providesHttpClient(application: Application) : OkHttpClient {
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(requestInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) : VacDBService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(httpUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VacDBService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofitCovidNum(okHttpClient: OkHttpClient) : CovidNumDBService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(httpCovidNum)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CovidNumDBService::class.java)
    }
}