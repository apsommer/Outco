package com.sommerengineering.library.helper_method_recursion;

import java.util.ArrayList;
import java.util.List;

public class HelperMethodRecursion {

    public static void main(String[] args) {

        int[] input = {};
        compute(input);
        for (int[] pair : output) {
            System.out.print("[" + pair[0] + " " + pair[1] + "] ");
        }
    }

    ///////////////////////////////////////////

    // member variables track state
    static int[][] output;
    static int j;

    public static int[][] compute(int[] arr) {

        // instantiate variables
        int size = arr.length/2;
        if (arr.length % 2 == 1) size ++; // odd number needs one extra int[]
        output = new int[size][2];
        j = 0;

        // recurse
        helper(arr, 0);
        return output;
    }

    private static int[] helper(int[] arr, int i) {

        // base cases
        if (arr.length == 0) {
            return arr;
        }
        if (arr.length - i == 1) {
            output[j] = new int[] {arr[i], -1};
            return arr;
        } else if (arr.length - i == 2) {
            output[j] = new int[] {arr[i], arr[i+1]};
            return arr;
        }

        // recursive case
        output[j] = new int[]{arr[i], arr[i+1]};
        j ++;
        i += 2;
        return helper(arr, i);
    }
}
