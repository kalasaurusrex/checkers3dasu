//----------------------------------------------------------------------
//FileName: King.java
//
//Author: Byron Lunt
//
//Description: This class represents a king game piece.
//----------------------------------------------------------------------
package checkers;

import java.io.Serializable;

public class King extends Piece implements Serializable
{
    //Attributes////////////////////////////////////////////////////////

    //------------------------------------------------------------------
    //Method: King()
    //Description: The default constructor for the class
    //------------------------------------------------------------------
    public King (boolean visitorTurn)
    {
        super(visitorTurn);
    }

    public King()
    {
        
    }
}