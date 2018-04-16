package sample.Change;

import sample.Coins.Coin;
import sample.Coins.CoinBox;

import java.util.ArrayList;
import java.util.Collections;

public class Change {

    private ArrayList<Coin> tempArrayList;

    public boolean isPossible(CoinBox coinBox, double change)
    {
        if(Double.compare(coinBox.getSumValue(),change)>=0)
        {
            this.tempArrayList = new ArrayList<>(coinBox.getCoinList());
            Collections.sort(tempArrayList);
//            for(int i =0 ; i<tempArrayList.size();i++)
//                System.out.println("Obecnie w maszynie: " + tempArrayList.get(i).getValue());
            double tryRest = change;
            int roundTryRest = (int)(tryRest*10);
            for (int i = 0; i < tempArrayList.size(); i++)
                if (tempArrayList.get(i).getValue() <= tryRest) {
//                    System.out.println("Odejmuje : " + tempArrayList.get(i).getValue());
                    roundTryRest -= (int)(tempArrayList.get(i).getValue()*10);
                    tryRest =(double)roundTryRest / 10;
                }
            return (tryRest == 0);
        }
        else
            return false;
    }

    public void noChange(CoinBox coinBox, int coinCounter)
    {
        int size = coinBox.getCoinList().size();
        for (int i = coinBox.getCoinList().size()-1; i > size - coinCounter - 1; i--) {
            coinBox.getCoinList().remove(i);
        }
//        for(int i =0 ; i<coinBox.getCoinList().size();i++)
//            System.out.println("Po usuwaniu: " + coinBox.getCoinList().get(i).getValue());
    }

    public void giveChange(CoinBox coinBox, double change)
    {
        Collections.sort(coinBox.getCoinList());
        int tempChange = (int)(change * 10);
        for (int i = 0; i < coinBox.getCoinList().size(); i++) {
            if (coinBox.getCoinList().get(i).getValue() <= change) {
                tempChange -= (int)(coinBox.getCoinList().get(i).getValue()*10);
                change = (double)tempChange/10;
                coinBox.getCoinList().remove(i);
                i=0;
            }
        }
//        for(int i =0 ; i<coinBox.getCoinList().size();i++)
//            System.out.println("Po wydaniu reszty: " + coinBox.getCoinList().get(i).getValue());
    }
}
