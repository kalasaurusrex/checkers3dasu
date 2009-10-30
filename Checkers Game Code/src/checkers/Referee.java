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

    public int executeMove (Square start, Square destination, ArrayList m)
    {
        int executeCode = 0; // returns 0 if error, 1 if move
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

        if(squareArray.length == 100)
            boardHeight = 10;
        else if (squareArray.length == 64)
            boardHeight = 8;

        //make sure the destination isn't a setup piece
        if (destination.getPosition().getBoard() == 0)
            return false;

        //create a check that the user isn't placing a piece on top of another
        //piece
//        if (destination.getPiece() == null)
//            return false;

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


    // Returns true if the player with the correct turn is trying to see his
    // moves and false otherwise.
    public boolean validateTurn (Piece piece)
    {
        //red(home) = 0, black(visitor) = 1
        if (piece.getColor() == 1 && game.getVisitorTurn()== true) //checks it it's visitor's turn
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

