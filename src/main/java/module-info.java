module com.example.OOPGame_Solovey {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens com.example.OOPGame_Solovey to javafx.fxml;
    exports com.example.OOPGame_Solovey;
    exports com.example.OOPGame_Solovey.Player;
    opens com.example.OOPGame_Solovey.Player to javafx.fxml;
}