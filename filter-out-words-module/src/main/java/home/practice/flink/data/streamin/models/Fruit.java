package home.practice.flink.data.streamin.models;

public class Fruit {
    private String name;
    private Integer quantity;
    private Double prices;

    public Fruit(String name, Integer quantity, Double prices) {
        this.name = name;
        this.quantity = quantity;
        this.prices = prices;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrices() {
        return prices;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", prices=" + prices +
                '}';
    }
}
