package com.bed.gitbed.framework

import com.bed.gitbed.data.repository.collaborators.CollaboratorsRemoteDataSource
import com.bed.gitbed.data.repository.collaborators.CollaboratorsRepository
import com.bed.gitbed.domain.entity.Collaborators
import com.bed.gitbed.framework.network.response.CollaboratorsResponse
import com.bed.gitbed.framework.network.response.toEntity
import com.bed.gitbed.usecase.base.ResultStatus

class CollaboratorsRepositoryImpl(
    private val collaboratorsRemoteDataSource: CollaboratorsRemoteDataSource<List<CollaboratorsResponse>>
) : CollaboratorsRepository {
    override suspend fun fetchCollaborators(query: String): ResultStatus<List<Collaborators>> {
        val response = collaboratorsRemoteDataSource.getCollaborators(query).map { collaborator ->
            collaborator.toEntity()
        }

        return ResultStatus.Success(response)
    }
}