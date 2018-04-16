package sample.Controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import javafx.scene.text.Text;
import sample.ButtonsLogic.ButtonSettings;
import sample.ButtonsLogic.MainButtons;

import sample.Clock.Clock;
import sample.Coins.CoinBox;
import sample.Interface.IMethods;
import sample.Tickets.TicketList;
import sample.Timer.ExpiredSession;


import java.lang.reflect.InvocationTargetException;



public class ChooseAmountController extends ButtonSettings implements IController, IMethods {


    private TicketList ticketList;
    private MainButtons mainButtons;
    private MainController mainController;
    private ExpiredSession expiredSession;
    private Clock clock;
    private CoinBox coinBox;
    @FXML
    private TextField ticketTextField;
    @FXML
    private Text actualTicketField;
    @FXML
    private Text maxAmountText;
    @FXML
    private Button butt0,butt1,butt2,butt3,butt4,butt5,butt6,butt7,butt8,butt9, buttApply;
    @FXML
    private Label clockLabel, dateLabel;
    @FXML
    public void initialize()
    {
        ticketTextField.setText("");
        setText(butt0,butt0,buttApply,ticketTextField);
        setText(butt1,butt0,buttApply,ticketTextField);
        setText(butt2,butt0,buttApply,ticketTextField);
        setText(butt3,butt0,buttApply,ticketTextField);
        setText(butt4,butt0,buttApply,ticketTextField);
        setText(butt5,butt0,buttApply,ticketTextField);
        setText(butt6,butt0,buttApply,ticketTextField);
        setText(butt7,butt0,buttApply,ticketTextField);
        setText(butt8,butt0,buttApply,ticketTextField);
        setText(butt9,butt0,buttApply,ticketTextField);
        mainButtons = new MainButtons();
    }
    @FXML
    public void setMainController(MainController mainController) {
        this.mainController=mainController;
    }
    @FXML
    public void openMainMenu()
    {
        expiredSession.stopTime();
        mainButtons.openFirstMenu(mainController,coinBox);
    }
    @FXML
    public void openLastScreen() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        expiredSession.stopTime();
        mainButtons.backToChosenTicket(mainController,ticketList,coinBox);
    }
    @FXML
    public void clearTextField()
    {
        ticketTextField.setText("");
        butt0.setDisable(true);
        buttApply.setDisable(true);
    }
    @FXML
    public void applyAmount() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(Integer.valueOf(ticketTextField.getText())>15)
        {
            this.clearTextField();
            maxAmountText.setStyle("-fx-fill: red");
            return;
        }
        expiredSession.stopTime();
        ticketList.getTicketList().get(0).setAmount(Integer.valueOf(ticketTextField.getText()));
        mainButtons.backToChosenTicket(mainController,ticketList,coinBox);
    }

    @Override
    public void setTicketList(TicketList ticketList) {
        this.ticketList = ticketList;
        actualTicketField.setText(ticketList.getTicketList().get(0).getName());
    }
    @Override
    public void setCoinBox(CoinBox coinBox){this.coinBox = coinBox;}
    @Override
    public void setSession(MainController mainController, CoinBox coinBox){
        this.expiredSession = new ExpiredSession(mainController,mainButtons,60,coinBox);}
    @Override
    public void setClockAndDate(MainController mainController){
        clock = mainController.getClock();
        clockLabel.textProperty().bind(Bindings.convert(clock.timeProperty()));
        dateLabel.textProperty().bind(Bindings.convert(clock.dateProperty()));
    }
}
