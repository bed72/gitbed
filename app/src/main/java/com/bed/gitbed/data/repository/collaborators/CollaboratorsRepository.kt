package com.bed.gitbed.data.repository.collaborators

import com.bed.gitbed.domain.entity.Collaborators
import com.bed.gitbed.usecase.base.ResultStatus

interface CollaboratorsRepository {
    suspend fun fetchCollaborators(query: String): ResultStatus<List<Collaborators>>
}