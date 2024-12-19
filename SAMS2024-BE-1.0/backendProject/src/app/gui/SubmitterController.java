package app.gui;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.io.File;

import backend.enums.PaperFormat;
import backend.webinterface.Submitter;
import backend.webinterface.WebInterface;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import static app.gui.App.PdfPopup;

//import static app.gui.App.PdfPopup;

public class SubmitterController implements Initializable {

    @FXML
    private TextField UploadPathway;

    @FXML
    private TextField AuthorTitleTextBox;

    @FXML
    private TextField PaperTitleTextBox;

    @FXML
    private TextField UploadPathwayTextBox;

    private Submitter submitter;

    @FXML
    private TableView<papers> paperList;
    @FXML
    private TableColumn<papers, Integer> col_paperId;
    @FXML
    private TableColumn <papers, String> col_paperTitle;
    @FXML
    private TableColumn <papers, String> col_paperStatus;
    //@FXML
    //private TableColumn <papers, String> col_paperDeadline;
    @FXML
    private TableColumn <papers, String> col_paperAuthors;
    @FXML
    private TableColumn <papers, String> col_paperVersion;
    @FXML
    private TableColumn <papers, String> col_paperFormat;
    @FXML
    private TableColumn <papers, String> col_filename;
    @FXML
    private TableColumn <papers, String> col_address;

    //
    @FXML
    private TableColumn <papers, RadioButton> col_viewFile;
    //


    @FXML
    private void Logout() throws IOException {
        App.GetWebInterface().getUser().Logout();
        App.setRoot("login");
    }

    @FXML
    private void Back() throws IOException {
        App.setRoot("submitter");
    }

    @FXML
    private void UploadPaper() throws IOException, SQLException {
        String filePath = UploadPathwayTextBox.getText();
        String fileNameWithoutExtension = getFileNameWithoutExtension(filePath);
        String fileExtension = getFileExtension(filePath);

        try
        {

            PaperFormat.valueOf(fileExtension.toLowerCase());
            System.out.print(AuthorTitleTextBox.getText());
            Date dateSubmitted = new java.util.Date();
            submitter.CreatePaper(PaperTitleTextBox.getText(), submitter.getUser().GetUserId(),  true,  PaperFormat.valueOf(fileExtension.toLowerCase()),  AuthorTitleTextBox.getText(), dateSubmitted.toString(), fileNameWithoutExtension);
            App.setRoot("submitter");
        } catch (IllegalArgumentException err)
        {
            App.setErrorCondition("Invalid format for paper upload (pdf and words docs (doc) only");
            App.setRoot("error");
        }


    }

    private String getFileNameWithoutExtension(String path) {
        // Extract the filename with extension
        String fileNameWithExtension = path.substring(path.lastIndexOf(File.separator) + 1);
        // Remove the extension
        return fileNameWithExtension.contains(".") ? fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf('.')) : fileNameWithExtension;
    }

    private String getFileExtension(String path) {
        return path.substring(path.lastIndexOf('.') + 1);
    }
    @FXML
    private void EditPaper() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("login");
    }
    @FXML
    private void UpdatePaper() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("login");
    }

    @FXML
    private void DeletePaper() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("login");
    }
    @FXML
    private void WithdrawPaper() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("login");
    }
    @FXML
    private void SubmitPaper() throws IOException
    {
        /* TODO : HOOKUP TO BACKEND */
        //UploadPathway.setText("TODO : HOOKUP TO BACKEND");
        //System.out.println(UploadPathway.getPromptText() + " : " + UploadPathway.getText());
        App.setRoot("submitterSubmitPaper");
    }

    private void Display()
    {
        //listChanged = false;
        ObservableList<papers> papersObservableList;
        try {
            papersObservableList = submitter.GetPapers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //username, password, primary_role, firstname, lastname, email, phone, address;
        col_paperId.setCellValueFactory(new PropertyValueFactory<>("paperId"));

        col_paperTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_paperTitle.setCellFactory(TextFieldTableCell.forTableColumn());

        col_paperStatus.setCellValueFactory(new PropertyValueFactory<>("isSubmitted"));
        col_paperStatus.setCellFactory(TextFieldTableCell.forTableColumn());

        col_paperAuthors.setCellValueFactory(new PropertyValueFactory<>("authors"));
        col_paperAuthors.setCellFactory(TextFieldTableCell.forTableColumn());

        col_paperVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
        col_paperVersion.setCellFactory(TextFieldTableCell.forTableColumn());

        col_paperFormat.setCellValueFactory(new PropertyValueFactory<>("format"));
        col_paperFormat.setCellFactory(TextFieldTableCell.forTableColumn());

        col_filename.setCellValueFactory(new PropertyValueFactory<>("filename"));
        col_filename.setCellFactory(TextFieldTableCell.forTableColumn());



        //
        col_viewFile.setCellValueFactory(param -> {
            RadioButton radioButton = new RadioButton();
            radioButton.setOnAction(event -> {
                try {
                    papers selectedPaper = param.getValue();

                    String filename = selectedPaper.getFilename();
                    String exten = selectedPaper.getFormat();

                    PdfPopup(submitter.getUser().GetUserId(), filename, exten);

                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            });

            return new SimpleObjectProperty<>(radioButton);
        });
        //
        paperList.setItems(papersObservableList);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        submitter = App.GetWebInterface().getUser().getSubmitterInstance();
        if(App.GetRoot().equals("submitter")) {
            Display();
        }
    }
}
