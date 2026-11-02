// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template.method

import cn.hx.plugin.junkcode.template.MethodTemplate

internal fun mapsTemplates(): List<MethodDefinition> = listOf(
    MethodDefinition(MethodTemplate.MAP_WORD_FREQUENCY, ReturnKind.STRING, """
        java.util.Map<String, Integer> counts = new java.util.TreeMap<String, Integer>();
        for (String word : "red blue red green blue red".split(" ")) {
            Integer old = counts.get(word);
            counts.put(word, old == null ? 1 : old + 1);
        }
        return counts.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_GROUP_BY_LENGTH, ReturnKind.STRING, """
        java.util.Map<Integer, java.util.List<String>> groups = new java.util.TreeMap<Integer, java.util.List<String>>();
        for (String word : new String[]{"a", "bb", "c", "ddd"}) {
            java.util.List<String> group = groups.get(word.length());
            if (group == null) {
                group = new java.util.ArrayList<String>();
                groups.put(word.length(), group);
            }
            group.add(word);
        }
        return groups.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_INVERT, ReturnKind.STRING, """
        java.util.Map<String, Integer> source = new java.util.LinkedHashMap<String, Integer>();
        source.put("a", 1);
        source.put("b", 2);
        java.util.Map<Integer, String> out = new java.util.TreeMap<Integer, String>();
        for (java.util.Map.Entry<String, Integer> entry : source.entrySet()) out.put(entry.getValue(), entry.getKey());
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_MERGE_COUNTS, ReturnKind.STRING, """
        java.util.Map<String, Integer> a = new java.util.TreeMap<String, Integer>(), b = new java.util.TreeMap<String, Integer>();
        a.put("x", 2);
        a.put("y", 1);
        b.put("x", 3);
        b.put("z", 4);
        for (java.util.Map.Entry<String, Integer> entry : b.entrySet()) {
            Integer old = a.get(entry.getKey());
            a.put(entry.getKey(), (old == null ? 0 : old) + entry.getValue());
        }
        return a.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_FILTER_VALUES, ReturnKind.STRING, """
        java.util.Map<String, Integer> values = new java.util.TreeMap<String, Integer>();
        values.put("a", 1);
        values.put("b", 4);
        values.put("c", 2);
        java.util.Iterator<java.util.Map.Entry<String, Integer>> iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue() < 3) iterator.remove();
        }
        return values.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_MAX_ENTRY, ReturnKind.STRING, """
        java.util.Map<String, Integer> values = new java.util.TreeMap<String, Integer>();
        values.put("a", 7);
        values.put("b", 9);
        values.put("c", 3);
        String best = "";
        int maximum = Integer.MIN_VALUE;
        for (java.util.Map.Entry<String, Integer> entry : values.entrySet()) {
            if (entry.getValue() > maximum) {
                maximum = entry.getValue();
                best = entry.getKey();
            }
        }
        return best;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_INDEX_WORDS, ReturnKind.STRING, """
        java.util.Map<String, java.util.List<Integer>> index = new java.util.TreeMap<String, java.util.List<Integer>>();
        String[] words = {"a", "b", "a", "c"};
        for (int i = 0; i < words.length; i++) {
            java.util.List<Integer> positions = index.get(words[i]);
            if (positions == null) {
                positions = new java.util.ArrayList<Integer>();
                index.put(words[i], positions);
            }
            positions.add(i);
        }
        return index.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_FIRST_SEEN_INDEX, ReturnKind.STRING, """
        java.util.Map<String, Integer> first = new java.util.LinkedHashMap<String, Integer>();
        String[] values = {"b", "a", "b", "c"};
        for (int i = 0; i < values.length; i++) {
            if (!first.containsKey(values[i])) first.put(values[i], i);
        }
        return first.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_LAST_SEEN_INDEX, ReturnKind.STRING, """
        java.util.Map<String, Integer> last = new java.util.TreeMap<String, Integer>();
        String[] values = {"b", "a", "b", "c"};
        for (int i = 0; i < values.length; i++) last.put(values[i], i);
        return last.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_TWO_SUM_INDICES, ReturnKind.INT_ARRAY, """
        int[] values = {2, 7, 11, 15};
        java.util.Map<Integer, Integer> seen = new java.util.HashMap<Integer, Integer>();
        for (int i = 0; i < values.length; i++) {
            Integer prior = seen.get(9 - values[i]);
            if (prior != null) return new int[]{prior, i};
            seen.put(values[i], i);
        }
        return new int[0];
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_SUBARRAY_SUM_COUNT, ReturnKind.INT, """
        int[] values = {1, -1, 2, 1, -2};
        java.util.Map<Integer, Integer> counts = new java.util.HashMap<Integer, Integer>();
        counts.put(0, 1);
        int prefix = 0, total = 0, target = 1;
        for (int value : values) {
            prefix += value;
            Integer matches = counts.get(prefix - target);
            total += matches == null ? 0 : matches;
            Integer old = counts.get(prefix);
            counts.put(prefix, old == null ? 1 : old + 1);
        }
        return total;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_ISOMORPHIC_STRINGS, ReturnKind.BOOLEAN, """
        String a = "paper", b = "title";
        java.util.Map<Character, Character> forward = new java.util.HashMap<Character, Character>(), reverse = new java.util.HashMap<Character, Character>();
        for (int i = 0; i < a.length(); i++) {
            char x = a.charAt(i), y = b.charAt(i);
            if (forward.containsKey(x) && forward.get(x) != y) return false;
            if (reverse.containsKey(y) && reverse.get(y) != x) return false;
            forward.put(x, y);
            reverse.put(y, x);
        }
        return true;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_ANAGRAM_GROUPS, ReturnKind.STRING, """
        java.util.Map<String, java.util.List<String>> groups = new java.util.TreeMap<String, java.util.List<String>>();
        for (String word : new String[]{"eat", "tea", "tan", "ate"}) {
            char[] chars = word.toCharArray();
            java.util.Arrays.sort(chars);
            String key = new String(chars);
            java.util.List<String> group = groups.get(key);
            if (group == null) {
                group = new java.util.ArrayList<String>();
                groups.put(key, group);
            }
            group.add(word);
        }
        return groups.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_SPARSE_DOT_PRODUCT, ReturnKind.INT, """
        java.util.Map<Integer, Integer> a = new java.util.HashMap<Integer, Integer>(), b = new java.util.HashMap<Integer, Integer>();
        a.put(1, 3);
        a.put(4, 2);
        b.put(1, 5);
        b.put(3, 7);
        int sum = 0;
        for (java.util.Map.Entry<Integer, Integer> entry : a.entrySet()) {
            Integer value = b.get(entry.getKey());
            if (value != null) sum += entry.getValue() * value;
        }
        return sum;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_LRU_ORDER, ReturnKind.STRING, """
        java.util.LinkedHashMap<Integer, String> cache = new java.util.LinkedHashMap<Integer, String>(4, 0.75f, true);
        for (int key : new int[]{1, 2, 3, 1, 4}) {
            cache.put(key, "v" + key);
            if (cache.size() > 3) {
                java.util.Iterator<Integer> iterator = cache.keySet().iterator();
                iterator.next();
                iterator.remove();
            }
        }
        return cache.keySet().toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_BUCKET_HISTOGRAM, ReturnKind.STRING, """
        java.util.Map<Integer, Integer> buckets = new java.util.TreeMap<Integer, Integer>();
        for (int value : new int[]{3, 12, 18, 25, 29}) {
            int bucket = value / 10;
            Integer old = buckets.get(bucket);
            buckets.put(bucket, old == null ? 1 : old + 1);
        }
        return buckets.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_FLATTEN_ENTRIES, ReturnKind.STRING, """
        java.util.Map<String, Integer> values = new java.util.TreeMap<String, Integer>();
        values.put("a", 2);
        values.put("b", 3);
        StringBuilder out = new StringBuilder();
        for (java.util.Map.Entry<String, Integer> entry : values.entrySet()) {
            if (out.length() > 0) out.append(';');
            out.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_PARSE_PAIRS, ReturnKind.STRING, """
        java.util.Map<String, String> values = new java.util.TreeMap<String, String>();
        for (String pair : "a=1;b=two;c=3".split(";")) {
            int separator = pair.indexOf('=');
            if (separator >= 0) values.put(pair.substring(0, separator), pair.substring(separator + 1));
        }
        return values.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_FLOOR_LOOKUP, ReturnKind.STRING, """
        java.util.TreeMap<Integer, String> bands = new java.util.TreeMap<Integer, String>();
        bands.put(0, "low");
        bands.put(10, "medium");
        bands.put(20, "high");
        java.util.Map.Entry<Integer, String> match = bands.floorEntry(17);
        return match == null ? "" : match.getValue();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MAP_RANGE_KEYS, ReturnKind.STRING, """
        java.util.TreeMap<Integer, String> values = new java.util.TreeMap<Integer, String>();
        for (int i = 0; i < 8; i++) values.put(i, "v" + i);
        return values.subMap(2, true, 6, false).keySet().toString();
    """.trimIndent()),
)
