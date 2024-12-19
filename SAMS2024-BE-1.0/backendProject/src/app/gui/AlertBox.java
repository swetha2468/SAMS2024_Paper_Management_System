package app.gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public AlertBox() {
    }

    public static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250.0);
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("OK");
        closeButton.setOnAction((e) -> {
            window.close();
        });
        VBox layout = new VBox(10.0);
        layout.getChildren().addAll(new Node[]{label, closeButton});
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 100.0, 100.0);
        window.setScene(scene);
        window.showAndWait();
    }
}

