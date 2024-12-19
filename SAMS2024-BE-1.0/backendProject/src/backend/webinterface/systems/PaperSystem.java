package backend.webinterface.systems;

import app.gui.papers;
import backend.database.SAMS2024DbInterface;
import backend.database.columns.PaperCells;
import backend.database.tables.Tables;
import backend.enums.ConferenceDeadLineType;
import backend.enums.MessageType;
import backend.enums.TemplateType;
import backend.webinterface.Deadline;
import backend.enums.PaperFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaperSystem
{

    private ObservableList<papers> observeList;

    static SAMS2024DbInterface dataBase;
    static NotificationSystem notificationSystem;
    static boolean DebugOn;

    static UserAccountSystem accountSystem;


    public PaperSystem(SAMS2024DbInterface inDatabase, UserAccountSystem accountSystem, NotificationSystem notificationSystem, boolean inDebug)
    {
        dataBase = inDatabase;
        DebugOn = inDebug;
        this.accountSystem = accountSystem;
        this.notificationSystem = notificationSystem;
    }

    public void CreatePaper(String title, Integer submitterId,  boolean isSubmitter, PaperFormat paperFormat, String authors, String version, String fileName) throws SQLException, IOException {


        /* Todo fix me*/
        String readPath = Paths.get(System.getProperty("user.dir"), "src","app","gui","uploads", ("ConferencePaperSample" + "." + PaperFormat.pdf.name())).toString();
        File readPathFile = new File(readPath);

        String[] paperColumnDetails = new String[]{PaperCells.deadlineId.toString(), PaperCells.userId.toString(), PaperCells.isSubmitted.toString(), PaperCells.authors.toString(), PaperCells.title.toString(), PaperCells.format.toString(), PaperCells.version.toString(), PaperCells.paperContent.toString(), PaperCells.filename.toString()};
        String[] paperCellDetails = new String[]{"1", String.valueOf(submitterId), "1", authors, title, paperFormat.toString(), version, "null", fileName};
        String query = dataBase.InsertStatement(Tables.Papers.toString(), paperColumnDetails, paperCellDetails);
        dataBase.Insert(query);


        paperColumnDetails = new String[]{PaperCells.deadlineId.toString(), PaperCells.userId.toString(), PaperCells.isSubmitted.toString(), PaperCells.authors.toString(), PaperCells.title.toString(), PaperCells.format.toString(), PaperCells.version.toString(), PaperCells.filename.toString()};
        paperCellDetails = new String[]{"1", String.valueOf(submitterId), "1", authors, title, paperFormat.toString(), version, fileName};
        query = dataBase.CreateQueryStatement(PaperCells.paperId.toString(), Tables.Papers.name(), paperColumnDetails, paperCellDetails);
        ArrayList<String> results = dataBase.Select(query);
        if(results.size() == 1)
        {
            dataBase.InsertPaper(results.get(0), readPathFile);
        }


        notificationSystem.SetNotification(TemplateType.notification, "Submission Notification", MessageType.submission, title + " has been submitted is ready for review.");
    }
    public void SetDeadline(ConferenceDeadLineType cdlType, String day, String month, String year, Integer cldId)
    {
        Deadline deadline = new Deadline();
        deadline.SetDeadLine(cdlType, day, month, year, cldId);
    }

    public void ChoosePaperToReview(Integer userId, Integer paperId)
    {
        /* TODO: DATABASE HOOKUP */
    }
    public void SetAuthors(String[] authorName)
    {
        /* TODO: DATABASE HOOKUP */
    }

    public ObservableList<papers> GetPapers(int userId) throws SQLException
    {
        String[] paperTableCells = new String[]{ PaperCells.userId.toString()};
        String[] requirementList = new String[]{String.valueOf(userId)};
        String[] paperColumns = new String[]{PaperCells.paperId.toString(),  PaperCells.isSubmitted.toString(), PaperCells.authors.toString(), PaperCells.title.toString(), PaperCells.format.toString(), PaperCells.version.toString(),PaperCells.filename.toString()};
        String query = dataBase.CreateQueryStatement(paperColumns, Tables.Papers.toString(), paperTableCells, requirementList );
        if(DebugOn) {
            System.out.println(query);
        }
        observeList = FXCollections.observableArrayList();

        ResultSet rs = dataBase.SelectRs(query);
        if (rs != null)
        {
            while (rs.next())
            {
                observeList.add(new papers(rs.getInt(PaperCells.paperId.toString()),  rs.getString(PaperCells.isSubmitted.toString()), rs.getString(PaperCells.authors.toString()), rs.getString(PaperCells.title.toString()), rs.getString(PaperCells.format.toString()), rs.getString(PaperCells.version.toString()),rs.getString(PaperCells.filename.toString())));
            }
        }
        return observeList;
    }

}
