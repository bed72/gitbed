package com.bed.gitbed.framework.network.response

import com.bed.gitbed.domain.entity.Repositories
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
)

fun RepositoryResponse.toEntity() =
    Repositories(
        id = this.id,
        url = this.url,
        name = this.name,
        forks = this.forks,
        stars = this.stars,
        private = this.private,
        language = this.language,
        description = this.description ?: "Opps esse veio sem descrição :("
    )