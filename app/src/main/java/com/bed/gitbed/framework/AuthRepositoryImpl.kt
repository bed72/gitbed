package com.bed.gitbed.framework

import com.bed.gitbed.data.repository.auth.AuthRemoteDataSource
import com.bed.gitbed.data.repository.auth.AuthRepository
import com.bed.gitbed.data.repository.preferences.PreferencesRepository
import com.bed.gitbed.domain.entity.Token
import com.bed.gitbed.framework.network.response.AuthResponse
import com.bed.gitbed.framework.network.response.toEntity
import com.bed.gitbed.usecase.base.ResultStatus

class AuthRepositoryImpl(
    private val preferencesRepository: PreferencesRepository,
    private val authRemoteDataSource: AuthRemoteDataSource<AuthResponse>
) : AuthRepository {
    override suspend fun login(query: String): ResultStatus<Token> {
        val response = authRemoteDataSource.auth(query)
        response.accessToken?.let { token -> preferencesRepository.save(token) }

        return ResultStatus.Success(response.toEntity())
    }
}