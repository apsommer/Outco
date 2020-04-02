package com.sommerengineering.library.helper_method_recursion;

public class HelperMethodRecursion {

    public static void main(String[] args) {

//        int[] input = {1,2,3};
//        compute(input);

//        int[] input = {};
//        compute(input);

        int[] input = {5};
        compute(input);
    }

    static int size;

    public static void compute(int[] arr) {
        size = arr.length;
        computeHelper(arr, 0);
    }

    public static void computeHelper(int[] arr, int index) {

        // base case
        if (index == size) return;

        System.out.print(arr[index] +"\n");
        index ++;
        computeHelper(arr, index);
    }
}
