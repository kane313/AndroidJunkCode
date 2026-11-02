// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template.method

import cn.hx.plugin.junkcode.template.MethodTemplate

internal fun listsTemplates(): List<MethodDefinition> = listOf(
    MethodDefinition(MethodTemplate.LIST_FILTER_EVEN, ReturnKind.STRING, """
        java.util.List<Integer> values = java.util.Arrays.asList(1, 2, 3, 4, 5, 6);
        java.util.List<Integer> out = new java.util.ArrayList<Integer>();
        for (int value : values) {
            if (value % 2 == 0) out.add(value);
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_SQUARE_VALUES, ReturnKind.STRING, """
        java.util.List<Integer> out = new java.util.ArrayList<Integer>();
        for (int value : java.util.Arrays.asList(2, 3, 4)) out.add(value * value);
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_FLATTEN, ReturnKind.STRING, """
        java.util.List<java.util.List<Integer>> rows = java.util.Arrays.asList(java.util.Arrays.asList(1, 2), java.util.Arrays.asList(3), java.util.Arrays.asList(4, 5));
        java.util.List<Integer> out = new java.util.ArrayList<Integer>();
        for (java.util.List<Integer> row : rows) out.addAll(row);
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_ZIP_SUM, ReturnKind.STRING, """
        java.util.List<Integer> a = java.util.Arrays.asList(1, 2, 3), b = java.util.Arrays.asList(4, 5);
        java.util.List<Integer> out = new java.util.ArrayList<Integer>();
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) out.add(a.get(i) + b.get(i));
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_CHUNK, ReturnKind.STRING, """
        java.util.List<Integer> values = java.util.Arrays.asList(1, 2, 3, 4, 5);
        java.util.List<java.util.List<Integer>> chunks = new java.util.ArrayList<java.util.List<Integer>>();
        for (int i = 0; i < values.size(); i += 2) chunks.add(new java.util.ArrayList<Integer>(values.subList(i, Math.min(i + 2, values.size()))));
        return chunks.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_INTERLEAVE, ReturnKind.STRING, """
        java.util.List<Integer> a = java.util.Arrays.asList(1, 3, 5), b = java.util.Arrays.asList(2, 4);
        java.util.List<Integer> out = new java.util.ArrayList<Integer>();
        for (int i = 0; i < Math.max(a.size(), b.size()); i++) {
            if (i < a.size()) out.add(a.get(i));
            if (i < b.size()) out.add(b.get(i));
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_INSERT_SORTED, ReturnKind.STRING, """
        java.util.List<Integer> values = new java.util.ArrayList<Integer>(java.util.Arrays.asList(1, 3, 7));
        int value = 5, index = 0;
        while (index < values.size() && values.get(index) < value) index++;
        values.add(index, value);
        return values.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_REMOVE_NEGATIVE, ReturnKind.STRING, """
        java.util.List<Integer> values = new java.util.ArrayList<Integer>(java.util.Arrays.asList(-2, 3, -1, 4));
        java.util.Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() < 0) iterator.remove();
        }
        return values.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_PARTITION, ReturnKind.STRING, """
        java.util.List<Integer> low = new java.util.ArrayList<Integer>(), high = new java.util.ArrayList<Integer>();
        for (int value : java.util.Arrays.asList(1, 8, 3, 9)) {
            if (value < 5) low.add(value);
            else high.add(value);
        }
        low.addAll(high);
        return low.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_TAKE_WHILE, ReturnKind.STRING, """
        java.util.List<Integer> out = new java.util.ArrayList<Integer>();
        for (int value : java.util.Arrays.asList(1, 2, 6, 3)) {
            if (value >= 5) break;
            out.add(value);
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_DROP_WHILE, ReturnKind.STRING, """
        java.util.List<Integer> values = java.util.Arrays.asList(1, 2, 6, 3);
        int start = 0;
        while (start < values.size() && values.get(start) < 5) start++;
        return new java.util.ArrayList<Integer>(values.subList(start, values.size())).toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_PAIRWISE_SUM, ReturnKind.STRING, """
        java.util.List<Integer> values = java.util.Arrays.asList(1, 3, 5, 7), out = new java.util.ArrayList<Integer>();
        for (int i = 1; i < values.size(); i++) out.add(values.get(i - 1) + values.get(i));
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_CARTESIAN_PRODUCT, ReturnKind.STRING, """
        java.util.List<String> out = new java.util.ArrayList<String>();
        for (String a : java.util.Arrays.asList("a", "b")) for (int b : java.util.Arrays.asList(1, 2, 3)) out.add(a + b);
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_STACK_EVALUATE, ReturnKind.INT, """
        java.util.Deque<Integer> stack = new java.util.ArrayDeque<Integer>();
        for (String token : new String[]{"3", "4", "+", "2", "*"}) {
            if (token.equals("+") || token.equals("*")) {
                int b = stack.pop(), a = stack.pop();
                stack.push(token.equals("+") ? a + b : a * b);
            }
            else stack.push(Integer.parseInt(token));
        }
        return stack.pop();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_QUEUE_ROUND_ROBIN, ReturnKind.INT, """
        java.util.Queue<Integer> queue = new java.util.ArrayDeque<Integer>(java.util.Arrays.asList(1, 2, 3));
        int turns = 0;
        while (!queue.isEmpty()) {
            int remaining = queue.remove() - 1;
            if (remaining > 0) queue.add(remaining);
            turns++;
        }
        return turns;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_JOSEPHUS, ReturnKind.INT, """
        java.util.List<Integer> values = new java.util.ArrayList<Integer>();
        for (int i = 1; i <= 7; i++) values.add(i);
        int at = 0;
        while (values.size() > 1) {
            at = (at + 2) % values.size();
            values.remove(at);
        }
        return values.get(0);
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_TOP_K, ReturnKind.STRING, """
        java.util.PriorityQueue<Integer> heap = new java.util.PriorityQueue<Integer>();
        for (int value : new int[]{5, 1, 8, 3, 9, 2}) {
            heap.add(value);
            if (heap.size() > 3) heap.remove();
        }
        java.util.List<Integer> out = new java.util.ArrayList<Integer>();
        while (!heap.isEmpty()) out.add(heap.remove());
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_SORT_BY_LENGTH, ReturnKind.STRING, """
        java.util.List<String> values = new java.util.ArrayList<String>(java.util.Arrays.asList("bbb", "a", "cc"));
        java.util.Collections.sort(values, new java.util.Comparator<String>() {
            @Override public int compare(String a, String b) {
                return Integer.compare(a.length(), b.length());
            }
        }
        );
        return values.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_SLIDING_MAX, ReturnKind.STRING, """
        int[] values = {1, 3, 2, 5, 4};
        java.util.Deque<Integer> indices = new java.util.ArrayDeque<Integer>();
        java.util.List<Integer> out = new java.util.ArrayList<Integer>();
        for (int i = 0; i < values.length; i++) {
            while (!indices.isEmpty() && indices.peekFirst() <= i - 3) indices.removeFirst();
            while (!indices.isEmpty() && values[indices.peekLast()] <= values[i]) indices.removeLast();
            indices.addLast(i);
            if (i >= 2) out.add(values[indices.peekFirst()]);
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.LIST_UNDO_HISTORY, ReturnKind.STRING, """
        java.util.Deque<String> history = new java.util.ArrayDeque<String>();
        String current = "a";
        for (String next : new String[]{"ab", "abc"}) {
            history.push(current);
            current = next;
        }
        if (!history.isEmpty()) current = history.pop();
        return current;
    """.trimIndent()),
)
