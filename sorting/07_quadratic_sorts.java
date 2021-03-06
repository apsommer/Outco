package com.sommerengineering.library.sorting;
/**
 *  Homework 07 - Quadratic Sorts
 *
 *  Problem 1: Insertion Sort
 *
 *  Prompt:    Given an unsorted array of integers, return the array sorted
 *             using insertion sort.
 *
 *  Input:     input {Array}
 *  Output:    {Array}
 *
 *  Example:   [3,9,1,4,7] --> [1,3,4,7,9]
 *
 *
 *  Problem 2: Selection Sort
 *
 *  Prompt:    Given an unsorted array of integers, return the array
 *             sorted using selection sort.
 *
 *  Input:     input {Array}
 *  Output:    {Array}
 *
 *  Example:   [3,9,1,4,7] --> [1,3,4,7,9]
 *
 *
 *  Problem 3: Bubble Sort
 *
 *  Prompt:    Given an unsorted array of integers, return the array
 *             sorted using bubble sort.
 *
 *  Input:     input {Array}
 *  Output:    {Array}
 *
 *  Example:   [3,9,1,4,7] --> [1,3,4,7,9]
 */

import java.util.*;

class BasicSort {

  // insertion is the best choice from these basic quadratic sorts ... it is about twice as fast, on average, relative
  // to selection or bubble

  // all these basic sorts can only handle n < 10, the N^2 time makes any larger too inefficient

  // Time Complexity: O(N^2) for worst and average, reduces to O(N) if the input array is already sorted
  // Auxiliary Space Complexity: O(1) in-place
  // stable, and is streamable as new elements are obtained they can be added to the sorted partition
  // good choice (fast) if the elements are only a few places away from their final positions, reduces to O(kN) where
  // k = average distance elements are away from their final positions
  public static int[] insertion(int[] input) {

    // consider two partitions in the array: sorted at beginning, unsorted at end
    // insertion sort inserts element into the sorted position moving all elements in the sorted partition as needed

    // in contrast, selection sort selects the minimum in the unsorted partition and there is only one place it can go:
    // at the end of the sorted section, this follows from the definition of local minimum

    int temp;

    // loop through each element once and insert it into the sorted partition
    for (int i = 1; i < input.length; i ++) {

      // loop through just the sorted partition
      for (int j = 0; j < i; j ++) {

        // the correct, sorted position for this i element is located in the sorted partition
        if (input[i] < input[j]) {

          // save off the element to be inserted
          temp = input[i];

          // all elements downstream of this position must be nudged forward one index to make room
          for (int k = i; k > j; k --) {
            input[k] = input[k-1];
          }

          // insert this element into its sorted position within the sorted partition
          input[j] = temp;

          // no need to consider other j's in the sorted portion, move to next element i
          break;
        }
      }
    }

    // this is an in-place sort, return the sorted input array
    return input;
  }

  // Time Complexity: O(N^2) in best, average, and worst since the minimum must be searched every iteration regardless
  // Auxiliary Space Complexity: O(1) this is in-place
  // not stable!
  public static int[] selection(int[] input) {

    // loop through array and find the minimum value, put this minimum value at the beginning of the array, now the first
    // element is sorted so we don't need to consider it anymore, find the next minimum value ... continue

    // selection sort "selects the minimum value and puts it in its correct position"
    // it accomplishes this by splitting the array into two partitions: sorted at the front and unsorted in the back
    // one element is added to the sorted side for each iteration, then repeat

    int min, temp;

    // master loop identifies the boundary between sorted and unsorted partitions
    for (int i = 0; i < input.length - 1; i ++) {

      // start of the unsorted partition
      min = i;

      // find the minimum value in the unsorted partition
      for (int j = i + 1; j < input.length; j ++) {
        if (input[j] < input[min]) min = j;
      }

      // move this minimum value to last element in the sorted section
      temp = input[i];
      input[i] = input[min];
      input[min] = temp;
    }

    // this sort is in-place, return the sorted input
    return input;
  }


  // Time Complexity: O(N^2) for worst and average, O(N) for best case
  // Auxiliary Space Complexity: O(1)
  // this is a stable sort
  public static int[] bubble(int[] input) {

    // keep a "did swap" flag
    // compare each element to its neighbor, swap if necessary, continue until no swaps are made
    // this "bubbles up" the largest element to the end on each iteration, therefore each iteration we don't need to
    // consider the last element relative to the last iteration

    // in the worst case we must do N passes through the array of N elements -> quadratic time
    // this is an in-place sort so O(1) space

    boolean isSwapMade = true;
    int currentLength = input.length - 1;
    int temp;

    while (isSwapMade) {

      // reset the flag for this iteration
      isSwapMade = false;

      for (int i = 0; i < currentLength; i ++) {

        // swap if needed
        if (input[i] > input[i+1]) {

          // move the elements using a temp int
          temp = input[i];
          input[i] = input[i+1];
          input[i+1] = temp;

          // a swap has been made
          isSwapMade = true;
        }

      }

      currentLength --; // since we bubbled up the largest element for this pass
    }

    return input;
  }
}


////////////////////////////////////////////////////////////
///////////////  DO NOT TOUCH TEST BELOW!!!  ///////////////
////////////////////////////////////////////////////////////

// use the Main class to run the test cases
class Main {
  private int[] testCount;

  // an interface to perform tests
  public interface Test {
    public boolean execute();
  }

  public static void main(String[] args) {

    // instantiate the testing of each module by resetting count and printing title of module
    int[] testCount = {0, 0};
    System.out.println("Insertion Sort Tests");

    // tests are in the form as shown
    assertTest(testCount, "should sort example input", new Test() {
      public boolean execute() {
        BasicSort basicSort = new BasicSort();
        return arraysEqual(basicSort.insertion(new int[]{3, 9, 1, 4, 7}), new int[]{1, 3, 4, 7, 9});
      }
    });

    assertTest(testCount, "should sort single-element input", new Test() {
      public boolean execute() {
        BasicSort basicSort = new BasicSort();
        return arraysEqual(basicSort.insertion(new int[]{10}), new int[]{10});
      }
    });

    assertTest(testCount, "should sort moderate-sized input", new Test() {
      public boolean execute() {
        BasicSort basicSort = new BasicSort();
        int[] input = new int[1000];
        for (int i = 0 ; i < input.length ; i++) {
          input[i] = (int) Math.floor(Math.random() * 1000);
        }

        int[] solution = new int[1000];
        System.arraycopy(input, 0, solution, 0, input.length);
        input = basicSort.insertion(input);

        Arrays.sort(solution);
        return isSorted(input) && arraysEqual(input, solution);
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Selection Sort Tests");

    assertTest(testCount, "should sort example input", new Test() {
      public boolean execute() {
        BasicSort basicSort = new BasicSort();
        return arraysEqual(basicSort.selection(new int[]{3, 9, 1, 4, 7}), new int[]{1, 3, 4, 7, 9});
      }
    });

    assertTest(testCount, "should sort single-element input", new Test() {
      public boolean execute() {
        BasicSort basicSort = new BasicSort();
        return arraysEqual(basicSort.selection(new int[]{10}), new int[]{10});
      }
    });

    assertTest(testCount, "should sort moderate-sized input", new Test() {
      public boolean execute() {
        BasicSort basicSort = new BasicSort();
        int[] input = new int[1000];
        for (int i = 0 ; i < input.length ; i++) {
          input[i] = (int) Math.floor(Math.random() * 1000);
        }

        int[] solution = new int[1000];
        System.arraycopy(input, 0, solution, 0, input.length);
        input = basicSort.selection(input);

        Arrays.sort(solution);
        return isSorted(input) && arraysEqual(input, solution);
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Bubble Sort Tests");

    // tests are in the form as shown
    assertTest(testCount, "should sort example input", new Test() {
      public boolean execute() {
        BasicSort basicSort = new BasicSort();
        return arraysEqual(basicSort.bubble(new int[]{3, 9, 1, 4, 7}), new int[]{1, 3, 4, 7, 9});
      }
    });

    assertTest(testCount, "should sort single-element input", new Test() {
      public boolean execute() {
        BasicSort basicSort = new BasicSort();
        return arraysEqual(basicSort.bubble(new int[]{10}), new int[]{10});
      }
    });

    assertTest(testCount, "should sort moderate-sized input", new Test() {
      public boolean execute() {
        BasicSort basicSort = new BasicSort();
        int[] input = new int[1000];
        for (int i = 0 ; i < input.length ; i++) {
          input[i] = (int) Math.floor(Math.random() * 1000);
        }

        int[] solution = new int[1000];
        System.arraycopy(input, 0, solution, 0, input.length);
        input = basicSort.bubble(input);

        Arrays.sort(solution);
        return isSorted(input) && arraysEqual(input, solution);
      }
    });

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
  public static boolean isSorted(int[] input) {
    if (input.length < 2) {
      return true;
    }

    for (int i = 1 ; i < input.length ; i++) {
      if (input[i-1] > input[i]) {
        System.out.println("bad elements, i = " + i + " input[i-1] = " + input[i-1] + " input[i] = " + input[i]);
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
