/*
Given a string as input find its powerset.
Powerset means every possible combination of characters, in their original order.

abc -> abc, ab, ac, bc, a, b, c

*/


class Result {

    /*
     * Complete the 'PowerSet' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING inputStr as parameter.
     */

    static Set<String> set;

    public static List<String> PowerSet(String inputStr) {

        set = new HashSet<>();
        set.add(inputStr);

        helper(inputStr);


        // convert to list and sort
        List<String> output = new ArrayList<>(set);
        Collections.sort(output); // in-place
        return output;
    }

    private static void helper(String s) {

        // base case: single element
        if (s.length() < 0) return;

        // add this combination to the set
        set.add(s);

        for (int i = 0; i < s.length(); i ++) {

            // capture single element

            // create left and right partitions around single character
            String left = s.substring(0, i);
            String right = s.substring(i+1, s.length());

            // recurse on both partitions
            helper(left + right);
        }
    }

}
