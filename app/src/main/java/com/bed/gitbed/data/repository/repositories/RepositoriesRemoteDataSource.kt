package com.bed.gitbed.data.repository.repositories

interface RepositoriesRemoteDataSource<T> {
    suspend fun getRepositories(queries: Map<String, String>): T
}