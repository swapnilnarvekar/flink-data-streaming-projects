package home.practice.java.streaming.examples;

import java.util.*;

/**
 *
 */
public class NumberOfOccurancesOfGivenElementInListCounter {

    private static void findOccurancesOfGivenNumberInList(int inputNumber) {
        List<Integer> integers = Arrays.asList(5, 3, 4, 1, 3, 7, 2, 9, 9, 4);
        System.out.println(String.format("Input number : %s is found %s times in the given list",
                inputNumber, Collections.frequency(integers, inputNumber)));
    }

    private static void countOccurrenceOfEachNumberInList() {
        List<Integer> integers = Arrays.asList(5, 3, 4, 1, 3, 7, 2, 9, 9, 4);
        Set<Integer> integerSet = new HashSet<>(integers);
        for (Integer eachInteger : integerSet) {
            System.out.println(String.format("Input number : %s is found %s times in the given list",
                    eachInteger, Collections.frequency(integers, eachInteger)));
        }
    }

    public static void main(String[] args) {
        /*NumberOfOccurancesOfGivenElementInListCounter.findOccurancesOfGivenNumberInList(4);
        NumberOfOccurancesOfGivenElementInListCounter.findOccurancesOfGivenNumberInList(5);
        NumberOfOccurancesOfGivenElementInListCounter.findOccurancesOfGivenNumberInList(1);
        NumberOfOccurancesOfGivenElementInListCounter.findOccurancesOfGivenNumberInList(10);
        NumberOfOccurancesOfGivenElementInListCounter.findOccurancesOfGivenNumberInList(9);*/

        NumberOfOccurancesOfGivenElementInListCounter.countOccurrenceOfEachNumberInList();
    }
}
