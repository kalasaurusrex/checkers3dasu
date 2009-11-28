//--------------------------------------------------------------------------
//FileName: Piece.Java
//
//Author: Byron Lunt
//
//Description:  This is an abstract class that represents a piece for
//              a board game.
//--------------------------------------------------------------------------

package checkers;

import java.io.Serializable;

public abstract class Piece implements Serializable
{
    //Attributes////////////////////////////////////////////////////////////
    private static final int RED = 0;
    private static final int BLACK = 1;

    private int color;

    //Methods///////////////////////////////////////////////////////////////

     //----------------------------------------------------------------------
    //Method: Piece()
    //Description: This is the constructor of the Piece Class. It is called
    //             during board setup.
    //----------------------------------------------------------------------

    public Piece(boolean visitorTurn)
    {
        if (visitorTurn)
            color = BLACK;
        else
            color = RED;
    }

    public Piece()
    {

    }

    //-----------------------------------------------------------------------
    //Method: int getColor()
    //Description: This method returns the value of 'color', mostly used
    //             by the Referee to validate a turn, also to determine if
    //             a smart mine blows up.
    //-----------------------------------------------------------------------

    public int getColor ()
    {
        return color;
    }
}

