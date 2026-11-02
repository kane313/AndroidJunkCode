// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template.method

import cn.hx.plugin.junkcode.template.MethodTemplate
import com.squareup.javapoet.MethodSpec

internal enum class ReturnKind(val javaType: Class<*>) {
    INT(Int::class.javaPrimitiveType!!),
    LONG(Long::class.javaPrimitiveType!!),
    DOUBLE(Double::class.javaPrimitiveType!!),
    BOOLEAN(Boolean::class.javaPrimitiveType!!),
    STRING(String::class.java),
    INT_ARRAY(IntArray::class.java),
    INT_MATRIX(Array<IntArray>::class.java)
}

internal data class MethodDefinition(
    val template: MethodTemplate,
    val returnKind: ReturnKind,
    val body: String
) {
    fun applyTo(builder: MethodSpec.Builder) {
        builder.returns(returnKind.javaType).addCode("\$L\n", body)
    }
}
