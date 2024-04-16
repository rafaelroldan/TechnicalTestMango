import java.io.FileInputStream
import java.util.Properties

plugins {
    id("technicaltestmango.android.library")
    id("kotlin-kapt")
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.rafaelroldan.network"

    defaultConfig {
        val localProperties: Properties =
            Properties().apply {
                load(FileInputStream(File(rootProject.rootDir, "local.properties")))
            }
        buildConfigField(
            type = "String",
            name = "MARVEL_PRIVATE_KEY",
            value = localProperties.getProperty("MARVEL_PRIVATE_KEY") ?: "",
        )
        buildConfigField(
            type = "String",
            name = "MARVEL_PUBLIC_KEY",
            value = localProperties.getProperty("MARVEL_PUBLIC_KEY") ?: "",
        )
    }
}

dependencies {
    api(project(":data:modeldto"))
    api(project(":core:common"))

    api(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.retrofit.json)

    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
