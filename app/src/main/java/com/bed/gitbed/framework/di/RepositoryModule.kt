package com.bed.gitbed.framework.di

import com.bed.gitbed.data.repository.auth.AuthRemoteDataSource
import com.bed.gitbed.data.repository.auth.AuthRepository
import com.bed.gitbed.data.repository.repositories.RepositoriesRemoteDataSource
import com.bed.gitbed.data.repository.repositories.RepositoriesRepository
import com.bed.gitbed.framework.AuthRepositoryImpl
import com.bed.gitbed.framework.RepositoriesRepositoryImpl
import com.bed.gitbed.framework.network.response.AuthResponse
import com.bed.gitbed.framework.network.response.RepositoriesResponse
import com.bed.gitbed.framework.remote.RetrofitAuthDataSource
import com.bed.gitbed.framework.remote.RetrofitRepositoriesDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(preferencesRepository = get(), authRemoteDataSource = get())
    }

    single<RepositoriesRepository> {
        RepositoriesRepositoryImpl(get())
    }

    single<AuthRemoteDataSource<AuthResponse>> {
        RetrofitAuthDataSource(get())
    }

    single<RepositoriesRemoteDataSource<RepositoriesResponse>> {
       RetrofitRepositoriesDataSource(get())
    }
}