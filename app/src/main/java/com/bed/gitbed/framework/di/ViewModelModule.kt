package com.bed.gitbed.framework.di

import com.bed.gitbed.presentation.auth.AuthViewModel
import com.bed.gitbed.presentation.privates.PrivateViewModel
import com.bed.gitbed.presentation.publics.PublicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AuthViewModel(get())
    }

    viewModel {
        PublicViewModel(get())
    }

    viewModel {
        PrivateViewModel(get())
    }
}