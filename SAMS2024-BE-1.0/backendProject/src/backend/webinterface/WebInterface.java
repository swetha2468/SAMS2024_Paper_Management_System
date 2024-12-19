package backend.webinterface;

import backend.database.SAMS2024DbInterface;
import backend.enums.Role;
import backend.webinterface.systems.NotificationSystem;
import backend.webinterface.systems.PaperSystem;
import backend.webinterface.systems.ReportSystem;
import backend.webinterface.systems.UserAccountSystem;

import java.sql.SQLException;

public class WebInterface
{
    SAMS2024DbInterface database;
    UserAccountSystem userAccountSystem;
    PaperSystem paperSystem;
    ReportSystem reportSystem;

    NotificationSystem notificationSystem;
    User userInstance;

    boolean DebugOn;

    public WebInterface(boolean DebugOn) throws SQLException, ClassNotFoundException {

        this.DebugOn = DebugOn;
        database = new SAMS2024DbInterface(this.DebugOn);
        userAccountSystem = new UserAccountSystem(database, this.DebugOn);
        notificationSystem = new NotificationSystem(database, this.DebugOn);
        paperSystem = new PaperSystem(database, userAccountSystem, notificationSystem, this.DebugOn);
        reportSystem = new ReportSystem();
        userInstance = new User(userAccountSystem, paperSystem, this.DebugOn);

        //System.out.println(userAccountSystem.CreateAccount("adam21", "adam21", "adam", "adam", Role.submitter, "email.com", "1234567890", "123 adam street"));
    }

    public void Shutdown() throws SQLException {
        database.StopDatabase();
    }
    public User getUser()
    {
        return userInstance;
    }
    public SAMS2024DbInterface getDatabase()
    {
        return database;
    }

    public NotificationSystem getNotificationSystem()
    {
        return notificationSystem;
    }

    public UserAccountSystem GetUserAccountSystem()
    {
        return userAccountSystem;
    }
}
