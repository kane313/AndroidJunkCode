// Downstream compatibility fixture, 2026-11-03.
pluginManagement {
    repositories {
        if (providers.gradleProperty("useMavenLocal").orNull == "true") mavenLocal()
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("io.github.kane313.android-junk-code") version
            providers.gradleProperty("junkCodeVersion").getOrElse("2.0.0-team.2")
    }
}
dependencyResolutionManagement { repositories { google(); mavenCentral() } }
rootProject.name = "junk-code-gradle74-compatibility"
