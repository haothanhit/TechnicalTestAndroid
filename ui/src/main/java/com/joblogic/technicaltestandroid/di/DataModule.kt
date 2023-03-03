package com.joblogic.technicaltestandroid.di

import com.joblogic.technicaltestandroid.BuildConfig
import com.joblogic.technicaltestandroid.cache.MainCacheImp
import com.joblogic.technicaltestandroid.data.api.MainService
import com.joblogic.technicaltestandroid.data.api.ServiceFactory
import com.joblogic.technicaltestandroid.data.repository.MainCache
import com.joblogic.technicaltestandroid.data.repository.MainRepositoryImp
import com.joblogic.technicaltestandroid.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMainService(): MainService {
        return ServiceFactory.createMainService(BuildConfig.DEBUG, BuildConfig.BASE_URL)
    }
    @Provides
    @Singleton
    fun provideUserRepository(mainRepositoryImp: MainRepositoryImp): MainRepository =
        mainRepositoryImp

    @Provides
    @Singleton
    fun provideMainCacheRepository(mainCacheImp: MainCacheImp): MainCache =
        mainCacheImp

}
