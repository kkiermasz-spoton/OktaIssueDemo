package com.kkiermasz.oktaissuedemo.login

import android.content.Context
import androidx.appcompat.widget.ThemedSpinnerAdapter.Helper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kkiermasz.oktaissuedemo.AppHelper
import com.kkiermasz.oktaissuedemo.BuildConfig
import com.kkiermasz.oktaissuedemo.NavigationRoute
import com.kkiermasz.oktaissuedemo.navigation
import com.okta.authfoundation.client.OidcClientResult
import com.okta.authfoundation.credential.Token
import com.okta.authfoundationbootstrap.CredentialBootstrap
import com.okta.webauthenticationui.WebAuthenticationClient.Companion.createWebAuthenticationClient
import com.spoton.connectcompanion.android.features.account.ui.login.router.LoginRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object LoginRoute: NavigationRoute {
    override val id: String
        get() = "LoginGraphRoute"
    override val title: String
        get() = "Login"
}

fun NavGraphBuilder.loginGraph(
    navController: NavController,
    navigateToDashboard: () -> Unit = {}
) {
    navigation(LoginNavigationRoute.Login, LoginRoute) {
        composable(LoginNavigationRoute.Login.id) {
            loginRoute(navigateToDashboard)
        }
    }
}

internal enum class LoginNavigationRoute(override val id: String, override val title: String) : NavigationRoute {
    Login("LoginId", "LoginTitle")
}

@Composable
private fun loginRoute(navigateToDashboard: () -> Unit = {}) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val router = LoginRouter(
        onLaunchOkta = { result ->
            invokeOktaLogin(context, coroutineScope) {
                result(it)
            }
        }
    )
    LoginScreen(router)
}

private fun invokeOktaLogin(context: Context, coroutineScope: CoroutineScope, onResult: (OidcClientResult<Token>) -> Unit) {
    val webAuthenticationClient = CredentialBootstrap.oidcClient.createWebAuthenticationClient()
    var scope = AppHelper.DEFAULT_SCOPE
    scope += " device_sso"

    coroutineScope.launch {
        val result = webAuthenticationClient.login(
            context = context,
            redirectUrl = BuildConfig.SIGN_IN_REDIRECT_URI,
            scope = scope,
        )
        onResult(result)
    }
}
