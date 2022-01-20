package com.bed.gitbed.framework.network.response

import com.bed.gitbed.domain.entity.Collaborators
import com.bed.gitbed.domain.entity.Repositories
import com.bed.gitbed.presentation.common.Utils
import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("id")
    val id: Number,

    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("private")
    val private: Boolean,

    @SerializedName("language")
    val language: String?,

    @SerializedName("forks_count")
    val forks: Int,

    @SerializedName("stargazers_count")
    val stars: Int,

    var collaborators: List<CollaboratorsResponse>?
)

fun RepositoryResponse.toEntity() =
    Repositories(
        id = this.id,
        url = this.url,
        name = this.name,
        forks = this.forks,
        stars = this.stars,
        private = this.private,
        language = this.language ?: Utils.LANGUAGE_MESSAGE_RESPONSE,
        description = this.description ?: Utils.DESCRIPTION_MESSAGE_RESPONSE,
        collaborators = this.collaborators?.map { it.toEntity() } ?: defaultCollaborators
    )

private val defaultCollaborators =
    listOf(Collaborators(id = Utils.ID_USER, avatarUrl = Utils.AVATAR_URL))