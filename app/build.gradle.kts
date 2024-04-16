import com.diffplug.spotless.LineEnding
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("technicaltestmango.android.application")
    id("kotlin-kapt")
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltPlugin)
    id("com.diffplug.spotless") version "6.25.0"
}

android {
    defaultConfig {
        applicationId = "com.rafaelroldan.technicaltestmango"
        versionCode = 1

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

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

// region Spotless

spotless {
    lineEndings = LineEnding.PLATFORM_NATIVE

    format("misc") {
        target("**/*.md", "**/.gitignore", "**/*.pro")
        targetExclude("**/build/**", ".idea/**", "/docs/public/**")

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    format("xml") {
        target("**/*.xml")
        targetExclude("**/build/**", ".idea/**", "**/detekt-baseline.xml")

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    format("yml") {
        target("**/*.yml", "**/*.yaml")

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    format("toml") {
        target("**/*.toml")
        targetExclude("**/build/**", ".idea/**")

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        targetExclude("**/build/**")

        ktlint()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    kotlin {
        target("**/*.kt", "**/*.kts")
        targetExclude("**/build/**", "**/*.gradle.kts", "**/trusteer/**")

        ktlint()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}

// endregion
