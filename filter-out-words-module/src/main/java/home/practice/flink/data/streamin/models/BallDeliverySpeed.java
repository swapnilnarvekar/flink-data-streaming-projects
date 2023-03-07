package home.practice.flink.data.streamin.models;

public class BallDeliverySpeed {

    private Double speed;

    private static BallDeliverySpeed ballDeliverySpeed;

    private BallDeliverySpeed() {
    }

    public static BallDeliverySpeed of(String inputString) {
        ballDeliverySpeed = new BallDeliverySpeed();
        ballDeliverySpeed.speed = Double.parseDouble(inputString);
        return ballDeliverySpeed;
    }

    public Double getSpeed() {
        return ballDeliverySpeed.speed;
    }

    @Override
    public String toString() {
        return "BallDeliverySpeed{" +
                "speed=" + speed +
                '}';
    }
}
