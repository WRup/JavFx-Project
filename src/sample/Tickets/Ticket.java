package sample.Tickets;

public class Ticket{
    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }



    private String type;
    private String brand;
    private double value;
    private int amount;

    public Ticket(String type, String brand, double value, int amount)
    {
        this.type = type;
        this.brand = brand;
        this.value = value;
        this.amount = amount;
    }

    public String getName() {
        return getType() + getBrand();

    }
    public double getValue() {
        return value;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

}
