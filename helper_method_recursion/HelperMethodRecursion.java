package com.sommerengineering.library.helper_method_recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelperMethodRecursion {

    public static void main(String[] args) {

        System.out.print(compute(10, 6));
    }

    ///////////////////////////////////////////

    /**
     * 2f. Given a base and an exponent, create a method to find the power using
     *     Helper Method Recursion
     *
     * Input:   Two Integers, base and exponent
     * Output:  Integer
     *
     * Example: Power.compute(3, 4) => 81
     */

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

