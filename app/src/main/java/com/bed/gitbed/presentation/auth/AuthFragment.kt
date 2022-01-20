package com.bed.gitbed.presentation.auth

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bed.gitbed.BuildConfig
import com.bed.gitbed.R
import com.bed.gitbed.databinding.FragmentAuthBinding
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.bed.gitbed.presentation.auth.AuthViewModel.AuthState
import com.bed.gitbed.presentation.common.snack

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private lateinit var binding: FragmentAuthBinding
    private val viewModel: AuthViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAuthBinding.bind(view)

        setListeners()
        observeEvents()
    }

    override fun onResume() {
        super.onResume()

        handleCodeOAuth(activity?.intent?.data)
    }

    private fun setListeners() {
        binding.buttonLogin.setOnClickListener { handleAuthenticateWithWebView() }
    }

    private fun observeEvents() {
        viewModel.authStateData.observe(viewLifecycleOwner) { state ->
            when (state) {
                AuthState.ShowLoading -> setStateLoading()
                AuthState.ShowError -> {
                    setStateButton()

                    view?.snack(getString(R.string.text_error_auth))
                }
                AuthState.AuthSuccess ->
                    findNavController().navigate(R.id.action_auth_to_repositories)
            }
        }
    }

    private fun handleAuthenticateWithWebView() {
        val url = handleUrl().toString()

        setStateLoading()
        binding.webView.loadUrl(url)
    }

    private fun handleUrl() =
        BuildConfig.AUTHORIZATION_URL.toHttpUrl().newBuilder()
            .addQueryParameter(SCOPE_NAME, SCOPE_ACCESS)
            .addQueryParameter(CLIENT_ID, BuildConfig.GITHUB_ID)
            .addQueryParameter(REDIRECT_URI, BuildConfig.REDIRECT_URL)
            .build()

    private fun handleCodeOAuth(uri: Uri?) {
        if (uri != null && uri.toString().startsWith(BuildConfig.REDIRECT_URL)) {
            setStateLoading()

            uri.getQueryParameter(CODE)?.let { code ->
                viewModel.auth(code)
            }
        } else setStateButton()
    }

    private fun setStateLoading() {
        binding.flipperAuth.displayedChild = POSITION_SHOW_LOADING
    }

    private fun setStateButton() {
        binding.flipperAuth.displayedChild = POSITION_SHOW_BUTTON
    }


    companion object {
        private const val CODE = "code"
        private const val SCOPE_NAME = "scope"
        private const val SCOPE_ACCESS = "repo"
        private const val CLIENT_ID = "client_id"
        private const val REDIRECT_URI = "redirect_uri"

        private const val POSITION_SHOW_BUTTON = 0
        private const val POSITION_SHOW_LOADING = 1
    }
}