# Gradle 7.4 compatibility check

This independent Android fixture exercises Kotlin DSL enum configuration, generated Java/resources/manifest, and release shrinking with AGP 7.3.0. Install Android SDK 33, set `ANDROID_HOME`, and use an actual Gradle 7.4 installation (the repository wrapper uses Gradle 9).

```bash
# Build and publish the candidate locally using the repository wrapper and JDK 17.
bash gradlew :library:build :library:publishToMavenLocal -PskipDemo=true

# Run with JAVA_HOME pointing first to JDK 11, then to JDK 17.
/path/to/gradle-7.4/bin/gradle -p compatibility/gradle74 \
  clean verifyTemplates assembleRelease -PuseMavenLocal=true

# After Central publication, omit useMavenLocal to verify the remote artifact.
/path/to/gradle-7.4/bin/gradle -p compatibility/gradle74 \
  clean verifyTemplates assembleRelease --refresh-dependencies
```

Override `-PjunkCodeVersion=<version>` when testing a new release. Success requires an APK under `build/outputs/apk/release/`; `help` alone does not verify generation or resource merging.
