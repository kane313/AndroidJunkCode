// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template.method

import cn.hx.plugin.junkcode.template.MethodTemplate

internal fun bitsTemplates(): List<MethodDefinition> = listOf(
    MethodDefinition(MethodTemplate.BIT_POPCOUNT, ReturnKind.INT, """
        int value = 0x5a3, count = 0;
        while (value != 0) {
            value &= value - 1;
            count++;
        }
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_PARITY, ReturnKind.BOOLEAN, """
        int value = 0x5a3;
        value ^= value >>> 16;
        value ^= value >>> 8;
        value ^= value >>> 4;
        value ^= value >>> 2;
        value ^= value >>> 1;
        return (value & 1) != 0;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_REVERSE, ReturnKind.INT, """
        int value = 0x53, result = 0;
        for (int i = 0; i < 8; i++) {
            result = (result << 1) | (value & 1);
            value >>>= 1;
        }
        return result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_ROTATE_LEFT, ReturnKind.INT, """
        int value = 0x12345678, distance = 5;
        return (value << distance) | (value >>> (32 - distance));
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_ROTATE_RIGHT, ReturnKind.INT, """
        int value = 0x12345678, distance = 5;
        return (value >>> distance) | (value << (32 - distance));
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_POWER_OF_TWO, ReturnKind.BOOLEAN, """
        int value = 64;
        return value > 0 && (value & (value - 1)) == 0;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_NEXT_POWER_OF_TWO, ReturnKind.INT, """
        int value = 73 - 1;
        value |= value >>> 1;
        value |= value >>> 2;
        value |= value >>> 4;
        value |= value >>> 8;
        value |= value >>> 16;
        return value + 1;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_GRAY_ENCODE, ReturnKind.INT, """
        int value = 23;
        return value ^ (value >>> 1);
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_GRAY_DECODE, ReturnKind.INT, """
        int gray = 28, value = gray;
        while ((gray >>>= 1) != 0) value ^= gray;
        return value;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_HAMMING_DISTANCE, ReturnKind.INT, """
        int difference = 0x55 ^ 0x37, count = 0;
        for (int i = 0; i < 32; i++) count += (difference >>> i) & 1;
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_PACK_BYTES, ReturnKind.INT, """
        int[] bytes = {18, 52, 86, 120};
        int result = 0;
        for (int value : bytes) result = (result << 8) | (value & 255);
        return result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_UNPACK_BYTES, ReturnKind.INT_ARRAY, """
        int value = 0x12345678;
        int[] result = new int[4];
        for (int i = 0; i < 4; i++) result[i] = (value >>> (8 * (3 - i))) & 255;
        return result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_INTERLEAVE, ReturnKind.INT, """
        int x = 13, y = 7, result = 0;
        for (int i = 0; i < 8; i++) {
            result |= ((x >>> i) & 1) << (2 * i);
            result |= ((y >>> i) & 1) << (2 * i + 1);
        }
        return result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_TRAILING_ZERO_COUNT, ReturnKind.INT, """
        int value = 96, count = 0;
        while (count < 32 && (value & 1) == 0) {
            count++;
            value >>>= 1;
        }
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.BIT_SUBMASK_SUM, ReturnKind.INT, """
        int mask = 21, sum = 0;
        for (int sub = mask; sub > 0; sub = (sub - 1) & mask) sum += sub;
        return sum;
    """.trimIndent()),
)
