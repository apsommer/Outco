/*
 *  Homework 04 - Helper Method Recursion
 *
 *  Instructions: Using the Helper Method Recursive Pattern work through the
 *  following problems.
 *
 */

package com.sommerengineering.library.helper_method_recursion;

import java.io.*;
import java.util.*;


/**
 * 1a. What is the term when the recursive call invokes itself more than once.
 * Multiple recursion
 *
 */


/**
 * 1b. List the steps involved to build a Helper Method Recursion algorithm.
 * Instantiate variables (track state, results, ...)
 * Return results
 * Instantiate helper method and invoke it
 * Define the base case
 * Define the recursive case
 */


/**
 * 1c. Should the recursive case or base case typically be tackled first?
 * Base case
 *
 */


/**
 *  2a. Print each integer in an array in order using Helper Method Recursion
 *
 *  Input:   arr {Array}
 *  Output:  {void}
 *
 *  Example: int[] nums = {1, 2, 3};
 *
 *          PrintArray.compute(nums) =>
 *          1
 *          2
 *          3
 */
class PrintArray {

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

/**
 *  2b. Given an integer array, print each item backwards using Helper Method
 *      Recursion
 *
 *  Input:   arr {Array}
 *  Output:  {void}
 *
 *  Example: int[] nums = {1, 2, 3};
 *
 *          PrintReverse.compute(nums) =>
 *          3
 *          2
 *          1
 */
class PrintReverse {

  public static void compute(int[] arr) {
    computeHelper(arr, arr.length - 1);
  }

  public static void computeHelper(int[] arr, int index) {

    // base case
    if (index < 0) return;

    System.out.print(arr[index] +"\n");
    index --;
    computeHelper(arr, index);
  }
}


/**
 *  2c. Reverse a string using Helper Method Recursion
 *
 *  Input:   String
 *  Output:  String
 *
 *  Example: String greeting = 'hello';
 *          ReverseString.compute(greeting) => 'olleh'
 */
class ReverseString {

  static String output = "";

  public static String compute(String str) {

    // catch trivial inputs
    if (str.isEmpty()) return str;
    if (str.length() == 1) return str;

    // recurse
    helper(str);
    return output;
  }

  private static String helper(String str) {

    // base case
    if (str.length() < 2) {
      output += str;
      return str;
    }

    output += str.charAt(str.length()-1);
    str = str.substring(0, str.length() - 1);
    return helper(str);
  }

}


/**
 * 2d. Given an array of positive integers, create an array of two-item integer
 *     arrays (two dimensional integer array) using Helper Method Recursion
 *
 * Input:   Array of Integers
 * Output:  two-dimensional Integer Array
 *
 * Example: int[] nums = {1, 2, 3, 4, 5, 6};
 *          ArrayPairs.compute(nums)  =>
 *          {{1,2}, {3,4}, {5,6}}
 *
 * Example: int[] nums2 = {1, 2, 3, 4, 5};
 *          ArrayPairs.compute(nums2)  =>
 *          {{1,2}, {3,4}, {5, -1}}
 */
class ArrayPairs {

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


/**
 * 2e. Flatten a two dimensional integer array using Helper Method of Recursion
 *
 * Input:   two-dimensional Integer Array
 * Output:  Array of Integers
 *
 * Example: int com.sommerengineering.library.matrix[][]= {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
 *          Flatten.compute(com.sommerengineering.library.matrix) =>
 *          {1, 2, 3, 4, 5, 6, 7, 8, 9}
 */
class Flatten {

  private static List<Integer> list;
  private static int[] output;

  static int[] compute(int[][] matrix) {

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

/**
 * 2f. Given a base and an exponent, create a method to find the power using
 *     Helper Method Recursion
 *
 * Input:   Two Integers, base and exponent
 * Output:  Integer
 *
 * Example: Power.compute(3, 4) => 81
 */
class Power {

  private static int output;

  public static int compute(int a, int b) {

    output = a;
    return helper(b);
  }

  private static int helper(int b) {

    if (b == 0) {
      return 1;
    }

    return output * helper(b-1);
  }
}

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
class Merge {

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
    System.out.println("PrintArray tests");

    // tests are in the form as shown
    assertTest(testCount, "able to print the elements of [1,2,3] forwards", new Test() {
      public boolean execute() {

        PrintArray printArray = new PrintArray();

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        // Print some output: goes to your special stream
        int[] input = {1,2,3};
        printArray.compute(input);

        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        //System.out.println("Here: " + baos.toString());

        return baos.toString().equals("1\n2\n3\n");
      }
    });

    assertTest(testCount, "does not print for an empty array", new Test() {
      public boolean execute() {

        PrintArray printArray = new PrintArray();

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        // Print some output: goes to your special stream
        int[] input = {};
        printArray.compute(input);

        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        //System.out.println("Here: " + baos.toString());

        return baos.toString().equals("");
      }
    });

    assertTest(testCount, "able to print a single element array [5]", new Test() {
      public boolean execute() {

        PrintArray printArray = new PrintArray();

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        // Print some output: goes to your special stream
        int[] input = {5};
        printArray.compute(input);

        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        //System.out.println("Here: " + baos.toString());

        return baos.toString().equals("5\n");
      }
    });


    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");




    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("PrintReverse tests");

    // tests are in the form as shown
    assertTest(testCount, "able to print the elements of [1,2,3] backwards", new Test() {
      public boolean execute() {

        PrintReverse printReverse = new PrintReverse();

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        // Print some output: goes to your special stream
        int[] input = {1,2,3};
        printReverse.compute(input);

        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        //System.out.println("Here: " + baos.toString());

        return baos.toString().equals("3\n2\n1\n");
      }
    });

    assertTest(testCount, "does not print for an empty array", new Test() {
      public boolean execute() {

        PrintReverse printReverse = new PrintReverse();

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        // Print some output: goes to your special stream
        int[] input = {};
        printReverse.compute(input);

        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        //System.out.println("Here: " + baos.toString());

        return baos.toString().equals("");
      }
    });

    assertTest(testCount, "able to print a single element array [5]", new Test() {
      public boolean execute() {

        PrintReverse printReverse = new PrintReverse();

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        // Print some output: goes to your special stream
        int[] input = {5};
        printReverse.compute(input);

        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        //System.out.println("Here: " + baos.toString());

        return baos.toString().equals("5\n");
      }
    });


    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");



    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("ReverseString tests");

    // tests are in the form as shown
    assertTest(testCount, "able to reverse string 'hello'", new Test() {
      public boolean execute() {

        ReverseString reverseString = new ReverseString();

        return reverseString.compute("'hello'").equals("'olleh'");

      }
    });

    assertTest(testCount, "able to return an empty string for empty string input", new Test() {
      public boolean execute() {

        ReverseString reverseString = new ReverseString();

        return reverseString.compute("").equals("");

      }
    });

    assertTest(testCount, "able to return the same character for a single-character input string", new Test() {
      public boolean execute() {

        ReverseString reverseString = new ReverseString();

        return reverseString.compute("b").equals("b");
      }
    });


    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");



    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("ArrayPairs tests");

    // tests are in the form as shown
    assertTest(testCount, "should return [[1,2],[3,4],[5,6]] output for [1,2,3,4,5,6] input", new Test() {
      public boolean execute() {

        ArrayPairs arrayPairs = new ArrayPairs();

        int[] input = {1,2,3,4,5,6};
        int[][] output = arrayPairs.compute(input);

        return output.length == 3 &&
                  output[0][0] == 1 && output[0][1] == 2 &&
                  output[1][0] == 3 && output[1][1] == 4 &&
                  output[2][0] == 5 && output[2][1] == 6;

      }
    });

    assertTest(testCount, "should return [[1,2],[3,4],[5,-1]] output for [1,2,3,4,5] input", new Test() {
      public boolean execute() {

        ArrayPairs arrayPairs = new ArrayPairs();

        int[] input = {1,2,3,4,5};
        int[][] output = arrayPairs.compute(input);

        return output.length == 3 &&
                  output[0][0] == 1 && output[0][1] == 2 &&
                  output[1][0] == 3 && output[1][1] == 4 &&
                  output[2][0] == 5 && output[2][1] == -1;

      }
    });

    assertTest(testCount, "should return [] output for [] input", new Test() {
      public boolean execute() {

        ArrayPairs arrayPairs = new ArrayPairs();

        int[] input = {};
        int[][] output = arrayPairs.compute(input);

        return output.length == 0;

      }
    });


    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");




    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Flatten tests");

    // tests are in the form as shown
    assertTest(testCount, "should return [1,2,3,4,5,6,7,8,9] output for [[1, 2, 3],[4, 5, 6],[7, 8, 9]] input", new Test() {
      public boolean execute() {

        Flatten flatten = new Flatten();

        int[][] input = {{1, 2, 3} ,{4, 5, 6}, {7, 8, 9}};
        int[] output = flatten.compute(input);

        return output.length == 9 &&
                  output[0] == 1 && output[1] == 2 &&
                  output[2] == 3 && output[3] == 4 &&
                  output[4] == 5 && output[5] == 6 &&
                  output[6] == 7 && output[7] == 8 &&
                  output[8] == 9;

      }
    });

    assertTest(testCount, "should return [] output for [] input", new Test() {
      public boolean execute() {

        Flatten flatten = new Flatten();

        int[][] input = {};
        int[] output = flatten.compute(input);
        return output.length == 0;

      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Power tests");

    // tests are in the form as shown
    assertTest(testCount, "should return 81 for 3 to the 4th power", new Test() {
      public boolean execute() {

        Power power = new Power();

        return power.compute(3,4) == 81;

      }
    });

    assertTest(testCount, "should return 1 for 5 to the 0th power", new Test() {
      public boolean execute() {

        Power power = new Power();

        return power.compute(5,0) == 1;

      }
    });

    assertTest(testCount, "should return 1 for 1 to the 100th power", new Test() {
      public boolean execute() {

        Power power = new Power();

        return power.compute(1,100) == 1;

      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    // instantiate the testing of each module by resetting count and printing title of module
    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Merge tests");

    // tests are in the form as shown
    assertTest(testCount, "should return [1, 2, 3, 4, 6, 7, 9] when merging [1, 4, 7] and [2, 3, 6, 9]", new Test() {
      public boolean execute() {

        Merge merge = new Merge();

        int[] input1 = {1,4,7};
        int[] input2 = {2,3,6,9};

        int[] output = merge.compute(input1, input2);

        return output.length == 7 &&
                  output[0] == 1 &&
                  output[1] == 2 &&
                  output[2] == 3 &&
                  output[3] == 4 &&
                  output[4] == 6 &&
                  output[5] == 7 &&
                  output[6] == 9;

      }
    });

    assertTest(testCount, "should handle inputs when left argument is empty array", new Test() {
      public boolean execute() {

        Merge merge = new Merge();

        int[] input1 = {};
        int[] input2 = {2,3,6,9};

        int[] output = merge.compute(input1, input2);

        return output.length == 4 &&
                  output[0] == 2 &&
                  output[1] == 3 &&
                  output[2] == 6 &&
                  output[3] == 9;

      }
    });

    assertTest(testCount, "should handle inputs when right argument is empty array", new Test() {
      public boolean execute() {

        Merge merge = new Merge();

        int[] input1 = {1,4,7};
        int[] input2 = {};

        int[] output = merge.compute(input1, input2);

        return output.length == 3 &&
                  output[0] == 1 &&
                  output[1] == 4 &&
                  output[2] == 7;

      }
    });

    assertTest(testCount, "should handle two empty arrays as inputs", new Test() {
      public boolean execute() {

        Merge merge = new Merge();

        int[] input1 = {};
        int[] input2 = {};

        int[] output = merge.compute(input1, input2);

        return output.length == 0;

      }
    });

    // print the result of tests passed for a module
    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


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
