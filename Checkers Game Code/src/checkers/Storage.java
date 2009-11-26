package checkers;

import java.util.*;
import javax.naming.*;
import java.io.*;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.489A3A6B-B4A5-65DC-5BA2-96A9B4A68D83]
// </editor-fold>
// the Storage class is a final class that implements the
// serializable interface.  this allows a list of Users and Games to be stored
// that may be required during subsequent runs of the program.
public final class Storage implements Serializable {

    private Vector<User> users;
    private ArrayList<Game> games;

    // Storage constructor.  empty ArrayLists are created to store
    // Users and Games.
    public Storage() {
        users = new Vector<User>();
        games = new ArrayList<Game>();
    }
    // the getUsers method returns an ArrayList of all users that have been
    // previously created in the system.

    public Vector<User> getUsers() {
        return users;
    }
    // the addUser method adds a new User to the list of users.  the list is
    // then sorted, so that names appear in alphabetical order when logging in.
    // each time a new User is added, the saveStorage method is called, which
    // serializes the Storage to a file for use during subsequent runs of the
    // program.

    public void addUser(User newUser) {
        users.add(newUser);
        Collections.sort((AbstractList) users);
        this.saveStorage();
    }
    // returns a list of saved Games. each time a new Game is added, the
    // saveStorage method is called, which serializes the Storage to a file for
    // use during subsequent runs of the program.

    public ArrayList<Game> getGames() {
        return games;
    }
    // the addGame method adds a new Game to the list of games.

    public void addGame(Game newGame) {
        games.add(newGame);
        this.saveStorage();
    }
    // the saveStorage method is used to serialize a Storage Object for later
    // use.

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
