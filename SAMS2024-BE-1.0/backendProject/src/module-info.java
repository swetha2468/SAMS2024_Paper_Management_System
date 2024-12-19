module app.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires com.fasterxml.jackson.core;
    //requires com.fasterxml.jackson.databind;

    opens app.gui to javafx.fxml;
    exports app.gui;
}
