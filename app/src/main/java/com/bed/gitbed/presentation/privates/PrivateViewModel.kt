package com.bed.gitbed.presentation.privates

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bed.gitbed.domain.entity.Repositories
import com.bed.gitbed.presentation.common.viewModel.RepositoriesViewMode
import com.bed.gitbed.usecase.RepositoriesUseCase
import kotlinx.coroutines.flow.Flow

class PrivateViewModel(
    private val repositoriesUseCase: RepositoriesUseCase
) : RepositoriesViewMode<Flow<PagingData<Repositories>>>() {
    override fun fetchRepositories(query: String): Flow<PagingData<Repositories>> =
        repositoriesUseCase(
            RepositoriesUseCase.RepositoriesParams(
                query,
                getPageConfig()
            )
        ).cachedIn(viewModelScope)

    private fun getPageConfig() = PagingConfig(
        pageSize = 10
    )
}