package com.bed.gitbed.framework.remote

import com.bed.gitbed.data.repository.details.DetailsRemoteDataSource
import com.bed.gitbed.framework.network.ApiService
import okhttp3.ResponseBody

class RetrofitDetailsDataSource(
    private val apiService: ApiService
) : DetailsRemoteDataSource<ResponseBody> {
    override suspend fun getDetails(query: String): ResponseBody =
        apiService.readme(query)
}