plugins {
    id("technicaltestmango.android.library")
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.rafaelroldan.repository"
}

dependencies {

    implementation(project(":data:network"))

    implementation(libs.androidx.core.ktx)
}
