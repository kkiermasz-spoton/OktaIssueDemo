package com.kkiermasz.oktaissuedemo

import android.app.Application
import com.okta.authfoundation.AuthFoundationDefaults
import com.okta.authfoundation.client.OidcClient
import com.okta.authfoundation.client.OidcConfiguration
import com.okta.authfoundation.client.SharedPreferencesCache
import com.okta.authfoundation.credential.CredentialDataSource.Companion.createCredentialDataSource
import com.okta.authfoundationbootstrap.CredentialBootstrap
import okhttp3.HttpUrl.Companion.toHttpUrl

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        setupOkta()
    }

    private fun setupOkta() {
        AuthFoundationDefaults.cache = SharedPreferencesCache.create(this)
        val oidcConfiguration = OidcConfiguration(
            clientId = BuildConfig.CLIENT_ID,
            defaultScope = AppHelper.DEFAULT_SCOPE,
        )
        val oidcClient = OidcClient.createFromDiscoveryUrl(
            oidcConfiguration,
            "${BuildConfig.ISSUER}/.well-known/openid-configuration".toHttpUrl(),
        )
        CredentialBootstrap.initialize(oidcClient.createCredentialDataSource(this))
    }
}

internal object AppHelper {

    const val DEFAULT_SCOPE: String = "openid email profile offline_access"

}