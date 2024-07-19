package com.lvefunc;

import java.util.*;

public class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        if (matrix.length == 0)
            return Collections.emptyList();
        if (matrix[0].length == 0)
            return Collections.emptyList();

        int m = matrix.length;
        int n = matrix[0].length;

        Set<Integer> minimums = new HashSet<>();

        int minimum, maximum; // 1 <= matrix[i][j] <= 10^5

        for (int i = 0; i < m; i++) {
            minimum = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++)
                if (j == 0 || matrix[i][j] < minimum)
                    minimum = matrix[i][j];

            minimums.add(minimum);
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            maximum = Integer.MIN_VALUE;

            for (int j = 0; j < m; j++) {
                if (j == 0 || matrix[j][i] > maximum)
                    maximum = matrix[j][i];
            }

            if (minimums.contains(maximum))
                result.add(maximum);
        }

        return result;
    }
}
