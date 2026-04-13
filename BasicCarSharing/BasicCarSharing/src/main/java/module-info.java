module bsp.aufgabe11 {
    requires javafx.controls;
    requires javafx.fxml;


    opens bsp.aufgabe11 to javafx.fxml;
    exports bsp.aufgabe11;
}