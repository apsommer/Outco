package com.sommerengineering.library.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * Recognize that a problem can be broken down into subproblem, and that these subproblems repeat themselves.
 *
 * Memoization: When the subproblem overlap (repeat), Memoization is a potential solution technique. This is
 * a top down approach, typically using recursion, and a cache.
 *
 * Tabulation: The solutions to subproblems build upon one another, this is a bottom up approach. Uses an
 * array or com.sommerengineering.library.matrix to track solutions to subproblems, adding them up to a final answer.
 *
 * **/

public class DynamicProgramming {

    public static void main(String[] args) {

        System.out.println(coinSumMemo(new int[] {1,2,3}, 4));

        for (String key : cache.keySet()) {
            System.out.println(key);
        }
    }

    ////////////////////////////////////////////////////////////////////

//    static int count;
//
//    public static int coinSum(int[] coins, int total) {
//
//        count = 0;
//        helper(coins, total, 0);
//        return count;
//    }
//
//    private static int helper(int[] coins, int total, int index) {
//
//        if (total < 0) return 0;
//        if (total == 0) return 1;
//
//        count = 0; // reset counter because we are gathering "everything below this level"
//        for (int i = index; i < coins.length; i ++) {
//
//            int coin = coins[i];
//            count += helper(coins, total - coin, i);
//
//            // passing third parameter (index) is the key step to prevent duplicates, by logic
//            // "only use this coin and the ones that follow it"
//        }
//        return count;
//    }

    static int count;
    static Map<String, Integer> cache;

    public static int coinSumMemo(int[] coins, int total) {

        cache = new HashMap<>();
        count = 0;
        helper(coins, total, 0);
        return count;
    }

    private static int helper(int[] coins, int total, int index) {

        if (cache.containsKey(total + "_" + index)) return cache.get(total + "_" + index);
        if (total < 0) return 0;
        if (total == 0) return 1;

        count = 0; // reset counter because we are gathering "everything below this level"
        for (int i = index; i < coins.length; i ++) {

            int coin = coins[i];
            count += helper(coins, total - coin, i);

            // passing third parameter (index) is the key step to prevent duplicates, by logic
            // "only use this coin and the ones that follow it"
        }

        cache.put(total + "_" + index, count);
        return count;
    }
}
