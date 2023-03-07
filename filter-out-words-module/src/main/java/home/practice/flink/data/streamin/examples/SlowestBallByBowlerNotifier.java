package home.practice.flink.data.streamin.examples;

import home.practice.flink.data.streamin.models.BallDeliverySpeed;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import scala.Tuple2;

import java.util.Iterator;

/**
 * input -> bowlerName , deliverySpeed
 * output -> slowest ball with the speed of _ is delivered by you.
 */
public class SlowestBallByBowlerNotifier {

    public static void main(String[] args) throws Exception {
        System.out.println("Input format : bowler-name(String),delivery-speed(double)");
        StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> inputStream = streamExecutionEnvironment.socketTextStream("localhost", 5983);

        SingleOutputStreamOperator<String > singleOutputStreamOperator = inputStream.map(new InpuStringToBowlingSpeedMapFunction()).
                keyBy(Tuple2::_1).flatMap(new FastestAndSlowestBallNotifierRichMapFunction());

        singleOutputStreamOperator.print();

        streamExecutionEnvironment.execute("Slowest-Ball-By-Bowler-Notifier");
    }

    private static class InpuStringToBowlingSpeedMapFunction implements MapFunction<String, Tuple2<String, BallDeliverySpeed>> {

        @Override
        public Tuple2<String, BallDeliverySpeed> map(String inputString) throws Exception {
            System.out.println(String.format("%s.map() - input parameter is : %s", this.getClass().getName(), inputString));
            String bowlerName = inputString.split(",")[0];
            String deliverySpeed = inputString.split(",")[1];
            return new Tuple2<>(bowlerName, BallDeliverySpeed.of(deliverySpeed));
        }
    }

    private static class FastestAndSlowestBallNotifierRichMapFunction extends RichFlatMapFunction<Tuple2<String, BallDeliverySpeed>, String> {

        private ListState<Tuple2<String, BallDeliverySpeed>> listState;

        @Override
        public void flatMap(Tuple2<String, BallDeliverySpeed> inputParameter, Collector<String> collector) throws Exception {
            Iterable<Tuple2<String, BallDeliverySpeed>> iterable = listState.get();
            Iterator<Tuple2<String, BallDeliverySpeed>> iterator = iterable.iterator();
            boolean bowledWithMaximumSpeed = false;
            boolean bowledWithSlowestSpeed = false;
            while (iterator.hasNext()) {
                Tuple2<String, BallDeliverySpeed> eachTuple = iterator.next();
                if (eachTuple._1().equals(inputParameter._1())) {
                    if (inputParameter._2().getSpeed() > eachTuple._2().getSpeed()) {
                        bowledWithMaximumSpeed = true;
                    }

                    if (inputParameter._2().getSpeed() < eachTuple._2().getSpeed()) {
                        bowledWithSlowestSpeed = true;
                    }
                }
            }
            if (bowledWithMaximumSpeed) {
                collector.collect(String.format("Hurray !!  %s has bowled the ball with max speed of %s", inputParameter._1().toUpperCase(), inputParameter._2()));
            }

            if (bowledWithSlowestSpeed) {
                collector.collect(String.format("Yay !!  %s has bowled the ball with slowest speed of %s", inputParameter._1().toUpperCase(), inputParameter._2()));
            }
            listState.add(inputParameter);

        }
    }

    public void open(Configuration configuration){
        ListStateDescriptor<Tuple2<String,BallDeliverySpeed>> stateDescriptor = new ListStateDescriptor<Tuple2<String, BallDeliverySpeed>>("ballSpeedNotifier",
                                                                                        TypeInformation.of(new TypeHint<Tuple2<String, BallDeliverySpeed>>() {
                                                                                        }));

    }
}
