package app.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PCMController implements Initializable {

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }
}
