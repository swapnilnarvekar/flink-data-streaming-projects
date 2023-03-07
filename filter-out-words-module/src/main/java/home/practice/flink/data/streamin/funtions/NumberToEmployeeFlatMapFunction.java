package home.practice.flink.data.streamin.funtions;

import home.practice.flink.data.streamin.models.Employee;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

public class NumberToEmployeeFlatMapFunction implements FlatMapFunction<Long, Employee> {

    @Override
    public void flatMap(Long numberOfEmployees, Collector<Employee> collector) throws Exception {
        System.out.println(String.format("Number of employees to be created %s",numberOfEmployees));

        for (int counter = 0; counter< numberOfEmployees ; counter++){
            long generatedEmployeeNumber = generateRandomNumber(counter,numberOfEmployees);
            Employee employee = new Employee(generatedEmployeeNumber ,
                                            new String(generatedEmployeeNumber + " - employeeName"));

            collector.collect(employee);
        }
    }

    private long generateRandomNumber(long min, long max){
       return (int) ((Math.random() * (max - min)) + min);
    }

    public static class NumberFilterFunction implements FilterFunction<String> {

        @Override
        public boolean filter(String inputString) throws Exception {
            boolean result = false;
            try {
                Double.parseDouble(inputString);
                result = true;
            }catch (Exception exception){
                result = false;
            }
            return result;
        }
    }
}
