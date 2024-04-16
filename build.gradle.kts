import com.diffplug.spotless.LineEnding

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.hiltPlugin) apply false
    alias(libs.plugins.spotlessPlugin)
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

        // ktlint()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}

// endregion
