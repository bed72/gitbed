package com.bed.gitbed.framework.network.response

import com.bed.gitbed.domain.entity.Token
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token_type")
    val tokenType: String?,
    @SerializedName("access_token")
    val accessToken: String?
)

fun AuthResponse.toEntity() = Token(
    tokenType = this.tokenType,
    accessToken = this.accessToken
)