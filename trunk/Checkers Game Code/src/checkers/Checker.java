//-------------------------------------------------------------------------
//FileName: Checker.java
//
//Author: Byron Lunt
//
//Description: This class represents a regular checker
//-------------------------------------------------------------------------

package checkers;

import java.io.Serializable;

public class Checker extends Piece implements Serializable
{
    //Attributes///////////////////////////////////////////////////////////

    //---------------------------------------------------------------------
    //Methods: Checker()
    //Descripton: The constructor for the Checker piece.
    //---------------------------------------------------------------------

    public Checker (boolean visitorTurn)
    {
        super(visitorTurn);
    }

    public Checker ()
    {
        //super();
    }
}