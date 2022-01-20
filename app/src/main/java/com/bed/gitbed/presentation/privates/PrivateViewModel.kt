package com.bed.gitbed.presentation.privates

import com.bed.gitbed.presentation.common.viewModel.RepositoriesViewModel
import com.bed.gitbed.usecase.RepositoriesUseCase

class PrivateViewModel(
    repositoriesUseCase: RepositoriesUseCase
) : RepositoriesViewModel(repositoriesUseCase) { }