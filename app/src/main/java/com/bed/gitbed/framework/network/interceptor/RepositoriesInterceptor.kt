package com.bed.gitbed.framework.network.interceptor

import com.bed.gitbed.framework.network.response.RepositoryResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONException

class RepositoriesInterceptor(
    private val gson: Gson = Gson()
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return if (response.code == SUCCESS && PATH in request.url.toString())
           handleResponse(response)
        else response
    }

    private fun handleResponse(response: Response): Response =
        try {
            val formatted = formatResponse(response.body, nextPage(response))
            val body = formatted.toResponseBody(response.body?.contentType())

            response.newBuilder().body(body).build()
        } catch (exception: JSONException) {
            response
        }

    private fun formatResponse(response: ResponseBody?, next: Int): String {
        val data = response?.string()
        val totalResults = toJson(data).size
        val config: Int = if (next < totalResults) next else 1

        return "{$TOTAL_RESULTS: $totalResults, $OFFSET: $config, $RESULTS: $data}"
    }

    private fun toJson(data: String?): Collection<RepositoryResponse> {
        val repositoriesResponseType =
            object : TypeToken<Collection<RepositoryResponse?>?>() {}.type

        return gson.fromJson(data, repositoriesResponseType)
    }

    private fun nextPage(response: Response) =
        response.header("link")?.substringAfter("page=")?.substringBefore("&per_page")?.toInt() ?: 0

    companion object {
        private const val SUCCESS = 200
        private const val PATH = "/repos"
        private const val OFFSET = "offset"
        private const val RESULTS = "results"
        private const val TOTAL_RESULTS = "total"
    }
}