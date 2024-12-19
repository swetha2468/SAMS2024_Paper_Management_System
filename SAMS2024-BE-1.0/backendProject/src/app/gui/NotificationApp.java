package app.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotificationApp extends Application {
    public NotificationApp() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Notifications");
        Notification[] notifications = new Notification[]{new Notification("Notification 1", "This is the first notification."), new Notification("Notification 2", "Another notification."), new Notification("Notification 3", "One more notification.")};
        String user_name = "Soudagar";
        Label label_username = new Label("Notifications for: " + user_name);
        label_username.setPadding(new Insets(10.0));
        Region spacer = new Region();
        spacer.setPrefWidth(180.0);
        Button back_btn = new Button("Go Back");
        HBox root_hbox = new HBox();
        root_hbox.getChildren().add(label_username);
        root_hbox.getChildren().add(spacer);
        root_hbox.getChildren().add(back_btn);
        HBox.setMargin(back_btn, new Insets(15.0, 0.0, 0.0, 0.0));
        VBox root = new VBox();
        root.getChildren().add(root_hbox);
        Notification[] var9 = notifications;
        int var10 = notifications.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            Notification notification = var9[var11];
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("NotificationCard.fxml"));
            Parent card = (Parent)loader.load();
            NotificationCardController controller = (NotificationCardController)loader.getController();
            controller.setNotification(notification);
            root.getChildren().add(card);
        }

        Scene scene = new Scene(root, 500.0, 500.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

