//-------------------------------------------------------------------------
//FileName: Square.Java
//
//Author: Byron Lunt
//
//Description: This class represents a square on the game board, the board
//             is made up of an array of Squares.
//--------------------------------------------------------------------------


package checkers;


public class Square extends javax.swing.JLabel
{
    //Attributes/////////////////////////////////////////////////////////////

    private boolean safe;
    private boolean blocked;
    private boolean mine;
    private Position position;
    private Piece piece;
    private boolean visitorMine;
    private int index;

    //Methods////////////////////////////////////////////////////////////////

    //---------------------------------------------------------------------
    //Method: Square(Position)
    //Description:  This method is a constructor of the Square class.
    // It takes as input a parameter of typeposition, which determines
    // as square's position on the board.
    //----------------------------------------------------------------------

     public Square(Position position, int index)
    {
      safe = false;
      blocked = false;
      mine = false;
      this.position = position;
      piece = null;     //indicates that the square is not occupied by a piece
      visitorMine = false;
      this.index = index;
    }

    //----------------------------------------------------------------------
    //Method: boolean getBlocked()
    //Description: This method returns the value of blocked which is used
    //             to determine whether or not a square is a blocked square.
    //----------------------------------------------------------------------

     public int getIndex()
     {
         return index;
     }
    public boolean getBlocked ()
    {
        return blocked;
    }

    //----------------------------------------------------------------------
    //Method: void setBlocked(boolean)
    //Description: This method assigns the boolean parameter to the
    //attribute 'blocked'.  This method is only used for board setup
    //----------------------------------------------------------------------

    public void setBlocked (boolean val)
    {
        this.blocked = val;
    }

    //-----------------------------------------------------------------------
    //Method: boolean getMine()
    //Description: This method returns the value of mine which is used
    //             to determine whether or not a square contains a smart mine.
    //-----------------------------------------------------------------------

    public boolean getMine ()
    {
        return mine;
    }

    //-----------------------------------------------------------------------
    //Method: void setMine(boolean value)
    //Description: This method assigns the parameter to 'mine'.
    //             This method is used during board setup to place a mine,
    //             as well after it has exploded, to remove it from the square.
    //-----------------------------------------------------------------------

    public void setMine (boolean val)
    {
        this.mine = val;
    }

    //-----------------------------------------------------------------------
    //Method: Piece getPiece()
    //Description: This method returns the value of 'piece', if the square
    //             is not occupied by a piece, then it returns 'null'.
    //-----------------------------------------------------------------------

    public Piece getPiece ()
    {
        return piece;
    }

    //-----------------------------------------------------------------------
    //Method: void setPiece(Piece)
    //Description: This method sets the value of 'piece', if a piece
    //             is moved onto the square, it is set to 'piece', if
    //             a piece moves off of the square, 'piece' is set to null.
    //-----------------------------------------------------------------------

    public void setPiece (Piece val)
    {
        this.piece = val;
    }

    //-----------------------------------------------------------------------
    //Method: Position getPosition()
    //Description: This method is used to get the position of the square,
    //             mainly for use by the Referee to validate and show moves.
    //-----------------------------------------------------------------------

    public Position getPosition ()
    {
        return position;
    }

    //-----------------------------------------------------------------------
    //Method: boolean getSafe()
    //Description:  Returns the value of 'safe'.  Is used to determine
    //              whether or not a square is a safe zone.
    //-----------------------------------------------------------------------

    public boolean getSafe ()
    {
        return safe;
    }

    //----------------------------------------------------------------------
    //Method: void setSafe(boolean)
    //Description: Receives a parameter of type boolean which is then
    //             assigned to the 'safe' data member.  This method is
    //             only used during board setup.
    //-----------------------------------------------------------------------

    public void setSafe (boolean val)
    {
        this.safe = val;
    }

    //-----------------------------------------------------------------------
    //Method: boolean getVisitorMine()
    //Description: Returns the value of 'visitorMine' which is used
    //             to determine whether a mine belongs to the visitor,
    //             or the home player.
    //-----------------------------------------------------------------------


    public boolean getVisitorMine ()
    {
        return visitorMine;
    }

    //-----------------------------------------------------------------------
    //Method: void setVisitorMine(boolean)
    //Description: Used during board setup to assign the mine to the home
    //             or visitor player.
    //-----------------------------------------------------------------------

    public void setVisitorMine (boolean val)
    {
        this.visitorMine = val;
    }
}