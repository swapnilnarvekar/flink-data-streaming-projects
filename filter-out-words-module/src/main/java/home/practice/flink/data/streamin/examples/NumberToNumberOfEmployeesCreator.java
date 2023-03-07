package home.practice.flink.data.streamin.examples;

import home.practice.flink.data.streamin.funtions.NumberToEmployeeFlatMapFunction;
import home.practice.flink.data.streamin.models.Employee;
import home.practice.flink.data.streamin.funtions.NumberToRoundedNumberMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class NumberToNumberOfEmployeesCreator {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputDataStream = streamExecutionEnvironment.socketTextStream("localhost", 5983);

        SingleOutputStreamOperator<Employee> outputStreamOperator = inputDataStream.filter(new NumberToEmployeeFlatMapFunction.NumberFilterFunction()).
                map(new NumberToRoundedNumberMapFunction()).
                flatMap(new NumberToEmployeeFlatMapFunction());

        outputStreamOperator.print();

        streamExecutionEnvironment.execute("create-employees-based-on-given-numbers");
    }
}
