package home.practice.java.streaming.examples;

import home.practice.flink.data.streamin.models.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByFruitsByExample {


    private static void doGroupByFruitName(List<Fruit> fruits) {
        System.out.println(GroupingByFruitsByExample.class.getCanonicalName() + ".doGroupByFruitName()");
        Map<String, List<Fruit>> nameToFruits = fruits.stream().collect(Collectors.groupingBy(Fruit::getName));
        System.out.println("name to Fruits : " + nameToFruits);
    }

    private static void doGroupByFruitNameAndSumOfItsQuantities(List<Fruit> fruits) {
        System.out.println(GroupingByFruitsByExample.class.getName() + ".doGroupByFruitNameAndSumOfItsQuantities()");
        Map<String, Integer> nameToFruits = fruits.stream().
                collect(Collectors.groupingBy(Fruit::getName,
                        Collectors.summingInt(Fruit::getQuantity)));
        System.out.println("name to Fruits : " + nameToFruits);
        System.out.println("------------------------------------------------------------------------------------");
    }

    private static void doGroupByFruitNameAndSumOfItsPrices(List<Fruit> fruits) {
        System.out.println(GroupingByFruitsByExample.class.getName() + ".doGroupByFruitNameAndSumOfItsPrices()");
        Map<String, Double> nameToFruits = fruits.stream().
                collect(Collectors.groupingBy(Fruit::getName,
                        Collectors.summingDouble(Fruit::getPrices)));
        System.out.println("name to Fruits : " + nameToFruits);
        System.out.println("------------------------------------------------------------------------------------");
    }

    private static void doGroupByFruitNameAndAverageOfItsPrices(List<Fruit> fruits) {
        System.out.println(GroupingByFruitsByExample.class.getName() + ".doGroupByFruitNameAndAverageOfItsPrices()");
        Map<String, Double> nameToFruits = fruits.stream().
                collect(Collectors.groupingBy(Fruit::getName,
                        Collectors.averagingDouble(Fruit::getPrices)));
        System.out.println("name to Fruits : " + nameToFruits);
        System.out.println("------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit = new Fruit("Apple", 10, 78.50);
        fruits.add(fruit);

        fruit = new Fruit("Banana", 20, 120.50);
        fruits.add(fruit);

        fruit = new Fruit("Apple", 20, 157.00);
        fruits.add(fruit);

        fruit = new Fruit("Banana", 24, 150.00);
        fruits.add(fruit);

        fruit = new Fruit("Grapes", 52, 150.00);
        fruits.add(fruit);

        fruit = new Fruit("Grapes", 100, 250.00);
        fruits.add(fruit);

        fruit = new Fruit("Banana", 25, 500.00);
        fruits.add(fruit);

        doGroupByFruitName(fruits);
        doGroupByFruitNameAndSumOfItsQuantities(fruits);
        doGroupByFruitNameAndSumOfItsPrices(fruits);
        doGroupByFruitNameAndAverageOfItsPrices(fruits);
    }
}
