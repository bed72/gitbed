package com.bed.gitbed.presentation.publics

import com.bed.gitbed.presentation.common.viewModel.RepositoriesViewModel
import com.bed.gitbed.usecase.RepositoriesUseCase

class PublicViewModel(
    repositoriesUseCase: RepositoriesUseCase
) : RepositoriesViewModel(repositoriesUseCase) {}