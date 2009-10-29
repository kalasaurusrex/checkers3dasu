package checkers;

 
public class User
{
    private String userName;
    private String password;
    private boolean admin;

    public User (String name) 
    {

    }

    public boolean getAdmin ()
    {
        return admin;
    }

    public void setAdmin (boolean val)
    {
        this.admin = val;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String val)
    {
        this.password = val;
    }

    public String getUserName ()
    {
        return userName;
    }

    public void setUserName (String val)
    {
        this.userName = val;
    }
}

