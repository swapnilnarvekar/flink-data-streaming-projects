package home.practice.flink.data.streamin.funtions;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;


public class StudentsDetailsToNameAndMarksMapFunction implements MapFunction<String, Tuple2<String, Integer>> {

    @Override
    public Tuple2<String, Integer> map(String inputString) throws Exception {
        System.out.println(String.format("%s.filter()-inputString is received as %s",
                this.getClass().getName(), inputString));
        String[] studentDetails = inputString.split(",");

        return new Tuple2<>(studentDetails[0], Integer.parseInt(studentDetails[1]));
    }
}
