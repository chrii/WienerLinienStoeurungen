package com.chrispi.stoerungen.modules

import android.content.Context
import com.chrispi.stoerungen.BaseApplication
import com.chrispi.stoerungen.network.serivce.InterferenceDataService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
//    @Singleton
//    @Provides
//    fun provideBaseApplication(@ApplicationContext app: Context): BaseApplication {
//        return app as BaseApplication
//    }

    @Singleton
    @Provides
    fun provideInterferenceData(): InterferenceDataService {
        return Retrofit.Builder()
            .baseUrl("https://www.wienerlinien.at/ogd_realtime/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(InterferenceDataService::class.java)
    }
}