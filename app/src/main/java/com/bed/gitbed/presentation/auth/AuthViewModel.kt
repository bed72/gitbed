package com.bed.gitbed.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bed.gitbed.domain.entity.Token
import com.bed.gitbed.usecase.AuthUseCase
import com.bed.gitbed.usecase.AuthUseCase.AuthParams
import com.bed.gitbed.usecase.base.ResultStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _authStateData = MutableLiveData<AuthState>()
    val authStateData: LiveData<AuthState> get() = _authStateData

    fun auth(code: String) = viewModelScope.launch {
        authUseCase(AuthParams(code)).watchStatus()
    }

    private fun Flow<ResultStatus<Token>>.watchStatus() = viewModelScope.launch {
        collect { status ->
            _authStateData.value = when(status) {
                ResultStatus.Loading -> AuthState.ShowLoading
                is ResultStatus.Error -> AuthState.ShowError
                is ResultStatus.Success -> AuthState.AuthSuccess
            }
        }
    }

    sealed class AuthState {
        object ShowError : AuthState()
        object AuthSuccess : AuthState()
        object ShowLoading : AuthState()
    }
}
