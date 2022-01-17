package com.bed.gitbed.usecase.base

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class UseCase<in Params, R> {
    operator fun invoke(params: Params): Flow<ResultStatus<R>> =
        flow {
            emit(ResultStatus.Loading)
            emit(doWork(params))
        }.catch { throwable ->
            emit(ResultStatus.Error(throwable))
        }

    protected abstract suspend fun doWork(params: Params): ResultStatus<R>
}

abstract class PagingUseCase<in P, R : Any> {
    operator fun invoke(params: P): Flow<PagingData<R>> = createFlowObservable(params)

    protected abstract fun createFlowObservable(params: P): Flow<PagingData<R>>
}
