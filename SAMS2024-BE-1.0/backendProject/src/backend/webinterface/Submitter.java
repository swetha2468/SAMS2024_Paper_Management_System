package backend.webinterface;

import app.gui.papers;
import app.gui.users;
import backend.enums.PaperFormat;
import backend.webinterface.systems.UserAccountSystem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;

public class Submitter {
    User mUser;

    public Submitter(User inUser) {
        mUser = inUser;
    }

    public ObservableList<papers> GetPapers() throws SQLException {
        return mUser.paperSystem.GetPapers(mUser.GetUserId());
    }

    public User getUser(){
        return mUser;
    }

    public void CreatePaper(String title, Integer submitterId, boolean isSubmitter, PaperFormat paperFormat, String authors, String version, String fileName) throws SQLException, IOException {
        mUser.paperSystem.CreatePaper(title, submitterId,  isSubmitter,  paperFormat,  authors,  version,  fileName);
    }
}
