package sample.Controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.ButtonsLogic.MainButtons;
import sample.Change.Change;
import sample.Clock.Clock;
import sample.Coins.Coin;
import sample.Coins.CoinBox;
import sample.Interface.IMethods;
import sample.Timer.ExpiredSession;

import sample.Parser.CoinParser;
import sample.Parser.DecimalParser;
import sample.Tickets.TicketList;


import javafx.event.ActionEvent;
import sample.Timer.LabelTimer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static sample.Controllers.MainController.actualController;

public class PaymentAreaController implements IController, IMethods {

    public static int coinCounter = 0;

    private Change change;
    private CoinParser coinParser;
    private TicketList ticketList;
    private MainButtons mainButtons;
    private ArrayList<Label> labelList;
    private DecimalParser df;
    private CoinBox coinBox;
    private ExpiredSession expiredSession;
    private LabelTimer labelTimer;
    private Clock clock;
    private double actualChange = 0;
    private double price = 0;

    @FXML
    private MainController mainController;
    @FXML
    private Label label00, label01, label02, label10, label11, label12, label20,label21,label22,label30,label31,label32,label40,label41,label42,label50,label51,label52,label60,label61,label62;
    @FXML
    private Label labelLeftPrice,labelPrice,postfixLabel,postfixLabel2,printLabel,dateLabel,clockLabel;
    @FXML
    private Text cautionText;
    @FXML
    private Button butt1, butt2, butt3, butt4, butt5, butt6, cancelButton;


    public void initialize()
    {
        labelList =  new ArrayList<>();
        change = new Change();
        actualController = this.getClass().getSimpleName();
        mainButtons = new MainButtons();
        df = new DecimalParser();
        this.fillLabelList();

    }
    @Override
    public void setMainController(MainController mainController) {
        this.mainController=mainController;
    }


    @FXML
    public void cancelPayment() {
        expiredSession.stopTime();
        mainButtons.openFirstMenu(mainController,coinBox);
    }

    public void fillGridPane(double price) {
        this.price = price;
        this.actualChange = price;
        for(int i=0,j=0; i<ticketList.getTicketList().size();i++,j+=3)
        {
            labelList.get(j).setText(ticketList.getTicketList().get(i).getName());
            labelList.get(j+1).setText(String.valueOf(ticketList.getTicketList().get(i).getAmount()));
            labelList.get(j+2).setText("szt.");
        }
        labelPrice.setText(String.valueOf(df.round(price,2))+"0");
        labelLeftPrice.setText(String.valueOf(df.round(actualChange,2)) + "0");
        postfixLabel.setText("zł");
        postfixLabel2.setText("zł");

    }

    private void setButtons(boolean t) {
        butt1.setDisable(t);
        butt2.setDisable(t);
        butt3.setDisable(t);
        butt4.setDisable(t);
        butt5.setDisable(t);
        butt6.setDisable(t);
        cancelButton.setDisable(t);
    }

    private void ticketPrinting(boolean t) {
        coinCounter = 0;
        expiredSession.stopTime();
        printLabel.setOpacity(1);
        printLabel.setStyle("-fx-background-color: white");
        labelTimer = new LabelTimer(this, t,5);

    }
    public void receivingTickets(boolean t){
        printLabel.setOpacity(0);
        printLabel.setStyle("-fx-background-color: transparent");
        if(t)
            printLabel.setText("Odbierz bilety!");
        else {
            printLabel.setText("Odbierz bilety i resztę!");
            cautionText.setOpacity(1);
            cautionText.setText("Zwrot reszty: " + abs(actualChange) + "0 zł");
            cautionText.setStyle("-fx-text-fill: black");
            cautionText.setFont(Font.font(30));
        }
        printLabel.setOpacity(1);
        printLabel.setStyle("-fx-background-color: white");
        this.expiredSession = new ExpiredSession(mainController,mainButtons,8,this.coinBox);
    }

    public void putCoin(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InterruptedException {
        coinCounter++;
        cautionText.setOpacity(0);
        expiredSession.resetTime();
        Object object = event.getSource();
        Method getLabel = object.getClass().getMethod("getText");
        coinParser = new CoinParser(getLabel.invoke(object).toString());
        double value = coinParser.parseString();
        mainController.getCoinBox().add(new Coin(value));
        this.actualChange -= coinParser.parseString();
        this.actualChange = Math.round(actualChange *100d)/100d;
        if(actualChange <0)
        {
            this.actualChange = abs(actualChange);
            if(change.isPossible(this.coinBox, actualChange)) {
                change.giveChange(this.coinBox, actualChange);
                labelLeftPrice.setText("0.00");
                this.setButtons(true);
                this.ticketPrinting(false);
            }
            else
            {
                change.noChange(coinBox, coinCounter);
                cautionText.setOpacity(1);
                cautionText.setText("Zwrot pieniędzy. \nWRZUĆ ODLICZONĄ KWOTĘ!");
                cautionText.setFont(Font.font(30));
                cautionText.setStyle("-fx-text-fill: red");
                this.setButtons(false);
                this.actualChange = price;
                labelLeftPrice.setText(labelPrice.getText());
                coinCounter = 0;
            }
            return;
        }
        if(actualChange == 0)
        {
            labelLeftPrice.setText("0.00");
            printLabel.setOpacity(1);
            printLabel.setStyle("-fx-background-color: white");
            this.setButtons(true);
            this.ticketPrinting(true);
        }
        else {
            labelLeftPrice.setText(String.valueOf(actualChange) + "0");
        }
    }



    @Override
    public void setTicketList(TicketList ticketList) {
        this.ticketList = ticketList;
    }
    @Override
    public void setCoinBox(CoinBox coinBox) { this.coinBox = coinBox;}
    @Override
    public void setSession(MainController mainController, CoinBox coinBox){
        this.expiredSession = new ExpiredSession(mainController,mainButtons,60,coinBox);}
    @Override
    public void setClockAndDate(MainController mainController){
        clock = mainController.getClock();
        clockLabel.textProperty().bind(Bindings.convert(clock.timeProperty()));
        dateLabel.textProperty().bind(Bindings.convert(clock.dateProperty()));

    }

    private void fillLabelList()
    {
        labelList.add(label00);
        labelList.add(label01);
        labelList.add(label02);
        labelList.add(label10);
        labelList.add(label11);
        labelList.add(label12);
        labelList.add(label20);
        labelList.add(label21);
        labelList.add(label22);
        labelList.add(label30);
        labelList.add(label31);
        labelList.add(label32);
        labelList.add(label40);
        labelList.add(label41);
        labelList.add(label42);
        labelList.add(label50);
        labelList.add(label51);
        labelList.add(label52);
        labelList.add(label60);
        labelList.add(label61);
        labelList.add(label62);
    }
}
