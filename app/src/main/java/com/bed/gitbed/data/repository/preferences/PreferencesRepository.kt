package com.bed.gitbed.data.repository.preferences

import kotlinx.coroutines.flow.Flow


interface PreferencesRepository {
    suspend fun clear()
    suspend fun save(data: String)
    suspend fun fetch(): Flow<String>
}