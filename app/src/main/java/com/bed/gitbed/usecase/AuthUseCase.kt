package com.bed.gitbed.usecase

import com.bed.gitbed.data.repository.auth.AuthRepository
import com.bed.gitbed.domain.entity.Token
import com.bed.gitbed.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow

import com.bed.gitbed.usecase.AuthUseCase.AuthParams
import com.bed.gitbed.usecase.base.ResultStatus


interface AuthUseCase {
    operator fun invoke(params: AuthParams): Flow<ResultStatus<Token>>

    data class AuthParams(val query: String)
}

class AuthUseCaseImpl(
    private val authRepository: AuthRepository
) : UseCase<AuthParams, Token>(), AuthUseCase {
    override suspend fun doWork(params: AuthParams) =
         authRepository.login(params.query)
}