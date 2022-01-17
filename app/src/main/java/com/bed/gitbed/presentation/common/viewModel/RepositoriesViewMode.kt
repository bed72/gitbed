package com.bed.gitbed.presentation.common.viewModel

import androidx.lifecycle.ViewModel

abstract class RepositoriesViewMode<T>: ViewModel() {
    abstract fun fetchRepositories(query: String): T
}