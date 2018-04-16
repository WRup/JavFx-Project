package sample.Timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sample.ButtonsLogic.MainButtons;
import sample.Coins.CoinBox;
import sample.Controllers.MainController;

import static sample.Controllers.SingleAreaController.ticketCounter;

public class ExpiredSession {

    Timeline timeline;
    public ExpiredSession(MainController mainController, MainButtons mainButtons, int sec, CoinBox coinBox){
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(sec), ev -> {
            mainButtons.openFirstMenu(mainController,coinBox);
            ticketCounter =0;
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void resetTime()
    {
        this.timeline.stop();
        this.timeline.play();
    }
    public void stopTime()
    {
        this.timeline.stop();
    }

}
