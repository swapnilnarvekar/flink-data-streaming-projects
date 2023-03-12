package home.practice.flink.data.streamin.models;

import java.util.Objects;

public class Person {

    private int id;
    private String name;
    private double salary;
    private Department department;

    private String mobileNumber;

    public Person(int id, String name, double salary, Department department, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.mobileNumber = mobileNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && Double.compare(person.getSalary(), getSalary()) == 0 && getName().equals(person.getName()) && getDepartment().equals(person.getDepartment()) && getMobileNumber().equals(person.getMobileNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSalary(), getDepartment(), getMobileNumber());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}
