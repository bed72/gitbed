package com.bed.gitbed.data.repository.details

import com.bed.gitbed.usecase.base.ResultStatus
import okhttp3.ResponseBody

interface DetailsRepository {
    suspend fun fetchDetails(query: String): ResultStatus<ResponseBody>
}