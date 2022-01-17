package com.bed.gitbed.data.repository.auth

import com.bed.gitbed.domain.entity.Token
import com.bed.gitbed.usecase.base.ResultStatus

interface AuthRepository {
    suspend fun login(query: String): ResultStatus<Token>
}