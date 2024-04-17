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

    testImplementation(libs.mokk)
    testImplementation(libs.mokk.android)
    testImplementation(libs.mokk.agent)
    androidTestImplementation(libs.mokk.android)
    androidTestImplementation(libs.mokk.agent)

    testImplementation(libs.android.coroutines.testing)
}
