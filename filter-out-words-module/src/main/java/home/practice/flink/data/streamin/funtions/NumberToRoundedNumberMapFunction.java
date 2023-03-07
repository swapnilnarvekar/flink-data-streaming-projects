package home.practice.flink.data.streamin.funtions;

import org.apache.flink.api.common.functions.MapFunction;

public class NumberToRoundedNumberMapFunction implements MapFunction<String,Long> {
    @Override
    public Long map(String inputString) throws Exception {
        double outputDouble = Double.parseDouble(inputString);

        return Math.round(outputDouble);
    }
}
