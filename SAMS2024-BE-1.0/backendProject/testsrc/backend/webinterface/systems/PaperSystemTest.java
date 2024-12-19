package backend.webinterface.systems;

import backend.database.SAMS2024DbInterface;
import backend.enums.ConferenceDeadLineType;
import backend.enums.PaperFormat;
import backend.webinterface.systems.NotificationSystem;
import backend.webinterface.systems.PaperSystem;
import backend.webinterface.systems.UserAccountSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import app.gui.papers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaperSystemTest {

    private SAMS2024DbInterface mockDatabase;
    private NotificationSystem mockNotificationSystem;
    private UserAccountSystem mockAccountSystem;
    private PaperSystem paperSystem;

    @BeforeEach
    void setUp() {
        mockNotificationSystem = new NotificationSystem();
        mockAccountSystem = new UserAccountSystem(mockDatabase, false);
        paperSystem = new PaperSystem(mockDatabase, mockAccountSystem, false);
    }

    @Disabled
    @Test
    void createPaper() throws SQLException {
        String title = "Sample Paper";
        Integer submitterId = 1;
        boolean isSubmitter = true;
        PaperFormat paperFormat = PaperFormat.PDF;
        String authors = "Author 1, Author 2";
        String version = "1.0";
        String fileName = "sample_paper.pdf";

        paperSystem.CreatePaper(title, submitterId, isSubmitter, paperFormat, authors, version, fileName);

        // Assuming some method to retrieve the created paper ID, replace it with your actual logic.
        Integer createdPaperId = 123;

        Paper createdPaper = paperSystem.GetPaper(createdPaperId);

        // Assertions
        assertEquals(title, createdPaper.getTitle());
        // Add more assertions based on your Paper class properties.
    }

    @Test
    @Disabled
    void getPaper() {
        Integer paperId = 1;

        Paper result = paperSystem.GetPaper(paperId);

        // Assertions
        assertEquals(paperId, result.getPaperId());
        // Add more assertions based on your Paper class properties.
    }

    @Test
    void listPapers() {
        Integer[] paperIds = {1, 2, 3};

        Paper[] result = paperSystem.ListPapers(paperIds);

        // Assertions
        assertEquals(paperIds.length, result.length);
        // Add more assertions based on your Paper class properties.
    }

    @Test
    void setDeadline() {
        ConferenceDeadLineType cdlType = ConferenceDeadLineType.REPORT_SUBMISSION;
        String day = "01";
        String month = "01";
        String year = "2023";
        Integer cldId = 1;

        paperSystem.SetDeadline(cdlType, day, month, year, cldId);

        // TODO: Add assertions for the SetDeadline method (e.g., check if the deadline was set)
    }

    @Test
    void choosePaperToReview() {
        // TODO: Add assertions for the ChoosePaperToReview method
    }

    @Test
    void setAuthors() {
        // TODO: Add assertions for the SetAuthors method
    }

    @Test
    @Disabled
    void getPapers() throws SQLException {
        int userId = 1;

        ObservableList<papers> result = paperSystem.GetPapers(userId);

        // TODO: Add assertions for the GetPapers method (e.g., check if the result is not null)
    }
}
