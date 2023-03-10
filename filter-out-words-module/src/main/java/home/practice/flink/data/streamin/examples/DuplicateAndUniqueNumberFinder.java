package home.practice.flink.data.streamin.examples;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Find duplicate elements by using Stream API
 */
public class DuplicateAndUniqueNumberFinder {

    public void findDuplicateNumber() {
        List<Integer> list = Arrays.asList(5, 3, 4, 1, 3, 7, 2, 9, 9, 4);
        Set<Integer> integerSet = new HashSet<>();

        Set<Integer> duplicateIntegerSet = list.stream()
                .filter(element -> !integerSet.add(element))
                .collect(Collectors.toSet());

        System.out.println("duplicate integer set : " + duplicateIntegerSet);

    }


    private void findDuplicatesByFrequency() {
        List<Integer> list = Arrays.asList(5, 3, 4, 1, 3, 7, 2, 9, 9, 4);
        Set<Integer> duplicateIntegerSet = list.stream().filter(element -> Collections.frequency(list, element) > 1).collect(Collectors.toSet());

        System.out.println("duplicate integer set by frequency : " + duplicateIntegerSet);
    }

    private void findUniquesByFrequency() {
        List<Integer> list = Arrays.asList(5, 3, 4, 1, 3, 7, 2, 9, 9, 4);
        Set<Integer> uniqueIntegerSet = list.stream().filter(element -> Collections.frequency(list, element) == 1).collect(Collectors.toSet());

        System.out.println("unique integer set by frequency : " + uniqueIntegerSet);
    }

    public static void main(String[] args) {
        DuplicateAndUniqueNumberFinder duplicateAndUniqueNumberFinder = new DuplicateAndUniqueNumberFinder();
        duplicateAndUniqueNumberFinder.findDuplicateNumber();
        duplicateAndUniqueNumberFinder.findDuplicatesByFrequency();
        duplicateAndUniqueNumberFinder.findUniquesByFrequency();
    }


}
