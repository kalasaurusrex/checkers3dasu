package checkers;

import java.util.*;

public class Referee
{
    private final int ALLOWABLE_ROWS = 3;
    int width = 0;
    int halfHeight = 0;
    Square[] squareArray;
    Game game;
    int evenUpLeft;
    int evenUpRight;
    int evenDownLeft;
    int evenDownRight;
    int oddUpLeft;
    int oddUpRight;
    int oddDownLeft;
    int oddDownRight;

    public Referee (Square[] s, Game g)
    {
        squareArray = s;
        game = g;

        width = game.getWidth();
        halfHeight = (width * width) / 2;

        evenUpLeft = -((width/2)+1);
        evenUpRight = -(width/2);
        evenDownLeft = (width/2)-1;
        evenDownRight = (width/2);
        oddUpLeft = -(width/2);
        oddUpRight = -(width/2)+1;
        oddDownLeft = (width/2);
        oddDownRight = (width/2)+1;
    }

    public int executeMove (Square start, Square destination, ArrayList m)
    {
        int executeCode = 0; // returns 0 if error, 1 if valid move
        //Square tempStart = start;
        //Square tempDestination = destination;
        ArrayList<Square> moves = m;
        //int startIndex = start.getIndex();
        int destinationIndex = destination.getIndex();
        int tempIndex;

        for (int i = 0; i < moves.size(); i++ )
        {
            tempIndex = moves.get(i).getIndex();
            if (destinationIndex == tempIndex)
            {
                if (destination.getMine() && destination.getVisitorMine() &&
                      !game.getVisitorTurn())
                {
                    destination.setPiece(null);
                    destination.setMine(false);
                }
                else if (destination.getMine() && !destination.getVisitorMine() &&
                      game.getVisitorTurn())
                {
                    destination.setPiece(null);
                    destination.setMine(false);
                }
                else
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
        //make sure the destination isn't a setup piece
        if (destination.getPosition().getBoard() == 0)
            return false;

        if (destination.getPosition().getRow() <= width/2 &&
              selection.getPosition().getRow() == 2 &&
              selection.getPosition().getColumn() == 2 &&
              !destination.getMine() && !destination.getBlocked()) //visitor safe
        {
            destination.setSafe(true);
            game.setVisitorTurn(false);

            return true;
        }
        else if (destination.getPosition().getRow() <= width/2 &&
              selection.getPosition().getRow() == 2 &&
              selection.getPosition().getColumn() == 3 &&
              !destination.getSafe() && !destination.getBlocked() &&
              !destination.getMine()) //visitor mine
        {
            destination.setMine(true);
            destination.setVisitorMine(true);
            game.setVisitorTurn(false);

            return true;
        }
        else if (destination.getPosition().getRow() > width/2 &&
              selection.getPosition().getRow() == 4 &&
              selection.getPosition().getColumn() == 2 &&
              !destination.getMine() && !destination.getBlocked()) //home safe
        {
            destination.setSafe(true);
            game.setVisitorTurn(true);

            return true;
        }
        else if (destination.getPosition().getRow() > width/2 &&
              selection.getPosition().getRow() == 4 &&
              selection.getPosition().getColumn() == 3 &&
              !destination.getSafe() && !destination.getBlocked() &&
              !destination.getMine()) //home mine
        {
            destination.setMine(true);
            destination.setVisitorMine(false);
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
        
            if (destination.getPosition().getRow() > width - ALLOWABLE_ROWS &&
                    selection.getPosition().getRow() == 3 &&
                    selection.getPosition().getColumn() == 1) //home checker
            {
                destination.setPiece(new Checker(game.getVisitorTurn()));
                game.setVisitorTurn(true);
                return true;
            }
            else if (destination.getPosition().getRow() > width - ALLOWABLE_ROWS &&
                    selection.getPosition().getRow() == 3 &&
                    selection.getPosition().getColumn() == 2) //home king
            {
                destination.setPiece(new King(game.getVisitorTurn()));
                game.setVisitorTurn(true);
                return true;
            }
            else if (destination.getPosition().getRow() <= width/2 &&
                    selection.getPosition().getRow() == 2 &&
                    selection.getPosition().getColumn() == 1 &&
                    !destination.getSafe() && !destination.getMine()) //visitor blocked
            {
                destination.setBlocked(true);
                game.setVisitorTurn(false);
                return true;
            }
            else if (destination.getPosition().getRow() > width/2 &&
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

        //Square tempSquare = null;
        ArrayList<Square> moves = new ArrayList();
        int index = square.getIndex();
        Piece piece = square.getPiece();

        //see if warp move is available
        if (index >= halfHeight && squareArray[index - halfHeight].getPiece() == null &&
              !squareArray[index - halfHeight].getBlocked())
            moves.add(squareArray[index - halfHeight]);
        else if (index < halfHeight && squareArray[index + halfHeight].getPiece() == null &&
              !squareArray[index + halfHeight].getBlocked())
            moves.add(squareArray[index + halfHeight]);

        //the logic for pieces in even rows
        if (positionRow%2 == 0)
        {  
            if (game.getVisitorTurn() == false || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move up
            {
                if (positionRow != 1 && positionColumn != 1)//makes sure checker won't jump off board
                {
                    //tempSquare = squareArray[index-((width/2)+1)];//up and left
                    if (squareArray[index + evenUpLeft].getPiece() == null &&
                      !squareArray[index + evenUpLeft].getBlocked())//checks if the space is empty
                        moves.add(squareArray[index + evenUpLeft]); //adds the square to the ArrayList that will be returned.
                    
                    if (piece instanceof King && index >= halfHeight &&
                      !squareArray[(index - halfHeight) + evenUpLeft].getBlocked()) //up and left on board 1
                    {
                        if (squareArray[(index - halfHeight) + evenUpLeft].getPiece() == null)
                            moves.add(squareArray[(index - halfHeight) + evenUpLeft]);
                    }
                    else if (piece instanceof King && index < halfHeight &&
                      !squareArray[(index + halfHeight) + evenUpLeft].getBlocked()) //up and left on board 2
                    {
                        if (squareArray[(index + halfHeight) + evenUpLeft].getPiece() == null)
                            moves.add(squareArray[(index + halfHeight)+ evenUpLeft]);
                    }
                }

                if (positionRow != 1 && positionColumn != width)//makes sure checker won't jump off board
                {
                    //tempSquare = squareArray[index-(width/2)];//up and right
                    if (squareArray[index + evenUpRight].getPiece() == null &&
                      !squareArray[index + evenUpRight].getBlocked())
                    {
                        moves.add(squareArray[index + evenUpRight]); //adds the square to the ArrayList that will be returned.
                    }

                    if (piece instanceof King && index >= halfHeight &&
                      !squareArray[(index - halfHeight) + evenUpRight].getBlocked()) //up and right on board 1
                    {
                        if (squareArray[(index - halfHeight) + evenUpRight].getPiece() == null)
                            moves.add(squareArray[(index - halfHeight) + evenUpRight]);
                    }
                    else if (piece instanceof King && index < halfHeight &&
                      !squareArray[(index + halfHeight) + evenUpRight].getBlocked()) //up and right on board 2
                    {
                        if (squareArray[(index + halfHeight) + evenUpRight].getPiece() == null)
                            moves.add(squareArray[(index + halfHeight) + evenUpRight]);
                    }
                }
            }

            if (game.getVisitorTurn() == true || piece instanceof King) //checks if it's visitor's turn or a King, i.e. the only two cases where you can move down
            {
                if (positionRow != width && positionColumn != 1)//makes sure checker won't jump off board
                {
                    //tempSquare = squareArray[index+((width/2)-1)];//down and left
                    if (squareArray[index + evenDownLeft].getPiece() == null &&
                      !squareArray[index + evenDownLeft].getBlocked())
                    {
                        moves.add(squareArray[index + evenDownLeft]); //adds the square to the ArrayList that will be returned.
                    }

                    if (piece instanceof King && index >= halfHeight &&
                      !squareArray[(index - halfHeight) + evenDownLeft].getBlocked()) //down and left on board 1
                    {
                        if (squareArray[(index - halfHeight) + evenDownLeft].getPiece() == null)
                            moves.add(squareArray[(index - halfHeight) + evenDownLeft]);
                    }
                    else if (piece instanceof King && index < halfHeight &&
                      !squareArray[(index + halfHeight) + evenDownLeft].getBlocked()) //down and left on board 2
                    {
                        if (squareArray[(index + halfHeight) + evenDownLeft].getPiece() == null)
                            moves.add(squareArray[(index + halfHeight) + evenDownLeft]);
                    }
                }

                if (positionRow != width && positionColumn != width)//makes sure checker won't jump off board
                {
                    //tempSquare = squareArray[index+(width/2)];//down and right
                    if (squareArray[index + evenDownRight].getPiece() == null &&
                      !squareArray[index + evenDownRight].getBlocked())
                    {
                        moves.add(squareArray[index + evenDownRight]); //adds the square to the ArrayList that will be returned.
                    }

                    if (piece instanceof King && index >= halfHeight &&
                      !squareArray[(index - halfHeight) + evenDownRight].getBlocked()) //down and right on board 1
                    {
                        if (squareArray[(index - halfHeight) + evenDownRight].getPiece() == null)
                            moves.add(squareArray[(index - halfHeight) + evenDownRight]);
                    }
                    else if (piece instanceof King && index < halfHeight &&
                      !squareArray[(index + halfHeight) + evenDownRight].getBlocked()) //down and right on board 2
                    {
                        if (squareArray[(index + halfHeight) + evenDownRight].getPiece() == null)
                            moves.add(squareArray[(index + halfHeight) + evenDownRight]);
                    }
                }
            }
        }
        //the logic for pieces in odd rows
        else
        {
            if (game.getVisitorTurn() == false || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move up
            {
                if (positionRow != 1 && positionColumn != 1)//makes sure checker won't jump off board
                {
                    //tempSquare = squareArray[index-(width/2)];//up and left
                    if (squareArray[index + oddUpLeft].getPiece() == null &&
                      !squareArray[index + oddUpLeft].getBlocked())//checks if the space is empty
                    {
                        moves.add(squareArray[index + oddUpLeft]); //adds the square to the ArrayList that will be returned.
                    }

                    if (piece instanceof King && index >= halfHeight &&
                      !squareArray[(index - halfHeight) + oddUpLeft].getBlocked()) //up and left on board 1
                    {
                        if (squareArray[(index - halfHeight) + oddUpLeft].getPiece() == null)
                            moves.add(squareArray[(index - halfHeight) + oddUpLeft]);
                    }
                    else if (piece instanceof King && index < halfHeight &&
                      !squareArray[(index + halfHeight) + oddUpLeft].getBlocked()) //up and left on board 2
                    {
                        if (squareArray[(index + halfHeight) + oddUpLeft].getPiece() == null)
                            moves.add(squareArray[(index + halfHeight) + oddUpLeft]);
                    }
                }

                if (positionRow != 1 && positionColumn != width)//makes sure checker won't jump off board
                {
                    //tempSquare = squareArray[index-(width/2)+1];//up and right
                    if (squareArray[index + oddUpRight].getPiece() == null &&
                      !squareArray[index + oddUpRight].getBlocked())
                    {
                        moves.add(squareArray[index + oddUpRight]); //adds the square to the ArrayList that will be returned.
                    }

                    if (piece instanceof King && index >= halfHeight &&
                      !squareArray[(index - halfHeight) + oddUpRight].getBlocked()) //up and right on board 1
                    {
                        if (squareArray[(index - halfHeight) + oddUpRight].getPiece() == null)
                            moves.add(squareArray[(index - halfHeight) + oddUpRight]);
                    }
                    else if (piece instanceof King && index < halfHeight &&
                      !squareArray[(index + halfHeight) + oddUpRight].getBlocked()) //up and right on board 2
                    {
                        if (squareArray[(index + halfHeight) + oddUpRight].getPiece() == null)
                            moves.add(squareArray[(index + halfHeight) + oddUpRight]);
                    }
                }
            }

            if (game.getVisitorTurn() == true || piece instanceof King) //check if it's visitor's turn or a king, i.e. the only two cases where you can move up
            {
                if (positionRow != width && positionColumn != 1)//makes sure checker won't jump off board
                {
                    //tempSquare = squareArray[index+(width/2)];//down and left
                    if (squareArray[index + oddDownLeft].getPiece() == null &&
                      !squareArray[index + oddDownLeft].getBlocked())
                    {
                        moves.add(squareArray[index + oddDownLeft]); //adds the square to the ArrayList that will be returned.
                    }

                    if (piece instanceof King && index >= halfHeight &&
                      !squareArray[(index - halfHeight) + oddDownLeft].getBlocked()) //down and left on board 1
                    {
                        if (squareArray[(index - halfHeight) + oddDownLeft].getPiece() == null)
                            moves.add(squareArray[(index - halfHeight) + oddDownLeft]);
                    }
                    else if (piece instanceof King && index < halfHeight &&
                      !squareArray[(index + halfHeight) + oddDownLeft].getBlocked()) //down and left on board 2
                    {
                        if (squareArray[(index + halfHeight) + oddDownLeft].getPiece() == null)
                            moves.add(squareArray[(index + halfHeight) + oddDownLeft]);
                    }
                }

                if (positionRow != width && positionColumn != width)//makes sure checker won't jump off board
                {
                    //tempSquare = squareArray[index+((width/2)+1)];//down and right
                    if (squareArray[index + oddDownRight].getPiece() == null &&
                      !squareArray[index + oddDownRight].getBlocked())
                    {
                        moves.add(squareArray[index + oddDownRight]); //adds the square to the ArrayList that will be returned.
                    }

                    if (piece instanceof King && index >= halfHeight &&
                      !squareArray[(index - halfHeight) + oddDownRight].getBlocked()) //down and right on board 1
                    {
                        if (squareArray[(index - halfHeight) + oddDownRight].getPiece() == null)
                            moves.add(squareArray[(index - halfHeight) + oddDownRight]);
                    }
                    else if (piece instanceof King && index < halfHeight &&
                      !squareArray[(index + halfHeight) + oddDownRight].getBlocked()) //down and right on board 2
                    {
                        if (squareArray[(index + halfHeight) + oddDownRight].getPiece() == null)
                            moves.add(squareArray[(index + halfHeight) + oddDownRight]);
                    }
                }
            }
        }

        moves.addAll(showJumps(square));

        return moves;
    }

    public ArrayList showJumps (Square square)
    {
        int index = square.getIndex();
        ArrayList<Square> squareJumps = new ArrayList();
        Piece piece = square.getPiece();
        int positionRow = square.getPosition().getRow();
        int positionColumn = square.getPosition().getColumn();
        int positionBoard = square.getPosition().getBoard();

        //even rows
        if (square.getPosition().getRow()%2 == 0)
        {
            if (game.getVisitorTurn() == false || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move up
            {
                if (positionRow > 2 && positionColumn > 2)//up left case. Makes sure checker won't jump off board
                {
                    if (squareArray[index + evenUpLeft].getPiece() != null &&
                            squareArray[index + evenUpLeft].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + evenUpLeft].getSafe())//if up left square is the opposite color
                    {
                        if(squareArray[index + evenUpLeft + oddUpLeft].getPiece() == null &&
                              !squareArray[index + evenUpLeft + oddUpLeft].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + evenUpLeft + oddUpLeft]);
                        }
                    }
                }
                if (positionRow > 2 && positionColumn <= (width-2) )//up right case. Makes sure piece won't jump off board
                {
                    if (squareArray[index + evenUpRight].getPiece() != null &&
                            squareArray[index + evenUpRight].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + evenUpRight].getSafe())//if up left square is the opposite color
                    {
                        if(squareArray[index + evenUpRight + oddUpRight].getPiece() == null&&
                              !squareArray[index + evenUpRight + oddUpRight].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + evenUpRight + oddUpRight]);
                        }
                    }
                }
            }

            if (game.getVisitorTurn() == true || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move down
            {
                if (positionRow <= (width-2) && positionColumn > 2)//down left case. Makes sure checker won't jump off board
                {
                    if (squareArray[index + evenDownLeft].getPiece() != null &&
                            squareArray[index + evenDownLeft].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + evenDownLeft].getSafe())//if up left square is the opposite color
                    {
                        if(squareArray[index + evenDownLeft + oddDownLeft].getPiece() == null&&
                              !squareArray[index + evenDownLeft + oddDownLeft].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + evenDownLeft + oddDownLeft]);
                        }
                    }
                }
                if (positionRow <= (width-2) && positionColumn <= (width-2) )//down right case. Makes sure piece won't jump off board
                {
                    if (squareArray[index + evenDownRight].getPiece() != null &&
                            squareArray[index + evenDownRight].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + evenDownRight].getSafe())//if up left square is the opposite color
                    {
                        if(squareArray[index + evenDownRight + oddDownRight].getPiece() == null&&
                              !squareArray[index + evenDownRight + oddDownRight].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + evenDownRight + oddDownRight]);
                        }
                    }
                }
            }
        }

        else //odd rows (square.getPosition().getRow()%2 == 1)
        {
            if (game.getVisitorTurn() == false || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move up
            {
                if (positionRow > 2 && positionColumn > 2)//up left case. Makes sure checker won't jump off board
                {
                    if (squareArray[index + oddUpLeft].getPiece() != null &&
                            squareArray[index + oddUpLeft].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + oddUpLeft].getSafe())//if up left square is the opposite color
                    {
                        if(squareArray[index + oddUpLeft + evenUpLeft].getPiece() == null &&
                              !squareArray[index + oddUpLeft + evenUpLeft].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + oddUpLeft + evenUpLeft]);
                        }
                    }
                }
                if (positionRow > 2 && positionColumn <= (width-2) )//up right case. Makes sure piece won't jump off board
                {
                    if (squareArray[index + oddUpRight].getPiece() != null &&
                            squareArray[index + oddUpRight].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + oddUpRight].getSafe())//if up left square is the opposite color
                    {
                        if(squareArray[index + oddUpRight + evenUpRight].getPiece() == null &&
                              !squareArray[index + oddUpRight + evenUpRight].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + oddUpRight + evenUpRight]);
                        }
                    }
                }
            }

            if (game.getVisitorTurn() == true || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move down
            {
                if (positionRow <= (width-2) && positionColumn > 2)//down left case. Makes sure checker won't jump off board
                {
                    if (squareArray[index + oddDownLeft].getPiece() != null &&
                            squareArray[index + oddDownLeft].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + oddDownLeft].getSafe())//if up left square is the opposite color
                    {
                        if(squareArray[index + oddDownLeft + evenDownLeft].getPiece() == null &&
                              !squareArray[index + oddDownLeft + evenDownLeft].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + oddDownLeft + evenDownLeft]);
                        }
                    }
                }
                if (positionRow <= (width-2) && positionColumn <= (width-2) )//down right case. Makes sure piece won't jump off board
                {
                    if (squareArray[index + oddDownRight].getPiece() != null &&
                            squareArray[index + oddDownRight].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + oddDownRight].getSafe())//if up left square is the opposite color
                    {
                        if(squareArray[index + oddDownRight + evenDownRight].getPiece() == null &&
                              !squareArray[index + oddDownRight + evenDownRight].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + oddDownRight + evenDownRight]);
                        }
                    }
                }
            }
        }

        return squareJumps;
    }

    public ArrayList requiredJumps()
    {
        ArrayList<Square> requiredSquares = new ArrayList();



        return requiredSquares;
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