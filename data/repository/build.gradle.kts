plugins {
    id("technicaltestmango.android.library")
    id("kotlin-kapt")
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.rafaelroldan.repository"
}

dependencies {

    implementation(project(":data:network"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
