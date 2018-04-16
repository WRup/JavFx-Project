package sample.Controllers;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.ButtonsLogic.MainButtons;
import sample.Clock.Clock;
import sample.Coins.CoinBox;
import sample.Interface.IMethods;
import sample.Tickets.TicketList;
import sample.Timer.ExpiredSession;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static sample.Controllers.MainController.actualController;
import static sample.Controllers.SingleAreaController.ticketCounter;
import static sample.Controllers.SingleAreaController.actualArea;

public class AgroAreaController implements IController, IMethods {

    private HashMap<Button,TextArea> hashMap;
    private MainButtons mainButtons;
    private TicketList ticketList;
    private ExpiredSession expiredSession;
    private Clock clock;
    private CoinBox coinBox;

    @FXML
    private MainController mainController;
    @FXML
    private Button button1, button2, button3, button4, button7, button8, button9, button10, button11, button12, button17, button18, button19, button20, button21;
    @FXML
    private TextArea text1, text2,text4, text5, text6, text9, text10, text11;
    @FXML
    private Label clockLabel, dateLabel;

    @FXML
    public void initialize(){
        hashMap = new HashMap<>();
        this.fillHashMap();
        mainButtons = new MainButtons();
        ticketList = new TicketList();
        actualController = this.getClass().getSimpleName();
        actualArea = false;
        ticketCounter = 0;
    }
    @FXML
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    public void openMainMenu()
    {
        expiredSession.stopTime();
        mainButtons.openFirstMenu(mainController,coinBox);
    }
    @FXML
    public void openLastScreen() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        expiredSession.stopTime();
        if(ticketCounter !=0) {
            mainButtons.backToChosenTicket(mainController, ticketList,coinBox);
        }
        else {
            mainButtons.openLastScreen(mainController, ticketList,coinBox);
        }
    }
    @FXML
    public void openChosenTicket(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        expiredSession.stopTime();
        ticketCounter++;
        mainButtons.openChosenTicket(event, mainController,ticketList,hashMap,coinBox);

    }

    @Override
    public void setTicketList(TicketList ticketList)
    {
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

    private void fillHashMap()
    {
        hashMap.put(button1,text1);
        hashMap.put(button2,text1);
        hashMap.put(button3,text2);
        hashMap.put(button4,text2);
        hashMap.put(button7,text4);
        hashMap.put(button8,text4);
        hashMap.put(button9,text5);
        hashMap.put(button10,text5);
        hashMap.put(button11,text6);
        hashMap.put(button12,text6);
        hashMap.put(button17,text9);
        hashMap.put(button18,text9);
        hashMap.put(button19,text10);
        hashMap.put(button20,text10);
        hashMap.put(button21,text11);
    }
}
