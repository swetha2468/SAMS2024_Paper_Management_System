package backend.webinterface;

import app.gui.users;
import backend.enums.Role;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Administrator
{
    User mUser;

    public Administrator(User inUser)
    {
        mUser = inUser;

    }
    public String SetFirstName(String username, String password) throws SQLException {
        return mUser.userAccountSystem.SetFirstName(username, password);
    }
    public String SetLastName(String username, String password) throws SQLException {
       return  mUser.userAccountSystem.SetLastName(username, password);
    }

    public String SetUserName(String username, String password) throws Exception {
        return  mUser.userAccountSystem.SetUserName(username, password);
    }

    public String SetPassword(String username, String password) throws SQLException
    {
        return  mUser.userAccountSystem.SetPassword(username, password);
    }

    public void CreateAccount(users user) throws Exception
    {
        mUser.userAccountSystem.CreateAccount(user);
    }
    public void ModifyAccount(int userId, String username, String password, String firstname, String lastname, Role role, String email, String phone, String address) throws Exception
    {
        boolean loginSuccess = mUser.userAccountSystem.ValidateLogin(mUser.userAccountSystem.GetUsername(userId));
        if(loginSuccess)
        {
            if(password != null)
            {
                mUser.userAccountSystem.SetUserName(userId, username);
            }
            if(password != null)
            {
                mUser.userAccountSystem.SetPassword(username, password);
            }
            if(firstname != null)
            {
                mUser.userAccountSystem.SetFirstName(username, firstname);
            }
            if(lastname != null)
            {
                mUser.userAccountSystem.SetLastName(username, lastname);
            }
            if(role != null)
            {
                mUser.userAccountSystem.SetRole(username, role);
            }
            if(email != null)
            {
                mUser.userAccountSystem.SetEmail(username, email);
            }
            if(phone != null)
            {
                mUser.userAccountSystem.SetPhone(username, phone);
            }
            if(address != null)
            {
                mUser.userAccountSystem.SetAddress(username, address);
            }

        }

    }

    public String ModifyCredentials(String username, String password, String firstname, String lastname, Role role) throws SQLException
    {
        return "TODO: HOOKUP BACKEND";

    }
    public String ModifyContactInfo(String username, String password, String firstname, String lastname, Role role) throws SQLException
    {
        return "TODO: HOOKUP BACKEND";
    }

    public void DeleteAccount(String username) throws SQLException
    {
        mUser.userAccountSystem.DeleteAccount(username);
    }

    public Pcm GetPcm()
    {
        return mUser.pcmInstance;
    }
    public Pcc GetPcc()
    {
        return mUser.pccInstance;
    }
    public Submitter GetSubmitter()
    {
        return mUser.submitterInstance;
    }

    public ObservableList<users> GetAccounts() throws SQLException {
        return mUser.userAccountSystem.GetAccounts();
    }
    public User GetUser()
    {
        return mUser;
    }
}
