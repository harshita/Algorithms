import java.util.Arrays;

/**
 * @author: Harshita Karande
 * Radix sort is a stable sort, sorts in O(nk) where k is the number of digits.
 * It uses counting sort on each of its digits in order from MSB to LSB and uses the current sorted
 * sorted order when sorting on its next digit while going from MSB to LSB
 */
public class RadixSort {
    static int[] input = {326, 453, 608, 835, 751, 453, 435, 704, 835, 690};
    static int[] sortedInput = new int[input.length];
    static int[] counter = new int[10];
    public static void main(String args[]) {
        radixSort(3);
    }

    public static void radixSort(int k) {
        for (int i = k - 1; i >= 0; i--) {
             countingSort(i);
        }
        printSortedOutput();
    }

    public static void countingSort(int k) {
        int currentnumberatk = 0;
        for (int i = 0; i < input.length; i++) {
            currentnumberatk = Integer.toString(input[i]).charAt(k) - '0';
            counter[currentnumberatk]++;
        }
        for (int i = 1; i < 10; i++) {
            counter[i] += counter[i-1];
        }
        for (int i = input.length - 1; i >= 0; i-- ) {
            currentnumberatk = Integer.toString(input[i]).charAt(k) - '0';
            sortedInput[counter[currentnumberatk] - 1]  = input[i];
            counter[currentnumberatk]--;
        }
        input = sortedInput.clone();
        Arrays.fill(counter, 0);


    }

    public static void printSortedOutput() {
        for (int i = 0; i < input.length; i++) {
            System.out.println(sortedInput[i]);
        }
    }
}
