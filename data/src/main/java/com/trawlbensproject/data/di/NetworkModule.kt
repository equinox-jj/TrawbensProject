package com.trawlbensproject.data.di

import com.trawlbensproject.data.datasource.remote.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkDependency = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkhttpClient(get()) }
    single { provideGsonConverter() }
    single { provideRetrofitClient(get(), get()) }
    single { provideApiService(get()) }
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .readTimeout(10L, TimeUnit.SECONDS)
        .writeTimeout(10L, TimeUnit.SECONDS)
        .connectTimeout(10L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()

private fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

private fun provideRetrofitClient(
    okHttpClient: OkHttpClient,
    gson: GsonConverterFactory,
): Retrofit = Retrofit.Builder()
    .baseUrl("https://api.rawg.io/api/")
    .client(okHttpClient)
    .addConverterFactory(gson)
    .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)