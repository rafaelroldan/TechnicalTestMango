plugins {
    id("technicaltestmango.android.library")
    id("kotlin-kapt")
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
    kapt(libs.hilt.compiler)
}
