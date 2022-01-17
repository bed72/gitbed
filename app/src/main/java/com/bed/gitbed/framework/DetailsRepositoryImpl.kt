package com.bed.gitbed.framework

import com.bed.gitbed.data.repository.details.DetailsRemoteDataSource
import com.bed.gitbed.data.repository.details.DetailsRepository
import com.bed.gitbed.usecase.base.ResultStatus
import okhttp3.ResponseBody

class DetailsRepositoryImpl(
    private val detailsRemoteRemoteDataSource: DetailsRemoteDataSource<ResponseBody>
) : DetailsRepository {
    override suspend fun fetchDetails(query: String): ResultStatus<ResponseBody> {
        val response = detailsRemoteRemoteDataSource.getDetails(query)

        return ResultStatus.Success(response)
    }
}