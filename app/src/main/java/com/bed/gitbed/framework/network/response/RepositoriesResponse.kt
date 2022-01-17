package com.bed.gitbed.framework.network.response

import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(
    @SerializedName("total")
    val total: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("results")
    val results: List<RepositoryResponse>
)