package app.gui;

import backend.webinterface.Pcc;
import backend.webinterface.WebInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PCCController implements Initializable {


    Pcc pccInstance;
    @FXML
    private TextField UploadPathway;

    @FXML
    private void Logout() throws IOException {
        App.GetWebInterface().getUser().Logout();
        App.setRoot("login");
    }

    @FXML
    private void SubmitPaper() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("login");
    }

    @FXML
    private void EditPaper() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("login");
    }

    @FXML
    private void WithdrawPaper() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("primary");
    }

    @FXML
    private void UploadPaper() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        UploadPathway.setText("TODO : HOOKUP TO BACKEND");
        System.out.println(UploadPathway.getPromptText() + " : " + UploadPathway.getText());
        App.setRoot("login");
    }
    @FXML
    private void OpenMailbox() throws IOException {

        String user_name = "Soudagar";
        Label label_username = new Label("Notifications for: " + user_name);
        label_username.setPadding(new Insets(10.0));
        Region spacer = new Region();
        spacer.setPrefWidth(180.0);
        Button back_btn = new Button("Go Back");
        back_btn.setId("GoBack");
        HBox root_hbox = new HBox();
        root_hbox.getChildren().add(label_username);
        root_hbox.getChildren().add(spacer);
        root_hbox.getChildren().add(back_btn);
        HBox.setMargin(back_btn, new Insets(15.0, 0.0, 0.0, 0.0));
        VBox root = new VBox();
        root.getChildren().add(root_hbox);
        back_btn.setOnAction((e) -> {
            try {
                Scene scene = new Scene(App.loadFXML("pcc"));
                App.GetStage().setScene(scene);
                App.GetStage().sizeToScene();
                App.GetStage().show();
                App.SetScene(scene);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        ArrayList<Notification> tempList = null;
        try
        {
            tempList = App.GetWebInterface().getNotificationSystem().GetNotifications();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Notification item : tempList)
        {

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("NotificationCard.fxml"));
            //App.setRoot("NotificationCard");
            Parent card = (Parent)loader.load();
            NotificationCardController controller = (NotificationCardController)loader.getController();
            controller.setNotification(item);
            root.getChildren().add(card);
            System.out.println(item.getTitle() + " " + item.getMessage() );
        }
        Scene scene = new Scene(root, 500.0, 500.0);
        Stage tempState = App.GetStage();
        tempState.setScene(scene);
        tempState.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}
