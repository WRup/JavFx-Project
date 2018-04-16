package sample.Controllers;


import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.ButtonsLogic.MainButtons;
import sample.Clock.Clock;
import sample.Coins.CoinBox;
import sample.Parser.DecimalParser;
import sample.Interface.IMethods;
import sample.Tickets.TicketList;
import sample.Timer.ExpiredSession;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


import static sample.Controllers.MainController.actualController;
import static sample.Controllers.SingleAreaController.actualArea;
import static sample.Controllers.SingleAreaController.ticketCounter;

public class ChosenTicketController implements IController, IMethods {
    private TicketList ticketList;
    private MainButtons mainButtons;
    private ArrayList<Label> labelList;
    private DecimalParser df;
    private double price=0;
    private ExpiredSession expiredSession;
    private Clock clock;
    private CoinBox coinBox;
    @FXML
    private MainController mainController;
    @FXML
    private Label label00, label01, label02, label10, label11, label12, label20,label21,label22,label30,label31,label32,label40,label41,label42,label50,label51,label52,label60,label61,label62;
    @FXML
    private Label labelAmount, labelPrice, postfixLabel, dateLabel, clockLabel;
    @FXML
    private Button butt3;

    public void initialize()
    {
        actualController = this.getClass().getSimpleName();
        mainButtons = new MainButtons();
        df = new DecimalParser();
        labelList = new ArrayList<>();
        this.fillLabelList();
        if(ticketCounter ==6)
            butt3.setDisable(true);
    }
    @FXML
    public void setMainController(MainController mainController) {
        this.mainController=mainController;
    }
    @FXML
    public void openMainMenu() {
        expiredSession.stopTime();
        mainButtons.openFirstMenu(mainController,coinBox);
    }
    @FXML
    public void addNewTicket() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        expiredSession.stopTime();
        if(actualArea)
            mainButtons.openSingleArea(mainController,ticketList,coinBox);
        else {
            mainButtons.openAgroArea(mainController, ticketList,coinBox);
        }
    }
    @FXML
    public void chooseAmount(){
        expiredSession.stopTime();
        mainButtons.chooseAmount(mainController,this.ticketList,coinBox);
    }
    @FXML
    public void openPaymentArea() {
        expiredSession.stopTime();
        mainButtons.openPaymentArea(mainController,ticketList,price,coinBox);
    }

    @Override
    public void setTicketList(TicketList ticketList) {
        this.ticketList=ticketList;
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

    public void fillGridPane()
    {
        int amount = 0;
        for(int i=0,j=0; i<ticketList.getTicketList().size();i++,j+=3)
        {
            if(actualArea)
                labelList.get(j).setText(ticketList.getTicketList().get(i).getName()+" ,I");
            else
                labelList.get(j).setText(ticketList.getTicketList().get(i).getName()+" ,I+II");
            labelList.get(j+1).setText(String.valueOf(ticketList.getTicketList().get(i).getAmount()));
            labelList.get(j+2).setText("szt.");
            this.price += ticketList.getTicketList().get(i).getValue()*ticketList.getTicketList().get(i).getAmount();
            amount += (ticketList.getTicketList().get(i).getAmount());
        }
        labelPrice.setText(String.valueOf(df.round(price,2))+"0");
        labelAmount.setText(String.valueOf(amount));
        postfixLabel.setText("zł");

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
