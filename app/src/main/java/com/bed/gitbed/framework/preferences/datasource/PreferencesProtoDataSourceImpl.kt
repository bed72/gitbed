package com.bed.gitbed.framework.preferences.datasource

import androidx.datastore.core.DataStore

import com.bed.gitbed.DataPreferences
import com.bed.gitbed.data.repository.preferences.ProtoDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class PreferencesProtoDataSourceImpl(
    private val dataStore: DataStore<DataPreferences>
) : ProtoDataSource<DataPreferences> {

    override val preferencesFlow: Flow<DataPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException)
                emit(DataPreferences.getDefaultInstance())
            else throw exception
        }

    override suspend fun update(value: DataPreferences) {
        dataStore.updateData { dataPreferences ->
            dataPreferences.toBuilder()
                .setData(value.data)
                .build()
        }
    }

    override suspend fun clear() {
        dataStore.updateData { dataPreferences ->
            dataPreferences.toBuilder()
                .clear()
                .build()
        }
    }

}