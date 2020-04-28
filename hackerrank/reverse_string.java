package com.sommerengineering.library.hackerrank;

class ResultReverse {

    /*
     * Complete the 'dnaComplement' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    private static String reverse;

    public static String dnaComplement(String s) {

        // this problem also has no time or space constraints

        // reverse the string using recursion
        helper(s);

        // swap characters linearly
        String output = "";
        for (int i = 0; i < reverse.length(); i ++) {

            // get the current character
            char current = reverse.charAt(i);

            // swap each pair
            switch (current) {
                case 'A' :
                    output += "T";
                    break;
                case 'T' :
                    output += "A";
                    break;
                case 'C' :
                    output += "G";
                    break;
                case 'G' :
                    output += "C";
            }

        }

        return output;
    }

    private static String helper(String s) {

        // base case: 1 element
        if (s.length() == 1) return s;

        // capture the last character of string, and send rest (substring) back through recursion
        int end = s.length() - 1;
        reverse += s.charAt(end) + helper(s.substring(0, end)); // end is exclusive on substring
        return reverse;
    }

}
