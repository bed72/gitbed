package com.bed.gitbed.framework.network.response

import com.bed.gitbed.domain.entity.Collaborators
import com.google.gson.annotations.SerializedName

data class CollaboratorsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
)

fun CollaboratorsResponse.toEntity() =
    Collaborators(
        id = this.id,
        avatarUrl = this.avatarUrl
    )