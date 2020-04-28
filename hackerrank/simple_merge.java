class Result {

    /*
     * Complete the 'mergeArrays' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    public static List<Integer> mergeArrays(List<Integer> a, List<Integer> b) {

        // no time or space constraints, solve using linear comparison

        int i1 = 0;
        int i2 = 0;

        List<Integer> output = new ArrayList<>();

        // loop until one of the input arrays is exhausted
        while (i1 < a.size() && i2 < b.size()) {

            // a < b
            if (a.get(i1) < b.get(i2)) {
                output.add(a.get(i1));
                i1 ++;

            // b < a
            } else {
                output.add(b.get(i2));
                i2 ++;
            }
        }

        // at this point one of the arrays is empty, the other has at least one element
        while (i1 < a.size()) {
            output.add(a.get(i1));
            i1 ++;
        }

        while (i2 < b.size()) {
            output.add(b.get(i2));
            i2 ++;
        }

        // return the sorted output
        return output;
    }

}
