// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template

import cn.hx.plugin.junkcode.template.method.arraysTemplates
import cn.hx.plugin.junkcode.template.method.bitsTemplates
import cn.hx.plugin.junkcode.template.method.controlTemplates
import cn.hx.plugin.junkcode.template.method.listsTemplates
import cn.hx.plugin.junkcode.template.method.mapsTemplates
import cn.hx.plugin.junkcode.template.method.matricesTemplates
import cn.hx.plugin.junkcode.template.method.numericTemplates
import cn.hx.plugin.junkcode.template.method.setsTemplates
import cn.hx.plugin.junkcode.template.method.stringsTemplates
import com.squareup.javapoet.MethodSpec
import java.util.Date
import javax.lang.model.element.Modifier
import kotlin.random.Random

internal object MethodTemplateGenerator {
    private val allTemplates = MethodTemplate.values().toList()
    private val definitions = listOf(
        numericTemplates(), bitsTemplates(), stringsTemplates(), arraysTemplates(),
        listsTemplates(), mapsTemplates(), setsTemplates(), controlTemplates(), matricesTemplates()
    ).flatten().let { definitions ->
        require(definitions.map { it.template }.distinct().size == definitions.size) {
            "Duplicate method template definitions"
        }
        definitions.associateBy { it.template }
    }

    internal fun select(templates: List<MethodTemplate>, random: Random): MethodTemplate =
        (templates.ifEmpty { allTemplates }).random(random)

    fun generate(
        builder: MethodSpec.Builder,
        templates: List<MethodTemplate> = emptyList(),
        random: Random = Random.Default
    ) {
        val template = select(templates, random)
        val definition = definitions[template]
        if (definition != null) definition.applyTo(builder) else generateLegacy(template, builder)
    }

    /**
     * 保留原有五种模板的签名和行为。
     */
    private fun generateLegacy(template: MethodTemplate, methodBuilder: MethodSpec.Builder) {
        when (template) {
            MethodTemplate.LEGACY_TIME_BRANCH -> {
                methodBuilder.addStatement("long now = \$T.currentTimeMillis()", System::class.java)
                    .beginControlFlow("if (\$T.currentTimeMillis() < now)", System::class.java)
                    .addStatement(
                        "\$T.out.println(\$S)", System::class.java, "Time travelling, woo hoo!"
                    )
                    .nextControlFlow("else if (\$T.currentTimeMillis() == now)", System::class.java)
                    .addStatement("\$T.out.println(\$S)", System::class.java, "Time stood still!")
                    .nextControlFlow("else").addStatement(
                        "\$T.out.println(\$S)", System::class.java, "Ok, time still moving forward"
                    ).endControlFlow()
            }

            MethodTemplate.LEGACY_ACCUMULATE -> {
                methodBuilder.addCode("" + "int total = 0;\n" + "for (int i = 0; i < 10; i++) {\n" + "  total += i;\n" + "}\n")
            }

            MethodTemplate.LEGACY_THROW_EXCEPTION -> {
                methodBuilder.beginControlFlow("try")
                    .addStatement("throw new Exception(\$S)", "Failed")
                    .nextControlFlow("catch (\$T e)", Exception::class.java)
                    .addStatement("throw new \$T(e)", RuntimeException::class.java).endControlFlow()
            }

            MethodTemplate.LEGACY_CURRENT_DATE -> {
                methodBuilder.returns(Date::class.java)
                    .addStatement("return new \$T()", Date::class.java)
            }

            MethodTemplate.LEGACY_PRINT -> {
                methodBuilder.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(String::class.java, "args")
                    .addStatement("\$T.out.println(\$S)", System::class.java, "Hello")
            }
            else -> error("Not a legacy template: $template")
        }
    }
}
