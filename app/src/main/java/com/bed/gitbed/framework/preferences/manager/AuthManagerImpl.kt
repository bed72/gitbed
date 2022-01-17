package com.bed.gitbed.framework.preferences.manager

import com.bed.gitbed.DataPreferences
import com.bed.gitbed.data.repository.preferences.PreferencesRepository
import com.bed.gitbed.data.repository.preferences.ProtoDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthManagerImpl(
    private val protoDataSource: ProtoDataSource<DataPreferences>
) : PreferencesRepository {
    override suspend fun clear() {
        protoDataSource.clear()
    }

    override suspend fun save(data: String) {
        DataPreferences.newBuilder()
            .setData(data)
            .build().run {
                protoDataSource.update(this)
            }
    }

    override suspend fun fetch(): Flow<String> {
        return protoDataSource.preferencesFlow.map { it.toString() }
    }
}