package Util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Arrays;

public class ArrayCreator {
    public static int[] createArray(){
        int[] arr = new int[100];

        for (int i = 0; i < 100; i++) {
            arr[i] = i+5;
        }
        return arr;
    }

    public static int[] shuffleArray(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int first = (int) (Math.random() * 100);
            int second = (int) (Math.random() * 100);
            int tmp = array[first];
            array[first] = array[second];
            array[second] = tmp;
        }

        return array;
    }

    public static Queue<Action> bubbleSorting(int[] array) {
        Queue<Action> actions = new LinkedList<>();
        Action action = new Action();
        long start = System.nanoTime();
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j+1]) {
                    swap(array, j, j+1);
                    action.addPair(new Pair(j, j+1));
                }
            }
        }
        System.out.println("Sorted Bubble " + Arrays.toString(array));
        action.setDuration(System.nanoTime() - start);
        actions.add(action);
        return actions;
    }

    public static Queue<Action> selectionSorting(int[] array) {
        Queue<Action> actions = new LinkedList<>();
        Action action = new Action();
        long start = System.nanoTime();

        for (int i = 0; i < array.length-1; i++) {
            int indexMinimalValue = i+1;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[indexMinimalValue]) {
                    indexMinimalValue = j;
                }
            }
            if (array[indexMinimalValue] < array[i]) {
                swap(array, i, indexMinimalValue);
                action.addPair(new Pair(indexMinimalValue, i));
            }
        }
        System.out.println("Sorted Selection " + Arrays.toString(array));
        action.setDuration(System.nanoTime() - start);
        actions.add(action);
        return actions;
    }

    public static Queue<Action> combSort(int[] array) {
        Queue<Action> actions = new LinkedList<>();
        Action action = new Action();
        long start = System.nanoTime();

        int gap = array.length;
        boolean swapped = true;


        while (gap != 1 || swapped) {
            if (gap > 1) {
                gap /= 1.3;
            } else {
                gap = 1;
            }

            System.out.println(gap);

            swapped = false;
            for (int i = 0; i < array.length - gap; i++) {
                if (array[i] > array[i + gap]) {
                    swap(array, i, i + gap);
                    action.addPair(new Pair(i, i + gap));
                    swapped = true;
                }
            }
        }

        System.out.println("Sorted Comb " + Arrays.toString(array));
        action.setDuration(System.nanoTime() - start);
        actions.add(action);
        return actions;
    }

    private static void swap(int[] array, int first, int second) {
        int tmp = array[first];
        array[first] = array[second];
        array[second] = tmp;
    }
}
