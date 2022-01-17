package com.bed.gitbed.framework.network.interceptor

import com.bed.gitbed.data.repository.preferences.PreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor(
    private val preferencesRepository: PreferencesRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestAuthorization = formatAuthorizationRequest(request)

        return if (PATH in request.url.toString())
            chain.proceed(requestAuthorization)
        else chain.proceed(request)
    }

    private fun formatAuthorizationRequest(request: Request): Request {
        val data = runBlocking { preferencesRepository.fetch().first() }
        val tokenFormatted = data.substringAfterLast(DATA)
            .replace(Regex("[\\W+.~]"), "")

        return tokenFormatted.getNewRequest(request)
    }

    private fun String.getNewRequest(request: Request): Request =
        request.newBuilder()
            .header(HEADER_AUTHORIZATION, "$HEADER_BEARER $this")
            .build()

    companion object {
        private const val DATA = "data"
        private const val PATH = "/repos"
        private const val HEADER_BEARER = " Bearer"
        private const val HEADER_AUTHORIZATION = "authorization"
    }
}