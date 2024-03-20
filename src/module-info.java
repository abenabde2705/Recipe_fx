module com.example.traitement_recettes_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.xml;

    opens Presentation to javafx.fxml;
    exports Presentation;
    exports Repositories;
    opens Repositories to javafx.fxml;
}