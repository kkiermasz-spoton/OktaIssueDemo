package com.kkiermasz.oktaissuedemo.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okta.authfoundation.client.OidcClientResult
import com.spoton.connectcompanion.android.features.account.ui.login.router.LoginScreenRouter
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val title: String = "Login",
    val isOktaInProgress: Boolean
)


class LoginViewModel(
    private val router: LoginScreenRouter
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState(isOktaInProgress = false))
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun oktaButtonTapped() {
        uiState.value.copy(isOktaInProgress = true)
        viewModelScope.launch {
            router.launchOkta(viewModelScope)
            router.oidcResult.consumeEach {
                uiState.value.copy(isOktaInProgress = false)
                when(it) {
                    is OidcClientResult.Error -> {
//                      TODO("Implement error handling")
                    }
                    is OidcClientResult.Success -> {
//                      TODO("Implement success handling")
                    }
                }
            }
        }
    }

}
