package home.practice.flink.data.streamin.models;

public class Employee {
    private long employeeNumber;
    private String employeeName;

    private Gender gender;

    public Employee(long employeeNumber, String employeeName) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
    }

    public Employee(long employeeNumber, String employeeName, Gender gender) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.gender = gender;
    }

    public long getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", employeeName='" + employeeName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
