plugins {
    id("technicaltestmango.android.library")
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.rafaelroldan.usecase"
}

dependencies {

    implementation(project(":data:repository"))
    api(project(":domain:mappers"))

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt)
}