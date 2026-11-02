// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template

import cn.hx.plugin.junkcode.ext.JunkCodeConfig
import cn.hx.plugin.junkcode.task.GenerateJunkCodeTask
import cn.hx.plugin.junkcode.utils.JunkUtil
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import org.gradle.api.Action
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.lang.reflect.InvocationTargetException
import java.net.URLClassLoader
import javax.lang.model.element.Modifier
import javax.tools.DiagnosticCollector
import javax.tools.JavaFileObject
import javax.tools.ToolProvider
import kotlin.random.Random

class MethodTemplateTest {
    @get:Rule
    val temporaryFolder = TemporaryFolder()

    @Test(timeout = 60000)
    fun all200TemplatesCompileAndRun() {
        val templates = MethodTemplate.values()
        assertEquals(200, templates.size)
        val type = TypeSpec.classBuilder("AllTemplates").addModifiers(Modifier.PUBLIC)
        val bodies = mutableSetOf<String>()
        for (template in templates) {
            val builder = MethodSpec.methodBuilder(template.name)
            MethodTemplateGenerator.generate(builder, listOf(template))
            val method = builder.build()
            assertTrue("Duplicate method body: $template", bodies.add(method.code.toString()))
            type.addMethod(method)
        }
        val sourceDir = temporaryFolder.newFolder("all-sources")
        JavaFile.builder("generated", type.build()).build().writeTo(sourceDir)
        val classes = compile(sourceDir)
        URLClassLoader(arrayOf(classes.toURI().toURL()), javaClass.classLoader).use { loader ->
            val generated = loader.loadClass("generated.AllTemplates")
            val instance = generated.getDeclaredConstructor().newInstance()
            val results = mutableMapOf<MethodTemplate, Any?>()
            for (template in templates) {
                val method = generated.declaredMethods.single { it.name == template.name }
                method.isAccessible = true
                if (template == MethodTemplate.LEGACY_THROW_EXCEPTION) {
                    try {
                        method.invoke(instance)
                        fail("Legacy throwing template must retain its behavior")
                    } catch (exception: InvocationTargetException) {
                        assertTrue(exception.cause is RuntimeException)
                        assertEquals("Failed", exception.cause?.cause?.message)
                    }
                } else {
                    val arguments = if (template == MethodTemplate.LEGACY_PRINT) arrayOf<Any>("sample") else emptyArray()
                    val result = method.invoke(instance, *arguments)
                    if (method.returnType != Void.TYPE) assertNotNull(template.name, result)
                    results[template] = result
                }
            }
            // Independent expected results cover the algorithm families, not just compilation.
            assertEquals(78, results[MethodTemplate.INT_SUM])
            assertEquals(6, results[MethodTemplate.INT_GCD])
            assertEquals(144, results[MethodTemplate.INT_FIBONACCI])
            assertEquals(210L, results[MethodTemplate.INT_BINOMIAL])
            assertEquals(128, results[MethodTemplate.BIT_NEXT_POWER_OF_TWO])
            assertEquals(23, results[MethodTemplate.BIT_GRAY_DECODE])
            assertEquals("erutcurts", results[MethodTemplate.STRING_REVERSE])
            assertEquals("a3b2c4d1", results[MethodTemplate.STRING_RUN_LENGTH_ENCODE])
            assertEquals(1, results[MethodTemplate.STRING_EDIT_DISTANCE])
            assertArrayEquals(intArrayOf(24, 12, 8, 6), results[MethodTemplate.ARRAY_PRODUCT_EXCEPT_SELF] as IntArray)
            assertEquals(6, results[MethodTemplate.ARRAY_MAX_SUBARRAY])
            assertEquals(14, results[MethodTemplate.LIST_STACK_EVALUATE])
            assertEquals(4, results[MethodTemplate.LIST_JOSEPHUS])
            assertEquals("[3, 5, 5]", results[MethodTemplate.LIST_SLIDING_MAX])
            assertEquals("{blue=2, green=1, red=3}", results[MethodTemplate.MAP_WORD_FREQUENCY])
            assertEquals("[3, 1, 4]", results[MethodTemplate.MAP_LRU_ORDER])
            assertEquals(4, results[MethodTemplate.SET_LONGEST_CONSECUTIVE])
            assertEquals(true, results[MethodTemplate.SET_HAPPY_NUMBER])
            assertEquals(true, results[MethodTemplate.CONTROL_CHECKSUM_VALIDATE])
            assertEquals(2, results[MethodTemplate.CONTROL_COIN_CHANGE])
            assertEquals("MCMLXXXIV", results[MethodTemplate.CONTROL_ROMAN_NUMERAL])
            assertEquals(35, results[MethodTemplate.MATRIX_GRID_PATHS])
            assertEquals(7, results[MethodTemplate.MATRIX_MIN_PATH_COST])
            assertEquals("123698745", results[MethodTemplate.MATRIX_SPIRAL])
        }
    }

    @Test
    fun unsetAndEmptyPoolsSelectFromAllTemplates() {
        val config = JunkCodeConfig("debug")
        assertTrue(config.methodTemplates.isEmpty())
        val random = Random(12345)
        val selected = (0 until 10000).map {
            MethodTemplateGenerator.select(config.methodTemplates, random)
        }.toSet()
        assertEquals(MethodTemplate.values().toSet(), selected)
    }

    @Test
    fun configuredPoolNeverSelectsOtherTemplates() {
        val random = Random(42)
        val pool = listOf(MethodTemplate.STRING_REVERSE, MethodTemplate.ARRAY_SUM)
        val selected = (0 until 100).map { MethodTemplateGenerator.select(pool, random) }.toSet()
        assertEquals(pool.toSet(), selected)
        repeat(20) {
            assertEquals(MethodTemplate.STRING_REVERSE, MethodTemplateGenerator.select(pool.take(1), random))
        }
    }

    @Test
    fun selectionReachesOrdinaryClassesAndActivities() {
        val sourceDir = temporaryFolder.newFolder("integration-sources")
        val resources = temporaryFolder.newFolder("integration-resources")
        val config = config().apply { methodTemplates = listOf(MethodTemplate.STRING_REVERSE) }
        JunkUtil.generateJava(sourceDir, "generated", config)
        JunkUtil.generateActivity(sourceDir, resources, "generated", "generated", config)
        for (file in sourceDir.walkTopDown().filter { it.extension == "java" }) {
            assertTrue(file.readText(), file.readText().contains("String text = \"structure\""))
        }
        writeAndroidStubs(sourceDir, resources)
        compile(sourceDir)
    }

    @Test
    fun mixedAndDefaultPoolsCompileThroughBothGenerationPaths() {
        val sourceDir = temporaryFolder.newFolder("mixed-sources")
        val resources = temporaryFolder.newFolder("mixed-resources")
        for ((index, pool) in listOf(emptyList(), MethodTemplate.values().toList()).withIndex()) {
            val config = config().apply {
                methodTemplates = pool
                methodCountPerClass = 60
            }
            JunkUtil.generateJava(sourceDir, "generated.p$index", config)
            JunkUtil.generateActivity(sourceDir, resources, "generated", "generated.p$index", config)
        }
        writeAndroidStubs(sourceDir, resources)
        compile(sourceDir)
    }

    @Test
    fun customMethodGeneratorOverridesTemplatesInBothPaths() {
        val sourceDir = temporaryFolder.newFolder("callback-sources")
        val resources = temporaryFolder.newFolder("callback-resources")
        val config = config().apply {
            methodTemplates = listOf(MethodTemplate.LEGACY_THROW_EXCEPTION)
            methodGenerator = Action { it.returns(String::class.java).addStatement("return \$S", "callback") }
        }
        JunkUtil.generateJava(sourceDir, "generated", config)
        JunkUtil.generateActivity(sourceDir, resources, "generated", "generated", config)
        for (file in sourceDir.walkTopDown().filter { it.extension == "java" }) {
            val source = file.readText()
            assertTrue(source, source.contains("return \"callback\""))
            assertFalse(source, source.contains("throw new"))
        }
        writeAndroidStubs(sourceDir, resources)
        compile(sourceDir)
    }

    @Test
    fun customTypeGeneratorOverridesMethodGeneratorAndTemplates() {
        val sourceDir = temporaryFolder.newFolder("type-sources")
        val resources = temporaryFolder.newFolder("type-resources")
        val config = config().apply {
            methodTemplates = listOf(MethodTemplate.LEGACY_THROW_EXCEPTION)
            methodGenerator = Action { fail("typeGenerator must take precedence") }
            typeGenerator = Action {
                it.addMethod(MethodSpec.methodBuilder("customTypeMethod")
                    .returns(Int::class.javaPrimitiveType!!).addStatement("return 42").build())
            }
        }
        JunkUtil.generateJava(sourceDir, "generated", config)
        JunkUtil.generateActivity(sourceDir, resources, "generated", "generated", config)
        for (file in sourceDir.walkTopDown().filter { it.extension == "java" }) {
            assertTrue(file.readText().contains("customTypeMethod"))
            assertFalse(file.readText().contains("throw new"))
        }
        writeAndroidStubs(sourceDir, resources)
        compile(sourceDir)
    }

    @Test
    fun changingTemplatesRemovesPreviouslyGeneratedSources() {
        val project = ProjectBuilder.builder().withProjectDir(temporaryFolder.newFolder("project")).build()
        val task = project.tasks.register("generateTestJunk", GenerateJunkCodeTask::class.java).get()
        val output = temporaryFolder.newFolder("task-output")
        val config = config().apply {
            packageCount = 1
            packageBase = "generated"
            methodTemplates = listOf(MethodTemplate.STRING_REVERSE)
        }
        task.config.set(config)
        task.namespace.set("generated")
        task.outputFolder.set(output)
        task.taskAction()
        assertEquals(2, output.walkTopDown().count { it.extension == "java" })

        config.methodTemplates = listOf(MethodTemplate.ARRAY_SUM)
        task.taskAction()
        val files = output.walkTopDown().filter { it.extension == "java" }.toList()
        assertEquals("Changing the selection must not retain old classes", 2, files.size)
        files.forEach {
            assertFalse(it.readText().contains("String text = \"structure\""))
            assertTrue(it.readText().contains("int[] values = {3, 1, 4, 1, 5}"))
        }
    }

    private fun config() = JunkCodeConfig("debug").apply {
        otherCountPerPackage = 1
        activityCountPerPackage = 1
        methodCountPerClass = 1
    }

    private fun writeAndroidStubs(sourceDir: File, resources: File) {
        File(sourceDir, "android/app/Activity.java").apply {
            parentFile.mkdirs()
            writeText("package android.app; public class Activity { protected void onCreate(android.os.Bundle state) {} public void setContentView(int id) {} }")
        }
        File(sourceDir, "android/os/Bundle.java").apply {
            parentFile.mkdirs()
            writeText("package android.os; public class Bundle {}")
        }
        val fields = File(resources, "layout").listFiles().orEmpty().joinToString("\n") {
            "public static final int ${it.nameWithoutExtension} = 1;"
        }
        File(sourceDir, "generated/R.java").apply {
            parentFile.mkdirs()
            writeText("package generated; public class R { public static class layout { $fields } }")
        }
    }

    private fun compile(sourceDir: File): File {
        val compiler = ToolProvider.getSystemJavaCompiler()
        assertNotNull("Tests require a JDK, not a JRE", compiler)
        val diagnostics = DiagnosticCollector<JavaFileObject>()
        val output = temporaryFolder.newFolder()
        compiler.getStandardFileManager(diagnostics, null, null).use { manager ->
            val sources = sourceDir.walkTopDown().filter { it.extension == "java" }.toList()
            val units = manager.getJavaFileObjectsFromFiles(sources)
            val success = compiler.getTask(null, manager, diagnostics,
                listOf("--release", "8", "-d", output.absolutePath), null, units).call()
            assertTrue(diagnostics.diagnostics.joinToString("\n"), success)
        }
        return output
    }
}
