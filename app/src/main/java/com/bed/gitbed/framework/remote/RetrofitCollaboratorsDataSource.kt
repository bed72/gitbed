package com.bed.gitbed.framework.remote

import com.bed.gitbed.data.repository.collaborators.CollaboratorsRemoteDataSource
import com.bed.gitbed.framework.network.ApiService
import com.bed.gitbed.framework.network.response.CollaboratorsResponse

class RetrofitCollaboratorsDataSource(
    private val apiService: ApiService
) : CollaboratorsRemoteDataSource<List<CollaboratorsResponse>> {
    override suspend fun getCollaborators(query: String): List<CollaboratorsResponse> =
        apiService.collaborators(query)
}