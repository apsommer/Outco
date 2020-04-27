package com.sommerengineering.library.sorting;
/**
 *  Homework 08 - Quasilinear Sorts
 *
 *
 *  Problem 1: Mergesort
 *
 *  Prompt:    Given an unsorted array of integers, return the array
 *             sorted using mergesort.
 *
 *  Input:     {Array}
 *  Output:    {Array}
 *
 *  Example:   [3,9,1,4,7] --> [1,3,4,7,9]
 */

import java.util.*;

// Worst Time Complexity: O(NlogN) quasilinear sort, we must perform N comparison operations logN times
// Worst Total (Call Stack + Auxiliary) Space Complexity: O(N), we have N elements on the call stack at each level of
// recursion logN times, and N for the final output, which is O(NlogN + N) -> O(NlogN) at first glance, however the depth
// of recursion only goes down one side at a time rather than everything simultaneously, this "depth-first" recursion
// pattern is bounded by O(3N) -> O(N)
// Average Time Complexity: same as worst case
// Average Total (Call Stack + Auxiliary) Space Complexity: same as worst case
// Stability: yes, stable

// quicksort is another popular NlogN sorting algorithm
// quicksort is 2-3 times faster than mergesort on average, it is also in-place so we only need logN elements on the call
// stack for auxiliary space
// quicksort is not stable, also in worst case it degrades to quadratic

// heapsort is another quasilinear sort that obviously requires a heap, extraction from a heap is logN time, and we need
// to do this N times, resulting in O(NlogN) total time, can be implemented in O(1) time as it doesn't require recursion,
// requires a heap, not stable, on average slower than quicksort due to extra comparisons on bubble up and bubble down

// shellsort, comb sort, and Timsort are three more
// Java framework Collections.sort() uses a modified mergesort for SDK < 6, SDK >= 6 uses Timsort

class Mergesort{

  public static void main(String[] args) {

    int[] input = new int[]{3, 9, 1, 4, 7};
    int[] output = compute(input);
    print(output);

  }

  private static void print(int[] input) {

    for (int i : input) {
      System.out.print(i + ", ");
    }

    System.out.println();

  }

  public static int[] compute(int[] input) {

    // base case: single element and empty array are already sorted
    if (input.length <= 1) return input;

    // split the array in half
    int mid = input.length/2;
    int[] left = Arrays.copyOfRange(input, 0, mid);
    int[] right = Arrays.copyOfRange(input, mid, input.length);

    // send left side through recursion
    left = compute(left);

    // send right side through recursion
    right = compute(right);

    // merge the left and right sides
    return merge(left, right);
  }

  private static int[] merge(int[] array1, int[] array2) {

    // left pointers for each array
    int i1 = 0;
    int i2 = 0;
    int i = 0; // to track the output index

    // create an output array
    int[] output = new int[array1.length + array2.length];

    // while statement that each left pointer is within bounds
    while (i1 < array1.length && i2 < array2.length) {

      // compare the elements in each array, put the minimum into the output
      if (array1[i1] < array2[i2]) {

        output[i] = array1[i1];
        i1 ++;
        i ++;

      } else {

        output[i] = array2[i2];
        i2 ++;
        i ++;

      }
    }

    // one pointer did not exceed its array length, copy those elements into output
    while (i1 < array1.length) {
      output[i] = array1[i1];
      i1 ++;
      i ++;
    }

    // one pointer did not exceed its array length, copy those elements into output
    while (i2 < array2.length) {
      output[i] = array2[i2];
      i2 ++;
      i ++;
    }

    return output;
  }
}


////////////////////////////////////////////////////////////
///////////////  DO NOT TOUCH TEST BELOW!!!  ///////////////
////////////////////////////////////////////////////////////

// use the Main class to run the test cases
class Main2 {
  private int[] testCount;

  // an interface to perform tests
  public interface Test {
    public boolean execute();
  }

  public static void main(String[] args) {

    int[] testCount = {0, 0};
    System.out.println("Merge Sort Tests");

    // tests are in the form as shown
    assertTest(testCount, "should sort example input", new Test() {
      public boolean execute() {
        Mergesort mergesort = new Mergesort();
        return arraysEqual(mergesort.compute(new int[]{3, 9, 1, 4, 7}), new int[]{1, 3, 4, 7, 9});
      }
    });

    assertTest(testCount, "should return empty array for empty input", new Test() {
      public boolean execute() {
        Mergesort mergesort = new Mergesort();
        return arraysEqual(mergesort.compute(new int[]{}), new int[]{});
      }
    });

    assertTest(testCount, "should sort single-element input", new Test() {
      public boolean execute() {
        Mergesort mergesort = new Mergesort();
        return arraysEqual(mergesort.compute(new int[]{10}), new int[]{10});
      }
    });

    assertTest(testCount, "should sort moderate-sized input", new Test() {
      public boolean execute() {
        Mergesort mergesort = new Mergesort();
        int[] input = new int[1000];
        for (int i = 0 ; i < input.length ; i++) {
          input[i] = (int) Math.floor(Math.random() * 1000);
        }
        int[] inputSorted = new int[1000];

        System.arraycopy(input, 0, inputSorted, 0, input.length);

        int[] solution = new int[1000];
        System.arraycopy(input, 0, solution, 0, input.length);
        input = mergesort.compute(input);

        Arrays.sort(solution);
        return isSorted(input) && arraysEqual(input, solution);
      }
    });

    assertTest(testCount, "should sort large-sized input", new Test() {
      public boolean execute() {
        Mergesort mergesort = new Mergesort();
        int[] input = new int[1000000];
        for (int i = 0 ; i < input.length ; i++) {
          input[i] = (int) Math.floor(Math.random() * 1000000);
        }
        int[] inputSorted = new int[1000000];

        System.arraycopy(input, 0, inputSorted, 0, input.length);

        int[] solution = new int[1000000];
        System.arraycopy(input, 0, solution, 0, input.length);
        input = mergesort.compute(input);

        Arrays.sort(solution);
        return isSorted(input) && arraysEqual(input, solution);
      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

  }

  // function for checking if arrays are equal
  private static boolean arraysEqual(int[] arr1, int[] arr2) {
    if (arr1.length != arr2.length) {
      return false;
    }

    for (int i = 0 ; i < arr1.length ; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }

    return true;
  }


  // checks if array is sorted in linear time
  private static boolean isSorted(int[] input) {
    if (input.length < 2) {
      return true;
    }

    for (int i = 1 ; i < input.length ; i++) {
      if (input[i-1] > input[i]) {
        return false;
      }
    }

    return true;
  }

  // do not edit below, this is to wrap the test and check for exceptions
  private static void assertTest(int[] count, String name, Test test) {
    String pass = "false";
    count[1]++;

    try {
      if (test.execute()) {
        pass = " true";
        count[0]++;
      }
    } catch(Exception e) {}
    String result = "  " + (count[1] + ")   ").substring(0, 5) + pass + " : " + name;
    System.out.println(result);
  }
}
