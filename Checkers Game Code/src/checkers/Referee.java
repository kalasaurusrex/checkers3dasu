package checkers;

import java.util.*;

public class Referee
{
    Square[] squareArray;
    Game game;

    public Referee (Square[] s, Game g)
    {
        squareArray = s;
        game = g;
    }

    public int executeMove (Square start, Square destination)
    {
        //return a 1 if valid or 0 if invalid
        return 1;
    }

    public boolean executePlacement (Square selection, Square destination)
    {
        int boardHeight = 0;

        if(squareArray.length == 100)
            boardHeight = 10;
        else if (squareArray.length == 64)
            boardHeight = 8;

        //make sure the destination isn't a setup piece
        if (destination.getPosition().getBoard() == 0)
            return false;

        if (destination.getPiece() == null && game.getVisitorTurn())
        {
            if(destination.getPosition().getRow() <= 3 &&
                    selection.getPosition().getRow() == 1 &&
                    selection.getPosition().getColumn() == 1)
            {
                destination.setPiece(new Checker(game.getVisitorTurn()));
                game.setVisitorTurn(false);
                return true;
            }
            else if(destination.getPosition().getRow() <= 3 && 
                    selection.getPosition().getRow() == 1 &&
                    selection.getPosition().getColumn() == 2)
            {
                destination.setPiece(new King(game.getVisitorTurn()));
                game.setVisitorTurn(false);
                return true;
            }
        }
        else if (destination.getPiece() == null)
        {
            if(destination.getPosition().getRow() > boardHeight - 3 && 
                    selection.getPosition().getRow() == 3 &&
                    selection.getPosition().getColumn() == 1)
            {
                destination.setPiece(new Checker(game.getVisitorTurn()));
                game.setVisitorTurn(true);
                return true;
            }
            else if(destination.getPosition().getRow() >boardHeight - 3 && 
                    selection.getPosition().getRow() == 3 &&
                    selection.getPosition().getColumn() == 2)
            {
                destination.setPiece(new King(game.getVisitorTurn()));
                game.setVisitorTurn(true);
                return true;
            }
        }

        return false;
    }

    public boolean validateTurn (Piece piece)
    {
        return true;
    }

    public ArrayList showMoves (Square square)
    {
        //TEST VALUES
        ArrayList temp = new ArrayList();
        temp.add(squareArray[square.getIndex() + 4]);
        temp.add(squareArray[square.getIndex() + 5]);

        return temp;
    }

    public boolean verifySelection(Square square)
    {
        if (square.getPosition().getBoard() == 0)
        {
            if(game.getVisitorTurn() && square.getPosition().getRow() == 1 ||
                    square.getPosition().getRow() == 2)
                return true;
            else if(!game.getVisitorTurn() && square.getPosition().getRow() == 3 ||
                    square.getPosition().getRow() == 4)
                return true;
        }

        return false;
    }
}

