package com.joblogic.technicaltestandroid.di

import android.content.Context
import com.joblogic.technicaltestandroid.cache.dao.ProductDao
import com.joblogic.technicaltestandroid.cache.database.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): MyDatabase {
        return MyDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideProductDao(myDatabase: MyDatabase): ProductDao {
        return myDatabase.cachedProductDao()
    }

}
