package com.sommerengineering.library.decrease_and_conquer;

/*
 * Homework 03 - Decrease and Conquer
 * Utilize the decrease and conquer pattern to solve these problems.
 */

import java.util.Arrays;

class Problems {

  /*
   *
   *  Number of Ones
   *
   * *Given a sorted bit array (values of either 0 or 1), determine the number of 1's in the array.*
   *
   * **Parameters**
   * Input: arr {Array of Integers}
   * Output: {Integer}
   *
   * **Constraints**
   * Time: O(logN)
   * Space: O(1)
   *
   * **Examples**
   * `[0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1] --> 8`
   * `[0, 0, 0] --> 0`
   * `[0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1] --> 7`
   */

  public static int numberOfOnes(int[] arr) {

    // binary search algorithm to find break point of [ ..., 0, 1, ... ]

    int start = 0;
    int end = arr.length - 1;
    int mid;

    while (start <= end) {

      mid = (start + end) / 2;

      // move to right half
      if (arr[mid] == 0) {

        // catch array of all zeros
        if (mid + 1 == arr.length) return 0;

        // check if we are at the break point ..., 0, 1, ...
        if (arr[mid + 1] == 1) return arr.length - (mid + 1);
        else start = mid + 1;

        // move to left half
      } else {

        // catch array of all ones
        if (mid - 1 == -1) return arr.length;

        // check if we are at the break point ..., 0, 1, ...
        if (arr[mid - 1] == 0) return arr.length - mid;
        else end = mid - 1;
      }
    }

    // this line will never be reached
    return -1;
  }

  /*
   * Closest Value
   *
   * Given a sorted bit array of integers, and a target value, find the number in the array that is closest to the target.*
   *
   * **Parameters**
   * Input: arr {Array of Integers}
   * Input: target {Integer}
   * Output: {Integer}
   *
   * **Constraints**
   * If there are two numbers tied for the closest value, return the lowest value.
   *
   * Time: O(logN)
   * Space: O(1)
   *
   * **Examples**
   * `[1, 2, 3, 5, 5, 7, 9, 10, 11], 6 --> 5`
   * `[1, 2, 3], 8 --> 3`
   * `[1, 10, 22, 59, 67, 72, 100], 70 --> 72`
   */

  public static int closestValue(int[] arr, int target) {

    // this is standard binary search with an extra check on moving left

    int start = 0;
    int end = arr.length - 1;
    int mid;

    while (end - start != 0) {

      // calculate middle index using integer division
      mid = (end + start) / 2;

      // if target found return value
      if (target == arr[mid]) return arr[mid];

      // if the target is less than the middle AND there are at least 3 element under consideration AND the difference
      // between the target and mid-1 and mid+1 elements are equal (meaning the target is exactly between), then move left
      if (target < arr[mid] || (end - start) > 2 && target - arr[mid - 1] == arr[mid + 1] - target) {
        end = mid - 1;
      }

      // move right
      else start = mid + 1;

    }

    // this line is only reached if we have reduced down to a single element, both start and end are the same index
    return arr[start];
  }


  /*
   * Square Root
   *
   * Given an positive integer, find the square root.*
   *
   * **Parameters**
   * Input: value {Double}
   * Output: {Double}
   *
   * **Constraints**
   * Do not use a native built in method.
   * Ensure the result is accurate to 6 decimal places (0.000001)
   *
   * Time: O(logN)
   * Space: O(1)
   *
   * **Examples**
   * `4 --> 2.0`
   * `98 --> 9.899495`
   * `14856 --> 121.885192
   */

  // member variables for square root binary search
  static Double target;
  static int precision;

  // time constraint of log(N) implies binary search!
  public static Double squareRoot(Double n) {

    // save off n as a global variable
    target = n;
    precision = 6;

    // kick off recursion using 6 decimal precision
    return rootHelper(0d, n);
  }

  private static Double rootHelper(Double start, Double end) {

    // calculate a midpoint
    Double mid = (end + start) / 2;

    // base case: precision achieved
    // if the difference between the target value, and the square of this mid, is within the defined precision ...
    if (Math.abs(target - (mid * mid)) < Math.pow(10, -precision)) {

      // dirty trick here that breaks down on edge cases
      // multiply the mid (9.82348329495968) by 10^precision, round it, divide by 10^precision
      return Math.round(mid * Math.pow(10, precision)) / Math.pow(10, precision);
    }

    // move left
    if ((mid * mid) > target) {
      return rootHelper(start, mid);

      // move right
    } else {
      return rootHelper(mid, end);
    }
  }

  // achieves solution in ~ O(N) time
  public static Double squareRootBruteForce(Double n) {

    Double total = 1d;
    Double sqrt = 1d;
    Double delta = 1d;

    // six decimal places
    for (int digit = 0; digit < 6; digit++) {

      // increase the square until the input is exceeded
      while (total < n) {

        total = sqrt * sqrt;
        sqrt += delta;
      }

      // subtract off the last delta added
      sqrt -= delta;

      // move to the next decimal point
      delta = delta / 10;
      total = 0d;
    }

    return sqrt;
  }


  /*
   * Greater Values

   * *Given a sorted array of integers, and a target value return the number of values greater the target.*
   *
   * **Parameters**
   * Input: arr {Array of Integers}
   * Input: target {Integer}
   * Output: {Integer}
   *
   * **Constraints**
   *
   * Time: O(logN)
   * Space: O(1)
   *
   * **Examples**
   * `[1, 2, 3, 5, 5, 7, 9, 10, 11], 5 --> 4`
   * `[1, 2, 3], 4 --> 0`
   * `[1, 10, 22, 59, 67, 72, 100], 13 --> 5`
   *
   */

  // another binary search
  public static int greaterValues(int[] arr, int target) {

    // check target against lower bound of the sorted array
    if (target >= arr[arr.length - 1]) return 0;

    // define some index pointers
    int start = 0;
    int end = arr.length - 1;
    int mid;

    // standard binary search
    while (start != end) {

      // calculate a middle index using integer division
      mid = (start + end) / 2;

      // move left
      if (target < arr[mid]) {
        end = mid - 1;

        // move right
        // intentionally includes target == arr[mid] to handle duplicates in the sorted input
      } else {
        start = mid + 1;
      }
    }

    // if this line is reached then start index = end index, and this element is the closest value to the target
    return arr.length - start;
  }


  /*
   * Sorted and Rotated Array [Extra Credit]
   *
   * *Given a array that is sorted and rotated, find out if a target value exists in the array.*
   *
   * An sorted array is rotated by taking an unknown amount of values from the beginning and placing it at the end.
   *
   * `[3, 4, 5, 1, 2]` is rotated left by 2.
   * `[99, 14, 25, 78, 93]` is rotated to the right by 1.
   *
   * **Parameters**
   * Input: arr {Array of Integers}
   * Input: target {Integer}
   * Output: {Boolean}
   *
   * **Constraints**
   * Time: O(logN)
   * Space: O(N)
   *
   * **Examples**
   * `[35, 46, 79, 102, 1, 14, 29, 31], 46 --> true`
   * `[35, 46, 79, 102, 1, 14, 29, 31], 47 --> false`
   * `[7, 8, 9, 10, 1, 2, 3, 4, 5, 6], 9 --> true`
   */


  public static boolean rotatedArraySearch(int[] arr, int target) {

    // this is a binary search with extra conditions to consider on the base array

    int start = 0;
    int end = arr.length - 1;
    int mid;

    while (start != end) {

      // calculate the middle index for this section
      mid = (start + end) / 2;

      if (arr[mid] == target) return true;

      // determine which side has the discontinuity (step)
      // left side has a clean ascending sort
      if (arr[start] < arr[mid]) {

        // move right
        if (target > arr[mid]) {
          start = mid + 1;

          // move left
        } else {
          end = mid - 1;
        }

        // right side has a clean ascending sort
      } else {

        // move left
        if (target > arr[end]) {
          end = mid - 1;

          // move right
        } else {
          start = mid + 1;
        }
      }

    }

    // one last check, we are down to one element, if this is not the target then it does not exist in the input array
    return arr[start] == target;
  }

  // binary search using pure recursion
  private static boolean binarySearch(int[] nums, int start, int end, int target) {

    // calculate middle index for this range
    int mid = (start + end) / 2;

    // base case: target found
    if (nums[mid] == target) return true;

    // base case: elements exhausted
    if (start == end) return false;

    // move left
    if (target < nums[mid]) return binarySearch(nums, start, mid - 1, target);

      // move right
    else return binarySearch(nums, mid + 1, end, target);

  }


  /*
   * Multiplication Using Russian Peasant [Extra Credit]
   *
   * *Given two positive integers, return its product using Russian Peasant method of multiplication*
   *
   * Read up on how to apply the Russian Peasant method [here](https: *en.wikipedia.org/wiki/Ancient_Egyptian_multiplication).
   * It is also referred to as the Egyptian multiplication.
   *
   * **Parameters**
   * Input: a {Integer}
   * Input: b {Integer}
   * Output: {Integer}
   *
   * **Constraints**
   * Assume a <= b, and the value of a is N.
   *
   * Time: O(logN)
   * Space: O(1)
   *
   * **Examples**
   * `734, 487 --> 357458`
   * `846, 908--> 768168`
   */

  public static void main(String[] args) {

    System.out.println(Problems.multiplicationRussianPeasant(13 , 238)); // 3094

    String[] apples = Arrays.copyOfRange(args, 0, 2);

    for (String apple : apples) {
      System.out.println(apple);
    }

  }

  // member variable to hold sum of russian peasant recursion
  // "halving" and "doubling" nature of this problem leads to O(logN) time
  private static int ans;

  public static int multiplicationRussianPeasant(int a, int b) {

    // base case: a < 1
    if (a <= 1) {
      ans += b;
      return ans;
    }

    // only include this multiplicand if odd
    if (a % 2 != 0) {
      ans += b;
    }

    // move to next level of recursion with updated multiplicands
    return multiplicationRussianPeasant(a/2, b*2);

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
    System.out.println("Number Of Ones Tests");

    // tests are in the form as shown
    assertTest(testCount, "should return correct number of ones for array with zeroes and ones", new Test() {
      public boolean execute() {
        int output = Problems.numberOfOnes(new int[]{0, 0, 0, 1, 1, 1});
        return output == 3;
      }
    });

    assertTest(testCount, "should return correct number of ones for array with all zeroes", new Test() {
      public boolean execute() {
        int output = Problems.numberOfOnes(new int[]{0, 0, 0, 0, 0, 0});
        return output == 0;
      }
    });

    assertTest(testCount, "should return correct number of ones for array with all ones", new Test() {
      public boolean execute() {
        int output = Problems.numberOfOnes(new int[]{1, 1, 1, 1, 1});
        return output == 5;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Closest Value Tests");

    assertTest(testCount, "should return correct closest value for number in the middle range", new Test() {
      public boolean execute() {
        int output = Problems.closestValue(new int[]{1, 2, 3, 5, 5, 7, 9, 10, 11}, 6);
        return output == 5;
      }
    });

    assertTest(testCount, "should return closest value for highest number", new Test() {
      public boolean execute() {
        int output = Problems.closestValue(new int[]{1, 2, 3}, 8);
        return output == 3;
      }
    });

    assertTest(testCount, "should return closest value for lowest number", new Test() {
      public boolean execute() {
        int output = Problems.closestValue(new int[]{-2, -1, 0}, -5);
        return output == -2;
      }
    });


    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");

    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Square Root Tests");

    assertTest(testCount, "should return correct square root for number < 10", new Test() {
      public boolean execute() {
        Double output = Problems.squareRoot(4.0);
        return output == 2.0;
      }
    });

    assertTest(testCount, "should return correct square root for number between 10 and 100", new Test() {
      public boolean execute() {
        Double output = Problems.squareRoot(98.0);
        return output == 9.899495;
      }
    });

    assertTest(testCount, "should return correct square root for number over 10,000", new Test() {
      public boolean execute() {
        Double output = Problems.squareRoot(14856.0);
        return output == 121.885192;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");



    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Greater Values Tests");

    assertTest(testCount, "should return greater values for number in the middle of the array", new Test() {
      public boolean execute() {
        int output = Problems.greaterValues(new int[] {1, 2, 3, 5, 5, 7, 9, 10, 11}, 5);
        return output == 4;
      }
    });

    assertTest(testCount, "should return 0 for number greater than largest in the array", new Test() {
      public boolean execute() {
        int output = Problems.greaterValues(new int[] {1, 2, 3}, 4);
        return output == 0;
      }
    });

    assertTest(testCount, "should return length of array for number less than least in the array", new Test() {
      public boolean execute() {
        int output = Problems.greaterValues(new int[] {1, 10, 22, 59, 67, 72, 100}, -2);
        return output == 7;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");



    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Rotated Sorted Array Tests");

    assertTest(testCount, "returns true when target is in the array", new Test() {
      public boolean execute() {
        boolean output = Problems.rotatedArraySearch(new int[] {35, 46, 79, 102, 1, 14, 29, 31}, 46);
        return output == true;
      }
    });

    assertTest(testCount, "returns false when target is not in the array", new Test() {
      public boolean execute() {
        boolean output = Problems.rotatedArraySearch(new int[] {35, 46, 79, 102, 1, 14, 29, 31}, 47);
        return output == false;
      }
    });

    assertTest(testCount, "returns true when target is the first number in the array", new Test() {
      public boolean execute() {
        boolean output = Problems.rotatedArraySearch(new int[] {7, 8, 9, 10, 1, 2, 3, 4, 5, 6}, 7);
        return output == true;
      }
    });

    assertTest(testCount, "returns true when target is the last number in the array", new Test() {
      public boolean execute() {
        boolean output = Problems.rotatedArraySearch(new int[] {7, 8, 9, 10, 1, 2, 3, 4, 5, 6}, 6);
        return output == true;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");



    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Multiplication Russian Tests");

    assertTest(testCount, "returns correct value for two integers", new Test() {
      public boolean execute() {
        int output = Problems.multiplicationRussianPeasant(734, 487);
        return output == 357458;
      }
    });

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
