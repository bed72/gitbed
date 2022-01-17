package com.bed.gitbed

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.bed.gitbed.framework.di.*
import com.bed.gitbed.framework.preferences.serializer.PreferencesSerializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GitBed : Application() {
    val dataStorePreferences: DataStore<DataPreferences> by dataStore(
        fileName = BuildConfig.DATA_STORE,
        serializer = PreferencesSerializer
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@GitBed)

            modules(
                networkModule,
                useCaseModule,
                repositoryModule,
                preferencesDataStoreModule,
                authDataStoreModule,
                viewModelModule,
            )
        }
    }
}