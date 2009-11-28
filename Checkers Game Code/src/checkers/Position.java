/*
 * This class accepts three integer values as input that correspond to
 * the board, row and column location on a 3 dimensional checker board
 *
 * Author:  David Clark
 */

package checkers;

import java.io.Serializable;

public class Position implements Serializable
{
    private int board;
    private int row;
    private int column;
    
    public Position (int b, int r, int c)
    {
        board = b;
        row = r;
        column = c;
    }

    public int getBoard ()
    {
        return board;
    }

    public int getColumn ()
    {
        return column;
    }

    public int getRow ()
    {
        return row;
    }
}

