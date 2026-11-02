// Downstream modification of qq549631030/AndroidJunkCode.
// 2026-10-30: added template tests; 2026-11-02: package license and attribution files.
// 2026-11-02: configure Maven Central publishing under the downstream namespace.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-gradle-plugin`
    alias(libs.plugins.mavemPublish)
}

gradlePlugin {
    website.set("https://github.com/kane313/AndroidJunkCode")
    vcsUrl.set("https://github.com/kane313/AndroidJunkCode.git")
    plugins {
        create("androidJunkCode") {
            id = "io.github.kane313.android-junk-code"
            implementationClass = "cn.hx.plugin.junkcode.plugin.AndroidJunkCodePlugin"
            displayName = "AndroidJunkCode (kane313 fork)"
            description = "Android junk code generator with 200 selectable method templates"
        }
    }
}

mavenPublishing {
    pom {
        developers {
            developer {
                id.set("qq549631030")
                name.set("huangx")
                url.set("https://github.com/qq549631030")
                roles.add("upstream author")
            }
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin.api)
    implementation(gradleKotlinDsl())
    implementation(libs.javapoet)
    testImplementation(libs.junit)
}

// Carry attribution with binary, sources and documentation JARs.
tasks.withType<org.gradle.jvm.tasks.Jar>().configureEach {
    from(rootProject.layout.projectDirectory) {
        include("LICENSE", "NOTICE", "MODIFICATIONS.md")
        into("META-INF")
    }
}
