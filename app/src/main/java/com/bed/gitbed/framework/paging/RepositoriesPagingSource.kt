package com.bed.gitbed.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bed.gitbed.data.repository.collaborators.CollaboratorsRemoteDataSource
import com.bed.gitbed.data.repository.repositories.RepositoriesRemoteDataSource
import com.bed.gitbed.domain.entity.Repositories
import com.bed.gitbed.framework.network.response.CollaboratorsResponse
import com.bed.gitbed.framework.network.response.RepositoriesResponse
import com.bed.gitbed.framework.network.response.toEntity
import com.bed.gitbed.presentation.common.Utils

class RepositoriesPagingSource(
    private val query: String,
    private val remoteRepositoriesDataSource: RepositoriesRemoteDataSource<RepositoriesResponse>,
    private val remoteCollaboratorsDataSource: CollaboratorsRemoteDataSource<List<CollaboratorsResponse>>
) : PagingSource<Int, Repositories>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repositories> {
        return try {
            val page = params.key ?: PAGE
            val perPage = params.key ?: PER_PAGE
            val queries = hashMapOf(
                "page" to page.toString(),
                "per_page" to perPage.toString()
            )

            val response = handleRemoteRepositories(queries)
            val responseTotal = response.total
            val responseOffset = response.offset
            val type = if (query.toBoolean()) private(response) else public(response)

            LoadResult.Page(
                prevKey = null,
                data = type.map { it.toEntity() },
                nextKey = if (responseOffset < responseTotal) responseOffset + Utils.PAGE_SIZE else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repositories>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(Utils.PAGE_SIZE) ?: anchorPage?.nextKey?.minus(Utils.PAGE_SIZE)
        }

    private suspend fun handleRemoteRepositories(queries: HashMap<String, String>): RepositoriesResponse {
        val repositories = remoteRepositoriesDataSource.getRepositories(queries)
        repositories.results.forEach { repository ->
            repository.collaborators = remoteCollaboratorsDataSource.getCollaborators(repository.name)
        }

        return repositories
    }

    private fun public(repositories: RepositoriesResponse) =
        repositories.results.filter { types -> !types.private }

    private fun private(repositories: RepositoriesResponse) =
        repositories.results.filter { types -> types.private }

    companion object {
        private const val PAGE = 0
        private const val PER_PAGE = 80
    }
}