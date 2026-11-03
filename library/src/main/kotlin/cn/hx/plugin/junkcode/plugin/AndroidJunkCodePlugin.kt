// Downstream modification, 2026-11-03: use the Gradle Java API for older consumers.
package cn.hx.plugin.junkcode.plugin

import cn.hx.plugin.junkcode.ext.AndroidJunkCodeExt
import cn.hx.plugin.junkcode.ext.JunkCodeConfig
import cn.hx.plugin.junkcode.task.GenerateJunkCodeTask
import cn.hx.plugin.junkcode.task.ManifestMergeTask
import cn.hx.plugin.junkcode.utils.capitalizeCompat
import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.AppExtension
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidJunkCodePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val androidJunkCodeExt = extensions.create(
                "androidJunkCode",
                AndroidJunkCodeExt::class.java,
                objects.domainObjectContainer(JunkCodeConfig::class.java)
            )
            val androidComponents =
                extensions.getByType(ApplicationAndroidComponentsExtension::class.java)
            val legacyResources = androidComponents.pluginVersion.major == 7 &&
                androidComponents.pluginVersion.minor <= 3
            androidComponents.onVariants { variant ->
                val variantName = variant.name
                val junkCodeConfig =
                    androidJunkCodeExt.variantConfig.findByName(variantName) ?: return@onVariants
                if (androidJunkCodeExt.debug) {
                    println("AndroidJunkCode: generate code for variant $variantName")
                }
                //生成垃圾代码目录
                val junkCodeOutDir =
                    layout.buildDirectory.dir("generated/source/junk/${variantName}")
                val generateJunkCodeTaskProvider =
                    tasks.register("generate${variantName.capitalizeCompat()}JunkCode", GenerateJunkCodeTask::class.java) { task ->
                        task.config.set(junkCodeConfig)
                        task.namespace.set(variant.namespace)
                        task.outputFolder.set(junkCodeOutDir)
                    }
                //java文件
                variant.sources.java?.addGeneratedSourceDirectory(generateJunkCodeTaskProvider) {
                    it.javaOutputDirectory
                }
                //资源文件
                if (!legacyResources) {
                    variant.sources.res?.addGeneratedSourceDirectory(generateJunkCodeTaskProvider) {
                        it.resourceOutputDirectory
                    }
                }
                // Keep the public output layout after AGP assigns its default source directories.
                generateJunkCodeTaskProvider.configure { task ->
                    task.javaOutputDirectory.set(task.outputFolder.dir("java"))
                    task.resourceOutputDirectory.set(task.outputFolder.dir("res"))
                }
                //AndroidManifest.xml
                val manifestUpdater =
                    tasks.register("merge" + variantName.capitalizeCompat() + "JunkCodeManifest", ManifestMergeTask::class.java) { task ->
                        task.genManifestFile.set(generateJunkCodeTaskProvider.flatMap {
                            it.outputFolder.file(
                                "AndroidManifest.xml"
                            )
                        })
                    }
                variant.artifacts.use(manifestUpdater)
                    .wiredWithFiles(
                        { it.mergedManifest },
                        { it.updatedManifest })
                    .toTransform(SingleArtifact.MERGED_MANIFEST)
                //混淆文件
                variant.proguardFiles.add(generateJunkCodeTaskProvider.flatMap {
                    it.outputFolder.file("proguard-rules.pro")
                })
            }
            if (legacyResources) {
                // AGP 7.3 filters user-added generated res from its resource merger.
                // Its legacy API still wires these folders through extraGeneratedResFolders.
                @Suppress("DEPRECATION")
                val android = extensions.getByType(AppExtension::class.java)
                android.applicationVariants.all(Action { variant ->
                    if (androidJunkCodeExt.variantConfig.findByName(variant.name) != null) {
                        val generator = tasks.named(
                            "generate${variant.name.capitalizeCompat()}JunkCode",
                            GenerateJunkCodeTask::class.java
                        )
                        variant.registerGeneratedResFolders(
                            files(generator.flatMap { it.resourceOutputDirectory }).builtBy(generator)
                        )
                    }
                })
            }
        }
    }
}