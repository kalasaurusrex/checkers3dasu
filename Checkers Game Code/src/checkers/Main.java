/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package checkers;

/**
 *
 * @author clarkcito
 */
public class Main
{
    public static final int RED = 0;
    public static final int BLACK = 1;
    public static final int NEW = 0;
    public static final int ADMIN = 1;
    public static final int STATS = 2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        new WelcomeScreen().setVisible(true);
    }
}
