package app.gui;


import backend.webinterface.WebInterface;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.HostServices;

import java.nio.file.Paths;

/**
 * JavaFX App
 */
public class App extends Application {

    @FXML
    private static Stage mStage;
    private static Scene scene;
    private static String errorCondition;
    private static String previousScreen;
    private static String currentScreen;
    private static WebInterface webInterface;

    private static HostServices hostServices = null;

    public App() throws SQLException, ClassNotFoundException {
        webInterface = new WebInterface(true);
        hostServices = getHostServices();
    }

    @Override
    public void start(Stage stage) throws IOException {
        mStage = stage;
        currentScreen = "login";
        previousScreen = "login";
        scene = new Scene(loadFXML("login"));
        mStage.setScene(scene);
        mStage.sizeToScene();
        mStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        previousScreen = currentScreen;
        currentScreen = fxml;
        scene.setRoot(loadFXML(fxml));
        mStage.sizeToScene();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setErrorCondition(String errorString) {
        errorCondition = errorString;
    }

    public static String getErrorCondition() {
        return errorCondition;
    }

    static String getPreviousScreen() {
        return previousScreen;
    }

    public static void main(String[] args) {
        launch();

    }
    public static WebInterface GetWebInterface()
    {
        return webInterface;
    }

    public static String GetRoot()
    {
        return currentScreen;
    }
    public static void PdfPopup(Integer userId, String filename, String exten) throws SQLException, IOException
    {
        String writePath = Paths.get(System.getProperty("user.dir"), "src","app","gui","downloads", filename + "." + exten).toString();
        String username = webInterface.GetUserAccountSystem().GetUsername(userId);

        webInterface.getDatabase().Download(username, writePath, filename, exten);
        File file = new File(writePath);
        hostServices.showDocument(file.getAbsolutePath());

    }

    public static Stage GetStage()
    {
        return mStage;
    }
    public static void SetScene(Scene scene)
    {
        App.scene = scene;
    }
    public void SetStage(Stage stage)
    {
        App.mStage = stage;
    }
}