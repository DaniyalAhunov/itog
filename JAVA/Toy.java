public class Toy {
    private int id;
    private String name;
    private int quantity;
    private double weight;

    public Toy(int id, String name, int quantity, double weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void updateWeight(double weight) {
        this.weight = weight;
    }

    public boolean hasStock() {
        return quantity > 0;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Название: " + name + ", Количество: " + quantity + ", Вес: " + weight + "%";
    }
}
