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

    public Game (/*Square[] s, */int w, String v, String h)
    {
        visitorTurn = true;
        //square = s;
        visitor = v;
        home = h;
        width = w;

        if (width == 8)
            homePieces = visitorPieces = 10;
        else if (width == 10)
            homePieces = visitorPieces = 15;

        movesList = new ArrayList();
    }

    public Game()
    {

    }

    public String getHome ()
    {
        return home;
    }

    public void setHome (String val)
    {
        this.home = val;
    }

    public String getVisitor ()
    {
        return visitor;
    }

    public void setVisitor (String val)
    {
        this.visitor = val;
    }

    public boolean getVisitorTurn ()
    {
        return visitorTurn;
    }

    public void setVisitorTurn (boolean val)
    {
        this.visitorTurn = val;
    }

    public void decVisitorPieces()
    {
        visitorPieces--;
    }

    public int getVisitorPieces()
    {
        return visitorPieces;
    }

    public void decHomePieces()
    {
        homePieces--;
    }

    public int getHomePieces()
    {
        return homePieces;
    }

    public void storeMove (int[] m)
    {
        movesList.add(m);
    }

    public ArrayList getMoves ()
    {
        return movesList;
    }

    public int getWidth()
    {
        return width;
    }

    public Square[] getBoard()
    {
        return board;
    }

    public void saveGame(Square[] b, String fileName)
    {
        board = b;

        try
        {
            FileOutputStream fos = new FileOutputStream(fileName);
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
            ioe.printStackTrace();
        }
    }
}

