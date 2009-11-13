package checkers;

import javax.naming.*;
import java.security.*;
import java.io.*;
import java.util.ArrayList;

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

    // generic constructor of a new User object.  the maximum
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
        for (User user : Storage.getStorageInstance().getUserList()) {
            names.add(user.toString());
        }
        for (String name : names) {
            if (nameDesired.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return result;
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

    public void setPassword (String selectedPW) throws InvalidParameterException {
        if (selectedPW.length() < MIN_PW_LENGTH || selectedPW.length() > MAX_PW_LENGTH) {
            InvalidParameterException ipe = new InvalidParameterException(passwordPrompt);
            throw ipe;
        }
        password = selectedPW;
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
    public void saveUser() {
        try {
            File user = new File(userName);
            FileOutputStream fos = new FileOutputStream(user);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error saving User!");
        }
    }
}

