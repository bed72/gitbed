package com.bed.gitbed.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bed.gitbed.data.repository.repositories.RepositoriesRepository
import com.bed.gitbed.usecase.RepositoriesUseCase.RepositoriesParams
import com.bed.gitbed.domain.entity.Repositories
import com.bed.gitbed.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow

interface RepositoriesUseCase {
    operator fun invoke(params: RepositoriesParams): Flow<PagingData<Repositories>>

    data class RepositoriesParams(val query: String, val pagingConfig: PagingConfig)
}

class RepositoriesUseCaseImpl(
    private val repositoriesRepository: RepositoriesRepository
) : PagingUseCase<RepositoriesParams, Repositories>(), RepositoriesUseCase {
    override fun createFlowObservable(params: RepositoriesParams): Flow<PagingData<Repositories>> {
        val pagingSource = repositoriesRepository.fetchRepositories(params.query)

        return Pager(config = params.pagingConfig) {
            pagingSource
        }.flow
    }
}