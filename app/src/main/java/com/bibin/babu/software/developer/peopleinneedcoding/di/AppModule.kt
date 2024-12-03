package com.bibin.babu.software.developer.peopleinneedcoding.di

import com.bibin.babu.software.developer.peopleinneedcoding.data.remote.api.ApiService
import com.bibin.babu.software.developer.peopleinneedcoding.data.repository.AnalyticsLoggerImpl
import com.bibin.babu.software.developer.peopleinneedcoding.data.repository.PostRepositoryImpl
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.AnalyticsLogger
import com.bibin.babu.software.developer.peopleinneedcoding.domain.repository.PostRepository
import com.bibin.babu.software.developer.peopleinneedcoding.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePostApi(): ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideAnalyticsLogger():AnalyticsLogger{
        return AnalyticsLoggerImpl()
    }

    @Provides
    @Singleton
    fun providePostRepository(api: ApiService,analyticsLogger: AnalyticsLogger): PostRepository {
        return PostRepositoryImpl(api,analyticsLogger)
    }

}