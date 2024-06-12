import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")

}

android {
    namespace = "com.dashdrop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dashdrop"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.androidx.material.icons.extended)

    //Firebase BoM
    implementation(platform(libs.firebase.bom))

    //Firebase Auth
    implementation(libs.firebase.auth.ktx)

    //Firestore and analytics
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.database.ktx)

    implementation(libs.firebase.analytics)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Google Play Services Auth
    implementation (libs.play.services.auth)
    implementation(libs.androidx.credentials.play.services.auth)

    //To avoid Composition Local Error
    debugImplementation (libs.androidx.customview)
    debugImplementation (libs.androidx.customview.poolingcontainer)

    // Coil
    implementation(libs.coil.kt.compose)

//    // Java language implementation
//    implementation("androidx.navigation:navigation-fragment:$nav_version")
//    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Kotlin
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Feature module Support
    implementation(libs.androidx.navigation.dynamic.features.fragment)

    // Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)

    // Jetpack Compose Integration
    implementation(libs.androidx.navigation.compose)

    //DaggerHilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.gradle.plugin)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //Lottie
    implementation(libs.lottie.compose)

    //Picasso
    implementation (libs.picasso)

    //Accompanist Pager
    implementation (libs.accompanist.pager)
    implementation (libs.accompanist.pager.indicators)

}
