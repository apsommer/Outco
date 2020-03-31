package com.sommerengineering.library.frequency_count;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Frequency Counting

Frequency counting is a technique that can be used to solve problems what requires tracking,
counting, or looking up values quickly. These type of problems often involve a collection of
some sort (i.e., array, hashtable, or string). Often the problem will involve matching, counting,
or confirming values in the collection.

Implementation

To implement a frequency count, we typically uses a hashtable, However for specific cases,
we may opt to use a set, or an array.

Hashtable: General all purpose use
Array: Values in the collection that are of a small range of integer values.
Set: If only needed to track if something exists.

To populate our count we will have to loop through our input collection. This leads to a O(N)
time and potentially O(N) auxiliary space (if all values are unique). However future lookups
can be performed in O(1) time as a result. Lets look at examples to see its implementation.

Example 1: Two Sum

Given an array of integers, and a target value determine if there are two integers that add
to the sum.

Input: [4,2,6,5,7,9,10], 13
Output: true

Challenge 1: Sort a Bit Array

Given a bit array, return it sorted in-place (a bit array is simply an array that contains
only bits, either 0 or 1). See if you can solve this in O(N) time and O(1) auxiliary space. Try
to solve this using a frequency count rather than using multiple pointers, or using a comparison
sort function.

Input : [0, 1, 1, 0, 1, 1, 1, 0]
Output : [0, 0, 0, 1, 1, 1, 1, 1]
 */

public class FrequencyCounting {

    public static void main(String[] args) {

        // clock execution time
        final long start = System.nanoTime();

        // Example 1: Two Sum
        boolean isSum = isTwoNumberSum(new int[]{4,2,6,5,7,9,10}, 13);
        System.out.println("isTwoNumberSum: " + isSum); // true

        // Challenge 1: Sort a Bit Array
        int[] bits = sortBitArray(new int[]{0, 1, 1, 0, 1, 1, 1, 0});
        System.out.println("sortBitArray: " + Arrays.toString(bits));

        // Challenge 1: Sort a Bit Array with Multiple Pointers
        bits = sortBitArrayPointers(new int[]{0, 1, 1, 0, 1, 1, 1, 0});
        System.out.println("sortBitArrayPointers: " + Arrays.toString(bits));

        // clock execution time
        final long stop = System.nanoTime();
        System.out.println("\n Time elapsed " + (stop - start) * Math.pow(10, 9) + " seconds.");
    }

    // Example 1: brute force is ~ O(n^2)
//    private static boolean isTwoNumberSum(int[] arr, int target) {
//
//        int current;
//        for (int i = 0; i < arr.length; i ++) {
//            current = arr[i];
//            for (int j = i + 1; j < arr.length; j ++) {
//                if (target - current == arr[j]) return true;
//            }
//        }
//        return false;
//    }

    // Example 1: reduce time complexity to O(n) with cache
    private static boolean isTwoNumberSum(int[] arr, int target) {

        Set<Integer> cache = new HashSet<>();
        for (int current : arr) {
            if (cache.contains(current)) return true;
            else cache.add(target - current);
        }
        return false;
    }

    // Challenge 1: sort an array of bits in-place in O(n) time and O(1) space
    private static int[] sortBitArray(int[] arr) {

        // first pass through array counts number of zeros
        int zeros = 0;
        for (int bit : arr) {
            if (bit == 0) zeros ++;
        }

        // second pass through array places zeros at front
        for (int i = 0; i < arr.length; i ++) {

            if (zeros > 0) {
                arr[i] = 0;
                zeros --;
            }

            else arr[i] = 1;
        }

        return arr;
    }

    // Challenge 1: sort an array of bits in-place in O(n) time and O(1) space using multiple pointers
    private static int[] sortBitArrayPointers(int[] arr) {

        int mid = 0;
        int temp;
        for (int i = 0; i < arr.length; i ++) {

            // only swap on a zero, for a one do nothing
            if (arr[i] == 0) {
                temp = arr[mid];
                arr[mid] = arr[i];
                arr[i] = temp;
                mid ++;
            }
        }
        return arr;
    }
}
