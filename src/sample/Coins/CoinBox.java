package sample.Coins;


import java.util.ArrayList;

public class CoinBox {

    private ArrayList<Coin> coinList;

    public CoinBox()
    {
        this.coinList = new ArrayList<>();
    }

    public void add(Coin coin) {
        this.coinList.add(coin);
    }

    public ArrayList<Coin> getCoinList() {
        return coinList;
    }

    public double getSumValue(){
        double sum=0;
        for(Coin coins : coinList)
            sum+= coins.getValue();
        return sum;
    }
}
