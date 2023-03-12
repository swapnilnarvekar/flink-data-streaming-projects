package home.practice.java.streaming.examples;

import home.practice.flink.data.streamin.models.Department;
import home.practice.flink.data.streamin.models.Person;
import javafx.util.Pair;
import org.apache.flink.api.java.tuple.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupingByPersonExample {


    /**
     * Grouping all persons by departments
     *
     * @param persons
     */
    private static void doGroupingAllPersonsByDepartments(List<Person> persons) {
        System.out.println("doGroupingAllPersonsByDepartments -----");
        Map<Department, List<Person>> departmentToListOfPersons = persons.stream().
                collect(Collectors.groupingBy(Person::getDepartment));
        System.out.println(departmentToListOfPersons);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    /**
     * do grouping all persons but with person IDs by department
     *
     * @param persons
     */
    private static void doGroupingOnlyPersonIdsByDepartments(List<Person> persons) {
        System.out.println("doGroupingOnlyPersonIdsByDepartments -----");
        Map<Department, List<Integer>> departmentToListOfPersons = persons.stream().
                collect(Collectors.groupingBy(Person::getDepartment,
                        Collectors.mapping(Person::getId, Collectors.toList())));
        System.out.println(departmentToListOfPersons);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    /**
     * do grouping all persons but with person names by department
     *
     * @param persons
     */
    private static void doGroupingOnlyPersonNamesByDepartments(List<Person> persons) {
        System.out.println("doGroupingOnlyPersonNamesByDepartments -----");
        Map<Department, List<String>> departmentToListOfPersons = persons.stream().
                collect(Collectors.groupingBy(Person::getDepartment,
                        Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println(departmentToListOfPersons);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    /**
     * do grouping on distinct department and salary pairs
     *
     * @param persons
     */
    private static void doGroupingOnDistinctDepartmentAndSalaryPairs(List<Person> persons) {
        System.out.println("doGroupingOnDistinctDepartmentAndSalaryPairs -----");
        Map<Tuple2<Department, Double>, List<Integer>> distinctDepartmentAndSalaryPairs = persons.stream().
                collect(Collectors.groupingBy(person -> {
                    return new Tuple2(person.getDepartment(), person.getSalary());
                }, Collectors.mapping(Person::getId, Collectors.toList())));

        System.out.println(distinctDepartmentAndSalaryPairs);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person(1, "Alex", 100d, new Department(1, "HR"), "9819324234"),
                new Person(2, "Brian", 200d, new Department(1, "HR"), "981922525"),
                new Person(3, "Charles", 900d, new Department(2, "Finance"), "9833698221"),
                new Person(4, "David", 200d, new Department(2, "Finance"), "988733333"),
                new Person(5, "Edward", 200d, new Department(2, "Finance"), "98199123489"),
                new Person(6, "Frank", 800d, new Department(3, "ADMIN"), "987589752"),
                new Person(7, "George", 900d, new Department(3, "ADMIN"), "998223452"));

        doGroupingAllPersonsByDepartments(persons);
        doGroupingOnlyPersonIdsByDepartments(persons);
        doGroupingOnlyPersonNamesByDepartments(persons);
//        doGroupingOnDistinctDepartmentAndSalaryPairs(persons);
        doGroupingByDepartmentsAndCountingAllPersons(persons);
        countAllPersonsHavingSameSalary(persons);
        findAverageSalaryInEachDepartment(persons);
        doGroupingAllPersonsByDepartmentWhoseSalaryIsGreaterThan300(persons);
        getPersonWithExactMatchingName(persons);
        getAllPersonsWithMobileNumberStartsWithNumber9819(persons);
        convertAllPersonsToListOfTheirName(persons);
        convertAllPersonsToSingleString(persons);
    }

    private static void convertAllPersonsToSingleString(List<Person> persons) {
        System.out.println("convertAllPersonsToSingleString -----");

        String allPersonNames = persons.stream()
                .map(Person::getName)
                .collect(Collectors.joining());
        System.out.println("All persons to single string : " + allPersonNames);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static void convertAllPersonsToListOfTheirName(List<Person> persons) {
        System.out.println("convertAllPersonsToListOfTheirName -----");

        List<String> personNames = persons.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println("All persons to list of their name : " + personNames);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static void getAllPersonsWithMobileNumberStartsWithNumber9819(List<Person> persons) {
        System.out.println("getAllPersonsWithMobileNumberStartsWithNumber9819 -----");

        List<Person> personsStartsWithMobileNumber9819 = persons.stream()
                .filter(person -> person.getMobileNumber().startsWith("9819"))
                .collect(Collectors.toList());
        System.out.println("All persons with mobile number starts with 9819 : " + personsStartsWithMobileNumber9819);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static void doGroupingByDepartmentsAndCountingAllPersons(List<Person> persons) {
        System.out.println("doGroupingByDepartmentsAndCountingAllPersons -----");
        Map<Department, Long> departmentToAllPersons = persons.stream().
                collect(Collectors.groupingBy(Person::getDepartment, Collectors.counting()));

        System.out.println("Total number of persons in each department : " + departmentToAllPersons);
        System.out.println("-------------------------------------------------------------------------------------------");

    }

    private static void doGroupingAllPersonsByDepartmentWhoseSalaryIsGreaterThan300(List<Person> persons) {
        System.out.println("doGroupingAllPersonsByDepartmentWhoseSalaryIsGreaterThan300 -----");
        Map<Department, Long> departmentToAllPersons = persons.stream().
                filter(person -> {
                    return person.getSalary() > 300;
                }).
                collect(Collectors.groupingBy(Person::getDepartment, Collectors.counting()));

        System.out.println("All Persons By Department Whose Salary Is Greater Than 300 : " + departmentToAllPersons);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static void countAllPersonsHavingSameSalary(List<Person> persons) {
        System.out.println("countAllPersonsHavingSameSalary -----");
        Map<Double, Long> salaryToPersons = persons.stream().
                collect(Collectors.groupingBy(Person::getSalary, Collectors.counting()));

        System.out.println("Number of persons having same salary : " + salaryToPersons);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static void findAverageSalaryInEachDepartment(List<Person> persons) {
        System.out.println("findAverageSalaryInEachDepartment -----");
        Map<Department, Double> departmentToAverageSalary = persons.stream().
                collect(Collectors.groupingBy(Person::getDepartment, Collectors.averagingDouble(Person::getSalary)));

        System.out.println("average salary in each department : " + departmentToAverageSalary);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static void getPersonWithExactMatchingName(List<Person> persons) {
        System.out.println("getPersonWithExactMatchingName -----");
        String personNameToBeSearched = "Alex1";
        Optional<Person> requiredPerson = persons.stream()
                .filter(person -> {
                    return person.getName().equals(personNameToBeSearched);
                })
                .findAny();

        requiredPerson.ifPresent(person -> System.out.println("required person '" + requiredPerson.get().getName() + "' is found"));
        if (!requiredPerson.isPresent()) {
            System.out.println("required person '" + personNameToBeSearched + "' is not found");
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }
}
