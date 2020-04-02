package com.sommerengineering.library.helper_method_recursion;

public class HelperMethodRecursion {

    public static void main(String[] args) {

        String input = "";
        System.out.println(compute(input));
    }

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
