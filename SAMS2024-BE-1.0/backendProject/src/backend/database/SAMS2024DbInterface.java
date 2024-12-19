package backend.database;

import backend.database.columns.PaperCells;
import backend.database.columns.UserTableCells;
import backend.database.tables.Tables;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;

public class SAMS2024DbInterface
 {
    static Connection connection;
    static boolean DebugOn;

    /* Could add a function to validate if certain updates are necessary */
    /* Not sure how to address unsafe string sql */
    /* Add comments */
   /* Add more exception handling */

    public SAMS2024DbInterface(boolean inDebug) throws SQLException, ClassNotFoundException {
        StartDatabase();
        DebugOn = inDebug;
    }
    public void StopDatabase() throws SQLException
    {
        if(!connection.isClosed()) {
            connection.close();
        }
    }
    private void StartDatabase() throws ClassNotFoundException, SQLException {
        boolean successful = false;

        String driverName = "com.mysql.cj.jdbc.Driver";

        //throws ClassNotFoundException if driver missing on intellij
        Class.forName(driverName);

        String serverName = "localhost:3306";
        String dataBase = "samsdb";
        String url = "jdbc:mysql://" + serverName + "/" + dataBase;


        String username = "root";
        String password = "saudagar123";

        if(System.getProperty("user.dir").contains("sskal")) {
            username = "stacy";
            password = "746Group2!";
        }

        if(System.getProperty("user.dir").contains("princy")) {
            username = "root";
            password = "746Group2!";
        }

        connection = DriverManager.getConnection(url, username, password);

        if(!connection.isClosed())
        {
            successful = true;
        }
        if(DebugOn) {System.out.println("Connection to DB..." + (successful ? "Successful" : "Failed"));}
    }

     public String CreateQueryStatement(String cell, String table)
     {
         return "SELECT " + cell +  " FROM " + table;
     }
     public String CreateQueryStatement(String cell, String table, String[] cells, String[] requirements)
     {
         StringBuilder query = new StringBuilder("SELECT " + cell +  " FROM " + table + " WHERE " + cells[0] + " = " + singleQuote(requirements[0]));
         return getRequirements(cells, requirements, query);
     }

     public String CreatePdfQueryStatement(String cell, String table, String[] cells, String[] requirements, String filepath)
     {
         StringBuilder query = new StringBuilder("SELECT " + cell +" INTO outfile " + filepath + "   FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'\n" +
                 "  LINES TERMINATED BY '\\n'" +  " FROM " + table + " WHERE " + cells[0] + " = " + singleQuote(requirements[0]));
         return getRequirements(cells, requirements, query);
     }


     public String CreateQueryStatement(String []getCells, String table, String[] cells, String[] requirements)
     {
         String formatGetCells = String.join(",", getCells);
         StringBuilder query = new StringBuilder("SELECT " + formatGetCells +  " FROM " + table + " WHERE " + cells[0] + " = " + singleQuote(requirements[0]));
         return getRequirements(cells, requirements, query);
     }

     public String CreateDeleteStatement(String table, String[] cells, String[] requirements)
     {
         StringBuilder query = new StringBuilder("DELETE FROM " + table + " WHERE " + cells[0] + " = " + singleQuote(requirements[0]));
         return getRequirements(cells, requirements, query);
     }

     private String getRequirements(String[] cells, String[] requirements, StringBuilder query) {
         int i = 1;
         while(i < requirements.length)
         {
             query.append(" AND ").append(cells[i]).append(" = ").append(singleQuote(requirements[i]));
             i++;
         }
         query.append(";");
         if(DebugOn) {System.out.println(query);}
         return query.toString();
     }

     public String CreateSetterStatement(String table, String[] cellUpdates, String[] updates, String[] cellRequirements, String[] requirements)
     {
         StringBuilder query = new StringBuilder("UPDATE " + table + " SET ");
         query.append(cellUpdates[0]).append(" = ").append(singleQuote(updates[0]));
         int i = 1;
         while(i < updates.length)
         {
             query.append(" AND ").append(cellUpdates[i]).append(" = ").append(singleQuote(updates[i]));
             i++;
         }
         if(cellRequirements.length > 0)
         {
             query.append(" WHERE ").append(cellRequirements[0]).append(" = ").append(singleQuote(requirements[0]));
         }
         return getRequirements(cellRequirements, requirements, query);
     }
     public ArrayList<String> Select(String query) throws SQLException
     {

         ArrayList<String> resultSet = new ArrayList<>();
             Statement testQuery = connection.createStatement();

         //risk of injection issue, make a story to track
         ResultSet rs = testQuery.executeQuery(query);
        if(rs != null) {
            while (rs.next()) {
                int numColumns = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= numColumns; i++) {
                    if (DebugOn) {
                        System.out.println("COLUMN " + i + " = " + rs.getObject(i));
                    }
                    if(rs.getObject(i) != null)
                    {
                        resultSet.add(rs.getObject(i).toString());
                    }
                }
            }
            rs.close();
        }
         testQuery.close();
         return resultSet;
     }
     public ResultSet SelectRs(String query) throws SQLException
     {
         Statement testQuery = connection.createStatement();
         //risk of injection issue, make a story to track
         return  testQuery.executeQuery(query);
     }

     public String InsertStatement(String table, String[] cellUpdates, String[] updates)
     {
         //INSERT INTO users (contactId, credentialId, primary_role, firstname, lastname) VALUES
         String query = "INSERT INTO " + table + " (";
         query = query + String.join(",", cellUpdates) + ")";
         for (int i = 0; i < updates.length; i++)
         {
             updates[i] = singleQuote(updates[i]);
         }
         query = query + "VALUES (" + String.join(",", updates) + ")";
         return query;

     }

     public Integer Update(String aQuery) throws SQLException
     {
         int updates = 0;
         Statement testQuery = connection.createStatement();
         try {

             updates = testQuery.executeUpdate(aQuery);
             if(DebugOn) {System.out.println("Update made: " + updates);}
         }
         catch(java.sql.SQLException err)
         {
             System.out.println("Failed to execute:" + err);
         }

         System.out.println(updates);
         return updates;
     }

     public Integer Insert(String aQuery) throws SQLException
     {
         int updates = 0;
         Statement testQuery = connection.createStatement();
         try {

             updates = testQuery.executeUpdate(aQuery);
             if(DebugOn) {System.out.println("Update made: " + updates);}
         }
         catch(java.sql.SQLException err)
         {
             System.out.println("Failed to execute:" + err);
         }

         System.out.println(updates);
         return updates;
     }

     public Integer Delete(String aQuery) throws SQLException
     {
         int updates = 0;
         Statement testQuery = connection.createStatement();
         try {

             updates = testQuery.executeUpdate(aQuery);
             if(DebugOn) {System.out.println("Update made: " + updates);}
         }
         catch(java.sql.SQLException err)
         {
             System.out.println("Failed to execute:" + err);
         }

         System.out.println(updates);
         return updates;
     }
    public void Upload()
    {
        /* TODO: SWETHA UPLOAD PDF*/
    }

    public void Download(String username, String tempPath, String filename, String exten) throws SQLException, IOException {
        GetPDF(username, tempPath, filename, exten);
    }
     public static String singleQuote(String inString) {
         return '\'' + inString + '\'';
     }

     public void GetPDF(String username, String tempPath, String filename, String exten) throws SQLException, IOException
     {

         String writePath = Paths.get(System.getProperty("user.dir"), "src","app","gui","uploads", "ConferencePaperSample" + "." + exten).toString();
         File writeFile = new File(writePath);
         String readPath = Paths.get(System.getProperty("user.dir"), "src","app","gui","downloads", filename + "." + exten).toString();
         File readFile = new File(readPath);
         Files.copy(writeFile.toPath(), readFile.toPath());
        /*
         String[] cells = new String[]{UserTableCells.username.toString()};
         String[] requirements = new String[]{username};
         String query = CreateQueryStatement(UserTableCells.userId.toString(), Tables.Users.toString(), cells, requirements);
         ArrayList<String> result = Select(query);

         int userId = Integer.parseInt(result.get(0));

         cells = new String[]{PaperCells.userId.toString(), PaperCells.filename.name(), PaperCells.format.name()};
         requirements = new String[]{String.valueOf(userId), filename, exten};
         query = CreatePdfQueryStatement(PaperCells.paperContent.toString(), Tables.Papers.toString(), cells, requirements, tempPath);
         SelectRs(query);*/

     }

     public void InsertPaper(String paperId, File readPath ) throws IOException {
         //update papers set paperContent=LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/TCP.pdf') where userId = @id
         String query ="Update papers set paperContent=LOAD_FILE(" + singleQuote(String.valueOf(readPath.toPath().toAbsolutePath())) + ")" + "where paperId =" + paperId;
         if (DebugOn)
         {
             System.out.println(query);
         }
     }



 }
