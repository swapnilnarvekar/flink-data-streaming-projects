package home.practice.flink.data.streamin.examples;

import home.practice.flink.data.streamin.funtions.StudentsDetailsToNameAndMarksMapFunction;
import home.practice.flink.data.streamin.funtions.ValidStudentMarksFilter;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class HighestMarkOfStudentFinder {

    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> inputDataStream = environment.socketTextStream("localhost",5983);

        SingleOutputStreamOperator<Tuple2<String, Integer>> outputStream = inputDataStream.filter(new ValidStudentMarksFilter()).
                        map(new StudentsDetailsToNameAndMarksMapFunction()).
                        keyBy(0).
                        max(1);

        outputStream.print();

        environment.execute("highest-marks-of-student-finder");
    }
}
