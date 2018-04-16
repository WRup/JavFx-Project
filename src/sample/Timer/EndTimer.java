package sample.Timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sample.ButtonsLogic.MainButtons;
import sample.Coins.CoinBox;
import sample.Controllers.MainController;
import sample.Controllers.PaymentAreaController;

import static sample.Controllers.SingleAreaController.ticketCounter;

public class EndTimer {

    Timeline timeline;
    public EndTimer(MainController mainController, MainButtons mainButtons, PaymentAreaController paymentAreaController, int sec, CoinBox coinBox){
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(sec), ev -> {
            mainButtons.openFirstMenu(mainController,coinBox);
            ticketCounter =0;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
