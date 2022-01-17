package com.bed.gitbed.usecase.base

import kotlinx.coroutines.CoroutineDispatcher

data class AppCoroutinesDispatchers(
    val io: CoroutineDispatcher,
    val main: CoroutineDispatcher,
    val computation: CoroutineDispatcher
)
