package com.bed.gitbed.framework.remote

import com.bed.gitbed.framework.network.ApiService
import com.bed.gitbed.data.repository.auth.AuthRemoteDataSource
import com.bed.gitbed.framework.network.response.AuthResponse

class RetrofitAuthDataSource(
    private val apiService: ApiService
) : AuthRemoteDataSource<AuthResponse> {
    override suspend fun auth(query: String): AuthResponse = apiService.auth(query)
}