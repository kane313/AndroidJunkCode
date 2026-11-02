// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template.method

import cn.hx.plugin.junkcode.template.MethodTemplate

internal fun setsTemplates(): List<MethodDefinition> = listOf(
    MethodDefinition(MethodTemplate.SET_UNION, ReturnKind.STRING, """
        java.util.Set<Integer> values = new java.util.TreeSet<Integer>(java.util.Arrays.asList(1, 3, 5));
        values.addAll(java.util.Arrays.asList(2, 3, 4));
        return values.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_INTERSECTION, ReturnKind.STRING, """
        java.util.Set<Integer> values = new java.util.TreeSet<Integer>(java.util.Arrays.asList(1, 3, 5));
        values.retainAll(java.util.Arrays.asList(2, 3, 5));
        return values.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_DIFFERENCE, ReturnKind.STRING, """
        java.util.Set<Integer> values = new java.util.TreeSet<Integer>(java.util.Arrays.asList(1, 3, 5));
        values.removeAll(java.util.Arrays.asList(2, 3));
        return values.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_SYMMETRIC_DIFFERENCE, ReturnKind.STRING, """
        java.util.Set<Integer> values = new java.util.TreeSet<Integer>(java.util.Arrays.asList(1, 3, 5));
        for (int value : new int[]{2, 3, 4}) {
            if (!values.add(value)) values.remove(value);
        }
        return values.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_SUBSET_CHECK, ReturnKind.BOOLEAN, """
        java.util.Set<Integer> values = new java.util.HashSet<Integer>(java.util.Arrays.asList(1, 2, 3, 4));
        return values.containsAll(java.util.Arrays.asList(2, 4));
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_DISJOINT_CHECK, ReturnKind.BOOLEAN, """
        java.util.Set<Integer> a = new java.util.HashSet<Integer>(java.util.Arrays.asList(1, 3, 5));
        for (int value : new int[]{2, 4, 6}) {
            if (a.contains(value)) return false;
        }
        return true;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_ORDERED_DEDUP, ReturnKind.STRING, """
        java.util.Set<String> unique = new java.util.LinkedHashSet<String>();
        for (String word : new String[]{"b", "a", "b", "c", "a"}) unique.add(word);
        return unique.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_DUPLICATE_VALUES, ReturnKind.STRING, """
        java.util.Set<Integer> seen = new java.util.HashSet<Integer>(), duplicates = new java.util.TreeSet<Integer>();
        for (int value : new int[]{2, 1, 2, 3, 1}) {
            if (!seen.add(value)) duplicates.add(value);
        }
        return duplicates.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_LONGEST_CONSECUTIVE, ReturnKind.INT, """
        java.util.Set<Integer> values = new java.util.HashSet<Integer>(java.util.Arrays.asList(100, 4, 200, 1, 3, 2));
        int best = 0;
        for (int value : values) {
            if (!values.contains(value - 1)) {
                int length = 1;
                while (values.contains(value + length)) length++;
                best = Math.max(best, length);
            }
        }
        return best;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_HAPPY_NUMBER, ReturnKind.BOOLEAN, """
        int value = 19;
        java.util.Set<Integer> seen = new java.util.HashSet<Integer>();
        while (value != 1 && seen.add(value)) {
            int sum = 0;
            while (value > 0) {
                int digit = value % 10;
                sum += digit * digit;
                value /= 10;
            }
            value = sum;
        }
        return value == 1;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_MISSING_RANGE, ReturnKind.STRING, """
        java.util.Set<Integer> present = new java.util.HashSet<Integer>(java.util.Arrays.asList(1, 3, 6));
        java.util.Set<Integer> missing = new java.util.TreeSet<Integer>();
        for (int i = 1; i <= 7; i++) {
            if (!present.contains(i)) missing.add(i);
        }
        return missing.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_DISTINCT_WINDOW, ReturnKind.INT, """
        String text = "abcabcbb";
        java.util.Set<Character> window = new java.util.HashSet<Character>();
        int left = 0, best = 0;
        for (int right = 0; right < text.length(); right++) {
            while (window.contains(text.charAt(right))) window.remove(text.charAt(left++));
            window.add(text.charAt(right));
            best = Math.max(best, right - left + 1);
        }
        return best;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_POWER_SET, ReturnKind.STRING, """
        int[] values = {1, 2, 3};
        java.util.List<java.util.Set<Integer>> subsets = new java.util.ArrayList<java.util.Set<Integer>>();
        for (int mask = 0; mask < (1 << values.length); mask++) {
            java.util.Set<Integer> subset = new java.util.TreeSet<Integer>();
            for (int i = 0; i < values.length; i++) {
                if ((mask & (1 << i)) != 0) subset.add(values[i]);
            }
            subsets.add(subset);
        }
        return subsets.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_JACCARD_SIMILARITY, ReturnKind.DOUBLE, """
        java.util.Set<Integer> a = new java.util.HashSet<Integer>(java.util.Arrays.asList(1, 2, 3)), b = new java.util.HashSet<Integer>(java.util.Arrays.asList(2, 3, 4));
        java.util.Set<Integer> union = new java.util.HashSet<Integer>(a);
        union.addAll(b);
        a.retainAll(b);
        return union.isEmpty() ? 1.0 : (double) a.size() / union.size();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.SET_SIEVE_PRIMES, ReturnKind.STRING, """
        java.util.Set<Integer> candidates = new java.util.TreeSet<Integer>();
        for (int i = 2; i <= 30; i++) candidates.add(i);
        for (int prime = 2; prime * prime <= 30; prime++) {
            if (candidates.contains(prime)) for (int multiple = prime * prime; multiple <= 30; multiple += prime) candidates.remove(multiple);
        }
        return candidates.toString();
    """.trimIndent()),
)
