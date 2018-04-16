package sample.Timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sample.Controllers.PaymentAreaController;

import static sample.Controllers.SingleAreaController.ticketCounter;

public class LabelTimer {


    Timeline timeline;
    public LabelTimer(PaymentAreaController paymentAreaController,boolean t, int sec){
            this.timeline = new Timeline(new KeyFrame(Duration.seconds(sec), ev -> {
                paymentAreaController.receivingTickets(t);
                ticketCounter =0;
            }));
            timeline.setCycleCount(1);
            timeline.play();
    }
}
