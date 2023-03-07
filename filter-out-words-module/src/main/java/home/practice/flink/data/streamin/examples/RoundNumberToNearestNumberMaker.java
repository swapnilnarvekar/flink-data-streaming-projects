package home.practice.flink.data.streamin.examples;

import home.practice.flink.data.streamin.funtions.NumberToEmployeeFlatMapFunction;
import home.practice.flink.data.streamin.funtions.NumberToRoundedNumberMapFunction;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class RoundNumberToNearestNumberMaker {

    public static void main(String[] args) throws  Exception{
        System.out.println("A job : make-a-given-number-to-nearest-number is started");
        StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<Long> singleOutputStreamOperator = streamExecutionEnvironment.socketTextStream("localhost",5983).
                                    filter(new NumberToEmployeeFlatMapFunction.NumberFilterFunction()).
                                    map(new NumberToRoundedNumberMapFunction());
        singleOutputStreamOperator.print();
        streamExecutionEnvironment.execute("make-a-given-number-to-nearest-number");


    }
}
