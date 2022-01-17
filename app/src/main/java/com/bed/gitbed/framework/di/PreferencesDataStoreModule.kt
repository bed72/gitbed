package com.bed.gitbed.framework.di

import com.bed.gitbed.DataPreferences
import com.bed.gitbed.GitBed
import com.bed.gitbed.data.repository.preferences.PreferencesRepository
import com.bed.gitbed.data.repository.preferences.ProtoDataSource
import com.bed.gitbed.framework.preferences.datasource.PreferencesProtoDataSourceImpl
import com.bed.gitbed.framework.preferences.manager.AuthManagerImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val preferencesDataStoreModule = module {
    single<ProtoDataSource<DataPreferences>> {
        PreferencesProtoDataSourceImpl(
            (androidApplication().applicationContext as GitBed).dataStorePreferences
        )
    }
}

val authDataStoreModule = module {
    single<PreferencesRepository> {
        AuthManagerImpl(get())
    }
}