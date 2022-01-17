package com.bed.gitbed.data.repository.details

interface DetailsRemoteDataSource<T> {
    suspend fun getDetails(query: String): T
}