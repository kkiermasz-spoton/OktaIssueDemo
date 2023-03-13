object Versions {
    object Compose {
        const val core = "1.1.1"
        const val theme = "1.1.14"
        const val constraint = "1.0.1"
        // Do not bump composeActivity, it seems like version 1.6.0 will only compile on android 13 - https://issuetracker.google.com/issues/245271877
        const val activity = "1.5.1"
        const val viewModel = "2.5.1"
        const val foundation = "1.2.1"
        const val toolingPreview = "1.2.1"
        const val tooling = "1.2.1"
        const val ui = "1.2.1"
        const val material = "1.2.1"
        const val navigation = "2.5.1"
    }
    object Views {
        const val materialComponents = "1.7.0"
    }
    object Okta {
        object Android {
            const val bom = "1.1.2"
        }
    }
}

object Deps {
    object Compose {
        const val core = "androidx.compose.ui:ui:${Versions.Compose.core}"
        const val composeTheme = "com.google.android.material:compose-theme-adapter:${Versions.Compose.theme}"
        const val composeConstraint = "androidx.constraintlayout:constraintlayout-compose:${Versions.Compose.constraint}"
        const val composeActivity = "androidx.activity:activity-compose:${Versions.Compose.activity}"
        const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.viewModel}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.Compose.foundation}"
        const val ui = "androidx.compose.ui:ui:${Versions.Compose.ui}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.tooling}"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose.toolingPreview}"
        const val material = "androidx.compose.material:material:${Versions.Compose.material}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.Compose.navigation}"
    }
    object Views {
        const val materialComponents = "com.google.android.material:material:${Versions.Views.materialComponents}"
    }
    object Okta {
        object Android {
            const val bom = "com.okta.kotlin:bom:${Versions.Okta.Android.bom}"
            const val authFoundation = "com.okta.kotlin:auth-foundation"
            const val authFoundationBootstrap = "com.okta.kotlin:auth-foundation-bootstrap"
            const val oauth2 = "com.okta.kotlin:oauth2"
            const val webAuthenticationUi = "com.okta.kotlin:web-authentication-ui"
        }
    }
}
