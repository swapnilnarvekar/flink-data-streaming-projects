package home.practice.java.streaming.examples;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ReduceOperationExample {


    private static List<Integer> integers;

    private static void findSumOfElementInList() {
        List<Integer> integers = Arrays.asList(1, 30, 45, 78, 200);
        int result = integers.stream()
                .reduce(0,
                        (partialResult, nextNumber) -> partialResult + nextNumber);

        System.out.println("sum of element in the list : " + result);
    }

    private static void makeAllStringsToUpperCaseAndJoinThemTogether() {
        List<String> inputStrings = Arrays.asList("I", "am", "a", "programmer");
        String result = inputStrings.stream()
                .map(String::toUpperCase)
                .reduce("",
                        (partialResult, nextString) -> partialResult.concat(nextString));

        System.out.println("final string after joining is " + result);
    }

    // calculate the product of elements that comes in given range excluding the rightmost element
    private static void calculateProductOfElementsThatComesInRange() {
        OptionalInt result = IntStream.range(5, 7)
                .reduce((partialResult, nextElemenet) -> partialResult * nextElemenet);

        System.out.println("calculate product elements in given range : " + result.getAsInt());
    }

    public static void main(String[] args) {
        ReduceOperationExample.findSumOfElementInList();
        ReduceOperationExample.makeAllStringsToUpperCaseAndJoinThemTogether();
        ReduceOperationExample.calculateProductOfElementsThatComesInRange();
    }
}
