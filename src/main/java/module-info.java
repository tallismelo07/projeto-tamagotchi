module com.example.projetotamagochi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.projetotamagochi to javafx.fxml;
    exports com.example.projetotamagochi;
    exports application;
    opens application to javafx.fxml;
}