package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import sample.Clock.Clock;
import sample.Coins.Coin;
import sample.Coins.CoinBox;

import java.io.IOException;
import java.net.URL;

public class MainController {

    @FXML
    private StackPane mainStackPane;
    public static URL pre_lastScene;
    public static URL lastScene;
    public static String actualController;



    private CoinBox coinBox = new CoinBox();
    private Clock clock = new Clock();

    public MainController()
    {
        coinBox.add(new Coin(0.20));
        coinBox.add(new Coin(0.20));
        coinBox.add(new Coin(0.20));
        coinBox.add(new Coin(0.20));
        coinBox.add(new Coin(0.20));
    }
    @FXML
    public void initialize()
    {
        pre_lastScene = lastScene;
        lastScene = this.getClass().getResource("../fxml/FirstMenu.fxml");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/FirstMenu.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        menuController.setCoinBox(this.coinBox);
        setScreen(pane);
    }

    public void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }
    public CoinBox getCoinBox() {
        return coinBox;
    }
    public Clock getClock() { return clock; }
}
