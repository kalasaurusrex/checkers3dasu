package checkers;

 
public class User
{
    private String userName;
    private String password;
    private boolean admin;

    // generic constructor of a new User object.  all users are assigned the
    // default password of "password" at this point.
    // password functionality will be added in build 2.
    public User (String name) 
    {
        userName = name;
        password = "password";
        admin = false;
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

    public String toString() {
        return userName;
    }
    public void setUserName (String val)
    {
        this.userName = val;
    }
}

