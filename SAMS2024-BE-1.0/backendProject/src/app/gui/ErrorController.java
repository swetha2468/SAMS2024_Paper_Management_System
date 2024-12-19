package app.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class ErrorController implements Initializable{

    @FXML
    private Text errorConditionField1;

    @FXML
    private void returnToActiveScreen() throws IOException
    {
        App.setRoot(App.getPreviousScreen());
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
        errorConditionField1.setText(App.getErrorCondition());
    }
}
