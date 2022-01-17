package com.bed.gitbed.presentation.publics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bed.gitbed.domain.entity.Repositories
import com.bed.gitbed.presentation.common.viewModel.RepositoriesViewMode
import com.bed.gitbed.usecase.RepositoriesUseCase
import com.bed.gitbed.usecase.RepositoriesUseCase.RepositoriesParams
import kotlinx.coroutines.flow.Flow

class PublicViewModel(
    private val repositoriesUseCase: RepositoriesUseCase
) : RepositoriesViewMode<Flow<PagingData<Repositories>>>() {

    override fun fetchRepositories(query: String): Flow<PagingData<Repositories>> =
        repositoriesUseCase(
            RepositoriesParams(
                query,
                getPageConfig()
            )
        ).cachedIn(viewModelScope)

    private fun getPageConfig() = PagingConfig(
        pageSize = 10
    )
}