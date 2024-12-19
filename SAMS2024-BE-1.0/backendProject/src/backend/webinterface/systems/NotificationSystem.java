package backend.webinterface.systems;

import app.gui.Notification;
import backend.database.SAMS2024DbInterface;
import backend.database.columns.NotificationTemplateCells;
import backend.database.columns.PaperCells;
import backend.database.columns.TemplateCells;
import backend.database.tables.Tables;
import backend.enums.MessageType;
import backend.enums.TemplateType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationSystem
{
    SAMS2024DbInterface database;

    boolean mDebugOn;

    public NotificationSystem(SAMS2024DbInterface database, boolean DebugOn)
    {
        this.database = database;
        mDebugOn =  true;//DebugOn;
    }
    public String GetTypeOfMessage()
    {
        return MessageType.submission.toString();
    }
    public String SetTypeOfMessage()
    {
        return MessageType.submission.toString();
    }
    public String Notify()
    {
        return this.toString();
    }
    public ArrayList<Notification> GetNotifications() throws SQLException {

        String[] cell = {NotificationTemplateCells.message.name(), NotificationTemplateCells.typeOfTemplate.name()};
        String[] getColumns = new String[]{NotificationTemplateCells.typeOfTemplate.name()};
        String[] getCellContent = new String[]{MessageType.submission.name()};
        String query = database.CreateQueryStatement(cell, Tables.NotificationTemplates.name(), getColumns, getCellContent);
        ResultSet rs = database.SelectRs(query);
        ArrayList<Notification> notificationList = new ArrayList<Notification>();
        while(rs.next())
        {

            notificationList.add(new Notification(rs.getString(NotificationTemplateCells.typeOfTemplate.name()),rs.getString(NotificationTemplateCells.message.name())));
        }
        return notificationList;
    }
    public void SetNotification(TemplateType typeOfTemplate, String templateName, MessageType notificationTemplateType, String message) throws SQLException
    {
        String[] templateColumns = new String[]{TemplateCells.templateName.toString(), TemplateCells.typeOfTemplate.toString()};
        String[] templateCellDetails = new String[]{templateName, typeOfTemplate.name()};
        String firstQuery = database.InsertStatement(Tables.Templates.toString(), templateColumns, templateCellDetails);
        database.Insert(firstQuery);
        if(mDebugOn){System.out.println(firstQuery); }

        //Get this template id

        String[] notificationTemplateColumns = new String[]{NotificationTemplateCells.templateId.toString(), NotificationTemplateCells.typeOfTemplate.toString(), NotificationTemplateCells.message.toString()};
        String[] notificationTemplateCells = new String[]{"1", notificationTemplateType.name(), message};
        String secondQuery = database.InsertStatement(Tables.NotificationTemplates.toString(), notificationTemplateColumns, notificationTemplateCells);
        database.Insert(secondQuery);
        if(mDebugOn){System.out.println(secondQuery); }
    }
    public String SetMessage()
    {
        return this.toString();
    }
    public String GetMessage()
    {
        return this.toString();
    }

}
