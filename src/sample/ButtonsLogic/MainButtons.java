package sample.ButtonsLogic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import sample.Change.Change;
import sample.Coins.CoinBox;
import sample.Controllers.*;
import sample.Parser.TicketParser;
import sample.Tickets.TicketList;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static sample.Controllers.MainController.lastScene;
import static sample.Controllers.MainController.pre_lastScene;
import static sample.Controllers.PaymentAreaController.coinCounter;

public class MainButtons {

    public void openLastScreen(MainController mainController, TicketList ticketList, CoinBox coinBox) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String url = pre_lastScene.toString().substring(pre_lastScene.toString().lastIndexOf('/') +1);
        pre_lastScene=lastScene;
        lastScene = this.getClass().getResource("../fxml/" + url);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/" + url));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Object object;
        object = loader.getController();
        Method getLabel = object.getClass().getMethod("setMainController", MainController.class);
        getLabel.invoke(object,mainController);
        Method getLabel_2 = object.getClass().getMethod("setTicketList", TicketList.class);
        getLabel_2.invoke(object,ticketList);
        Method getLabel_3 = object.getClass().getMethod("setCoinBox", CoinBox.class);
        getLabel_3.invoke(object,coinBox);
        Method getLabel_4 = object.getClass().getMethod("setSession", MainController.class, CoinBox.class);
        getLabel_4.invoke(object,mainController,coinBox);
        Method getLabel_5 = object.getClass().getMethod("setClockAndDate", MainController.class);
        getLabel_5.invoke(object,mainController);
        mainController.setScreen(pane);
    }

    public void openSingleArea(MainController mainController, TicketList ticketList, CoinBox coinBox){
        pre_lastScene = lastScene;
        lastScene = this.getClass().getResource("../fxml/SingleArea.fxml");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/SingleArea.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        SingleAreaController singleAreaController = loader.getController();
        singleAreaController.setMainController(mainController);
        singleAreaController.setTicketList(ticketList);
        singleAreaController.setCoinBox(coinBox);
        singleAreaController.setSession(mainController,coinBox);
        singleAreaController.setClockAndDate(mainController);
        mainController.setScreen(pane);
    }

    public void openAgroArea(MainController mainController, TicketList ticketList, CoinBox coinBox){
        pre_lastScene = lastScene;
        lastScene = this.getClass().getResource("../fxml/AgroArea.fxml");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/AgroArea.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        AgroAreaController agroAreaController = loader.getController();
        agroAreaController.setMainController(mainController);
        agroAreaController.setTicketList(ticketList);
        agroAreaController.setCoinBox(coinBox);
        agroAreaController.setSession(mainController,coinBox);
        agroAreaController.setClockAndDate(mainController);
        mainController.setScreen(pane);
    }

    public void chooseAmount(MainController mainController, TicketList ticketList, CoinBox coinBox){
        pre_lastScene = lastScene;
        lastScene = this.getClass().getResource("../fxml/ChooseAmount.fxml");
        FXMLLoader loader =  new FXMLLoader(this.getClass().getResource("../fxml/ChooseAmount.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        ChooseAmountController chooseAmountController = loader.getController();
        chooseAmountController.setMainController(mainController);
        chooseAmountController.setTicketList(ticketList);
        chooseAmountController.setCoinBox(coinBox);
        chooseAmountController.setSession(mainController,coinBox);
        chooseAmountController.setClockAndDate(mainController);
        mainController.setScreen(pane);
    }
    public void openPaymentArea(MainController mainController, TicketList ticketList, double price, CoinBox coinBox) {
        pre_lastScene = lastScene;
        lastScene = this.getClass().getResource("../fxml/PaymentArea.fxml");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/PaymentArea.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PaymentAreaController paymentAreaController = loader.getController();
        paymentAreaController.setMainController(mainController);
        paymentAreaController.setTicketList(ticketList);
        paymentAreaController.setCoinBox(coinBox);
        paymentAreaController.setSession(mainController,coinBox);
        paymentAreaController.setClockAndDate(mainController);
        paymentAreaController.fillGridPane(price);
        mainController.setScreen(pane);
    }
    public void openChosenTicket(ActionEvent event, MainController mainController, TicketList ticketList, HashMap<Button,TextArea> hashMap, CoinBox coinBox) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        pre_lastScene = lastScene;
        lastScene = this.getClass().getResource("../fxml/ChosenTicket.fxml");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/ChosenTicket.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        ChosenTicketController chosenTicketController = loader.getController();
        chosenTicketController.setMainController(mainController);
        Object object = event.getSource();
        Method getLabel = object.getClass().getMethod("getText");
        TicketParser ticketParser = new TicketParser(getLabel.invoke(object).toString(), hashMap.get(object).getText());
        ticketList.add(ticketParser.setTicketParams());
        chosenTicketController.setTicketList(ticketList);
        chosenTicketController.setCoinBox(coinBox);
        chosenTicketController.setSession(mainController, coinBox);
        chosenTicketController.setClockAndDate(mainController);
        chosenTicketController.fillGridPane();
        mainController.setScreen(pane);
    }

    public void backToChosenTicket(MainController mainController, TicketList ticketList,CoinBox coinBox)
    {
        pre_lastScene = lastScene;
        lastScene = this.getClass().getResource("../fxml/ChosenTicket.fxml");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/ChosenTicket.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        ChosenTicketController chosenTicketController = loader.getController();
        chosenTicketController.setMainController(mainController);
        chosenTicketController.setTicketList(ticketList);
        chosenTicketController.setCoinBox(coinBox);
        chosenTicketController.setSession(mainController, coinBox);
        chosenTicketController.setClockAndDate(mainController);
        chosenTicketController.fillGridPane();
        mainController.setScreen(pane);
    }
    public void openFirstMenu(MainController mainController, CoinBox coinBox){
        pre_lastScene = lastScene;
        lastScene = this.getClass().getResource("../fxml/FirstMenu.fxml");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/FirstMenu.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Change change = new Change();
        change.noChange(coinBox,coinCounter);
        coinCounter = 0;

        MenuController menuController = loader.getController();
        menuController.setMainController(mainController);
        menuController.setCoinBox(coinBox);
        mainController.setScreen(pane);
    }
}
