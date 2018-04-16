package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.ButtonsLogic.MainButtons;
import sample.Coins.CoinBox;
import sample.Interface.IMethods;
import sample.Tickets.TicketList;


import static sample.Controllers.MainController.actualController;

public class MenuController implements IController, IMethods {
    private TicketList ticketList;
    private MainButtons mainButtons;
    private CoinBox coinBox;
    @FXML
    private MainController mainController;
    @FXML
    public void initialize(){
        actualController = this.getClass().getSimpleName();
        mainButtons = new MainButtons();
        ticketList = new TicketList();
    }
    @FXML
    public void openSingleArea(){
        mainButtons.openSingleArea(mainController,ticketList,coinBox);
    }
    @FXML
    public void openAgroArea(){
        mainButtons.openAgroArea(mainController,ticketList,coinBox);
    }

    @FXML
    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }
    @Override
    public void setTicketList(TicketList ticketList)
    {
        this.ticketList=ticketList;
    }
    @Override
    public void setCoinBox(CoinBox coinBox) { this.coinBox = coinBox;}

    @Override
    public void setSession(MainController mainController, CoinBox coinBox) {
    }

    @Override
    public void setClockAndDate(MainController mainController) {
    }
}
