package com.nyc.highschool.base.di.module

import com.nyc.highschool.base.ApiHelper
import com.nyc.highschool.base.ApiHelperImpl
import com.nyc.highschool.base.ApiService
import com.nyc.highschool.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

  fun provideBaseUrl() = AppConstants.baseURL

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
      .baseUrl(provideBaseUrl())
      .client(OkHttpClient.Builder().build())
      .addConverterFactory(GsonConverterFactory.create())
      .build()

  @Provides
  @Singleton
  fun provideApiService() = provideRetrofit().create(ApiService::class.java)

  @Provides
  @Singleton
  fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}