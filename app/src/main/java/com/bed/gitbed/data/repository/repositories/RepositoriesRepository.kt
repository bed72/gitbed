package com.bed.gitbed.data.repository.repositories

import androidx.paging.PagingSource
import com.bed.gitbed.domain.entity.Repositories
import com.bed.gitbed.usecase.base.ResultStatus

interface RepositoriesRepository {
    fun fetchRepositories(query: String): PagingSource<Int, Repositories>
}