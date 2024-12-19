package backend.webinterface;

import backend.enums.Role;
import backend.webinterface.systems.PaperSystem;
import backend.webinterface.systems.UserAccountSystem;

import java.sql.SQLException;
import java.util.ArrayList;

public class User
{
    PaperSystem paperSystem;
    UserAccountSystem userAccountSystem;
    Integer mUserId; //member variable
    Pcc pccInstance = null;
    Pcm pcmInstance = null;
    Administrator adminInstance = null;
    Submitter submitterInstance = null;

    boolean DebugOn;

    public User(UserAccountSystem userAccountSystem, PaperSystem paperSystem,  Integer userId, boolean DebugOn)
    {
        this.userAccountSystem = userAccountSystem;
        this.paperSystem = paperSystem;
        this.mUserId = userId;
        this.DebugOn = DebugOn;

    }
    public User(UserAccountSystem userAccountSystem, PaperSystem paperSystem, boolean DebugOn)
    {
        this.userAccountSystem = userAccountSystem;
        this.paperSystem = paperSystem;
        this.mUserId = 0;
        this.DebugOn = DebugOn;

    }

    public String Login(String userName, String password) throws SQLException {
        String role = "error";
        boolean loginSuccess = userAccountSystem.Login(userName, password);

        if(loginSuccess)
        {
            mUserId = userAccountSystem.GetUserId(userName);
            ArrayList<Role> actualRoles = userAccountSystem.GetRoles(userName);
            for (Role actualRole : actualRoles) {

                if(DebugOn){System.out.println(actualRole.toString());}

                switch (actualRole) {
                    case pcc:
                        setPccInstance();
                        role = actualRole.toString();
                        break;
                    case pcm:
                        setPcmInstance();
                        role = actualRole.toString();
                        break;
                    case author:
                    case submitter:
                        setSubmitterInstance();
                        role = actualRole.toString();
                        break;
                    case administrator:
                        setAdminInstance();
                        role = actualRole.toString();
                        break;
                    case unknown:
                    default:
                        break;
                }
            }
        }
        else
        {
            throw new SQLException("Invalid Credentials");
        }

        return role;
    }

    public void Logout()
    {
        mUserId = 0;
        pccInstance = null;
        pcmInstance = null;
        adminInstance = null;
        submitterInstance = null;
    }
    public Integer GetUserId()
    {
        return mUserId;
    }

    public Pcc getPccInstance()
    {
        return pccInstance;
    }
    public Pcm getPcmInstance()
    {
        return pcmInstance;
    }
    public Submitter getSubmitterInstance() {
        return submitterInstance;
    }
    public Administrator getAdminInstance()
    {
        return adminInstance;
    }
    public void setPccInstance()
    {
        if(pccInstance == null)
        {
            pccInstance = new Pcc(this);
        }

    }
    public void setPcmInstance()
    {
        if(pcmInstance == null)
        {
            pcmInstance = new Pcm(this);
        }
    }
    public void setSubmitterInstance()
    {
        if(submitterInstance == null)
        {
            submitterInstance = new Submitter(this);
        }
    }
    public void setAdminInstance()
    {
        if(adminInstance == null)
        {
            adminInstance = new Administrator(this);
        }
    }
}
