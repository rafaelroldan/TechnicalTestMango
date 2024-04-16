pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TechnicalTestMango"
include(":app")
include(":data:network")
include(":data:modeldto")
include(":data:repository")
include(":domain:mappers")
include(":domain:model")
include(":domain:usecase")
include(":core:common")
include(":core:designsystem")
include(":ui")
