package sample.Coins;

public class Coin implements Comparable<Coin> {

    private double value;

    public Coin(double value)
    {
        this.value = value;
    }

    public double getValue()
    {
        return this.value;
    }


    @Override
    public int compareTo(Coin o) {
            return this.getValue() > o.getValue() ? -1 : this.getValue() < o.getValue() ? 1 : 0;
    }
}
