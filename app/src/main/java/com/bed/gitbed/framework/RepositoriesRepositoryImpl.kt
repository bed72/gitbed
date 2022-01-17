package com.bed.gitbed.framework

import androidx.paging.PagingSource
import com.bed.gitbed.data.repository.repositories.RepositoriesRemoteDataSource
import com.bed.gitbed.data.repository.repositories.RepositoriesRepository
import com.bed.gitbed.domain.entity.Repositories
import com.bed.gitbed.framework.network.response.RepositoriesResponse
import com.bed.gitbed.framework.network.response.toEntity
import com.bed.gitbed.framework.paging.RepositoriesPagingSource
import com.bed.gitbed.usecase.base.ResultStatus

class RepositoriesRepositoryImpl(
    private val repositoriesRemoteDataSource: RepositoriesRemoteDataSource<RepositoriesResponse>
) : RepositoriesRepository {
    override fun fetchRepositories(query: String): PagingSource<Int, Repositories> =
        RepositoriesPagingSource(query, repositoriesRemoteDataSource)
}