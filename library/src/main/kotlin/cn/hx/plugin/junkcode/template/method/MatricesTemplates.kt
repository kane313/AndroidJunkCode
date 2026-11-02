// Added in the downstream AndroidJunkCode version, 2026-10-30.
// See LICENSE, NOTICE and MODIFICATIONS.md at the repository root.

package cn.hx.plugin.junkcode.template.method

import cn.hx.plugin.junkcode.template.MethodTemplate

internal fun matricesTemplates(): List<MethodDefinition> = listOf(
    MethodDefinition(MethodTemplate.MATRIX_TRANSPOSE, ReturnKind.INT_MATRIX, """
        int[][] values = {{1, 2, 3}, {4, 5, 6}}, out = new int[3][2];
        for (int r = 0; r < values.length; r++) for (int c = 0; c < values[r].length; c++) out[c][r] = values[r][c];
        return out;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_MULTIPLY, ReturnKind.INT_MATRIX, """
        int[][] a = {{1, 2}, {3, 4}}, b = {{5, 6}, {7, 8}}, out = new int[2][2];
        for (int r = 0; r < 2; r++) for (int c = 0; c < 2; c++) for (int k = 0; k < 2; k++) out[r][c] += a[r][k] * b[k][c];
        return out;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_TRACE, ReturnKind.INT, """
        int[][] values = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int trace = 0;
        for (int i = 0; i < values.length; i++) trace += values[i][i];
        return trace;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_DETERMINANT, ReturnKind.INT, """
        int[][] a = {{2, 1, 3}, {0, 4, 5}, {1, 0, 6}};
        return a[0][0] * (a[1][1] * a[2][2] - a[1][2] * a[2][1]) - a[0][1] * (a[1][0] * a[2][2] - a[1][2] * a[2][0]) + a[0][2] * (a[1][0] * a[2][1] - a[1][1] * a[2][0]);
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_ROTATE_CLOCKWISE, ReturnKind.INT_MATRIX, """
        int[][] values = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int n = values.length;
        for (int layer = 0; layer < n / 2; layer++) for (int i = layer; i < n - layer - 1; i++) {
            int last = n - layer - 1, offset = i - layer, top = values[layer][i];
            values[layer][i] = values[last - offset][layer];
            values[last - offset][layer] = values[last][last - offset];
            values[last][last - offset] = values[i][last];
            values[i][last] = top;
        }
        return values;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_SPIRAL, ReturnKind.STRING, """
        int[][] values = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int top = 0, bottom = 2, left = 0, right = 2;
        StringBuilder out = new StringBuilder();
        while (top <= bottom && left <= right) {
            for (int c = left; c <= right; c++) out.append(values[top][c]);
            top++;
            for (int r = top; r <= bottom; r++) out.append(values[r][right]);
            right--;
            if (top <= bottom) {
                for (int c = right; c >= left; c--) out.append(values[bottom][c]);
                bottom--;
            }
            if (left <= right) {
                for (int r = bottom; r >= top; r--) out.append(values[r][left]);
                left++;
            }
        }
        return out.toString();
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_ROW_SUMS, ReturnKind.INT_ARRAY, """
        int[][] values = {{1, 2, 3}, {4, 5, 6}};
        int[] sums = new int[values.length];
        for (int r = 0; r < values.length; r++) for (int value : values[r]) sums[r] += value;
        return sums;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_COLUMN_MAX, ReturnKind.INT_ARRAY, """
        int[][] values = {{1, 8, 3}, {4, 5, 6}};
        int[] max = values[0].clone();
        for (int r = 1; r < values.length; r++) for (int c = 0; c < max.length; c++) max[c] = Math.max(max[c], values[r][c]);
        return max;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_SYMMETRY, ReturnKind.BOOLEAN, """
        int[][] values = {{1, 2, 3}, {2, 4, 5}, {3, 5, 6}};
        for (int r = 0; r < values.length; r++) for (int c = r + 1; c < values.length; c++) {
            if (values[r][c] != values[c][r]) return false;
        }
        return true;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_IDENTITY, ReturnKind.INT_MATRIX, """
        int[][] identity = new int[4][4];
        for (int i = 0; i < identity.length; i++) identity[i][i] = 1;
        return identity;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_PREFIX_SUM, ReturnKind.INT_MATRIX, """
        int[][] values = {{1, 2}, {3, 4}}, sums = new int[3][3];
        for (int r = 1; r < sums.length; r++) for (int c = 1; c < sums[r].length; c++) sums[r][c] = values[r - 1][c - 1] + sums[r - 1][c] + sums[r][c - 1] - sums[r - 1][c - 1];
        return sums;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_GRID_PATHS, ReturnKind.INT, """
        int[][] paths = new int[4][5];
        for (int r = 0; r < paths.length; r++) for (int c = 0; c < paths[r].length; c++) paths[r][c] = r == 0 || c == 0 ? 1 : paths[r - 1][c] + paths[r][c - 1];
        return paths[3][4];
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_MIN_PATH_COST, ReturnKind.INT, """
        int[][] cost = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        for (int r = 0; r < cost.length; r++) for (int c = 0; c < cost[r].length; c++) {
            if (r == 0 && c == 0) continue;
            int above = r > 0 ? cost[r - 1][c] : Integer.MAX_VALUE, left = c > 0 ? cost[r][c - 1] : Integer.MAX_VALUE;
            cost[r][c] += Math.min(above, left);
        }
        return cost[2][2];
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_NEIGHBOR_SUM, ReturnKind.INT_MATRIX, """
        int[][] values = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, out = new int[3][3];
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        for (int r = 0; r < 3; r++) for (int c = 0; c < 3; c++) for (int k = 0; k < 4; k++) {
            int nr = r + dr[k], nc = c + dc[k];
            if (nr >= 0 && nr < 3 && nc >= 0 && nc < 3) out[r][c] += values[nr][nc];
        }
        return out;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_CHECKERBOARD, ReturnKind.INT_MATRIX, """
        int[][] board = new int[4][4];
        for (int r = 0; r < board.length; r++) for (int c = 0; c < board[r].length; c++) board[r][c] = (r + c) & 1;
        return board;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_FLOOD_FILL, ReturnKind.INT_MATRIX, """
        int[][] grid = {{1, 1, 0}, {1, 0, 0}, {0, 0, 1}};
        java.util.Queue<Integer> queue = new java.util.ArrayDeque<Integer>();
        queue.add(0);
        grid[0][0] = 2;
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int cell = queue.remove(), r = cell / 3, c = cell % 3;
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k], nc = c + dc[k];
                if (nr >= 0 && nr < 3 && nc >= 0 && nc < 3 && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    queue.add(nr * 3 + nc);
                }
            }
        }
        return grid;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_LIFE_STEP, ReturnKind.INT_MATRIX, """
        int[][] grid = {{0, 1, 0}, {0, 1, 0}, {0, 1, 0}}, out = new int[3][3];
        for (int r = 0; r < 3; r++) for (int c = 0; c < 3; c++) {
            int neighbors = 0;
            for (int dr = -1; dr <= 1; dr++) for (int dc = -1; dc <= 1; dc++) {
                int nr = r + dr, nc = c + dc;
                if ((dr != 0 || dc != 0) && nr >= 0 && nr < 3 && nc >= 0 && nc < 3) neighbors += grid[nr][nc];
            }
            out[r][c] = neighbors == 3 || (neighbors == 2 && grid[r][c] == 1) ? 1 : 0;
        }
        return out;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_TOEPLITZ_CHECK, ReturnKind.BOOLEAN, """
        int[][] values = {{1, 2, 3}, {4, 1, 2}, {5, 4, 1}};
        for (int r = 1; r < values.length; r++) for (int c = 1; c < values[r].length; c++) {
            if (values[r][c] != values[r - 1][c - 1]) return false;
        }
        return true;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_SADDLE_POINT, ReturnKind.INT, """
        int[][] values = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};
        for (int r = 0; r < values.length; r++) {
            int column = 0;
            for (int c = 1; c < values[r].length; c++) {
                if (values[r][c] < values[r][column]) column = c;
            }
            boolean largest = true;
            for (int other = 0; other < values.length; other++) {
                if (values[other][column] > values[r][column]) largest = false;
            }
            if (largest) return values[r][column];
        }
        return -1;
    """.trimIndent()),
    MethodDefinition(MethodTemplate.MATRIX_ZERO_ROWS_COLUMNS, ReturnKind.INT_MATRIX, """
        int[][] values = {{1, 2, 3}, {4, 0, 6}, {7, 8, 9}};
        boolean[] rows = new boolean[3], columns = new boolean[3];
        for (int r = 0; r < 3; r++) for (int c = 0; c < 3; c++) {
            if (values[r][c] == 0) {
                rows[r] = true;
                columns[c] = true;
            }
        }
        for (int r = 0; r < 3; r++) for (int c = 0; c < 3; c++) {
            if (rows[r] || columns[c]) values[r][c] = 0;
        }
        return values;
    """.trimIndent()),
)
