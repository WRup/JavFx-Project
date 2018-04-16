package sample.Parser;

public class CoinParser {

    private String amount;

    public CoinParser(String amount)
    {
        this.amount=amount;
    }

    public double parseString()
    {
        double amount = Double.valueOf(this.amount.split(" ")[0]);
        if(amount > 5.0) {
            amount *= 0.01;
            return amount;
        }
        else
            return amount;

    }
}
