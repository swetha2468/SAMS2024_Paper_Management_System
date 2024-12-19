package app.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NotificationCardController {
    @FXML
    private Label titleLabel;
    @FXML
    private Label messageLabel;

    public NotificationCardController() {
    }

    public void setNotification(Notification notification) {
        this.titleLabel.setText(notification.getTitle());
        this.messageLabel.setText(notification.getMessage());
    }

}
