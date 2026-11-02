// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template.method

import cn.hx.plugin.junkcode.template.MethodTemplate

internal fun arraysTemplates(): List<MethodDefinition> = listOf(
    MethodDefinition(MethodTemplate.ARRAY_SUM, ReturnKind.INT, """
        int[] values = {3, 1, 4, 1, 5};
        int sum = 0;
        for (int value : values) sum += value;
        return sum;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_MIN_MAX_RANGE, ReturnKind.INT, """
        int[] values = {3, 1, 4, 1, 5};
        int min = values[0], max = values[0];
        for (int value : values) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
        return max - min;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_REVERSE, ReturnKind.INT_ARRAY, """
        int[] values = {3, 1, 4, 1, 5};
        for (int l = 0, r = values.length - 1; l < r; l++, r--) {
            int temp = values[l];
            values[l] = values[r];
            values[r] = temp;
        }
        return values;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_ROTATE, ReturnKind.INT_ARRAY, """
        int[] values = {3, 1, 4, 1, 5}, result = new int[values.length];
        for (int i = 0; i < values.length; i++) result[(i + 2) % values.length] = values[i];
        return result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_PREFIX_SUM, ReturnKind.INT_ARRAY, """
        int[] values = {3, 1, 4, 1, 5}, prefix = new int[values.length + 1];
        for (int i = 0; i < values.length; i++) prefix[i + 1] = prefix[i] + values[i];
        return prefix;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_DIFFERENCES, ReturnKind.INT_ARRAY, """
        int[] values = {2, 5, 9, 14}, differences = new int[values.length - 1];
        for (int i = 1; i < values.length; i++) differences[i - 1] = values[i] - values[i - 1];
        return differences;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_BINARY_SEARCH, ReturnKind.INT, """
        int[] values = {1, 3, 5, 7, 9};
        int low = 0, high = values.length - 1, target = 7;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (values[mid] == target) return mid;
            if (values[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_LOWER_BOUND, ReturnKind.INT, """
        int[] values = {1, 3, 3, 7, 9};
        int low = 0, high = values.length, target = 3;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (values[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_BUBBLE_SORT, ReturnKind.INT_ARRAY, """
        int[] values = {5, 2, 4, 1, 3};
        for (int end = values.length - 1; end > 0; end--) {
            boolean swapped = false;
            for (int i = 0; i < end; i++) {
                if (values[i] > values[i + 1]) {
                    int temp = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return values;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_INSERTION_SORT, ReturnKind.INT_ARRAY, """
        int[] values = {5, 2, 4, 1, 3};
        for (int i = 1; i < values.length; i++) {
            int value = values[i], j = i - 1;
            while (j >= 0 && values[j] > value) {
                values[j + 1] = values[j];
                j--;
            }
            values[j + 1] = value;
        }
        return values;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_SELECTION_SORT, ReturnKind.INT_ARRAY, """
        int[] values = {5, 2, 4, 1, 3};
        for (int i = 0; i < values.length; i++) {
            int smallest = i;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[smallest]) smallest = j;
            }
            int temp = values[i];
            values[i] = values[smallest];
            values[smallest] = temp;
        }
        return values;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_COUNTING_SORT, ReturnKind.INT_ARRAY, """
        int[] values = {3, 1, 4, 1, 2}, counts = new int[5];
        for (int value : values) counts[value]++;
        int index = 0;
        for (int value = 0; value < counts.length; value++) for (int j = 0; j < counts[value]; j++) values[index++] = value;
        return values;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_MERGE_SORTED, ReturnKind.INT_ARRAY, """
        int[] a = {1, 4, 7}, b = {2, 3, 8}, out = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length || j < b.length) {
            if (j == b.length || (i < a.length && a[i] <= b[j])) out[k++] = a[i++];
            else out[k++] = b[j++];
        }
        return out;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_DEDUP_SORTED, ReturnKind.INT_ARRAY, """
        int[] values = {1, 1, 2, 3, 3, 4};
        int size = 0;
        for (int value : values) {
            if (size == 0 || values[size - 1] != value) values[size++] = value;
        }
        return java.util.Arrays.copyOf(values, size);
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_PARTITION_EVEN, ReturnKind.INT_ARRAY, """
        int[] values = {1, 2, 3, 4, 5, 6};
        int next = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                int temp = values[next];
                values[next++] = values[i];
                values[i] = temp;
            }
        }
        return values;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_MOVE_ZEROS, ReturnKind.INT_ARRAY, """
        int[] values = {0, 1, 0, 3, 2};
        int next = 0;
        for (int value : values) {
            if (value != 0) values[next++] = value;
        }
        while (next < values.length) values[next++] = 0;
        return values;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_MAX_SUBARRAY, ReturnKind.INT, """
        int[] values = {-2, 3, -1, 4, -5};
        int current = values[0], best = current;
        for (int i = 1; i < values.length; i++) {
            current = Math.max(values[i], current + values[i]);
            best = Math.max(best, current);
        }
        return best;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_WINDOW_SUM, ReturnKind.INT, """
        int[] values = {2, 1, 5, 1, 3, 2};
        int sum = 0, best = Integer.MIN_VALUE, width = 3;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
            if (i >= width) sum -= values[i - width];
            if (i >= width - 1) best = Math.max(best, sum);
        }
        return best;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_TWO_SUM, ReturnKind.BOOLEAN, """
        int[] values = {1, 3, 5, 8, 12};
        int left = 0, right = values.length - 1, target = 13;
        while (left < right) {
            int sum = values[left] + values[right];
            if (sum == target) return true;
            if (sum < target) left++;
            else right--;
        }
        return false;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_MAJORITY, ReturnKind.INT, """
        int[] values = {2, 1, 2, 3, 2, 2, 2};
        int candidate = 0, balance = 0;
        for (int value : values) {
            if (balance == 0) candidate = value;
            balance += value == candidate ? 1 : -1;
        }
        return candidate;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_MISSING_NUMBER, ReturnKind.INT, """
        int[] values = {0, 1, 3, 4};
        int missing = values.length;
        for (int i = 0; i < values.length; i++) missing ^= i ^ values[i];
        return missing;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_PRODUCT_EXCEPT_SELF, ReturnKind.INT_ARRAY, """
        int[] values = {1, 2, 3, 4}, result = new int[values.length];
        int product = 1;
        for (int i = 0; i < values.length; i++) {
            result[i] = product;
            product *= values[i];
        }
        product = 1;
        for (int i = values.length - 1; i >= 0; i--) {
            result[i] *= product;
            product *= values[i];
        }
        return result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_DOT_PRODUCT, ReturnKind.INT, """
        int[] a = {1, 2, 3}, b = {4, 5, 6};
        int result = 0;
        for (int i = 0; i < a.length; i++) result += a[i] * b[i];
        return result;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_MEDIAN, ReturnKind.DOUBLE, """
        int[] values = {7, 2, 4, 9, 1, 6};
        java.util.Arrays.sort(values);
        int middle = values.length / 2;
        return values.length % 2 == 0 ? (values[middle - 1] + values[middle]) / 2.0 : values[middle];
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_VARIANCE, ReturnKind.DOUBLE, """
        int[] values = {2, 4, 6, 8};
        double mean = 0, squared = 0;
        int count = 0;
        for (int value : values) {
            count++;
            double delta = value - mean;
            mean += delta / count;
            squared += delta * (value - mean);
        }
        return squared / count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_LONGEST_INCREASING_RUN, ReturnKind.INT, """
        int[] values = {1, 2, 3, 1, 2, 5, 7};
        int run = 1, best = 1;
        for (int i = 1; i < values.length; i++) {
            run = values[i] > values[i - 1] ? run + 1 : 1;
            best = Math.max(best, run);
        }
        return best;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_LOCAL_PEAK_COUNT, ReturnKind.INT, """
        int[] values = {1, 4, 2, 5, 3};
        int count = 0;
        for (int i = 1; i + 1 < values.length; i++) {
            if (values[i] > values[i - 1] && values[i] > values[i + 1]) count++;
        }
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_INVERSION_COUNT, ReturnKind.INT, """
        int[] values = {3, 1, 4, 2};
        int count = 0;
        for (int i = 0; i < values.length; i++) for (int j = i + 1; j < values.length; j++) {
            if (values[i] > values[j]) count++;
        }
        return count;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_PAIRWISE_DISTANCE, ReturnKind.INT, """
        int[] values = {1, 4, 8, 10};
        int sum = 0, prefix = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i] * i - prefix;
            prefix += values[i];
        }
        return sum;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.ARRAY_CONVOLUTION, ReturnKind.INT_ARRAY, """
        int[] a = {1, 2, 3}, b = {1, -1}, out = new int[a.length + b.length - 1];
        for (int i = 0; i < a.length; i++) for (int j = 0; j < b.length; j++) out[i + j] += a[i] * b[j];
        return out;
    """.trimIndent()),
)
