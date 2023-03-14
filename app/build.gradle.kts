import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
}

val oktaProperties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "okta.properties")))
}

android {
    namespace = "com.kkiermasz.oktaissuedemo"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.kkiermasz.oktaissuedemo"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "ISSUER", oktaProperties.getProperty("issuer"))
        buildConfigField("String", "CLIENT_ID", oktaProperties.getProperty("clientId"))
        buildConfigField("String", "SIGN_IN_REDIRECT_URI", oktaProperties.getProperty("signInRedirectUri"))
        buildConfigField("String", "SIGN_OUT_REDIRECT_URI", oktaProperties.getProperty("signOutRedirectUri"))

        manifestPlaceholders["webAuthenticationRedirectScheme"] = oktaProperties.getProperty("signInRedirectUri")
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }

        getByName("debug") {
            applicationIdSuffix = "debug"
        }

        create("staging") {
            initWith(getByName("debug"))
            applicationIdSuffix = "staging"
            matchingFallbacks += listOf("debug")
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.annotation:annotation:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.core:core-ktx:1.7.0")
    with(Deps.Compose) {
        implementation(core)
        implementation(composeTheme)
        implementation(composeConstraint)
        implementation(composeActivity)
        implementation(composeViewModel)
        implementation(foundation)
        implementation(ui)
        implementation(tooling)
        implementation(toolingPreview)
        implementation(material)
        implementation(navigation)
    }
    with(Deps.Views) {
        implementation(materialComponents)
    }
    with(Deps.Okta.Android) {
        implementation(platform(bom))
        implementation(authFoundationBootstrap)
        implementation(webAuthenticationUi)
    }
}

//dependencies {
//
//    implementation 'androidx.core:core-ktx:1.7.0'
//    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
//    implementation 'androidx.activity:activity-compose:1.3.1'
//    implementation "androidx.compose.ui:ui:$compose_ui_version"
//    implementation "androidx.compose.ui:ui-tooling-preview:$compose_ui_version"
//    implementation 'androidx.compose.material:material:1.2.0'
//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
//    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_ui_version"
//    debugImplementation "androidx.compose.ui:ui-tooling:$compose_ui_version"
//    debugImplementation "androidx.compose.ui:ui-test-manifest:$compose_ui_version"
//}