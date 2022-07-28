public class Product {
    protected String name;
    protected double price;

    // constructors
    public Product() {
        this.name = "";
        this.price = -1;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString
    public String toString() {
        String output = String.format("%22s   $%9.2f",
                this.getName(),this.getPrice());
        return output;
    }
}
