module net.ramgames.modloader {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens net.ramgames.modloader to javafx.fxml;
    exports net.ramgames.modloader;
}