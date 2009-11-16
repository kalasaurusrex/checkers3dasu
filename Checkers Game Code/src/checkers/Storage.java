package checkers;

import java.util.*;
import javax.naming.*;
import java.io.*;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.489A3A6B-B4A5-65DC-5BA2-96A9B4A68D83]
// </editor-fold>
// the Storage class is a final, singleton class that implements the serializable
// interface.  this allows a list of Users and Games to be stored that may be
// required during subsequent runs of the program.
public final class Storage implements Serializable
{
    private Vector<User> users;
    private ArrayList<Game> games;
    private static Storage instance = new Storage();

    // private Storage constructor.  empty ArrayLists are created to store
    // Users and Games.
    private Storage() {
            users = new Vector<User>();
            games = new ArrayList<Game>();
        }
    public static Storage getStorageInstance() {
        return instance;
        // the commented code below attempts to preserve the user list between
        // runs of the program.  it will be completed in Build 3.
        /*Storage instance = null;
        try {
            FileInputStream fis = new FileInputStream("store.storage");
            ObjectInputStream ois = new ObjectInputStream(fis);
            instance = (Storage) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            try {
                instance = new Storage();
            } catch (Exception ex) {

            }
        }
        return instance; */
    }
    public void addUser(User newUser) {
        users.add(newUser);
        Collections.sort((AbstractList) users);
        //newUser.saveUser();
        //this.saveStorage();
    }
    public ArrayList<Game> getGames ()
    {
        return games;
    }

    public void setGames (ArrayList val)
    {
        this.games = val;
    }
    // the getUsers method returns an ArrayList of all users that have been
    // previously created in the system.
    public Vector<User> getUserList ()
    {
       return users;
    }
    // the saveStorage method is used to serialize a Storage Object for later
    // use.  it will be used in Build 3.
    public void saveStorage() {
        try {
            FileOutputStream fos = new FileOutputStream("store.storage");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (FileNotFoundException fne) {
            System.out.println("File not found");
        } catch (IOException io) {
            System.out.println("IO Exception");
        }
    }
}
