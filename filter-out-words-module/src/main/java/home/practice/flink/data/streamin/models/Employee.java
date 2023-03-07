package home.practice.flink.data.streamin.models;

public class Employee {
    private long employeeNumber;
    private String employeeName;

    public Employee(long employeeNumber,String employeeName){
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", employeeName='" + employeeName + '\'' +
                '}';
    }
}
