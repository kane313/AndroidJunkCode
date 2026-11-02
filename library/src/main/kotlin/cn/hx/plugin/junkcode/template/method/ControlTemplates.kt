// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template.method

import cn.hx.plugin.junkcode.template.MethodTemplate

internal fun controlTemplates(): List<MethodDefinition> = listOf(
    MethodDefinition(MethodTemplate.CONTROL_CLAMP, ReturnKind.INT, """
        int value = 27, minimum = 0, maximum = 20;
        if (value < minimum) return minimum;
        if (value > maximum) return maximum;
        return value;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_SIGN, ReturnKind.INT, """
        int value = -17;
        return value < 0 ? -1 : value > 0 ? 1 : 0;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_GRADE, ReturnKind.STRING, """
        int score = 83;
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        return "D";
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_LEAP_YEAR, ReturnKind.BOOLEAN, """
        int year = 2024;
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_DAYS_IN_MONTH, ReturnKind.INT, """
        int month = 4, year = 2024;
        switch (month) {
            case 2: return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) ? 29 : 28;
            case 4: case 6: case 9: case 11: return 30;
            default: return 31;
        }
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_TRAFFIC_LIGHT, ReturnKind.STRING, """
        int state = 0;
        StringBuilder out = new StringBuilder();
        for (int tick = 0; tick < 5; tick++) {
            switch (state) {
                case 0: out.append("red;");
                state = 1;
                break;
                case 1: out.append("green;");
                state = 2;
                break;
                default: out.append("yellow;");
                state = 0;
            }
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_VENDING_MACHINE, ReturnKind.INT, """
        int credit = 0, dispensed = 0;
        for (int coin : new int[]{1, 2, 1, 2, 2}) {
            credit += coin;
            while (credit >= 3) {
                credit -= 3;
                dispensed++;
            }
        }
        return dispensed;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_RUN_TRANSITIONS, ReturnKind.INT, """
        boolean[] states = {false, false, true, true, false, true};
        int transitions = 0;
        for (int i = 1; i < states.length; i++) {
            if (states[i] != states[i - 1]) transitions++;
        }
        return transitions;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_DEBOUNCE, ReturnKind.INT, """
        int[] samples = {0, 1, 1, 1, 0, 0, 0};
        int stable = samples[0], candidate = stable, streak = 0;
        for (int sample : samples) {
            if (sample == candidate) streak++;
            else {
                candidate = sample;
                streak = 1;
            }
            if (streak >= 3) stable = candidate;
        }
        return stable;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_TOKEN_CLASSIFY, ReturnKind.STRING, """
        String text = "a1 !";
        StringBuilder out = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) out.append('L');
            else if (Character.isDigit(c)) out.append('D');
            else if (Character.isWhitespace(c)) out.append('S');
            else out.append('P');
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_PARSE_INTEGER, ReturnKind.INT, """
        String text = "-2048";
        int index = 0, sign = 1, result = 0;
        if (text.charAt(0) == '-') {
            sign = -1;
            index++;
        }
        while (index < text.length()) {
            char c = text.charAt(index++);
            if (c < '0' || c > '9') break;
            result = result * 10 + c - '0';
        }
        return sign * result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_CHECKSUM_VALIDATE, ReturnKind.BOOLEAN, """
        String digits = "79927398713";
        int sum = 0;
        boolean doubled = false;
        for (int i = digits.length() - 1; i >= 0; i--) {
            int digit = digits.charAt(i) - '0';
            if (doubled) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
            doubled = !doubled;
        }
        return sum % 10 == 0;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_INTERVAL_OVERLAP, ReturnKind.INT, """
        int aStart = 2, aEnd = 8, bStart = 5, bEnd = 11;
        return Math.max(0, Math.min(aEnd, bEnd) - Math.max(aStart, bStart));
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_BINARY_FLAGS, ReturnKind.STRING, """
        int flags = 5;
        StringBuilder out = new StringBuilder();
        if ((flags & 1) != 0) out.append("read;");
        if ((flags & 2) != 0) out.append("write;");
        if ((flags & 4) != 0) out.append("execute;");
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_FIZZ_BUZZ, ReturnKind.STRING, """
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= 15; i++) {
            if (i % 15 == 0) out.append("FizzBuzz");
            else if (i % 3 == 0) out.append("Fizz");
            else if (i % 5 == 0) out.append("Buzz");
            else out.append(i);
            out.append(',');
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_COIN_CHANGE, ReturnKind.INT, """
        int[] coins = {1, 3, 4};
        int target = 6;
        int[] dp = new int[target + 1];
        java.util.Arrays.fill(dp, target + 1);
        dp[0] = 0;
        for (int amount = 1; amount <= target; amount++) for (int coin : coins) {
            if (coin <= amount) dp[amount] = Math.min(dp[amount], dp[amount - coin] + 1);
        }
        return dp[target];
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_KNAPSACK, ReturnKind.INT, """
        int[] weights = {2, 3, 4}, values = {3, 4, 5};
        int[] dp = new int[6];
        for (int i = 0; i < weights.length; i++) for (int capacity = dp.length - 1; capacity >= weights[i]; capacity--) dp[capacity] = Math.max(dp[capacity], dp[capacity - weights[i]] + values[i]);
        return dp[5];
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_STAIR_WAYS, ReturnKind.INT, """
        int[] ways = new int[9];
        ways[0] = 1;
        for (int step = 1; step < ways.length; step++) {
            ways[step] = ways[step - 1];
            if (step >= 2) ways[step] += ways[step - 2];
            if (step >= 3) ways[step] += ways[step - 3];
        }
        return ways[8];
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_INTERVAL_MERGE, ReturnKind.STRING, """
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {9, 12}};
        StringBuilder out = new StringBuilder();
        int start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) end = Math.max(end, intervals[i][1]);
            else {
                out.append(start).append('-').append(end).append(';');
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        return out.append(start).append('-').append(end).toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.CONTROL_ROMAN_NUMERAL, ReturnKind.STRING, """
        int value = 1984;
        int[] amounts = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < amounts.length; i++) {
            while (value >= amounts[i]) {
                value -= amounts[i];
                out.append(symbols[i]);
            }
        }
        return out.toString();
    """.trimIndent()),
)
