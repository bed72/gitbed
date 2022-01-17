package com.bed.gitbed.data.repository.collaborators

interface CollaboratorsRemoteDataSource<T> {
    suspend fun getCollaborators(query: String): T
}