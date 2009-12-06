/*
 * The Main file initializes the program and constants that are used between
 * more than one class
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
    public static final int HOME_WON = 1;
    public static final int VISITOR_WON = 2;
    public static final int SQ_BL = 1;
    public static final int CK_BL = 2;
    public static final int CK_RD = 3;
    public static final int KG_BL = 4;
    public static final int KG_RD = 5;
    public static final int SQ_SF = 6;
    public static final int SF_CK_BL = 7;
    public static final int SF_CK_RD = 8;
    public static final int SF_KG_BL = 9;
    public static final int SF_KG_RD = 10;
    public static final int SQ_BO = 11;
    public static Storage storage;
    private static WelcomeScreen welcome;

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
	    
        welcome = new WelcomeScreen();
        welcome.setVisible(true);
    }

    public static void restart()
    {
        welcome.setVisible(true);
    }
}
