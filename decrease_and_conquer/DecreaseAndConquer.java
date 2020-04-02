package com.sommerengineering.library.decrease_and_conquer;

/*
You may have heard of a foundational algorithm technique called Divide and Conquer. In this section,
we will focus on its lesser known cousin Decrease and Conquer.

Lets clarify the difference now:

Divide and Conquer:

    divides a problem into two or more subproblems.
    the subproblems are solved independently
    (optionally) the results are combined back together.
    implemented with multiple recursion or a stack and while loop
    e.g., Quicksort, Mergesort, Strassen matrix multiplication

Decrease and Conquer:

    reduces a problem to a single smaller subproblem.
    the reduction can be by a constant value or a constant factor.
    implemented with a while loop (stack often is not necessary with a single subproblem)
    or single recursion
    e.g., Binary Search, Quickselect, Russian Peasant Multiplication

We will focus on decrease and conquer for now and introduce divide and conquer in a later section.
Lets cover few algorithms using decrease and conquer to reduce the problem by a constant or variable
factor.

Note: Decrease and conquer includes reduction by a constant value as well (subtract by a value),
however we will focus on reduction by a factor (division by a factor).

Example 1: Binary Search

Given a sorted array of unique integers, and a target value determine the index of a matching
value within the array. If there is not match, return -1.

Input: [1,3,4,5,6,7,8,10,11,13,15,17,20,22], 17
Output: 11

Binary search is a foundational search algorithm used to find the index of a value within a sorted
array. It converges at a solution in logarithmic time O(log(N)) which is quicker than performing a
linear search. Binary search uses a process of elimination to discard of the potential locations
where a value could be.

Dictionary Analogy

Think about how one searches for the definition of a word within an English dictionary. We can begin
a picking a page in the middle of the book. Then check whether the word is on the page. If it isn’t
there, the word has to be either before or after this page. We deduce from alphabetical order which
half to continue the search.

Next we split the appropriate section in half again and look for the word in the new page. We repeat
until either the current page has the target word, or we find that the word does not exist in the
dictionary.

Pseudocode

    1. Start with the full range of the array (0 to array length - 1).
    2. Check the value at the middle of that range.
    2. If mid matches target we return the mid index.
    3. If the mid is larger than target we can eliminate the right half.
    4. If the mid is less than target, we can eliminate the left half.
    5. Adjust the range depending on which half if still active.
    6. Repeat steps 2-5 until a match is found or the range is empty
    7. If the range is empty, return -1.

Example 2: Greatest Common Divisor (GCD)
Given two integers, find the greatest common divisor (GCD).

Input: 52, 78
Output: 26

In mathematics, the GCD of two integers is the largest positive integer that is a factor of both
integers. In the case both 52 and 78 are divisible by 26. Which also happens to be the largest common
factor as well.

Prime Factorization
One potential approach is to find all the prime factors of each number. In the example case above,
we get:

52 has prime factors [2, 2, 13]
78 has prime factors [2, 3, 13]

Multiply all the prime factors common to both integers. In this case, 2 and 13 are common therefore
2 x 13 = 26 is the GCD.

Finding all the prime factors for both input integers and then computing the product of all the
common factors can take more time than necessary. Lets explore a faster approach.

Euclid’s Algorithm (Decrease and Conquer)

Euclid’s algorithm uses decrease an conquer to converge at the GCD faster than the prime
factorization approach. The basis for Euclid’s algorithm is that the GCD of two numbers must
be a factor of its difference as well.

In the example above: 78 - 52 = 26, the GCD must be a factor of 26 as well. Another way to put
it is the GCD(78, 52) must also equal to the GCD(52, 26) and/or the GCD(78, 26).

Solving the smaller subproblem GCD(52, 26) we can apply the difference again 52 - 26 = 26 and
reduce the problem further to GCD(26,26). The GCD of two equal numbers that the number itself
so we get GCD(26,26) = 26 (since the GCD must be positive, if the equal pair is negative, take
the absolute value).

So instead of finding all the divisors or prime factors of two really large values, lets find
the difference between them to reduce the problem to smaller inputs. If we keep finding the
difference the problem reduces to the point where the inputs are much smaller.

Use Modulo instead of Subtract
Finding the difference is fine, but finding the modulo which gives the remainder is even faster.
This is because remainder is the result performing multiple subtractions in one shot. Lets see how
to approach the algorithm.

Pseudocode

Given integers A and B, where A > B, return its GCD.

    1. Save B as a temp variable.
    2. Set B equal to A % B.
    3. Set A equal to the temp.
    4. Repeat steps 1-3 until B equals 0.
    5. Return A.

Resources
To get a deeper understanding, check out a proof on Euclid’s Algorithm here by Khan Academy.

Refactoring
In some languages (Python, Ruby, Javascript ES6) swapping can be done a bit more clean, lets refactor
using an ES6 feature of JavaScript called destructuring assignment.

More Refactoring

Notice if we take the modulo A % B where A < B, we actually just get A back. For example 5 % 10 = 5.
This is because 10 goes into 5 zero times and we get a remainder of 5.

A further optimization is that we can just remove the initial swap out completely. Because the
logic inside the while loop takes care of the swap for us if A < B initially. Lets remove all the
comments out for clarity.

Challenge 1 : Number of Ones in a Sorted Bit Array

Given a sorted bit array (values of either 0 or 1), determine the number of 1’s in the array.

Perform this in O(log(N)) time complexity.

Input: [0,0,0,1,1,1,1,1,1,1,1]
Output: 8

*/

public class DecreaseAndConquer {

    public static void main(String[] args) {

        // clock execution time
        final long start = System.nanoTime();

        // Example 1: Binary Search using recursion
        int i = binarySearch(new int[]{1,3,4,5,6,7,8,10,11,13,15,17,20,22}, 17);
        System.out.println("binarySearch: " + i); // 11

        // Example 1: Binary Search using iteration
        i = binarySearchIteration(new int[]{1,3,4,5,6,7,8,10,11,13,15,17,20,22}, 17);
        System.out.println("binarySearchIteration: " + i); // 11

        // Example 2: Greatest Common Divisor (GCD) using recursion
        i = GCD(78, 52);
        System.out.println("GCD: " + i); // 26

        // Example 2: Greatest Common Divisor (GCD) using iteration
        i = GCDIteration(78, 52);
        System.out.println("GCDIteration: " + i); // 26

        // Challenge 1: Number of Ones is a Sorted Bit Array
        i = numberOfOnes(new int[]{0,0,0,1,1,1,1,1,1,1,1}); // 8
        System.out.println("NumberOfOnes: " + i);

        // clock execution time
        final long stop = System.nanoTime();
        System.out.println("\n Time elapsed " + (stop - start) * Math.pow(10, 9) + " seconds.");
    }

    // Example 1: recursive binary search uses O(log(n)) time
    private static int binarySearch(int[] arr, int target) {
        return binaryRecursion(arr, target, arr.length / 2, arr.length / 2);
    }

    // Example 1: recursive binary search uses O(log(n)) time
    private static int binaryRecursion(int[] arr, int target, int i, int range) {

        int current = arr[i];

        if (current == target) return i; // base case: target found

        else if (target < current) { // search left subarray
            if (i - range/2 < 0) return -1; // base case: array bounds exceeded
            return binaryRecursion(arr, target, i - range/2, range/2);
        }

        else { // search right subarray
            if (i + range/2 >= arr.length) return -1; // base case: array bounds exceeded
            return binaryRecursion(arr, target, i + range/2, range/2);
        }
    }

    // Example 1: iterative binary search uses O(log(n)) time
    private static int binarySearchIteration(int[] arr, int target) {

        int start = 0;
        int end = arr.length - 1;
        int mid;

        while (start <= end) {

            mid = (start + end)/2;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) start = mid + 1;
            else end = mid - 1;
        }

        return -1;
    }

    // Example 2: GCD Euclid's Algorithm using recursion
    private static int GCD(int a, int b) {

        if (b == 0) return a; // base case: b = 0
        return GCD(b, a - b);
    }

    // Example 2: GCD Euclid's Algorithm using iteration
    private static int GCDIteration(int a, int b) {
        int temp;
        while (b > 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Challenge 1: Number of Ones is a Sorted Bit Array at O(log(n)) complexity
    private static int numberOfOnes(int[] arr) {

        // binary search algorithm to find break point of ..., 0, 1, ...

        int start = 0;
        int end = arr.length - 1;
        int mid;

        while (start <= end) {

            mid = (start + end)/2;

            if (arr[mid] == 0) {

                // check if we are at the break point ..., 0, 1, ...
                if (arr[mid+1] == 1) return arr.length - (mid + 1);
                else start = mid + 1;
            }
            else {

                // check if we are at the break point ..., 0, 1, ...
                if (arr[mid-1] == 0)  return arr.length - mid;
                else end = mid - 1;
            }
        }

        return -1;
    }
}
