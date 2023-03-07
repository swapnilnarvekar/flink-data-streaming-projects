package home.practice.flink.data.streamin.examples;

import home.practice.flink.data.streamin.funtions.NumberToEmployeeFlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

public class DataStreamingNumberFilter {

    public static void main(String[] args) throws Exception{
        System.out.println("This is " + DataStreamingNumberFilter.class.getName());

        List<String> collectionOfData = new ArrayList<>();
        collectionOfData.add("as");
        collectionOfData.add("199");
        collectionOfData.add("200");
        collectionOfData.add("asdasd");

        StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> stringDataStream = streamExecutionEnvironment.
                                                socketTextStream("localhost",5983).// open a socket at port number 5983 on 'localhost'
//                                                readTextFile("file:\\C:\\nsa\\temp\\input-to-number-filter.txt").
                                                //filter(new NumberFilterFunction())
//                                                fromCollection(collectionOfData).
                                                filter(new NumberToEmployeeFlatMapFunction.NumberFilterFunction());
        System.out.println("stringDataStream output is written to the file");
        streamExecutionEnvironment.execute("data-stream-number-filter");
    }
}
