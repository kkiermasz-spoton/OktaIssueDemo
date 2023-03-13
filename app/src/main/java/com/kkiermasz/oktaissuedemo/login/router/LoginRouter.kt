package com.spoton.connectcompanion.android.features.account.ui.login.router

import com.okta.authfoundation.client.OidcClientResult
import com.okta.authfoundation.credential.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

class LoginRouter(
    val onLaunchOkta: (result: (OidcClientResult<Token>) -> Unit) -> Unit
): LoginScreenRouter, LoginRouteRouter {
    private val _oidcResult = Channel<OidcClientResult<Token>>()
    override val oidcResult: ReceiveChannel<OidcClientResult<Token>> = _oidcResult

    override suspend fun launchOkta(coroutineScope: CoroutineScope) {
        onLaunchOkta { coroutineScope.launch { resultReceived(it) } }
    }
    override suspend fun resultReceived(result: OidcClientResult<Token>) {
        _oidcResult.send(result)
    }
}
