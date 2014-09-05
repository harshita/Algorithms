/**
 * @author: Harshita Karande
 * Counting sort sorts n numbers in O(n) time.
 * It is a stable sort. Works better for smaller values of n
 */
public class CountingSort {
    private static int[] input = {2, 4, 7, 9, 1, 3, 7, 9, 4, 13, 0, 5, 6, 5, 8, 0, 39, 12};
    private static int[] counter;
    private static int[] sortedArray;
    public static void main(String args[]) {
        countingSort();
        printElements();
    }

    public static void countingSort() {
        int max = Integer.MIN_VALUE;
        //get the max so that counter array can be initialized upto max length
        for (int i = 0; i < input.length; i++) {
            if (input[i] > max)
                max = input[i];
        }
        counter = new int[max+1];
        sortedArray = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            counter[input[i]]++;
        }
        for (int i = 1; i < counter.length; i++) {
            counter[i] += counter[i-1];
        }
        for (int i = input.length - 1; i >= 0; i--) {
            sortedArray[counter[input[i]] - 1] = input[i];
            counter[input[i]]--;
        }

    }

    public static void printElements() {
        for (int i = 0; i < input.length; i++) {
            System.out.println(sortedArray[i]);
        }
    }
}
