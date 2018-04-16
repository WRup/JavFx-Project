package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane stackpane = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        primaryStage.setTitle("Biletomat 1.0");
        primaryStage.setScene(new Scene(stackpane, 1268, 818));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
