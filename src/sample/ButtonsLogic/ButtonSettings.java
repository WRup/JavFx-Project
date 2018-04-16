package sample.ButtonsLogic;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public abstract class ButtonSettings {
    public void setText(Button button, Button zeroButton, Button buttApply, TextField textField){
        EventHandler<MouseEvent> mouseHandler = e -> {
            if(textField.getLength() < 2)
            textField.setText(textField.getText() + button.getText());
            zeroButton.setDisable(false);
            buttApply.setDisable(false);
            System.out.println(textField.getText());
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseHandler);
    }
}
