// Downstream compatibility fixture, 2026-11-03.
import cn.hx.plugin.junkcode.template.MethodTemplate
plugins {
    id("com.android.application") version "7.3.0"
    id("io.github.kane313.android-junk-code")
}
android {
    namespace = "verification.compatibility"
    compileSdk = 33
    defaultConfig { minSdk = 23; targetSdk = 33 }
    buildTypes { getByName("release") { isMinifyEnabled = true; proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt")) } }
}
androidJunkCode {
    variantConfig {
        create("release") {
            packageBase = "verification.generated"
            packageCount = 1
            activityCountPerPackage = 2
            otherCountPerPackage = 5
            methodCountPerClass = 20
            resPrefix = "junk_"
            drawableCount = 3
            stringCount = 3
            methodTemplates = listOf(MethodTemplate.STRING_REVERSE, MethodTemplate.ARRAY_SUM)
        }
    }
}
tasks.register("verifyTemplates") { doLast { check(MethodTemplate.values().size == 200) } }
