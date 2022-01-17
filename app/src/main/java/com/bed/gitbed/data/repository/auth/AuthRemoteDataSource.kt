package com.bed.gitbed.data.repository.auth

interface AuthRemoteDataSource<T> {
    suspend fun auth(query: String): T
}