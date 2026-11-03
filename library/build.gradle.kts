// Downstream modification of qq549631030/AndroidJunkCode.
// 2026-10-30: added template tests; 2026-11-02: package license and attribution files.
// 2026-11-02: configure Maven Central publishing under the downstream namespace.
// 2026-11-03: compile consumer artifacts against Kotlin 1.6 / Gradle 7.4 / Java 11.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

plugins {
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
    publishToMavenCentral()
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

// Publishing runs on modern Gradle/JDK, but consumers may still use Gradle 7.4/JDK 11.
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin.api)
    compileOnly("com.android.tools.build:gradle:7.3.0") { isTransitive = false }
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.21")
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

// Isolate the legacy compiler from the Kotlin version embedded in the publishing build.
// Kotlin 1.6 metadata is readable by the Kotlin 1.5.31 compiler embedded in Gradle 7.4.
val compatibilityCompiler by configurations.creating
val compatibilityGradleApi by configurations.creating

dependencies {
    compatibilityCompiler("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.6.21")
    compatibilityGradleApi("dev.gradleplugins:gradle-api:7.4") { isTransitive = false }
}

val mainKotlinOutput = layout.buildDirectory.dir("classes/kotlin/main")
val testKotlinOutput = layout.buildDirectory.dir("classes/kotlin/test")

fun registerCompatibilityCompile(
    taskName: String,
    sourceDirectory: String,
    destination: Provider<Directory>,
    compilationClasspath: FileCollection,
    friendDirectory: Provider<Directory>? = null
) = tasks.register<JavaExec>(taskName) {
    val sources = fileTree(sourceDirectory) { include("**/*.kt") }
    inputs.files(sources).withPathSensitivity(PathSensitivity.RELATIVE)
    inputs.files(compilationClasspath).withPropertyName("compilationClasspath").withNormalizer(ClasspathNormalizer::class)
    inputs.files(compatibilityGradleApi).withNormalizer(ClasspathNormalizer::class)
    outputs.dir(destination)
    classpath = compatibilityCompiler
    mainClass.set("org.jetbrains.kotlin.cli.jvm.K2JVMCompiler")
    doFirst {
        delete(destination)
        destination.get().asFile.mkdirs()
        // gradleApi() includes the publisher's embedded stdlib; never compile against that.
        val compatibleClasspath = compilationClasspath.files.filter {
            it.name != "gradle-api-${gradle.gradleVersion}.jar" &&
                (!it.name.startsWith("kotlin-") || it.name.contains("1.6.21"))
        } + compatibilityGradleApi.files
        args = listOf(
            "-no-stdlib", "-no-reflect", "-jvm-target", "11",
            "-api-version", "1.6", "-language-version", "1.6",
            "-module-name", "android-junk-code",
            "-classpath", compatibleClasspath.joinToString(File.pathSeparator),
            "-d", destination.get().asFile.absolutePath
        ) + (friendDirectory?.let { listOf("-Xfriend-paths=" + it.get().asFile.absolutePath) } ?: emptyList()) +
            sources.files.sortedBy { it.path }.map { it.absolutePath }
    }
}

val compileKotlin = registerCompatibilityCompile(
    "compileKotlin", "src/main/kotlin", mainKotlinOutput, sourceSets.main.get().compileClasspath
)
sourceSets.main {
    output.dir(mapOf("builtBy" to compileKotlin), mainKotlinOutput)
}
tasks.named("classes") { dependsOn(compileKotlin) }

val compileTestKotlin = registerCompatibilityCompile(
    "compileTestKotlin", "src/test/kotlin", testKotlinOutput,
    sourceSets.test.get().compileClasspath, mainKotlinOutput
)
compileTestKotlin.configure { dependsOn(compileKotlin) }
sourceSets.test {
    output.dir(mapOf("builtBy" to compileTestKotlin), testKotlinOutput)
}
tasks.named<Test>("test") {
    dependsOn(compileTestKotlin)
    testClassesDirs = files(testKotlinOutput, sourceSets.test.get().output.classesDirs)
}
tasks.withType<Jar>().matching { it.name == "sourcesJar" }.configureEach {
    from("src/main/kotlin")
}

tasks.named<ValidatePlugins>("validatePlugins") {
    classes.from(mainKotlinOutput)
    dependsOn(compileKotlin)
}
