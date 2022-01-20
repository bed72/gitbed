package com.bed.gitbed.framework.di

import com.bed.gitbed.data.repository.auth.AuthRemoteDataSource
import com.bed.gitbed.data.repository.auth.AuthRepository
import com.bed.gitbed.data.repository.collaborators.CollaboratorsRemoteDataSource
import com.bed.gitbed.data.repository.collaborators.CollaboratorsRepository
import com.bed.gitbed.data.repository.repositories.RepositoriesRemoteDataSource
import com.bed.gitbed.data.repository.repositories.RepositoriesRepository
import com.bed.gitbed.framework.AuthRepositoryImpl
import com.bed.gitbed.framework.CollaboratorsRepositoryImpl
import com.bed.gitbed.framework.RepositoriesRepositoryImpl
import com.bed.gitbed.framework.network.response.AuthResponse
import com.bed.gitbed.framework.network.response.CollaboratorsResponse
import com.bed.gitbed.framework.network.response.RepositoriesResponse
import com.bed.gitbed.framework.remote.RetrofitAuthDataSource
import com.bed.gitbed.framework.remote.RetrofitCollaboratorsDataSource
import com.bed.gitbed.framework.remote.RetrofitRepositoriesDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(
            authRemoteDataSource = get(),
            preferencesRepository = get()
        )
    }

    single<AuthRemoteDataSource<AuthResponse>> {
        RetrofitAuthDataSource(get())
    }

    single<RepositoriesRepository> {
        RepositoriesRepositoryImpl(
            repositoriesRemoteDataSource = get(),
            collaboratorsRemoteDataSource = get()
        )
    }

    single<RepositoriesRemoteDataSource<RepositoriesResponse>> {
       RetrofitRepositoriesDataSource(get())
    }

    single<CollaboratorsRepository> {
        CollaboratorsRepositoryImpl(get())
    }

    single<CollaboratorsRemoteDataSource<List<CollaboratorsResponse>>> {
        RetrofitCollaboratorsDataSource(get())
    }
}