package com.bed.gitbed.presentation.common.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bed.gitbed.domain.entity.Repositories
import com.bed.gitbed.presentation.common.Utils
import com.bed.gitbed.usecase.RepositoriesUseCase
import kotlinx.coroutines.flow.Flow

open class RepositoriesViewModel(
    private val repositoriesUseCase: RepositoriesUseCase
) : ViewModel() {

    fun fetchRepositories(query: String): Flow<PagingData<Repositories>> =
        repositoriesUseCase(
            RepositoriesUseCase.RepositoriesParams(
                query,
                getPageConfig()
            )
        ).cachedIn(viewModelScope)

    private fun getPageConfig() = PagingConfig(
        pageSize = Utils.PAGE_SIZE
    )
}