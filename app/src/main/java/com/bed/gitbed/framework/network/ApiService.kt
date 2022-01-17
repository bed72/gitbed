package com.bed.gitbed.framework.network

import com.bed.gitbed.BuildConfig
import com.bed.gitbed.framework.network.response.AuthResponse
import com.bed.gitbed.framework.network.response.CollaboratorsResponse
import com.bed.gitbed.framework.network.response.RepositoriesResponse
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST(BuildConfig.AUTHENTICATION_URL)
    @Headers("Accept: application/json")
    suspend fun auth(
        @Field("code") code: String,
        @Field("client_id") clientId: String = BuildConfig.GITHUB_ID,
        @Field("client_secret") clientSecret: String = BuildConfig.GITHUB_SECRET,
    ): AuthResponse

    @GET("user/repos")
    @Headers("Content-Type: application/json")
    suspend fun repositories(
        @QueryMap
        queries: Map<String, String>
    ): RepositoriesResponse

    @GET("repos/bed72/{user}/collaborators")
    suspend fun collaborators(
        @Path("user") user: String,
    ): List<CollaboratorsResponse>

    @GET("repos/bed72/{user}/readme")
    @Headers("Accept: application/vnd.github.raw")
    suspend fun readme(
        @Path("user") user: String,
    ): ResponseBody
}