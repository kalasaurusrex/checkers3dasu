package checkers;

import javax.naming.*;
import java.security.*;
import java.io.*;
import java.util.ArrayList;

// the User class represents an individual who interacts with the program.
// Users can be players and/or administrators.  an administrator user has
// additional capabilities but may also play games vs. other Users.
// this class implements the Serializable interface, so that User data can be
// preserved for subsequent runs of the program.
// this class implements the Comparable interface, allowing the list of users to
// remain sorted in alphabetical order.
public class User implements Serializable, Comparable
{
    // the constants MIN_NAME_LENGTH and MAX_NAME_LENGTH are used to restrict
    // the length of usernames
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 12;
    // the constants MIN_PW_LENGTH and MAX_PW_LENGTH are used to restrict the
    // length of users' passwords
    private static final int MIN_PW_LENGTH = 4;
    private static final int MAX_PW_LENGTH = 8;
    public final static String userNamePrompt = "Please specify" +
                    " a User Name between " + MIN_NAME_LENGTH + " and " +
                    MAX_NAME_LENGTH + " characters";
    public final static String passwordPrompt = "Please specify a password" +
          " between " + MIN_PW_LENGTH + " and " + MAX_PW_LENGTH + " characters";
    private String userName;
    private String password;
    private boolean admin;

    // generic constructor of a new User object.
    // checks are made to validate the length of the User's selected password.
    // the checkForDuplicates helper method is called to ensure that multiple
    // Users with the same name are not allowed.
    // User names are not case sensitive.
    public User (String name) throws InvalidNameException
    {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            InvalidNameException ine = new InvalidNameException(userNamePrompt);
            throw ine;
        }
        if (checkForDuplicates(name)) {
            InvalidNameException taken = new InvalidNameException("A User already" +
                    " exists with the chosen User Name.  Please select another.");
            throw taken;
        }
        userName = name;
        password = null;
        admin = false;
    }
    // chechForDuplicates is called when attempting to create a new User.
    // if a User already exists in the system with the desired name (ignoring
    // case), the method returns true.
    // this is used to prevent duplicate and minimize confusing logins.
    private static boolean checkForDuplicates(String nameDesired) {
        boolean result = false;
        ArrayList<String> names = new ArrayList<String>();
        for (User user : Main.storage.getUsers()) {
            names.add(user.toString());
        }
        for (String name : names) {
            if (nameDesired.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return result;
    }
    // getAdmin method checks the administrator flag of a User object.
    public boolean getAdmin ()
    {
        return admin;
    }
    // setAdmin sets the administrator flag of a User object.
    public void setAdmin (boolean val)
    {
        this.admin = val;
    }
    // getPassword returns the User object's password field.
    public String getPassword ()
    {
        return password;
    }
    // setPassword sets the User object's password field.
    // an InvalidParameterException is thrown if the selected password does not
    // meet the specified length parameters.
    public void setPassword (String selectedPW) throws InvalidParameterException
        {
        if (selectedPW.length() < MIN_PW_LENGTH || selectedPW.length()
                > MAX_PW_LENGTH) {
            InvalidParameterException ipe =
                                  new InvalidParameterException(passwordPrompt);
            throw ipe;
        }
        password = selectedPW;
    }
    // accessor method for the userName field.
    public String getUserName ()
    {
        return userName;
    }
    // the toString method returns the specified User's name.
    public String toString() {
        return userName;
    }
    // compareTo method, specified by the Comparable interface.  User objects
    // are compared based on the User's userName field, ignoring case.  this
    // allows the list of Users to remain sorted, in alphabetical order, as new
    // Users are added to the system.
    public int compareTo(Object other) {
        int result = 0;
        try {
            User otherUser = (User) other;
            result = this.userName.compareToIgnoreCase(otherUser.userName);
        } catch (ClassCastException e) {
            System.out.println("Illegal comparison!");
        }
        return result;
    }
}

