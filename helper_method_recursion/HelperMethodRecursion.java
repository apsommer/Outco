package com.sommerengineering.library.helper_method_recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelperMethodRecursion {

    public static void main(String[] args) {

        int matrix[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        compute(matrix);
        for (int i = 0; i < output.length; i ++) {
            System.out.print(output[i] + ", ");
        }
    }

    ///////////////////////////////////////////

    private static List<Integer> list;
    private static int[] output;

    private static int[] compute(int[][] matrix) {

        list = new ArrayList<>();

        // use recursion for each subarray
        for (int[] chunk : matrix) helper(chunk);

        output = new int[list.size()]; // list now contains the full result
        for (int i = 0; i < output.length; i ++) output[i] = list.get(i);

        return output;
    }

    private static void helper(int[] chunk) {

        if (chunk.length == 0) return;
        list.add(chunk[0]);
        helper(Arrays.copyOfRange(chunk, 1, chunk.length)); // tail recursion
    }
}

