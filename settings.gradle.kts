// Downstream modification, 2026-11-02: allow standalone plugin publishing.
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidJunkCode"
include(":library")
// Build/publish the plugin before its first remote release without resolving the demo plugin.
if (!providers.gradleProperty("skipDemo").map { it.toBoolean() }.getOrElse(false)) {
    include(":app")
}
