package app.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import backend.webinterface.WebInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
        
    @FXML
    private TextField Username, Password;

    @FXML
    private void jumpToToolTip() throws IOException 
    {
        App.setRoot("tooltip");
    }

    @FXML
    private void Login() throws IOException {

        try
        {
            try
            {
                WebInterface webInterface = App.GetWebInterface();
                if(Username.getText().isEmpty())
                {
                    throw new Exception("Please enter a username");
                }
                else if (Password.getText().isEmpty())
                {
                    throw new Exception("Please enter a password");
                }
                else
                {
                    String role = webInterface.getUser().Login(Username.getText(), Password.getText());
                    App.setRoot(role.toLowerCase());
                }
            }
            catch (Exception err)
            {
                App.setErrorCondition(err.getMessage());
                App.setRoot("error");
            }
        }
        catch (IllegalArgumentException err)
        {
            App.setErrorCondition(err.getMessage());
            App.setRoot("error");
        }
        catch (IndexOutOfBoundsException err)
        {
            App.setErrorCondition("Invalid Credentials...");
            App.setRoot("error");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
