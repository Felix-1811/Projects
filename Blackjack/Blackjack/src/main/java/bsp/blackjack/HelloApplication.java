package bsp.blackjack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override

    public void start(Stage stage) {
        Mypane view = new Mypane(); // View erstellt Controller intern
        Scene scene = new Scene(view, 600, 500);
        stage.setTitle("Blackjack");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
