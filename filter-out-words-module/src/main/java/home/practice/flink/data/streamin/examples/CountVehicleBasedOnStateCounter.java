package home.practice.flink.data.streamin.examples;

import home.practice.flink.data.streamin.funtions.VehicleNumberToIndiaStateCodeFlatMapFunction;
import home.practice.flink.data.streamin.models.IndiaStateCode;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * In this example, we need to count number of vehicles which are passed by a toll plaza
 * based on the states. Use Tumbling window of 15 secs
 */
public class CountVehicleBasedOnStateCounter {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputDataStream = streamExecutionEnvironment.socketTextStream("localhost", 5983);

        SingleOutputStreamOperator<Tuple2<IndiaStateCode, Integer>> outputStreamOperator = inputDataStream.flatMap(new VehicleNumberToIndiaStateCodeFlatMapFunction()).
                keyBy(0).
                window(TumblingProcessingTimeWindows.of(Time.seconds(10))).
                sum(1);

        outputStreamOperator.print();

        streamExecutionEnvironment.execute("counter-number-of-vehicles-based-on-state-code");
    }
}
