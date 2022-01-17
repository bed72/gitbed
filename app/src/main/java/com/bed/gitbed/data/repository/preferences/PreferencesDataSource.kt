package com.bed.gitbed.data.repository.preferences

import kotlinx.coroutines.flow.Flow

interface ProtoDataSource<T> {
    val preferencesFlow: Flow<T>
    suspend fun update(value: T)
    suspend fun clear()
}