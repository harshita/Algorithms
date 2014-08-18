/**
 * User: Harshita Karande
 */

import java.util.Arrays;

/**
 * This algorithm shuffles N elements uniformly in O(N) time
 */
public class KnuthShuffle <T> {
    private T[] elements;
    public static void main(String args[]) {
        Integer[] elementsToBeShuffled = new Integer[6];
        for (int i = 1; i < 7; i++) {
            elementsToBeShuffled[i-1] = i;
        }
        KnuthShuffle<Integer> knuthShuffle = new KnuthShuffle<Integer>(elementsToBeShuffled);
        System.out.println("Original sequence");
        knuthShuffle.printElements();
        //shuffling here
        knuthShuffle.knuthShuffle();

        System.out.println("Shuffled sequence");
        knuthShuffle.printElements();




    }

    public KnuthShuffle (T[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public void printElements() {
        for (int i = 0; i < this.elements.length; i++) {
            System.out.print("\t"+this.elements[i]);
        }
        System.out.println();

    }


    public void knuthShuffle() {
        int N = this.elements.length;
        for (int i = 0; i < N; i++) {
            //for any i, selects a random value in range i to N
            int randomChoice = i + (int) (Math.random() * (N-i));
            T swap = this.elements[randomChoice];
            this.elements[randomChoice] = elements[i];
            this.elements[i] = swap;
        }

    }
}
