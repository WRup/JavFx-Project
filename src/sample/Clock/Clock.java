package sample.Clock;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Clock {
    private SimpleIntegerProperty minute = new SimpleIntegerProperty(this, "minute");
    private SimpleIntegerProperty hour = new SimpleIntegerProperty(this, "hour");
    private SimpleIntegerProperty second = new SimpleIntegerProperty(this, "second");
    private SimpleIntegerProperty day = new SimpleIntegerProperty(this, "day");
    private SimpleIntegerProperty month = new SimpleIntegerProperty(this, "month");
    private SimpleIntegerProperty year = new SimpleIntegerProperty(this, "year");
    private SimpleStringProperty time = new SimpleStringProperty(this,"time");
    private SimpleStringProperty date = new SimpleStringProperty(this,"date");

    public Clock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            second.set(LocalDateTime.now().getSecond());
            minute.set(cal.get(Calendar.MINUTE));
            hour.set(cal.get(Calendar.HOUR_OF_DAY));
            day.set(cal.get(Calendar.DAY_OF_MONTH));
            month.set(cal.get(Calendar.MONTH));
            year.set(cal.get(Calendar.YEAR));
            time.setValue(String.format("%02d",hour.getValue()) + ":" + String.format("%02d",minute.getValue()) + ":" + String.format("%02d",second.getValue()));
            date.setValue(String.format("%02d",day.getValue()) + "." + String.format("%02d",month.getValue()+1) + "." + String.format("%02d",year.getValue()));

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public StringProperty timeProperty(){
        return time;
    }
    public StringProperty dateProperty()
    {
        return date;
    }
}
