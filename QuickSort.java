import java.util.Arrays;

/**
 * User: Harshita Karande
 * O(n^2) worst case but O(n lg n) average case sorting algorithm
 * It uses technique divide and conquer and chooses the last element in the partition as the pivot
 * But this can also be randomized by selecting an element at random and then swapping with last element
 * within the partition function.
 */
public class QuickSort {
    private int[] numbers;

    public QuickSort(int[] numbers) {
        this.numbers = Arrays.copyOf(numbers, numbers.length);
    }
    public static void main(String args[]) {
          QuickSort qs = new QuickSort(new int[] {23, 46, 17, 90, 21, 3, 7, 34});
          qs.quickSort(0, qs.numbers.length-1);
          qs.printSortedResult();
    }

    public void printSortedResult() {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
    public void quickSort(int p, int r) {
      int pivotIndex = partition(p, r);
      if(pivotIndex - 1 >= 0 && pivotIndex - 1 > p)
        quickSort(p, pivotIndex - 1);
      if(pivotIndex + 1 <= r && pivotIndex + 1 < r)
        quickSort(pivotIndex + 1, r);
    }

    public int partition(int p, int q) {
        int randomElement = numbers[q];
        int i = p - 1;
        for (int j = p; j < q ; j++) {
           if (numbers[j] < randomElement) {
               i = i+1;
               int temp = numbers[i];
               numbers[i] = numbers[j];
               numbers[j] = temp;
           }
        }
        numbers[q] = numbers[i+1];
        numbers[i+1] = randomElement;
        return i+1;
    }
}
