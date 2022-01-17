package com.bed.gitbed.framework.di

import com.bed.gitbed.usecase.AuthUseCase
import com.bed.gitbed.usecase.AuthUseCaseImpl
import com.bed.gitbed.usecase.RepositoriesUseCase
import com.bed.gitbed.usecase.RepositoriesUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<AuthUseCase> {
        AuthUseCaseImpl(get())
    }

    single<RepositoriesUseCase> {
        RepositoriesUseCaseImpl(get())
    }
}