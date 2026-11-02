# Repository Guidelines

## Project Structure & Module Organization

- `library/` implements the Gradle plugin. Kotlin sources live under `src/main/kotlin/cn/hx/plugin/junkcode/`, organized into `plugin`, `ext`, `task`, `template`, and `utils` packages. Plugin metadata lives in `src/main/resources/META-INF/gradle-plugins/`.
- `app/` is the Android demo. Application code is in `src/main/java/`, resources in `src/main/res/`, JVM tests in `src/test/`, and device tests in `src/androidTest/`.
- `gradle/libs.versions.toml` centralizes dependencies and plugin versions. Root Gradle scripts configure repositories and modules.
- Generated Java, resources, manifests, and ProGuard rules belong under `app/build/generated/source/junk/<variant>/`; do not edit or commit generated output.

## Build, Test, and Development Commands

Use the Gradle wrapper with JDK 17 and Android SDK 36 installed. Configure the SDK location through local, uncommitted `local.properties` when needed.

- `./gradlew :library:build` — compile and check the plugin module.
- `./gradlew :app:assembleDebug -PPLUGIN_ENABLE=false` — build the demo without junk generation.
- `./gradlew :app:assembleDebug -PPLUGIN_ENABLE=true` — exercise debug generation and packaging.
- `./gradlew :app:installDebug` — install the demo on a connected device or emulator.
- `./gradlew :app:testDebugUnitTest` — run local JUnit tests.
- `./gradlew :app:connectedDebugAndroidTest` — run device tests.
- `./gradlew :app:lintDebug` — run Android lint; inspect reports because lint is configured not to abort on errors.

The demo resolves the versioned plugin alias, not automatically the sibling `library` sources. When validating plugin changes, publish locally and point the alias to that local version; plugin resolution already includes `mavenLocal()`.

## Coding Style & Naming Conventions

Follow existing Kotlin and Gradle Kotlin DSL style: four-space indentation, `UpperCamelCase` classes, `lowerCamelCase` functions/properties, and lowercase packages. Use descriptive task names such as `GenerateJunkCodeTask` and snake_case Android resource names. No dedicated formatter or Kotlin linter is configured.

## Testing Guidelines

Existing tests use JUnit 4 and AndroidX JUnit with `AndroidJUnitRunner`; Espresso is available. Name test classes `*Test` and methods after the behavior checked. No coverage threshold is configured. For generator changes, verify generated sources, resource references, manifest entries, and release shrinking with `:app:assembleRelease -PPLUGIN_ENABLE=true` against the modified plugin.

## Commit & Pull Request Guidelines

Recent commits use short Chinese descriptions of concrete changes, without mandatory Conventional Commit prefixes. Keep commits focused. In pull requests, describe behavior changes, link relevant issues, and report validation commands and results. Include generated-output examples for generator changes and screenshots only for visible demo changes. Document compatibility or configuration changes explicitly.
