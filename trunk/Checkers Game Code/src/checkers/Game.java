package checkers;

import java.util.ArrayList;
import java.io.*;

public class Game implements Serializable
{
    private int homePieces, visitorPieces;
    private int width;
    private Square[] board;
    private String visitor;
    private String home;
    private boolean visitorTurn;
    private ArrayList<int[]> movesList;

    //constructor for new Game object
    public Game (int w, String v, String h)
    {
        visitorTurn = true;
        visitor = v;
        home = h;
        width = w;

        if (width == 8)
            homePieces = visitorPieces = 10;
        else if (width == 10)
            homePieces = visitorPieces = 15;

        movesList = new ArrayList();
    }

    //empty constructor for Serializable
    public Game()
    {

    }

    //get the home player's name
    public String getHome ()
    {
        return home;
    }

    //get the visitor player's name
    public String getVisitor ()
    {
        return visitor;
    }

    //find out who's turn it is
    public boolean getVisitorTurn ()
    {
        return visitorTurn;
    }

    //set the turn
    public void setVisitorTurn (boolean val)
    {
        this.visitorTurn = val;
    }

    //subtract 1 from visitor's piece count
    public void decVisitorPieces()
    {
        visitorPieces--;
    }

    //get visitor's piece count
    public int getVisitorPieces()
    {
        return visitorPieces;
    }

    //subtract 1 from home's piece count
    public void decHomePieces()
    {
        homePieces--;
    }

    //get visitor's piece count
    public int getHomePieces()
    {
        return homePieces;
    }

    //store the state of the board
    public void storeMove (int[] m)
    {
        movesList.add(m);
    }

    //return the list of moves
    public ArrayList getMoves ()
    {
        return movesList;
    }

    //return the width of the board
    public int getWidth()
    {
        return width;
    }

    //return the array of squares that comprises the board
    public Square[] getBoard()
    {
        return board;
    }

    //save the Game object as a Serializable file
    public void saveGame(Square[] b, String fileName)
    {
        board = b;

        try
        {
            //if the "games" directory doesn't exist, create one
            new File (new File(".").getCanonicalPath() + File.separator + "games").mkdir();
            
            String filePath =new File(".").getCanonicalPath() + File.separator + "games";
            FileOutputStream fos = new FileOutputStream(filePath + File.separator + fileName);
            System.out.println(fos);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("File not found");
        }
        catch (IOException ioe)
        {
            System.out.println("IO Exception");
        }
    }
}

