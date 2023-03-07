package home.practice.flink.data.streamin.funtions;

import home.practice.flink.data.streamin.models.IndiaStateCode;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class VehicleNumberToIndiaStateCodeFlatMapFunction implements FlatMapFunction<String, Tuple2<IndiaStateCode,Integer>> {

    @Override
    public void flatMap(String vehicleNumber, Collector<Tuple2<IndiaStateCode,Integer>> collector) throws Exception {
        System.out.println(String.format("Vehicle number : %s is passed by the toll plaza", vehicleNumber));
        String indiaStateCodeAsString = vehicleNumber.substring(0,2).toUpperCase();
        System.out.println(String.format("state code is extracted as : %s", indiaStateCodeAsString));
        collector.collect(new Tuple2<>(IndiaStateCode.valueOf(indiaStateCodeAsString),1));
    }
}
