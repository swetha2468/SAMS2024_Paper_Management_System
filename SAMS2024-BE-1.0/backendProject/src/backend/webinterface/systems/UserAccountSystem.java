package backend.webinterface.systems;

import app.gui.users;
import backend.database.columns.PaperCells;
import backend.enums.Role;
import backend.database.SAMS2024DbInterface;
import backend.database.columns.UserTableCells;
import backend.database.tables.Tables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/* Add comments */
/* Add more exception handling */

public class UserAccountSystem {

    static SAMS2024DbInterface dataBase;
    static NotificationSystem notificationSystem;
    static boolean DebugOn;

    private ObservableList<users> observeList;
    public UserAccountSystem(SAMS2024DbInterface inDatabase, boolean inDebug)
    {
        dataBase = inDatabase;
        DebugOn = inDebug;
    }
    public Integer SetRole(String username, Role role) throws SQLException
    {

        String[] userTableCells = new String[]{ UserTableCells.username.toString()};
        String[] updateCells = new String[]{ UserTableCells.primary_role.toString()};
        String[] updateList = new String[]{(String.valueOf(role))};
        String[] requirementList = new String[]{String.valueOf(username)};

        String query = dataBase.CreateSetterStatement(Tables.Users.toString(), updateCells, updateList, userTableCells, requirementList );
        return dataBase.Insert(query);
    }

    public Role GetRole(String username) throws SQLException
    {
        return GetRole(username, UserTableCells.primary_role);
    }
    public Role GetRole(String username, UserTableCells cell) throws SQLException
    {

        Role foundRole = Role.unknown;
        Role finalRole = foundRole;

        String[] userTableCells = new String[]{ UserTableCells.username.toString()};
        String[] requirementList = new String[]{String.valueOf(username)};
        String query = dataBase.CreateQueryStatement(cell.toString(), Tables.Users.toString(), userTableCells, requirementList );
        ArrayList<String> parseResult = dataBase.Select(query);

        if(parseResult.size() > 1)
        {
            throw new SQLException("More then one entry found!!");
        }
        else
        {
            if(parseResult.size() == 1) {
                try {
                    foundRole = Role.valueOf(parseResult.get(0));
                    finalRole = foundRole;
                } catch (IllegalArgumentException err) {
                    System.out.println("Invalid Role found");
                }
            }
            else
            {
                if(DebugOn) {System.out.println("No roles found");}
            }
        }
        return finalRole;
    }

    public ArrayList<Role> GetRoles(String username) throws SQLException
    {
        ArrayList<Role> finalRoles = new ArrayList<>();

        finalRoles.add(GetRole(username, UserTableCells.primary_role));
        finalRoles.add(GetRole(username, UserTableCells.secondary_role));
        finalRoles.add(GetRole(username, UserTableCells.tertiary_role));
        finalRoles.add(GetRole(username, UserTableCells.quaternary_role));
        return finalRoles;
    }

    public boolean Login(String userName, String password) throws SQLException
    {
        boolean loginSuccess = false;
        try
        {
           loginSuccess = ValidateLogin(userName) && GetPassword(userName, password);
        }
        catch (IndexOutOfBoundsException | SQLException err)
        {
            throw new SQLException("Invalid Credentials");
        }
        return loginSuccess;
    }
    public String SetPassword(String username, String password) throws SQLException {
        String table = Tables.Users.toString();
        String[] cells = new String[]{UserTableCells.password.toString()};
        String[] cellUpdates = new String[]{password};
        String[] requirementList = new String[]{UserTableCells.username.toString()};
        String[] requirements = new String[]{username};

        String query = dataBase.CreateSetterStatement(table, cells, cellUpdates, requirementList,requirements);
        if (dataBase.Update(query) == 1)
        {
            return "Update Successful";
        }
        else
        {
            return "Update Failed";
        }
    }
    public String SetUserName(String usernameFrom, String usernameTo) throws Exception {

        String table = Tables.Users.toString();
        String[] cells = new String[]{UserTableCells.username.toString()};
        String[] cellUpdates;
        if (IsNotUniqueUserName(usernameTo))
        {
            throw new Exception("Account exists in system.");
        }
        else {
            cellUpdates = new String[]{usernameTo};
        }

        String[] requirementList = new String[]{UserTableCells.username.toString()};
        String[] requirements = new String[]{String.valueOf(usernameFrom)};

        String query = dataBase.CreateSetterStatement(table, cells, cellUpdates, requirementList,requirements);
        if (dataBase.Update(query) == 1)
        {
            return "Update Successful: Username is : " + usernameTo;
        }
        else
        {
            return "Update Failed";
        }
    }

    public void SetUserName(int userId, String usernameTo) throws Exception {

        if(usernameTo.contains("@") && usernameTo.endsWith(".com"))
        {
            String table = Tables.Users.toString();
            String[] cells = new String[]{UserTableCells.username.toString()};
            String[] cellUpdates;

            cellUpdates = new String[]{usernameTo};
            String[] requirementList = new String[]{UserTableCells.userId.toString()};
            String[] requirements = new String[]{String.valueOf(userId)};

            String query = dataBase.CreateSetterStatement(table, cells, cellUpdates, requirementList, requirements);
            dataBase.Update(query);
        }
        else
        {
            throw new Exception("Incorrect username format, must be and email address.");
        }
    }
    public String SetFirstName(String username, String firstName) throws SQLException {
        String table = Tables.Users.toString();
        String[] cells = new String[]{UserTableCells.firstName.toString()};
        String[] cellUpdates = new String[]{firstName};
        String[] requirementList = new String[]{UserTableCells.username.toString()};
        String[] requirements = new String[]{String.valueOf(username)};

        //UPDATE Users SET firstName = 'joe' WHERE username = 'adam_admin@mail.com';
        String query = dataBase.CreateSetterStatement(table, cells, cellUpdates, requirementList,requirements);

        if (dataBase.Update(query) == 1)
        {
            return "Update Successful";
        }
        else
        {
            return "Update Failed";
        }
    }
    public String SetLastName(String username, String lastName) throws SQLException
    {
        String table = Tables.Users.toString();
        String[] cells = new String[]{UserTableCells.lastName.toString()};
        String[] cellUpdates = new String[]{lastName};
        String[] requirementList = new String[]{UserTableCells.username.toString()};
        String[] requirements = new String[]{String.valueOf(username)};
        String query = dataBase.CreateSetterStatement(table, cells, cellUpdates, requirementList,requirements);
        if (dataBase.Update(query) == 1)
        {
            return "Update Successful";
        }
        else
        {
            return "Update Failed";
        }

    }

    public boolean IsNotUniqueUserName(String username) throws SQLException {
        String [] cells = new String[]{UserTableCells.username.toString()};
        String [] requirements = new String[]{username};
        String query = dataBase.CreateQueryStatement(UserTableCells.username.toString(), Tables.Users.toString(), cells, requirements);
        ArrayList<String> result = dataBase.Select(query);
        return !result.isEmpty();
    }
    public int GetUserId(String username) throws SQLException {
        String[] cells = new String[]{UserTableCells.username.toString()};
        String[] requirements = new String[]{username};
        String query = dataBase.CreateQueryStatement(UserTableCells.userId.toString(), Tables.Users.toString(), cells, requirements);
        ArrayList<String> result = dataBase.Select(query);

        return Integer.parseInt(result.get(0));
    }



    /* TODO : SAFETY ISSUE, HIDE/ADMIN SHOULD NOT HAVE POWER */
    public String GetPassword(String username) throws SQLException {
        String finalResult = "";
        String [] cells = new String[]{UserTableCells.username.toString()};
        String [] requirements = new String[]{String.valueOf(username)};
        String query = dataBase.CreateQueryStatement(UserTableCells.password.toString(), Tables.Users.toString(), cells, requirements);
        ArrayList<String> result = dataBase.Select(query);
        if(DebugOn) {System.out.println(result.get(0));}
        try {
            finalResult = result.get(0);
        }
        catch(IndexOutOfBoundsException err)
        {
            System.out.println("Throwing sql exception");
            throw new SQLException("Invalid Credentials");
        }
        return finalResult;
    }

    public boolean GetPassword(String username, String password) throws SQLException {
        String finalResult = "";
        String [] cells = new String[]{UserTableCells.username.toString()};
        String [] requirements = new String[]{String.valueOf(username)};
        String query = dataBase.CreateQueryStatement(UserTableCells.password.toString(), Tables.Users.toString(), cells, requirements);
        ArrayList<String> result = dataBase.Select(query);
        if(DebugOn) {System.out.println(result.get(0));}
        try {
            finalResult = result.get(0);
        }
        catch(IndexOutOfBoundsException err)
        {
            throw new SQLException("Invalid Credentials");
        }
        return Objects.equals(finalResult, password);
    }
    public String GetUsername(int userId) throws SQLException {
        String [] cells = new String[]{UserTableCells.userId.toString()};
        String [] requirements = new String[]{String.valueOf(userId)};
        String query = dataBase.CreateQueryStatement(UserTableCells.username.toString(), Tables.Users.toString(), cells, requirements);
        ArrayList<String> result = dataBase.Select(query);
        if(DebugOn) {System.out.println(result.get(0));}
        return result.get(0);
    }
    public String GetUsername(String username) throws SQLException {
        if(username.contains("@") && username.contains(".com"))
        {
            String [] cells = new String[]{UserTableCells.username.toString()};
            String [] requirements = new String[]{username};
            String query = dataBase.CreateQueryStatement(UserTableCells.username.toString(), Tables.Users.toString(), cells, requirements);
            ArrayList<String> result = dataBase.Select(query);
            if(DebugOn) {System.out.println(result.get(0));}
            return result.get(0);
        }
        else
        {
            throw new SQLException("Please enter an email address for username.");
        }
    }

    public boolean ValidateLogin(String username) throws SQLException {
        String [] cells = new String[]{UserTableCells.username.toString()};
        String [] requirements = new String[]{username};
        String query = dataBase.CreateQueryStatement(UserTableCells.username.toString(), Tables.Users.toString(), cells, requirements);
        ArrayList<String> result = dataBase.Select(query);
        return !result.isEmpty();

    }

    public String GetFirstName(int userId) throws SQLException {
        String [] cells = new String[]{UserTableCells.userId.toString()};
        String [] requirements = new String[]{String.valueOf(userId)};
        String query = dataBase.CreateQueryStatement(UserTableCells.firstName.toString(), Tables.Users.toString(), cells, requirements);
        ArrayList<String> result = dataBase.Select(query);
        if (DebugOn) {System.out.println(result.get(0));}
        return result.get(0);
    }
    public String GetLastName(int userId) throws SQLException {
        String [] cells = new String[]{UserTableCells.userId.toString()};
        String [] requirements = new String[]{String.valueOf(userId)};
        String query = dataBase.CreateQueryStatement(UserTableCells.lastName.toString(), Tables.Users.toString(), cells, requirements);
        ArrayList<String> result = dataBase.Select(query);
        if (DebugOn) {System.out.println(result.get(0));}
        return result.get(0);
    }

    public void CreateAccount(users user) throws Exception {

        //Create Account
        if (IsNotUniqueUserName(user.getUsername())) {
            throw new Exception("Account exists in system.");
        }
        if (user.getUsername().contains("@") && user.getUsername().endsWith(".com"))
        {
            if (user.getEmail().contains("@") && user.getEmail().endsWith(".com")) {
                if (user.getPhone().matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d"))
                {
                    String table = Tables.Users.toString();
                    String[] cells = new String[]{UserTableCells.email.toString(), UserTableCells.phone.toString(), UserTableCells.address.toString(), UserTableCells.username.toString(), UserTableCells.password.toString(), UserTableCells.firstName.toString(), UserTableCells.lastName.toString(), UserTableCells.primary_role.toString()};
                    String[] cellUpdates = new String[]{user.getEmail(), user.getPhone(), user.getEmail(), user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getPrimary_role()};
                    String query = dataBase.InsertStatement(table, cells, cellUpdates);
                    dataBase.Insert(query);
                }
                else
                {
                    throw new Exception("Need a phone number in standard format area code then phone number 122233344444");
                }
            }
            else
            {
                throw new Exception("Incorrect email format, must be and email address e.g.(test@email.com).");
            }
        }
        else
        {
            throw new Exception("Must contain an email address in username");
        }

    }





    public ObservableList<users> GetAccounts() throws SQLException
    {
        observeList = FXCollections.observableArrayList();

        String query = dataBase.CreateQueryStatement("*", Tables.Users.toString());
        ResultSet rs = dataBase.SelectRs(query);
        if (rs != null)
        {
            while (rs.next())
            {
                //int userId = Integer.parseInt(rs.getString(UserTableCells.userId.toString()));
                observeList.add(new users(rs.getInt(UserTableCells.userId.toString()),  rs.getString(UserTableCells.username.toString()), rs.getString(UserTableCells.password.toString()), rs.getString(UserTableCells.primary_role.toString()), rs.getString(UserTableCells.firstName.toString()), rs.getString(UserTableCells.lastName.toString()),rs.getString(UserTableCells.email.toString()), rs.getString(UserTableCells.phone.toString()), rs.getString(UserTableCells.address.toString())));
            }
        }
        return observeList;
    }

    public void DeleteAccount(String username) throws SQLException {

        if (ValidateLogin(username))
        {
            String[]columns = new String[]{PaperCells.userId.toString()};
            String[] cells = new String[]{String.valueOf(GetUserId(username))};

            String query = dataBase.CreateDeleteStatement(Tables.Papers.toString(), columns, cells);
            dataBase.Delete(query);
            query = dataBase.CreateDeleteStatement(Tables.Users.toString(), columns, cells);
            dataBase.Delete(query);
        }
    }

    public void SetEmail(String username, String email) throws Exception {
        if(email.contains("@") && email.endsWith(".com"))
        {
            String table = Tables.Users.toString();
            String[] cells = new String[]{UserTableCells.email.toString()};
            String[] cellUpdates;

            cellUpdates = new String[]{email};
            String[] requirementList = new String[]{UserTableCells.username.toString()};
            String[] requirements = new String[]{String.valueOf(username)};

            String query = dataBase.CreateSetterStatement(table, cells, cellUpdates, requirementList, requirements);
            dataBase.Update(query);
        }
        else
        {
            throw new Exception("Incorrect email format, must be and email address.");
        }
    }

    public void SetPhone(String username, String phone) throws Exception {
        if (phone.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d"))
        {
            String table = Tables.Users.toString();
            String[] cells = new String[]{UserTableCells.phone.toString()};
            String[] cellUpdates;

            cellUpdates = new String[]{phone};
            String[] requirementList = new String[]{UserTableCells.username.toString()};
            String[] requirements = new String[]{String.valueOf(username)};

            String query = dataBase.CreateSetterStatement(table, cells, cellUpdates, requirementList, requirements);
            dataBase.Update(query);
        }
        else {
            throw new Exception("Need a phone number in standard format area code then phone number 122233344444");
        }
    }

    public void SetAddress(String username, String address) {
    }
}


