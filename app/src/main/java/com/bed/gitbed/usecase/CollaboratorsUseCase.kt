package com.bed.gitbed.usecase

import com.bed.gitbed.data.repository.collaborators.CollaboratorsRepository
import com.bed.gitbed.usecase.CollaboratorsUseCase.CollaboratorsParams
import com.bed.gitbed.domain.entity.Collaborators
import com.bed.gitbed.usecase.base.ResultStatus
import com.bed.gitbed.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow

interface CollaboratorsUseCase {
    operator fun invoke(params: CollaboratorsParams): Flow<ResultStatus<Collaborators>>

    data class CollaboratorsParams(val query: String)
}

class CollaboratorsUseCaseImpl(
    private val collaboratorsRepository: CollaboratorsRepository
) : UseCase<CollaboratorsParams, Collaborators>(), CollaboratorsUseCase {
    override suspend fun doWork(params: CollaboratorsParams) =
        collaboratorsRepository.fetchCollaborators(params.query)
}