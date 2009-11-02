package checkers;


public class Game
{
    private int homePieces, visitorPieces;
    private int width;
    private Square[] square;
    private String visitor;
    private String home;
    private boolean visitorTurn;

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

    public int getWidth()
    {
        return width;
    }
    
    /*The following modules will be implemented in later builds
    public void storeMove () 
    {

    }

    public void getBoardState () 
    {

    }
    */
}

