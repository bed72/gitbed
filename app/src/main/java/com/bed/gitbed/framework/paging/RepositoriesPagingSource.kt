package com.bed.gitbed.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bed.gitbed.data.repository.repositories.RepositoriesRemoteDataSource
import com.bed.gitbed.domain.entity.Repositories
import com.bed.gitbed.framework.network.response.RepositoriesResponse
import com.bed.gitbed.framework.network.response.toEntity

class RepositoriesPagingSource(
    private val query: String,
    private val remoteDataSource: RepositoriesRemoteDataSource<RepositoriesResponse>
) : PagingSource<Int, Repositories>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repositories> {
        return try {
            val page = params.key ?: 0
            val perPage = params.key ?: 80
            val queries = hashMapOf(
                "page" to page.toString(),
                "per_page" to perPage.toString()
            )

            val response = remoteDataSource.getRepositories(queries)
            val responseTotal = response.total
            val responseOffset = response.offset
            val type = if (query.toBoolean()) private(response) else public(response)

            LoadResult.Page(
                prevKey = null,
                data = type.map { it.toEntity() },
                nextKey = if (responseOffset < responseTotal) responseOffset + LIMIT else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repositories>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }

    private fun public(repositories: RepositoriesResponse) =
        repositories.results.filter { types -> !types.private }

    private fun private(repositories: RepositoriesResponse) =
        repositories.results.filter { types -> types.private }

    companion object {
        private const val LIMIT = 10
    }
}