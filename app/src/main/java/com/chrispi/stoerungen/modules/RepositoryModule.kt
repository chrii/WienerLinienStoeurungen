package com.chrispi.stoerungen.modules

import com.chrispi.stoerungen.network.serivce.InterferenceDataService
import com.chrispi.stoerungen.repositories.IfaceInterferenceRepository
import com.chrispi.stoerungen.repositories.implementation.InterferenceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideInterferenceRepository(
        interferenceDataService: InterferenceDataService
    ): IfaceInterferenceRepository = InterferenceRepositoryImpl(interferenceDataService)

}