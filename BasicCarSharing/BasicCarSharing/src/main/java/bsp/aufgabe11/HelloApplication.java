package bsp.aufgabe11;


import bsp.aufgabe11.MODEL.UngueltigerParameterException;
import bsp.aufgabe11.Mypane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, UngueltigerParameterException {
        Mypane root = new Mypane();
        Scene scene = new Scene(root, 500, 600);
        stage.setTitle("Taxi Vermeitung");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}