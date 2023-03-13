package com.spoton.connectcompanion.android.features.account.ui.login.router

import com.okta.authfoundation.client.OidcClientResult
import com.okta.authfoundation.credential.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel

interface LoginScreenRouter {
    val oidcResult: ReceiveChannel<OidcClientResult<Token>>

    suspend fun launchOkta(coroutineScope: CoroutineScope)
}
