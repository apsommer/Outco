package com.sommerengineering.library.complexity;

/*
 *  Homework 01 - Complexity
 *
 *  For the following functions, write the expected Time and Auxiliary Space
 *  Complexity using what you know about nested loops, hash table look-ups and
 *  the runtime of built in functions.
 *
 *  NOTE: You don't need to code to anything for these problems, just write
 *  what you the complexity to be using big-O notation
 **/



import java.util.*;

class Main {
  public static void main(String[] args) {
  }

  /*  Order of Magnitude

  Reduce the following into it's Big-O order of magnitude.

  1. 5 + N                    Answer:
  2. N + N^2                  Answer:
  3. 15N + 13N                Answer:
  4. 10000                    Answer:
  5. log(N) + 1               Answer:
  6. log(N) * 3 + 14N + 3     Answer:
  7. Nlog(N) + 3N^2           Answer:
  8. N^3 + log(N^4)           Answer:
  9. N! + 180000N^2           Answer:
  10. 15002^N                 Answer:
  */


  /*  Index Of

  Given an array of integers and a target value, return the index of the first
  element that matches the target value. If there are no matches, return -1.

  Parameters
  Input: arr {Array of Integers}
  Input: target {Integer}
  Output: {Integer}

  Examples
  [1, 2, 3, 4, 5, 6], 5 --> 4
  [9, 83, 74], 8 --> -1
  [6, 4, 7, 9, 7, 8, 2, 4, 3], 7 --> 2

  Time Complexity:
  Auxiliary Space Complexity:

  */
  public static int indexOf(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
      if(arr[i] == target) {
        return i;
      }
    }
    return -1;
  }


  /*  Evens

  Given an array of integers, return an array of only the even values.

  Parameters
  Input: arr {Array of Integers}
  Output: {ArrayList of Integers}

  Examples
  [1, 2, 3, 4, 5, 6] --> [2, 4, 6]
  [9, 83, 74] --> [74]
  [6, 4, 7, 9, 7, 8, 2, 4, 3] --> [6, 4, 8, 2, 4]

  Time Complexity:
  Auxiliary Space Complexity:

  */
  public static ArrayList evens(int [] arr) {
    ArrayList<Integer> results = new ArrayList<>();
    for(int i = 0; i < arr.length; i++) {
      if(arr[i] % 2 == 0) {
        results.add(arr[i]);
      }
    }
    return results;
  }


  /*  Split

  Given a string of characters, return an array with each character separated
  into its own separate item.

  Parameters
  Input: str {String}
  Output: {Array of Characters}

  Examples
  'hello' --> ['h', 'e', 'l', 'l', 'o']
  'foo' --> ['f', 'o'. 'o']

  Time Complexity:
  Auxiliary Space Complexity:

  */
  public static ArrayList split(String str) {
    ArrayList<Character> results = new ArrayList<>();
    for(int i = 0; i < str.length(); i++) {
      results.add(str.charAt(i));
    }
    return results;
  }


  /*  Sum

  Given an array of integers, return the sum of all the integers.

  Parameters
  Input: arr {Array of Integers}
  Output: {Integer}

  Examples
  [1, 2, 3, 4, 5] --> 15
  [0, 1, -1] --> 0
  [] --> 0

  Time Complexity:
  Auxiliary Space Complexity:

  */
  public static int sum(int[] arr) {
    int total = 0;
    for(int i = 0; i < arr.length; i++) {
      total = total + arr[i];
    }
    return total;
  }


  /*  Merge Arrays

  Given two sorted arrays of integers, return a merged sorted array of both inputs.

  Parameters
  Input: arr1 {Array of Integers}
  Input: arr2 {Array of Integers}
  Output: {Array of Integers}

  Examples
  [1, 3, 9], [2, 4, 8] --> [1, 2, 3, 4, 8, 9]
  [12, 25, 40], [20, 37, 45] --> [12, 20, 25, 37, 40, 45]
  [10, 13, 24], [12, 35] --> [10, 12, 13, 24, 35]

  Time Complexity:
  Auxiliary Space Complexity:

  */
  public static int[] merge(int[] arr1, int[] arr2) {
    int totalElements = arr1.length + arr2.length;
    int [] result = new int[arr1.length + arr2.length];
    int i = 0;
    int j = 0;
    int k = 0;
    while(i + j < totalElements) {
      if(j >= arr2.length || (i < arr1.length && arr1[i] <= arr2[j])) {
        result[k] = arr1[i];
        i++;
        k++;
      } else {
        result[k] = arr2[j];
        j++;
        k++;
      }
    }
    return result;
  }


  /*  Counting Sort

  Given an array and a range (min and max), use counting sort to sort the input array.

  Parameters
  Input: arr {Array of Integers}
  Input: min {Integer}
  Input: max {Integer}
  Output: {Array of Integers}

  Examples
  [5, 3, 1, 8, 9] --> [1, 3, 5, 8, 9]
  [8, 1, 3, 4, 6, 9] --> [1, 3, 4, 6, 8, 9]
  [4, 0, 1, 5, 6] --> [0, 1, 4, 5, 6]

  Time Complexity:
  Auxiliary Space Complexity:

  */
   public static int[] countingSort (int[] arr, int min, int max) {
    int[] count = new int[max - min + 1];
    Arrays.fill(count, 0);
    for(int i = 0; i < arr.length; i++) {
      count[arr[i] - min] = count[arr[i] - min] + 1;
    }
    int index = 0;
    for(int i = min; i < max - min + 1; i++) {
      while(count[i - min] > 0) {
        arr[index] = i;
        index += 1;
        count[i - min] = count[i - min] - 1;
      }
    }
    return arr;
  }


  /*  Binary Search

  Given a sorted array and a target value, use binary search to return the index of the target in the input array.
  Return -1 if no such target is found.

  Parameters
  Input: arr {Array of Integers}
  Input: val {Integer}
  Output: {Integer}

  Examples
  [1, 3, 4, 5, 8, 9], 5 --> 3
  [5, 7, 10, 12, 14], 7 --> 1
  [2, 4, 8, 9, 15], 3 --> -1

  Time Complexity:
  Auxiliary Space Complexity:

  */
  public static int binarySearch(int[] arr, int val) {
    int low = 0;
    int high = arr.length - 1;
    int mid;
    while(low <= high) {
      mid = (low + high) / 2;
      if(arr[mid] > val) {
        high = mid - 1;
      }
      else if (arr[mid] < val) {
        low = mid + 1;
      }
      else {
        return mid;
      }
    }
    return -1;
  }


  /*  Factorial

  Given an input integer n, return the n factorial value.

  Parameters
  Input: n {Integer}
  Output: {Integer}

  Examples
  5  --> 120 (5 * 4 * 3 * 2 * 1)
  1 --> 1 (1)
  9 --> 362880 (9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1)

  Time Complexity:
  Auxiliary Space Complexity:

  */
  public static int factorial(int n) {
    if(n <= 1) {
      return 1;
    }
    return n * factorial(n - 1);
  }


 /*  First Times Last
  *
  *  Time Complexity:
  *  Auxiliary Space Complexity:
  */
  public static int firstTimesLast(int[] arr) {
    int result = 0;
    if (arr.length < 2) {
      return result;
    } else {
      result = arr[0] * arr[arr.length-1];
      return result;
    }
  }


  /*  Most Frequent Occurrence
   *
   *  Time Complexity:
   *  Auxiliary Space Complexity:
   */
  public static char mostFrequentOccurrence(String str) {
    String lowerString = str.toLowerCase();
    Map<Character, Integer> letters = new HashMap<Character, Integer>();
    char mostFrequent = str.charAt(0);
    char currentChar;
    int maxCount = 0;

    for (int i = 0; i < lowerString.length(); i++) {
      currentChar = lowerString.charAt(i);
      if (letters.get(currentChar) != null) {
        letters.put(currentChar, letters.get(currentChar)+1);
      } else {
        letters.put(currentChar, 1);
      }
    }

    for (Character key : letters.keySet()) {
      if (letters.get(key) > maxCount) {
        mostFrequent = key;
        maxCount = letters.get(key);
      }
    }

    return mostFrequent;
  }


  /* Print Unordered Pairs
   *
   *  Time Complexity:
   *  Auxiliary Space Complexity:
   */
  public static void printUnorderedPairs(int[] arr) {
   for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        System.out.println(arr[i] + "," + arr[j]);
      }
    }
  }


  /* Sorted Array Search
   *
   *  Time Complexity:
   *  Auxiliary Space Complexity:
   */
  public static boolean sortedArraySearch(int[] sortedArray, int target) {
    int start = 0;
    int end = sortedArray.length - 1;
    int mid;
    while(start <= end){
      mid = (start + end) / 2;
      if(sortedArray[mid] == target){
        return true;
      }
      if(sortedArray[mid] > target){
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }

    return false;
  }


  /**
   *  Problem 5
   *  Time Complexity:
   *  Auxiliary Space Complexity:
   */
  public static int[][] makeCombinedMatrix(int[] arr1, int[] arr2) {
    int[][] result = new int[arr1.length][arr2.length];

    for (int i = 0; i < arr1.length; i++) {
      for (int j = 0; j < arr2.length; j++) {
        result[i][j] = arr1[i] + arr2[j];
      }
    }

    return result;
  }

 /**
  * Problem 6:
  *
  *  Time Complexity:
  *  Auxiliary Space Complexity:
  */

  public static void isThereCat(Map<String, String> map) {
    while(map.get("cat") != null) {
      System.out.println("There is a cat!");
      map.remove("cat");
    }
    System.out.println("There is no cat!");
  }


 /**
  * Problem 7:
  *
  *  Time Complexity:
  *  Auxiliary Space Complexity:
  */
  public static int[] powerOfThrees(int n) {
    int[] result = new int[3];
    int count = 1;
    int temp = 1;

    while(count <= 3) {
      temp *= n;
      result[count-1] = temp;
      count++;
    }

    return result;
  }


  /**
   * Problem 8:
   *
   *  Time Complexity:
   *  Auxiliary Space Complexity:
   */
  public static boolean findDuplicate(int[] arr) {
    int len = arr.length;
    int currItem;

    for (int i = 0; i < len; i++) {
      currItem = arr[i];

      for (int j = 0; j < len; j++) {
        if (j != i) {
          if (currItem == arr[j]) {
            return true;
          }
        }
      }
    }

    return false;
  }


  /**
   * Problem 9:
   *
   *  Time Complexity:
   *  Auxiliary Space Complexity:
   */
  public static ArrayList<Integer> intersectionPoints(int[] arr1, int[] arr2) {
    ArrayList<Integer> results = new ArrayList<Integer>();

    for (int i = 0; i < arr1.length; i++) {
      for (int j = 0; j < arr2.length; j++) {
        if (arr1[i] == arr2[j]) {
          results.add(arr1[i]);
        }
      }
    }

    return results;
  }


  /**
   *  Problem 10:
   *
   *  Time Complexity:
   *  Auxiliary Space Complexity:
   */
  public static int nthFibonacci(int n) {
    if(n < 2){ return n; }

    int[] result = new int[n+1];
    result[0] = 0;
    result[1] = 1;

    System.out.println(result);

    for(int i = 2; i < n+1; i++) {
      result[i] = result[i-1] + result[i-2];
    }
    return result[n];
  }


  /**
   *  Extra Credit 1:
   *
   *  Problem: Refactor findDuplicate such that it now finds and returns all
   *           repeating elements in O(N) time complexity.
   *
   *  Input: arr {Array}
   *  Output: {ArrayList}
   *
   *  Auxiliary Space Complexity:
   */
  public static ArrayList<Integer> findAllDuplicates(int[] arr) {
    // YOUR WORK HERE
    return null;

  }
}


/* Nth Fibonacci
 *
 *  Time Complexity:
 *  Auxiliary Space Complexity:
 */
class NthFib {

  private static HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
  private static int result;

  public static int compute(int n) {
    result = 0;

    result = searchFib(n);
    return result;
  }

  private static int searchFib(int index) {
    int solution;

    if (index < 2) {
      return index;
    }

    if (cache.get(index) != null) {
      return cache.get(index);
    }

    solution = searchFib(index-1) + searchFib(index-2);
    cache.put(index, solution);
    return solution;
  }

}
