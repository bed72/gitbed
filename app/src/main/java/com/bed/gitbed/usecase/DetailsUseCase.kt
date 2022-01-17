package com.bed.gitbed.usecase

import com.bed.gitbed.data.repository.details.DetailsRepository
import com.bed.gitbed.usecase.DetailsUseCase.DetailsParams
import com.bed.gitbed.usecase.base.ResultStatus
import com.bed.gitbed.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface DetailsUseCase {
    operator fun invoke(params: DetailsParams): Flow<ResultStatus<ResponseBody>>

    data class DetailsParams(val query: String)
}

class DetailsUseCaseImpl(
    private val detailsRepository: DetailsRepository
) : UseCase<DetailsParams, ResponseBody>(), DetailsUseCase {
    override suspend fun doWork(params: DetailsParams) =
        detailsRepository.fetchDetails(params.query)
}