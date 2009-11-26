/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers;

import java.io.*;

// the Main class attempts to load a serialized Storage object from a file, if
// it exists.
// if stored User and Game data is not available, the Storage constructor is
// called to create empty lists of Users and Games.
// the Main class then instatiates a WelcomeScreen object, which allows the user
// to select New Game, Load Game, Statistics, and Administration options.
public class Main
{
    public static final int RED = 0;
    public static final int BLACK = 1;
    public static final int NEW = 0;
    public static final int ADMIN = 1;
    public static final int STATS = 2;
    public static final int HOME_WON = 1;
    public static final int VISITOR_WON = 2;
    public static Storage storage;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try {
            FileInputStream fis = new FileInputStream("store.storage");
            ObjectInputStream ois = new ObjectInputStream(fis);
            storage = (Storage) ois.readObject();
        } catch (FileNotFoundException fne) {
            storage = new Storage();
        } catch (IOException ioe) {
            System.exit(1);
        } catch (ClassNotFoundException cne) {
            System.exit(2);
        }
        new WelcomeScreen().setVisible(true);
    }
}
