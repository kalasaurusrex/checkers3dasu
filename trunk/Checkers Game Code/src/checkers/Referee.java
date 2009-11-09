package checkers;

import java.util.*;

public class Referee
{
    private final int ALLOWABLE_ROWS = 3;
    Square[] squareArray;
    Game game;

    public Referee (Square[] s, Game g)
    {
        squareArray = s;
        game = g;
    }

    public int executeMove (Square start, Square destination, ArrayList m)
    {
        int executeCode = 0; // returns 0 if error, 1 if valid move
        Square tempStart = start;
        Square tempDestination = destination;
        ArrayList<Square> moves = m;
        int startIndex = tempStart.getIndex();
        int destinationIndex = tempDestination.getIndex();
        int tempIndex;

        for (int i = 0; i < moves.size(); i++ )
        {
            tempIndex = moves.get(i).getIndex();
            if (destinationIndex == tempIndex)
            {
                destination.setPiece(start.getPiece());
                start.setPiece(null);
                
                executeCode = 1;// a valid move
            }
        }

        if (executeCode != 0) // make sure a move is going to be exectued and then change the turns
        {
            if (game.getVisitorTurn()== true)
                game.setVisitorTurn(false);
            else
                game.setVisitorTurn(true);
        }
        
        return executeCode;

    }

    public boolean executePlacement (Square selection, Square destination)
    {
        int boardHeight = 0;
        int halfHeight = 0;

        if(squareArray.length == 100)
        {
            boardHeight = 10;
        }
        else if (squareArray.length == 64)
            boardHeight = 8;

        halfHeight = boardHeight / 2;

        //make sure the destination isn't a setup piece
        if (destination.getPosition().getBoard() == 0)
            return false;

        if (destination.getPosition().getRow() <= halfHeight &&
              selection.getPosition().getRow() == 2 &&
              selection.getPosition().getColumn() == 2 &&
              !destination.getMine() && !destination.getBlocked()) //visitor safe
        {
            destination.setSafe(true);
            game.setVisitorTurn(false);

            return true;
        }
        else if (destination.getPosition().getRow() <= halfHeight &&
              selection.getPosition().getRow() == 2 &&
              selection.getPosition().getColumn() == 3 &&
              !destination.getSafe() && !destination.getBlocked()) //visitor mine
        {
            destination.setMine(true);
            game.setVisitorTurn(false);

            return true;
        }
        else if (destination.getPosition().getRow() > halfHeight &&
              selection.getPosition().getRow() == 4 &&
              selection.getPosition().getColumn() == 2 &&
              !destination.getMine() && !destination.getBlocked()) //home safe
        {
            destination.setSafe(true);
            game.setVisitorTurn(true);

            return true;
        }
        else if (destination.getPosition().getRow() > halfHeight &&
              selection.getPosition().getRow() == 4 &&
              selection.getPosition().getColumn() == 3 &&
              !destination.getSafe() && !destination.getBlocked()) //home mine
        {
            destination.setMine(true);
            game.setVisitorTurn(true);

            return true;
        }
        else if (destination.getPiece() == null && !destination.getBlocked())
        {
            if (destination.getPosition().getRow() <= ALLOWABLE_ROWS &&
                    selection.getPosition().getRow() == 1 &&
                    selection.getPosition().getColumn() == 1) //visitor checker
            {
                destination.setPiece(new Checker(game.getVisitorTurn()));
                game.setVisitorTurn(false);
                return true;
            }
            else if (destination.getPosition().getRow() <= ALLOWABLE_ROWS &&
                    selection.getPosition().getRow() == 1 &&
                    selection.getPosition().getColumn() == 2) //visitor king
            {
                destination.setPiece(new King(game.getVisitorTurn()));
                game.setVisitorTurn(false);
                return true;
            }
        
            if (destination.getPosition().getRow() > boardHeight - ALLOWABLE_ROWS &&
                    selection.getPosition().getRow() == 3 &&
                    selection.getPosition().getColumn() == 1) //home checker
            {
                destination.setPiece(new Checker(game.getVisitorTurn()));
                game.setVisitorTurn(true);
                return true;
            }
            else if (destination.getPosition().getRow() > boardHeight - ALLOWABLE_ROWS &&
                    selection.getPosition().getRow() == 3 &&
                    selection.getPosition().getColumn() == 2) //home king
            {
                destination.setPiece(new King(game.getVisitorTurn()));
                game.setVisitorTurn(true);
                return true;
            }
            else if (destination.getPosition().getRow() <= halfHeight &&
                    selection.getPosition().getRow() == 2 &&
                    selection.getPosition().getColumn() == 1 &&
                    !destination.getSafe() && !destination.getMine()) //visitor blocked
            {
                destination.setBlocked(true);
                game.setVisitorTurn(false);
                return true;
            }
            else if (destination.getPosition().getRow() > halfHeight &&
                    selection.getPosition().getRow() == 4 &&
                    selection.getPosition().getColumn() == 1&&
                    !destination.getSafe() && !destination.getMine()) //home blocked
            {
                destination.setBlocked(true);
                game.setVisitorTurn(true);
                return true;
            }
        }

        return false;
    }


    // Returns true if the player with the correct turn is trying to see his
    // moves and false otherwise.
    public boolean validateTurn (Piece piece)
    {
        //red(home) = 0, black(visitor) = 1
        if (piece == null)
            return false;
        else if (piece.getColor() == 1 && game.getVisitorTurn()== true) //checks it it's visitor's turn
            return true;
        else if (piece.getColor() == 0 && game.getVisitorTurn() == false)//checks if it's home's turn
            return true;
        else
            return false; //if the wrong player is trying to move
    }

    public ArrayList showMoves (Square square)
    {
        int positionBoard = square.getPosition().getBoard();
        int positionColumn = square.getPosition().getColumn();
        int positionRow = square.getPosition().getRow();
        int width = game.getWidth();
        Square tempSquare = null;
        ArrayList moves = new ArrayList();
        int index = square.getIndex();
        Piece piece = square.getPiece();

        //the logic for pieces in even rows
        if (positionRow%2 == 0)
        {
            
            if (game.getVisitorTurn() == false || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move up
            {
                if (positionRow != 1 && positionColumn != 1)//makes sure checker won't jump off board
                {
                    tempSquare = squareArray[index-((width/2)+1)];//up and left
                    if (tempSquare.getPiece() == null)//checks if the space is empty
                    {
                        moves.add(tempSquare); //adds the square to the ArrayList that will be returned.
                    }
                }

                if (positionRow != 1 && positionColumn != width)//makes sure checker won't jump off board
                {
                    tempSquare = squareArray[index-(width/2)];//up and right
                    if (tempSquare.getPiece() == null)
                    {
                        moves.add(tempSquare); //adds the square to the ArrayList that will be returned.
                    }
                }
            }

            if (game.getVisitorTurn() == true || piece instanceof King) //checks if it's visitor's turn or a King, i.e. the only two cases where you can move down
            {
                if (positionRow != width && positionColumn != 1)//makes sure checker won't jump off board
                {
                    tempSquare = squareArray[index+((width/2)-1)];//down and left
                    if (tempSquare.getPiece() == null)
                    {
                        moves.add(tempSquare); //adds the square to the ArrayList that will be returned.
                    }
                }

                if (positionRow != width && positionColumn != width)//makes sure checker won't jump off board
                {
                    tempSquare = squareArray[index+(width/2)];//down and right
                    if (tempSquare.getPiece() == null)
                    {
                        moves.add(tempSquare); //adds the square to the ArrayList that will be returned.
                    }
                }
            }
        }

        //the logic for pieces in odd rows
        if (positionRow%2 == 1)
        {
            if (game.getVisitorTurn() == false || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move up
            {
                if (positionRow != 1 && positionColumn != 1)//makes sure checker won't jump off board
                {

                    tempSquare = squareArray[index-(width/2)];//up and left
                    if (tempSquare.getPiece() == null)//checks if the space is empty
                    {
                        moves.add(tempSquare); //adds the square to the ArrayList that will be returned.
                    }
                }
                if (positionRow != 1 && positionColumn != width)//makes sure checker won't jump off board
                {
                    tempSquare = squareArray[index-(width/2)+1];//up and right
                    if (tempSquare.getPiece() == null)
                    {
                        moves.add(tempSquare); //adds the square to the ArrayList that will be returned.
                    }
                }
            }

            if (game.getVisitorTurn() == true || piece instanceof King) //check if it's visitor's turn or a king, i.e. the only two cases where you can move up
            {
                if (positionRow != width && positionColumn != 1)//makes sure checker won't jump off board
                {
                    tempSquare = squareArray[index+(width/2)];//down and left
                    if (tempSquare.getPiece() == null)
                    {
                        moves.add(tempSquare); //adds the square to the ArrayList that will be returned.
                    }
                }

                if (positionRow != width && positionColumn != width)//makes sure checker won't jump off board
                {
                    tempSquare = squareArray[index+((width/2)+1)];//down and right
                    if (tempSquare.getPiece() == null)
                    {
                        moves.add(tempSquare); //adds the square to the ArrayList that will be returned.
                    }
                }
            }
           
        }
        
        return moves;
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