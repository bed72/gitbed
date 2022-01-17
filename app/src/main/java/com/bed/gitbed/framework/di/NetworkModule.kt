package com.bed.gitbed.framework.di

import com.bed.gitbed.BuildConfig
import com.bed.gitbed.framework.network.ApiService
import com.bed.gitbed.framework.network.interceptor.AuthorizationInterceptor
import com.bed.gitbed.framework.network.interceptor.RepositoriesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder

import com.google.gson.Gson

private const val TIMEOUT_SECONDS = 15L

//private val gson: Gson = GsonBuilder()
//    .setLenient()
//    .create()

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )
        }
    }

    single<Gson> {
        return@single GsonBuilder().setLenient().create()
    }

    single<GsonConverterFactory> {
        GsonConverterFactory.create(get())
    }

    single {
        AuthorizationInterceptor(get())
    }

    single {
        RepositoriesInterceptor()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<RepositoriesInterceptor>())
            .addInterceptor(get<AuthorizationInterceptor>())
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
            .create(ApiService::class.java)
    }
}