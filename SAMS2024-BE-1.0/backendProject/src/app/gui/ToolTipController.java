package app.gui;

import java.io.IOException;
import javafx.fxml.FXML;

  
public class ToolTipController {

    @FXML
    private void returnToPrimary() throws IOException
    {
        App.setRoot(App.getPreviousScreen());
    }
}


