package com.bed.gitbed.framework.remote

import com.bed.gitbed.data.repository.repositories.RepositoriesRemoteDataSource
import com.bed.gitbed.framework.network.ApiService
import com.bed.gitbed.framework.network.response.RepositoriesResponse

class RetrofitRepositoriesDataSource(
    private val apiService: ApiService
) : RepositoriesRemoteDataSource<RepositoriesResponse> {
    override suspend fun getRepositories(queries: Map<String, String>): RepositoriesResponse =
        apiService.repositories(queries)
}
