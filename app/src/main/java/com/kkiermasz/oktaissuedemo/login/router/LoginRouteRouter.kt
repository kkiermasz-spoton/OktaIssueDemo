package com.spoton.connectcompanion.android.features.account.ui.login.router

import com.okta.authfoundation.client.OidcClientResult
import com.okta.authfoundation.credential.Token

interface LoginRouteRouter {
    suspend fun resultReceived(result: OidcClientResult<Token>)
}
