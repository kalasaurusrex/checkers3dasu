package checkers;

import java.util.ArrayList;

public class Game
{
    private int homePieces, visitorPieces;
    private int width;
    private Square[] square;
    private String visitor;
    private String home;
    private boolean visitorTurn;
    private ArrayList<int[]> movesList;

    public Game (Square[] s, int w, String v, String h)
    {
        visitorTurn = true;
        square = s;
        visitor = v;
        home = h;
        width = w;

        if (width == 8)
            homePieces = visitorPieces = 10;
        else if (width == 10)
            homePieces = visitorPieces = 15;

        movesList = new ArrayList();
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
}

