module com.example.javafxpractice {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.javafxpractice to javafx.fxml;
    exports com.example.javafxpractice;
}