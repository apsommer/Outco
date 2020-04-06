package com.sommerengineering.library.helper_method_recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelperMethodRecursion {

    public static void main(String[] args) {
        int[] nums1 = {1, 4, 7};
        int[] nums2 = {2, 3, 6, 9};
        int[] merged = compute(nums1, nums2);
        for (int i : merged) System.out.print(i + ", ");
    }

    ///////////////////////////////////////////

    /**
     * 2g. Merge two sorted arrays using the Helper Method Recursion
     *
     * Input:   Two Integer Arrays, both sorted
     * Output:  Integer Array, sorted
     *
     * Example: int[] nums1 = {1, 4, 7};
     *          int[] nums2 = {2, 3, 6, 9};
     *
     *          Merge.compute(nums1, nums2) =>
     *          { 1, 2, 3, 4, 6, 7, 9 }
     */

    static List<Integer> list;

    public static int[] compute(int[] arr1, int[] arr2) {

        // catch trivial cases
        if (arr1.length == 0) return arr2;
        if (arr2.length == 0) return arr1;

        // begin the output list as the sorted first array
        list = new ArrayList<>();
        for (int i : arr1) list.add(i);

        // call the recursive helper to place each new element from the second array
        for (int j : arr2) helper(j, list.size() - 1);

        // convert the list to int[]
        int[] output = new int[list.size()];
        for (int i = 0; i < output.length; i ++) output[i] = list.get(i);
        return output;
    }

    private static void helper(int j, int index) {

        // base case: new element is in correct sorted position
        if (j > list.get(index)) {
            list.add(index + 1, j);
            return;
        }

        // decrement the index
        helper(j, index - 1);
    }
}

