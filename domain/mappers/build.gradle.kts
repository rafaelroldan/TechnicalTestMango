plugins {
    id("technicaltestmango.android.library")
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.rafaelroldan.mappers"
}

dependencies {

    api(project(":data:modeldto"))
    api(project(":domain:model"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt)


}