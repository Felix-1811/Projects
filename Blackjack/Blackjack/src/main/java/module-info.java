module bsp.blackjack {
    requires javafx.controls;
    requires javafx.fxml;


    opens bsp.blackjack to javafx.fxml;
    exports bsp.blackjack;
}