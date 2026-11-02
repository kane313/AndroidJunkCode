// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template.method

import cn.hx.plugin.junkcode.template.MethodTemplate

internal fun numericTemplates(): List<MethodDefinition> = listOf(
    MethodDefinition(MethodTemplate.INT_SUM, ReturnKind.INT, """
        int sum = 0;
        for (int i = 1; i <= 12; i++) sum += i;
        return sum;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_PRODUCT, ReturnKind.INT, """
        int product = 1;
        for (int i = 1; i <= 7; i++) product *= i;
        return product;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_GCD, ReturnKind.INT, """
        int a = 84, b = 30;
        while (b != 0) {
            int next = a % b;
            a = b;
            b = next;
        }
        return a;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_LCM, ReturnKind.INT, """
        int a = 12, b = 18, x = a, y = b;
        while (y != 0) {
            int t = x % y;
            x = y;
            y = t;
        }
        return a / x * b;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_FIBONACCI, ReturnKind.INT, """
        int a = 0, b = 1;
        for (int i = 0; i < 12; i++) {
            int next = a + b;
            a = b;
            b = next;
        }
        return a;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_PRIME_CHECK, ReturnKind.BOOLEAN, """
        int value = 97;
        for (int i = 2; i <= value / i; i++) {
            if (value % i == 0) return false;
        }
        return value >= 2;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_PRIME_COUNT, ReturnKind.INT, """
        int count = 0;
        for (int n = 2; n <= 40; n++) {
            boolean prime = true;
            for (int d = 2; d * d <= n; d++) {
                if (n % d == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) count++;
        }
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_DIGIT_SUM, ReturnKind.INT, """
        int value = 4827, sum = 0;
        do {
            sum += value % 10;
            value /= 10;
        }
        while (value != 0);
        return sum;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_DIGIT_REVERSE, ReturnKind.INT, """
        int value = 4827, reversed = 0;
        while (value > 0) {
            reversed = reversed * 10 + value % 10;
            value /= 10;
        }
        return reversed;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_DIGIT_COUNT, ReturnKind.INT, """
        int value = 4827, count = 0;
        do {
            count++;
            value /= 10;
        }
        while (value != 0);
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_POWER, ReturnKind.INT, """
        int base = 3, exponent = 7, result = 1;
        while (exponent > 0) {
            if ((exponent & 1) != 0) result *= base;
            base *= base;
            exponent >>= 1;
        }
        return result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_SQRT, ReturnKind.INT, """
        int value = 123, low = 0, high = value, answer = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((long) mid * mid <= value) {
                answer = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return answer;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_DIVISOR_COUNT, ReturnKind.INT, """
        int value = 36, count = 0;
        for (int d = 1; d * d <= value; d++) {
            if (value % d == 0) count += d * d == value ? 1 : 2;
        }
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_PERFECT_CHECK, ReturnKind.BOOLEAN, """
        int value = 28, sum = 0;
        for (int d = 1; d <= value / 2; d++) {
            if (value % d == 0) sum += d;
        }
        return sum == value;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_TRIANGULAR, ReturnKind.INT, """
        int n = 18;
        return n * (n + 1) / 2;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_BINOMIAL, ReturnKind.LONG, """
        int n = 10, k = 4;
        long result = 1;
        for (int i = 1; i <= k; i++) result = result * (n - i + 1) / i;
        return result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_COLLATZ_STEPS, ReturnKind.INT, """
        int value = 27, steps = 0;
        while (value != 1 && steps < 200) {
            value = value % 2 == 0 ? value / 2 : 3 * value + 1;
            steps++;
        }
        return steps;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_DIGITAL_ROOT, ReturnKind.INT, """
        int value = 98765;
        return value == 0 ? 0 : 1 + (value - 1) % 9;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_ARMSTRONG_CHECK, ReturnKind.BOOLEAN, """
        int value = 153, rest = value, sum = 0;
        while (rest > 0) {
            int digit = rest % 10;
            sum += digit * digit * digit;
            rest /= 10;
        }
        return sum == value;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.DOUBLE_HARMONIC_SUM, ReturnKind.DOUBLE, """
        double sum = 0;
        for (int i = 1; i <= 16; i++) sum += 1.0 / i;
        return sum;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.DOUBLE_NEWTON_SQRT, ReturnKind.DOUBLE, """
        double value = 17, estimate = value;
        for (int i = 0; i < 8; i++) estimate = (estimate + value / estimate) / 2;
        return estimate;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.DOUBLE_POLYNOMIAL, ReturnKind.DOUBLE, """
        double[] coefficients = {2, -3, 5, 7};
        double x = 2, result = 0;
        for (double coefficient : coefficients) result = result * x + coefficient;
        return result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.DOUBLE_WEIGHTED_MEAN, ReturnKind.DOUBLE, """
        double[] values = {3, 8, 12}, weights = {1, 2, 3};
        double total = 0, weight = 0;
        for (int i = 0; i < values.length; i++) {
            total += values[i] * weights[i];
            weight += weights[i];
        }
        return total / weight;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.DOUBLE_COMPOUND_INTEREST, ReturnKind.DOUBLE, """
        double balance = 100;
        for (int year = 0; year < 5; year++) balance *= 1.03;
        return balance;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.INT_BASE_CONVERSION, ReturnKind.STRING, """
        int value = 173, base = 7;
        StringBuilder result = new StringBuilder();
        do {
            result.append(value % base);
            value /= base;
        }
        while (value > 0);
        return result.reverse().toString();
    """.trimIndent()),
)
