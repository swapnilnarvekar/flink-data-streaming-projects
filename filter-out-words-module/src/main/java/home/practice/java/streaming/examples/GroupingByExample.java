package home.practice.java.streaming.examples;

import home.practice.flink.data.streamin.models.Employee;
import home.practice.flink.data.streamin.models.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByExample {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        Employee employee = new Employee(100, "Wdile", Gender.FEMALE);
        employees.add(employee);

        employee = new Employee(101, "Employee-101", Gender.MALE);
        employees.add(employee);

        employee = new Employee(102, "Employee-102", Gender.MALE);
        employees.add(employee);

        employee = new Employee(103, "Employee-103", Gender.TRANS);
        employees.add(employee);

        employee = new Employee(104, "Employee-104", Gender.TRANS);
        employees.add(employee);


        Map<Gender, List<Employee>> genderToListOfEmployees = employees.stream().
                collect(Collectors.groupingBy(Employee::getGender));

        System.out.println(genderToListOfEmployees);
    }
}
