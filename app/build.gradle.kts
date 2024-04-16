import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.io.FileInputStream
import java.util.Properties

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

        val localProperties: Properties = Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "local.properties")))
        }
        buildConfigField(
            type = "String",
            name = "MARVEL_PRIVATE_KEY",
            value = localProperties.getProperty("MARVEL_PRIVATE_KEY") ?: "")
        buildConfigField(
            type = "String",
            name = "MARVEL_PUBLIC_KEY",
            value = localProperties.getProperty("MARVEL_PUBLIC_KEY") ?: "")
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

    implementation(project(":ui"))
    implementation(project(":data:network"))

    implementation(libs.hilt)
    implementation(project(":data:repository"))
    kapt(libs.hilt.compiler)

    implementation(libs.retrofit.json)
}