package JAVA;

public class Toy {
    private int id;
    private String name;
    private int quantity;
    private double probability;

    public Toy(int id, String name, int quantity, double probability) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.probability = probability;
    }
        public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
    public void updateProbability(double probability) {
        this.probability = probability;
    }
    // Геттеры и сеттеры для свойств игрушек

    public boolean hasStock() {
        return quantity > 0;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Название: " + name + ", Количество: " + quantity + ", Шанс выпадения: " + probability + "%";
    }
}
