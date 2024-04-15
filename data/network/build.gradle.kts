plugins {
    id("technicaltestmango.android.library")
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.rafaelroldan.network"
}

dependencies {
    api(project(":data:modeldto"))
    api(project(":core:common"))
    api(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization)
}