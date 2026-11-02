// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template.method

import cn.hx.plugin.junkcode.template.MethodTemplate

internal fun stringsTemplates(): List<MethodDefinition> = listOf(
    MethodDefinition(MethodTemplate.STRING_REVERSE, ReturnKind.STRING, """
        String text = "structure";
        StringBuilder out = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) out.append(text.charAt(i));
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_PALINDROME, ReturnKind.BOOLEAN, """
        String text = "level";
        for (int left = 0, right = text.length() - 1; left < right; left++, right--) {
            if (text.charAt(left) != text.charAt(right)) return false;
        }
        return true;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_VOWEL_COUNT, ReturnKind.INT, """
        String text = "generation";
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if ("aeiou".indexOf(text.charAt(i)) >= 0) count++;
        }
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_WORD_COUNT, ReturnKind.INT, """
        String text = "one  two three";
        int count = 0;
        boolean inWord = false;
        for (int i = 0; i < text.length(); i++) {
            boolean space = Character.isWhitespace(text.charAt(i));
            if (!space && !inWord) count++;
            inWord = !space;
        }
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_RUN_LENGTH_ENCODE, ReturnKind.STRING, """
        String text = "aaabbccccd";
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < text.length();) {
            int end = i + 1;
            while (end < text.length() && text.charAt(end) == text.charAt(i)) end++;
            out.append(text.charAt(i)).append(end - i);
            i = end;
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_RUN_LENGTH_DECODE, ReturnKind.STRING, """
        String text = "a3b2c4";
        StringBuilder out = new StringBuilder();
        for (int i = 0; i + 1 < text.length(); i += 2) {
            int count = text.charAt(i + 1) - '0';
            for (int j = 0; j < count; j++) out.append(text.charAt(i));
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_REMOVE_ADJACENT_DUPLICATES, ReturnKind.STRING, """
        String text = "aaabbcca";
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (i == 0 || text.charAt(i) != text.charAt(i - 1)) out.append(text.charAt(i));
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_ROTATE, ReturnKind.STRING, """
        String text = "abcdef";
        int offset = 2;
        return text.substring(offset) + text.substring(0, offset);
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_ANAGRAM_CHECK, ReturnKind.BOOLEAN, """
        char[] first = "listen".toCharArray(), second = "silent".toCharArray();
        java.util.Arrays.sort(first);
        java.util.Arrays.sort(second);
        return java.util.Arrays.equals(first, second);
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_COMMON_PREFIX, ReturnKind.STRING, """
        String[] words = {"flower", "flow", "flight"};
        int length = words[0].length();
        for (int i = 1; i < words.length; i++) {
            length = Math.min(length, words[i].length());
            int j = 0;
            while (j < length && words[0].charAt(j) == words[i].charAt(j)) j++;
            length = j;
        }
        return words[0].substring(0, length);
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_TITLE_CASE, ReturnKind.STRING, """
        String text = "hello small world";
        StringBuilder out = new StringBuilder();
        boolean start = true;
        for (char c : text.toCharArray()) {
            out.append(start ? Character.toUpperCase(c) : c);
            start = Character.isWhitespace(c);
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_CAMEL_TO_SNAKE, ReturnKind.STRING, """
        String text = "sampleValueName";
        StringBuilder out = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) out.append('_');
            out.append(Character.toLowerCase(c));
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_SNAKE_TO_CAMEL, ReturnKind.STRING, """
        String text = "sample_value_name";
        StringBuilder out = new StringBuilder();
        boolean upper = false;
        for (char c : text.toCharArray()) {
            if (c == '_') upper = true;
            else {
                out.append(upper ? Character.toUpperCase(c) : c);
                upper = false;
            }
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_COLLAPSE_SPACES, ReturnKind.STRING, """
        String text = "a   b  c";
        StringBuilder out = new StringBuilder();
        boolean previousSpace = false;
        for (char c : text.toCharArray()) {
            boolean space = c == ' ';
            if (!space || !previousSpace) out.append(c);
            previousSpace = space;
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_COUNT_SUBSTRING, ReturnKind.INT, """
        String text = "abababa", pattern = "aba";
        int count = 0;
        for (int at = text.indexOf(pattern); at >= 0; at = text.indexOf(pattern, at + 1)) count++;
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_ESCAPE_XML, ReturnKind.STRING, """
        String text = "<a&b>";
        StringBuilder out = new StringBuilder();
        for (char c : text.toCharArray()) {
            switch (c) {
                case '&': out.append("&amp;");
                break;
                case '<': out.append("&lt;");
                break;
                case '>': out.append("&gt;");
                break;
                default: out.append(c);
            }
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_CAESAR_SHIFT, ReturnKind.STRING, """
        String text = "xyzabc";
        StringBuilder out = new StringBuilder();
        for (char c : text.toCharArray()) out.append((char) ('a' + (c - 'a' + 3) % 26));
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_HEX_ENCODE, ReturnKind.STRING, """
        String text = "hello", digits = "0123456789abcdef";
        StringBuilder out = new StringBuilder();
        for (char c : text.toCharArray()) {
            out.append(digits.charAt((c >>> 4) & 15));
            out.append(digits.charAt(c & 15));
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_HEX_DECODE, ReturnKind.STRING, """
        String hex = "68656c6c6f";
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            int high = Character.digit(hex.charAt(i), 16), low = Character.digit(hex.charAt(i + 1), 16);
            out.append((char) ((high << 4) | low));
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_BALANCED_PARENTHESES, ReturnKind.BOOLEAN, """
        String text = "(()())";
        int depth = 0;
        for (char c : text.toCharArray()) {
            if (c == '(') depth++;
            else if (c == ')' && --depth < 0) return false;
        }
        return depth == 0;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_FIRST_UNIQUE_CHAR, ReturnKind.INT, """
        String text = "swiss";
        int[] counts = new int[128];
        for (char c : text.toCharArray()) counts[c]++;
        for (int i = 0; i < text.length(); i++) {
            if (counts[text.charAt(i)] == 1) return i;
        }
        return -1;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_REVERSE_WORD_ORDER, ReturnKind.STRING, """
        String[] words = "one two three".split(" ");
        StringBuilder out = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (out.length() > 0) out.append(' ');
            out.append(words[i]);
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_PAD_LEFT, ReturnKind.STRING, """
        String text = "42";
        StringBuilder out = new StringBuilder();
        while (out.length() + text.length() < 6) out.append('0');
        return out.append(text).toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_REMOVE_DIGITS, ReturnKind.STRING, """
        String text = "a1b22c333";
        StringBuilder out = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c)) out.append(c);
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_ROLLING_HASH, ReturnKind.LONG, """
        String text = "template";
        long hash = 0;
        for (int i = 0; i < text.length(); i++) hash = (hash * 31 + text.charAt(i)) % 1000000007L;
        return hash;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_EDIT_DISTANCE, ReturnKind.INT, """
        String a = "cat", b = "cut";
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= a.length(); i++) for (int j = 1; j <= b.length(); j++) dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1));
        return dp[a.length()][b.length()];
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_SUBSEQUENCE_CHECK, ReturnKind.BOOLEAN, """
        String text = "abcdef", target = "ace";
        int matched = 0;
        for (int i = 0; i < text.length() && matched < target.length(); i++) {
            if (text.charAt(i) == target.charAt(matched)) matched++;
        }
        return matched == target.length();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_LONGEST_WORD, ReturnKind.STRING, """
        String[] words = "small medium longestword".split(" ");
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) longest = word;
        }
        return longest;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_INTERLEAVE, ReturnKind.STRING, """
        String a = "abc", b = "12345";
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
            if (i < a.length()) out.append(a.charAt(i));
            if (i < b.length()) out.append(b.charAt(i));
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.STRING_BIGRAM_COUNT, ReturnKind.INT, """
        String text = "banana";
        java.util.Set<String> pairs = new java.util.HashSet<String>();
        for (int i = 0; i + 1 < text.length(); i++) pairs.add(text.substring(i, i + 2));
        return pairs.size();
    """.trimIndent()),
)
