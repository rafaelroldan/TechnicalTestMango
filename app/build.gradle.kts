plugins {
    id("technicaltestmango.android.application")
    id("kotlin-kapt")
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltPlugin)
}

android {
    defaultConfig {
        applicationId = "com.rafaelroldan.technicaltestmango"
        versionCode = 1

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    namespace = "com.rafaelroldan.technicaltestmango"
}

dependencies {

    api(project(":core:designsystem"))
    api(project(":ui"))
    api(project(":data:network"))

    implementation(libs.hilt)
    kapt(libs.hiltCompiler)

    implementation(libs.retrofit.json)
}