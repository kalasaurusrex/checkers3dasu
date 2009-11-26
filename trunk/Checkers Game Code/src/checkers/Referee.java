package checkers;

import java.util.ArrayList;

public class Referee
{
    private final int ALLOWABLE_ROWS = 3;
    int width = 0;
    int halfBoard = 0;
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
        halfBoard = (width * width) / 2;

        evenUpLeft = -((width/2)+1);
        evenUpRight = -(width/2);
        evenDownLeft = (width/2)-1;
        evenDownRight = (width/2);
        oddUpLeft = -(width/2);
        oddUpRight = -(width/2)+1;
        oddDownLeft = (width/2);
        oddDownRight = (width/2)+1;
    }

    public Move executeMove (Square start, Square destination,
            ArrayList<Square> movesList, Move move)
    {
        int sIndex = start.getIndex();
        int dIndex = destination.getIndex();
        int rowDifference = destination.getPosition().getRow() -
                start.getPosition().getRow();
        int columnDifference = destination.getPosition().getColumn() -
                start.getPosition().getColumn();
        int boardDifference = destination.getPosition().getBoard() -
                start.getPosition().getBoard();
        //boolean landedOnMine = false;

        //if (!move.moreJumps())
        //{
            //check if the move is valid
            if (inList(movesList, dIndex))
                move.setValidMove(true);
            else
                move.setValidMove(false);

            //valid move
            if (move.isValidMove())
            {
                //check if destination is an opponents smart mine
                if (destination.getMine() && ((destination.getVisitorMine() &&
                        !game.getVisitorTurn()) || (!destination.getVisitorMine() &&
                        game.getVisitorTurn())))
                {
                    //landedOnMine = true;
                    move.setLandedOnMine(true);
                }

                //check for a jump
                if (rowDifference == 2)
                {
                    if (columnDifference == 2) //down and right
                    {
                        if (boardDifference == 1)
                        {
                            if (start.getPosition().getRow() % 2 == 1)
                            {
                                //move.addJump(squareArray[sIndex + halfBoard + oddDownRight]);

                                if (squareArray[sIndex + halfBoard + oddDownRight].getPiece() != null &&
                                        !squareArray[sIndex + halfBoard + oddDownRight].getSafe())
                                    move.addJump(squareArray[sIndex + halfBoard + oddDownRight]);

                                if (squareArray[sIndex + oddDownRight].getPiece() != null &&
                                        !squareArray[sIndex + oddDownRight].getSafe())
                                    move.addJump(squareArray[sIndex + oddDownRight]);
                            }
                            else
                            {
                                //move.addJump(squareArray[sIndex + halfBoard + evenDownRight]);

                                if (squareArray[sIndex + halfBoard + evenDownRight].getPiece() != null &&
                                        !squareArray[sIndex + halfBoard + evenDownRight].getSafe())
                                    move.addJump(squareArray[sIndex + halfBoard + evenDownRight]);
                                
                                if (squareArray[sIndex + evenDownRight].getPiece() != null &&
                                        !squareArray[sIndex + evenDownRight].getSafe())
                                    move.addJump(squareArray[sIndex + evenDownRight]);
                            }
                        }
                        else if (boardDifference == -1)
                        {
                            if (start.getPosition().getRow() % 2 == 1)
                            {
                                //move.addJump(squareArray[sIndex - (halfBoard - oddDownRight)]);

                                if (squareArray[sIndex - halfBoard + oddDownRight].getPiece() != null &&
                                        !squareArray[sIndex - halfBoard + oddDownRight].getSafe())
                                    move.addJump(squareArray[sIndex - halfBoard + oddDownRight]);
                                
                                if (squareArray[sIndex + oddDownRight].getPiece() != null &&
                                        !squareArray[sIndex + oddDownRight].getSafe())
                                    move.addJump(squareArray[sIndex + oddDownRight]);
                            }
                            else
                            {
                                //move.addJump(squareArray[sIndex - (halfBoard - evenDownRight)]);

                                if (squareArray[sIndex - halfBoard + evenDownRight].getPiece() != null &&
                                        !squareArray[sIndex - halfBoard + evenDownRight].getSafe())
                                    move.addJump(squareArray[sIndex - halfBoard + evenDownRight]);
                                
                                if (squareArray[sIndex + evenDownRight].getPiece() != null &&
                                        !squareArray[sIndex + evenDownRight].getSafe())
                                    move.addJump(squareArray[sIndex + evenDownRight]);
                            }
                        }
                        else if(boardDifference == 0)
                        {
                            if (start.getPosition().getRow() % 2 == 1)
                                move.addJump(squareArray[sIndex + oddDownRight]);
                            else
                                move.addJump(squareArray[sIndex + evenDownRight]);
                        }
                    }
                    else if (columnDifference == -2) //down and left
                    {
                        if (boardDifference == 1)
                        {
                            if (start.getPosition().getRow() % 2 == 1)
                            {
                                //move.addJump(squareArray[sIndex + halfBoard + oddDownLeft]);

                                if (squareArray[sIndex + halfBoard + oddDownLeft].getPiece() != null &&
                                        !squareArray[sIndex + halfBoard + oddDownLeft].getSafe())
                                    move.addJump(squareArray[sIndex + halfBoard + oddDownLeft]);
                                
                                if (squareArray[sIndex + oddDownLeft].getPiece() != null &&
                                        !squareArray[sIndex + oddDownLeft].getSafe())
                                    move.addJump(squareArray[sIndex + oddDownLeft]);
                            }
                            else
                            {
                                //move.addJump(squareArray[sIndex + halfBoard + evenDownLeft]);

                                if (squareArray[sIndex + halfBoard + evenDownLeft].getPiece() != null &&
                                        !squareArray[sIndex + halfBoard + evenDownLeft].getSafe())
                                    move.addJump(squareArray[sIndex + halfBoard + evenDownLeft]);

                                if (squareArray[sIndex + evenDownLeft].getPiece() != null &&
                                        !squareArray[sIndex + evenDownLeft].getSafe())
                                    move.addJump(squareArray[sIndex + evenDownLeft]);
                            }
                        }
                        else if (boardDifference == -1)
                        {
                            if (start.getPosition().getRow() % 2 == 1)
                            {
                                //move.addJump(squareArray[sIndex - (halfBoard - oddDownLeft)]);

                                if (squareArray[sIndex - halfBoard + oddDownLeft].getPiece() != null &&
                                        !squareArray[sIndex - halfBoard + oddDownLeft].getSafe())
                                    move.addJump(squareArray[sIndex - halfBoard + oddDownLeft]);

                                if (squareArray[sIndex + oddDownLeft].getPiece() != null &&
                                        !squareArray[sIndex + oddDownLeft].getSafe())
                                    move.addJump(squareArray[sIndex + oddDownLeft]);
                            }
                            else
                            {
                                //move.addJump(squareArray[sIndex - (halfBoard - evenDownLeft)]);

                                if (squareArray[sIndex - halfBoard + evenDownLeft].getPiece() != null &&
                                        !squareArray[sIndex - halfBoard + evenDownLeft].getSafe())
                                    move.addJump(squareArray[sIndex - halfBoard + evenDownLeft]);

                                if (squareArray[sIndex + evenDownLeft].getPiece() != null &&
                                        !squareArray[sIndex + evenDownLeft].getSafe())
                                    move.addJump(squareArray[sIndex + evenDownLeft]);
                            }
                        }
                        else if(boardDifference == 0)
                        {
                           if (start.getPosition().getRow() % 2 == 1)
                               move.addJump(squareArray[sIndex + oddDownLeft]);
                           else
                               move.addJump(squareArray[sIndex + evenDownLeft]);
                        }
                    }
                }
                else if (rowDifference == -2)
                {
                    if (columnDifference == 2) //up and right
                    {
                        if (boardDifference == 1)
                        {
                            if (start.getPosition().getRow() % 2 == 1)
                            {
                                //move.addJump(squareArray[sIndex + halfBoard + oddUpRight]);

                                if (squareArray[sIndex + halfBoard + oddUpRight].getPiece() != null &&
                                        !squareArray[sIndex + halfBoard + oddUpRight].getSafe())
                                    move.addJump(squareArray[sIndex + halfBoard + oddUpRight]);

                                if (squareArray[sIndex + oddUpRight].getPiece() != null &&
                                        !squareArray[sIndex + oddUpRight].getSafe())
                                    move.addJump(squareArray[sIndex + oddUpRight]);
                            }
                            else
                            {
                                //move.addJump(squareArray[sIndex + halfBoard + evenUpRight]);

                                if (squareArray[sIndex + halfBoard + evenUpRight].getPiece() != null &&
                                        !squareArray[sIndex + halfBoard + evenUpRight].getSafe())
                                    move.addJump(squareArray[sIndex + halfBoard + evenUpRight]);

                                if (squareArray[sIndex + evenUpRight].getPiece() != null &&
                                        !squareArray[sIndex + evenUpRight].getSafe())
                                    move.addJump(squareArray[sIndex + evenUpRight]);
                            }
                        }
                        else if (boardDifference == -1)
                        {
                            if (start.getPosition().getRow() % 2 == 1)
                            {
                                //move.addJump(squareArray[sIndex - (halfBoard - oddUpRight)]);

                                if (squareArray[sIndex - halfBoard + oddUpRight].getPiece() != null &&
                                        !squareArray[sIndex - halfBoard + oddUpRight].getSafe())
                                    move.addJump(squareArray[sIndex - halfBoard + oddUpRight]);

                                if (squareArray[sIndex + oddUpRight].getPiece() != null &&
                                        !squareArray[sIndex + oddUpRight].getSafe())
                                    move.addJump(squareArray[sIndex + oddUpRight]);
                            }
                            else
                            {
                                //move.addJump(squareArray[sIndex - (halfBoard - evenUpRight)]);

                                if (squareArray[sIndex - halfBoard + evenUpRight].getPiece() != null &&
                                        !squareArray[sIndex - halfBoard + evenUpRight].getSafe())
                                    move.addJump(squareArray[sIndex - halfBoard + evenUpRight]);

                                if (squareArray[sIndex + evenUpRight].getPiece() != null &&
                                        !squareArray[sIndex + evenUpRight].getSafe())
                                    move.addJump(squareArray[sIndex + evenUpRight]);
                            }
                        }
                        else if(boardDifference == 0)
                        {
                           if (start.getPosition().getRow() % 2 == 1)
                               move.addJump(squareArray[sIndex + oddUpRight]);
                           else
                               move.addJump(squareArray[sIndex + evenUpRight]);
                        }
                    }
                    else if (columnDifference == -2) //up and left
                    {
                        if (boardDifference == 1)
                        {
                            if (start.getPosition().getRow() % 2 == 1) //odd row
                            {
                                //move.addJump(squareArray[sIndex + halfBoard + oddUpLeft]);

                                if (squareArray[sIndex + halfBoard + oddUpLeft].getPiece() != null &&
                                        !squareArray[sIndex + halfBoard + oddUpLeft].getSafe())
                                    move.addJump(squareArray[sIndex + halfBoard + oddUpLeft]);

                                if (squareArray[sIndex + oddUpLeft].getPiece() != null &&
                                        !squareArray[sIndex + oddUpLeft].getSafe())
                                    move.addJump(squareArray[sIndex + oddUpLeft]);
                            }
                            else //even row
                            {
                                //move.addJump(squareArray[sIndex + halfBoard + evenUpLeft]);

                                if (squareArray[sIndex + halfBoard + evenUpLeft].getPiece() != null &&
                                        !squareArray[sIndex + halfBoard + evenUpLeft].getSafe())
                                    move.addJump(squareArray[sIndex + halfBoard + evenUpLeft]);

                                if (squareArray[sIndex + evenUpLeft].getPiece() != null &&
                                        !squareArray[sIndex + evenUpLeft].getSafe())
                                    move.addJump(squareArray[sIndex + evenUpLeft]);
                            }
                        }
                        else if (boardDifference == -1)
                        {
                            if (start.getPosition().getRow() % 2 == 1)
                            {
                                //move.addJump(squareArray[sIndex - (halfBoard - oddUpLeft)]);

                                if (squareArray[sIndex - halfBoard + oddUpLeft].getPiece() != null &&
                                        !squareArray[sIndex - halfBoard + oddUpLeft].getSafe())
                                    move.addJump(squareArray[sIndex - halfBoard + oddUpLeft]);

                                if (squareArray[sIndex + oddUpLeft].getPiece() != null &&
                                        !squareArray[sIndex + oddUpLeft].getSafe())
                                    move.addJump(squareArray[sIndex + oddUpLeft]);
                            }
                            else
                            {
                                //move.addJump(squareArray[sIndex - (halfBoard - evenUpLeft)]);

                                if (squareArray[sIndex - halfBoard + evenUpLeft].getPiece() != null &&
                                        !squareArray[sIndex - halfBoard + evenUpLeft].getSafe())
                                    move.addJump(squareArray[sIndex - halfBoard + evenUpLeft]);
                                
                                if (squareArray[sIndex + evenUpLeft].getPiece() != null &&
                                        !squareArray[sIndex + evenUpLeft].getSafe())
                                    move.addJump(squareArray[sIndex + evenUpLeft]);
                            }
                        }
                        else if(boardDifference == 0)
                        {
                           if (start.getPosition().getRow() % 2 == 1)
                               move.addJump(squareArray[sIndex + oddUpLeft]);
                           else
                               move.addJump(squareArray[sIndex + evenUpLeft]);
                        }
                    }
                }

                //update square properties and decrease applicable piece counts
                if (move.landedOnMine())
                {
                    destination.setMine(false);
                    
                    if (game.getVisitorTurn())
                    {
                        game.decVisitorPieces();
                        
                        if (game.getVisitorPieces() < 1)
                            move.setGameOver(Main.HOME_WON);
                    }
                    else
                    {
                        game.decHomePieces();
                        
                        if (game.getHomePieces() < 1)
                            move.setGameOver(Main.VISITOR_WON);
                    }
                }
                else if (game.getVisitorTurn() && start.getPiece() instanceof Checker &&
                        destination.getPosition().getRow() == width)
                    destination.setPiece(new King(true));
                else if (!game.getVisitorTurn() && start.getPiece() instanceof Checker &&
                        destination.getPosition().getRow() == 1)
                    destination.setPiece(new King(false));
                else
                    destination.setPiece(start.getPiece());

                if (move.jumpSize() == 1)
                    move.getJump().get(0).setPiece(null);
                
                //decrease the piece count when a jump has occured
                if (move.jumpSize() > 0)
                {
                    if (game.getVisitorTurn())
                    {
                        game.decHomePieces();

                        if (game.getHomePieces() < 1)
                            move.setGameOver(Main.VISITOR_WON);
                    }
                    else
                    {
                        game.decVisitorPieces();

                        if (game.getVisitorPieces() < 1)
                            move.setGameOver(Main.HOME_WON);
                    }

                }

                //find out if a warp has occured
                if (boardDifference == 1 || boardDifference == -1)
                    move.setWarp(true);

                start.setPiece(null);

                //if (move.jumpSize() == 0 || showJumps(destination).isEmpty())
                //    toggleTurn();
            }
            //invalid move
//            else
//            {
//                move.clearMove();
//            }

//            if ((move.isValidMove() && (move.jumpSize() == 0 || showJumps(destination).isEmpty())) ||
//                  (!move.isValidMove() && move.moreJumps()))
//                toggleTurn();
        //}

        return move;
    }

    public void removePiece(Square s)
    {
        s.setPiece(null);
    }

    private boolean inList(ArrayList<Square> moves, int index)
    {
        for (int i = 0; i < moves.size(); i++)
            if (moves.get(i).getIndex() == index)
                return true;

        return false;
    }

    public void toggleTurn()
    {
        if (game.getVisitorTurn()== true)
            game.setVisitorTurn(false);
        else
            game.setVisitorTurn(true);
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
    // movesList and false otherwise.
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

    public ArrayList showMoves (Square square, Move move)
    {
        //int board = square.getPosition().getBoard();
        int column = square.getPosition().getColumn();
        int row = square.getPosition().getRow();
        //Square tempSquare = null;
        int index = square.getIndex();
        Piece piece = square.getPiece();
        ArrayList<Square> movesList = new ArrayList();
        boolean warpBlocked = false;
        

        //see if warp move is available
        if (index >= halfBoard && squareArray[index - halfBoard].getPiece() == null &&
              !squareArray[index - halfBoard].getBlocked())
        {
            movesList.add(squareArray[index - halfBoard]);

            warpBlocked = false;
        }
        else if (index < halfBoard && squareArray[index + halfBoard].getPiece() == null &&
              !squareArray[index + halfBoard].getBlocked())
        {
            movesList.add(squareArray[index + halfBoard]);
            
            warpBlocked = false;
        }
        else
            warpBlocked = true;

        //the logic for pieces in even rows
        if (row%2 == 0)
        {  
            if (game.getVisitorTurn() == false || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move up
            {
                if (row != 1 && column != 1)//makes sure checker won't jump off board
                {
                    if (squareArray[index + evenUpLeft].getPiece() == null &&
                            !squareArray[index + evenUpLeft].getBlocked())//checks if the space is empty
                    {
                        movesList.add(squareArray[index + evenUpLeft]); //adds the square to the ArrayList that will be returned.
                    }

                    //if not blocked off check for available squares on other board
                    if (squareArray[index + evenUpLeft].getPiece() == null || !warpBlocked)
                    {
                        if (piece instanceof King && index >= halfBoard &&
                          !squareArray[(index - halfBoard) + evenUpLeft].getBlocked()) //up and left on board 1
                        {
                            if (squareArray[(index - halfBoard) + evenUpLeft].getPiece() == null)
                                movesList.add(squareArray[(index - halfBoard) + evenUpLeft]);
                        }
                        else if (piece instanceof King && index < halfBoard &&
                          !squareArray[(index + halfBoard) + evenUpLeft].getBlocked()) //up and left on board 2
                        {
                            if (squareArray[(index + halfBoard) + evenUpLeft].getPiece() == null)
                                movesList.add(squareArray[(index + halfBoard)+ evenUpLeft]);
                        }
                    }
                }

                if (row != 1 && column != width)//makes sure checker won't jump off board
                {
                    if (squareArray[index + evenUpRight].getPiece() == null &&
                      !squareArray[index + evenUpRight].getBlocked())
                    {
                        movesList.add(squareArray[index + evenUpRight]); //adds the square to the ArrayList that will be returned.
                    }

                    //if not blocked off check for available squares on other board
                    if (squareArray[index + evenUpRight].getPiece() == null || !warpBlocked)
                    {
                        if (piece instanceof King && index >= halfBoard &&
                          !squareArray[(index - halfBoard) + evenUpRight].getBlocked()) //up and right on board 1
                        {
                            if (squareArray[(index - halfBoard) + evenUpRight].getPiece() == null)
                                movesList.add(squareArray[(index - halfBoard) + evenUpRight]);
                        }
                        else if (piece instanceof King && index < halfBoard &&
                          !squareArray[(index + halfBoard) + evenUpRight].getBlocked()) //up and right on board 2
                        {
                            if (squareArray[(index + halfBoard) + evenUpRight].getPiece() == null)
                                movesList.add(squareArray[(index + halfBoard) + evenUpRight]);
                        }
                    }
                }
            }

            if (game.getVisitorTurn() == true || piece instanceof King) //checks if it's visitor's turn or a King, i.e. the only two cases where you can move down
            {
                if (row != width && column != 1)//makes sure checker won't jump off board
                {
                    if (squareArray[index + evenDownLeft].getPiece() == null &&
                      !squareArray[index + evenDownLeft].getBlocked())
                    {
                        movesList.add(squareArray[index + evenDownLeft]); //adds the square to the ArrayList that will be returned.
                    }

                    //if not blocked off check for available squares on other board
                    if (squareArray[index + evenDownLeft].getPiece() == null || !warpBlocked)
                    {
                        if (piece instanceof King && index >= halfBoard &&
                          !squareArray[(index - halfBoard) + evenDownLeft].getBlocked()) //down and left on board 1
                        {
                            if (squareArray[(index - halfBoard) + evenDownLeft].getPiece() == null)
                                movesList.add(squareArray[(index - halfBoard) + evenDownLeft]);
                        }
                        else if (piece instanceof King && index < halfBoard &&
                          !squareArray[(index + halfBoard) + evenDownLeft].getBlocked()) //down and left on board 2
                        {
                            if (squareArray[(index + halfBoard) + evenDownLeft].getPiece() == null)
                                movesList.add(squareArray[(index + halfBoard) + evenDownLeft]);
                        }
                    }
                }

                if (row != width && column != width)//makes sure checker won't jump off board
                {
                    if (squareArray[index + evenDownRight].getPiece() == null &&
                      !squareArray[index + evenDownRight].getBlocked())
                    {
                        movesList.add(squareArray[index + evenDownRight]); //adds the square to the ArrayList that will be returned.
                    }

                    //if not blocked off check for available squares on other board
                    if (squareArray[index + evenDownRight].getPiece() == null || !warpBlocked)
                    {
                        if (piece instanceof King && index >= halfBoard &&
                          !squareArray[(index - halfBoard) + evenDownRight].getBlocked()) //down and right on board 1
                        {
                            if (squareArray[(index - halfBoard) + evenDownRight].getPiece() == null)
                                movesList.add(squareArray[(index - halfBoard) + evenDownRight]);
                        }
                        else if (piece instanceof King && index < halfBoard &&
                          !squareArray[(index + halfBoard) + evenDownRight].getBlocked()) //down and right on board 2
                        {
                            if (squareArray[(index + halfBoard) + evenDownRight].getPiece() == null)
                                movesList.add(squareArray[(index + halfBoard) + evenDownRight]);
                        }
                    }
                }
            }
        }
        //the logic for pieces in odd rows
        else
        {
            if (game.getVisitorTurn() == false || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move up
            {
                if (row != 1 && column != 1)//makes sure checker won't jump off board
                {
                    if (squareArray[index + oddUpLeft].getPiece() == null &&
                      !squareArray[index + oddUpLeft].getBlocked())//checks if the space is empty
                    {
                        movesList.add(squareArray[index + oddUpLeft]); //adds the square to the ArrayList that will be returned.
                    }

                    //if not blocked off check for available squares on other board
                    if (squareArray[index + oddUpLeft].getPiece() == null || !warpBlocked)
                    {
                        if (piece instanceof King && index >= halfBoard &&
                          !squareArray[(index - halfBoard) + oddUpLeft].getBlocked()) //up and left on board 1
                        {
                            if (squareArray[(index - halfBoard) + oddUpLeft].getPiece() == null)
                                movesList.add(squareArray[(index - halfBoard) + oddUpLeft]);
                        }
                        else if (piece instanceof King && index < halfBoard &&
                          !squareArray[(index + halfBoard) + oddUpLeft].getBlocked()) //up and left on board 2
                        {
                            if (squareArray[(index + halfBoard) + oddUpLeft].getPiece() == null)
                                movesList.add(squareArray[(index + halfBoard) + oddUpLeft]);
                        }
                    }
                }

                if (row != 1 && column != width)//makes sure checker won't jump off board
                {
                    if (squareArray[index + oddUpRight].getPiece() == null &&
                      !squareArray[index + oddUpRight].getBlocked())
                    {
                        movesList.add(squareArray[index + oddUpRight]); //adds the square to the ArrayList that will be returned.
                    }

                    //if not blocked off check for available squares on other board
                    if (squareArray[index + oddUpRight].getPiece() == null || !warpBlocked)
                    {
                        if (piece instanceof King && index >= halfBoard &&
                          !squareArray[(index - halfBoard) + oddUpRight].getBlocked()) //up and right on board 1
                        {
                            if (squareArray[(index - halfBoard) + oddUpRight].getPiece() == null)
                                movesList.add(squareArray[(index - halfBoard) + oddUpRight]);
                        }
                        else if (piece instanceof King && index < halfBoard &&
                          !squareArray[(index + halfBoard) + oddUpRight].getBlocked()) //up and right on board 2
                        {
                            if (squareArray[(index + halfBoard) + oddUpRight].getPiece() == null)
                                movesList.add(squareArray[(index + halfBoard) + oddUpRight]);
                        }
                    }
                }
            }

            if (game.getVisitorTurn() == true || piece instanceof King) //check if it's visitor's turn or a king, i.e. the only two cases where you can move up
            {
                if (row != width && column != 1)//makes sure checker won't jump off board
                {
                    if (squareArray[index + oddDownLeft].getPiece() == null &&
                      !squareArray[index + oddDownLeft].getBlocked())
                    {
                        movesList.add(squareArray[index + oddDownLeft]); //adds the square to the ArrayList that will be returned.
                    }

                    //if not blocked off check for available squares on other board
                    if (squareArray[index + oddDownLeft].getPiece() == null || !warpBlocked)
                    {
                        if (piece instanceof King && index >= halfBoard &&
                          !squareArray[(index - halfBoard) + oddDownLeft].getBlocked()) //down and left on board 1
                        {
                            if (squareArray[(index - halfBoard) + oddDownLeft].getPiece() == null)
                                movesList.add(squareArray[(index - halfBoard) + oddDownLeft]);
                        }
                        else if (piece instanceof King && index < halfBoard &&
                          !squareArray[(index + halfBoard) + oddDownLeft].getBlocked()) //down and left on board 2
                        {
                            if (squareArray[(index + halfBoard) + oddDownLeft].getPiece() == null)
                                movesList.add(squareArray[(index + halfBoard) + oddDownLeft]);
                        }
                    }
                }

                if (row != width && column != width)//makes sure checker won't jump off board
                {
                    if (squareArray[index + oddDownRight].getPiece() == null &&
                      !squareArray[index + oddDownRight].getBlocked())
                    {
                        movesList.add(squareArray[index + oddDownRight]); //adds the square to the ArrayList that will be returned.
                    }

                    //if not blocked off check for available squares on other board
                    if (squareArray[index + oddDownRight].getPiece() == null || !warpBlocked)
                    {
                        if (piece instanceof King && index >= halfBoard &&
                          !squareArray[(index - halfBoard) + oddDownRight].getBlocked()) //down and right on board 1
                        {
                            if (squareArray[(index - halfBoard) + oddDownRight].getPiece() == null)
                                movesList.add(squareArray[(index - halfBoard) + oddDownRight]);
                        }
                        else if (piece instanceof King && index < halfBoard &&
                          !squareArray[(index + halfBoard) + oddDownRight].getBlocked()) //down and right on board 2
                        {
                            if (squareArray[(index + halfBoard) + oddDownRight].getPiece() == null)
                                movesList.add(squareArray[(index + halfBoard) + oddDownRight]);
                        }
                    }
                }
            }
        }

        movesList.addAll(showJumps(square, move));

        return movesList;
    }

    public ArrayList showJumps (Square square, Move move)
    {
        int index = square.getIndex();
        ArrayList<Square> squareJumps = new ArrayList();
        Piece piece = square.getPiece();
        int row = square.getPosition().getRow();
        int column = square.getPosition().getColumn();
        int board = square.getPosition().getBoard();
        boolean warpBlocked = false;

        //see if warp move is available
        if (index >= halfBoard && squareArray[index - halfBoard].getPiece() == null &&
              !squareArray[index - halfBoard].getBlocked())
        {
            //squareJumps.add(squareArray[index - halfBoard]);

            warpBlocked = false;
        }
        else if (index < halfBoard && squareArray[index + halfBoard].getPiece() == null &&
              !squareArray[index + halfBoard].getBlocked())
        {
            //squareJumps.add(squareArray[index + halfBoard]);

            warpBlocked = false;
        }
        else
            warpBlocked = true;

        //even rows
        if (row % 2 == 0)
        {
            if (game.getVisitorTurn() == false || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move up
            {
                if (row > 2 && column > 2)//up left case. Makes sure checker won't jump off board
                {
                    //check if adjacent square is occupied
                    if (squareArray[index + evenUpLeft].getPiece() != null &&
                            squareArray[index + evenUpLeft].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + evenUpLeft].getSafe())//if up left square is the opposite color
                    {
                        //check if jump is available
                        if (squareArray[index + evenUpLeft + oddUpLeft].getPiece() == null &&
                              !squareArray[index + evenUpLeft + oddUpLeft].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + evenUpLeft + oddUpLeft]);

                            //check if jump then warp is available
                            if (piece instanceof King && board == 1 && !move.hasWarped() &&
                                    squareArray[index + halfBoard + evenUpLeft + oddUpLeft].getPiece() == null &&
                                    !squareArray[index + halfBoard + evenUpLeft + oddUpLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenUpLeft + oddUpLeft]);
                            }
                            else if (piece instanceof King && board == 2 && !move.hasWarped() &&
                                    squareArray[index - halfBoard + evenUpLeft + oddUpLeft].getPiece() == null &&
                                    !squareArray[index - halfBoard + evenUpLeft + oddUpLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenUpLeft + oddUpLeft]);
                            }
                        }
                    }

                    //if not blocked off check for available squares on other board
                    if (!warpBlocked && !move.hasWarped())
                    {
                        //check up and left on board other board
                        if (piece instanceof King && board == 1 &&
                            squareArray[index + halfBoard + evenUpLeft].getPiece() != null &&
                            squareArray[index + halfBoard + evenUpLeft].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index + halfBoard + evenUpLeft].getSafe())
                        {
                            if (squareArray[index + halfBoard + evenUpLeft + oddUpLeft].getPiece() == null &&
                                  !squareArray[index + halfBoard + evenUpLeft + oddUpLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenUpLeft + oddUpLeft]);
                            }
                        }
                        else if (piece instanceof King && board == 2 &&
                            squareArray[index - halfBoard + evenUpLeft].getPiece() != null &&
                            squareArray[index - halfBoard + evenUpLeft].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index - halfBoard + evenUpLeft].getSafe())
                        {
                            if (squareArray[index - halfBoard + evenUpLeft + oddUpLeft].getPiece() == null &&
                                  !squareArray[index - halfBoard + evenUpLeft + oddUpLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenUpLeft + oddUpLeft]);
                            }
                        }
                    }
                }
                if (row > 2 && column <= (width-2) )//up right case. Makes sure piece won't jump off board
                {
                    //check if adjacent square is occupied
                    if (squareArray[index + evenUpRight].getPiece() != null &&
                            squareArray[index + evenUpRight].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + evenUpRight].getSafe())//if up right square is the opposite color
                    {
                        //check if jump is available
                        if(squareArray[index + evenUpRight + oddUpRight].getPiece() == null&&
                              !squareArray[index + evenUpRight + oddUpRight].getBlocked())//check the spot up and right of the piece
                        {
                            squareJumps.add(squareArray[index + evenUpRight + oddUpRight]);

                            //check if jump then warp is available
                            if (piece instanceof King && board == 1 && !move.hasWarped() &&
                                    squareArray[index + halfBoard + evenUpRight + oddUpRight].getPiece() == null &&
                                    !squareArray[index + halfBoard + evenUpRight + oddUpRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenUpRight + oddUpRight]);
                            }
                            else if (piece instanceof King && board == 2 && !move.hasWarped() &&
                                    squareArray[index - halfBoard + evenUpRight + oddUpRight].getPiece() == null &&
                                    !squareArray[index - halfBoard + evenUpRight + oddUpRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenUpRight + oddUpRight]);
                            }
                        }
                    }

                    //if not blocked off check for available squares on other board
                    if (!warpBlocked && !move.hasWarped())
                    {
                        //check up and right on board other board
                        if (piece instanceof King && board == 1 &&
                            squareArray[index + halfBoard + evenUpRight].getPiece() != null &&
                            squareArray[index + halfBoard + evenUpRight].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index + halfBoard + evenUpRight].getSafe())
                        {
                            if (squareArray[index + halfBoard + evenUpRight + oddUpRight].getPiece() == null &&
                                  !squareArray[index + halfBoard + evenUpRight + oddUpRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenUpRight + oddUpRight]);
                            }
                        }
                        else if (piece instanceof King && board == 2 &&
                            squareArray[index - halfBoard + evenUpRight].getPiece() != null &&
                            squareArray[index - halfBoard + evenUpRight].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index - halfBoard + evenUpRight].getSafe())
                        {
                            if (squareArray[index - halfBoard + evenUpRight + oddUpRight].getPiece() == null &&
                                  !squareArray[index - halfBoard + evenUpRight + oddUpRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenUpRight + oddUpRight]);
                            }
                        }
                    }
                }
            }

            if (game.getVisitorTurn() == true || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move down
            {
                if (row <= (width-2) && column > 2)//down left case. Makes sure checker won't jump off board
                {
                    //check if adjacent square is occupied
                    if (squareArray[index + evenDownLeft].getPiece() != null &&
                            squareArray[index + evenDownLeft].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + evenDownLeft].getSafe())//if up left square is the opposite color
                    {
                        //check if jump is available
                        if(squareArray[index + evenDownLeft + oddDownLeft].getPiece() == null&&
                              !squareArray[index + evenDownLeft + oddDownLeft].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + evenDownLeft + oddDownLeft]);

                            //check if jump then warp is available
                            if (piece instanceof King && board == 1 && !move.hasWarped() &&
                                    squareArray[index + halfBoard + evenDownLeft + oddDownLeft].getPiece() == null &&
                                    !squareArray[index + halfBoard + evenDownLeft + oddDownLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenDownLeft + oddDownLeft]);
                            }
                            else if (piece instanceof King && board == 2 && !move.hasWarped() &&
                                    squareArray[index - halfBoard + evenDownLeft + oddDownLeft].getPiece() == null &&
                                    !squareArray[index - halfBoard + evenDownLeft + oddDownLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenDownLeft + oddDownLeft]);
                            }
                        }
                    }

                    //if not blocked off check for available squares on other board
                    if (!warpBlocked && !move.hasWarped())
                    {
                        //check down and left on board other board
                        if (piece instanceof King && board == 1 &&
                            squareArray[index + halfBoard + evenDownLeft].getPiece() != null &&
                            squareArray[index + halfBoard + evenDownLeft].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index + halfBoard + evenDownLeft].getSafe())
                        {
                            if (squareArray[index + halfBoard + evenDownLeft + oddDownLeft].getPiece() == null &&
                                  !squareArray[index + halfBoard + evenDownLeft + oddDownLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenDownLeft + oddDownLeft]);
                            }
                        }
                        else if (piece instanceof King && board == 2 &&
                            squareArray[index - halfBoard + evenDownLeft].getPiece() != null &&
                            squareArray[index - halfBoard + evenDownLeft].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index - halfBoard + evenDownLeft].getSafe())
                        {
                            if (squareArray[index - halfBoard + evenDownLeft + oddDownLeft].getPiece() == null &&
                                  !squareArray[index - halfBoard + evenDownLeft + oddDownLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenDownLeft + oddDownLeft]);
                            }
                        }
                    }
                }
                if (row <= (width-2) && column <= (width-2) )//down right case. Makes sure piece won't jump off board
                {
                    //check if adjacent square is occupied
                    if (squareArray[index + evenDownRight].getPiece() != null &&
                            squareArray[index + evenDownRight].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + evenDownRight].getSafe())//if up left square is the opposite color
                    {
                        //check if jump is available
                        if(squareArray[index + evenDownRight + oddDownRight].getPiece() == null&&
                              !squareArray[index + evenDownRight + oddDownRight].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + evenDownRight + oddDownRight]);

                            //check if jump then warp is available
                            if (piece instanceof King && board == 1 && !move.hasWarped() &&
                                    squareArray[index + halfBoard + evenDownRight + oddDownRight].getPiece() == null &&
                                    !squareArray[index + halfBoard + evenDownRight + oddDownRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenDownRight + oddDownRight]);
                            }
                            else if (piece instanceof King && board == 2 && !move.hasWarped() &&
                                    squareArray[index - halfBoard + evenDownRight + oddDownRight].getPiece() == null &&
                                    !squareArray[index - halfBoard + evenDownRight + oddDownRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenDownRight + oddDownRight]);
                            }
                        }
                    }

                    //if not blocked off check for available squares on other board
                    if (!warpBlocked && !move.hasWarped())
                    {
                        //check down and right on board other board
                        if (piece instanceof King && board == 1 &&
                            squareArray[index + halfBoard + evenDownRight].getPiece() != null &&
                            squareArray[index + halfBoard + evenDownRight].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index + halfBoard + evenDownRight].getSafe())
                        {
                            if (squareArray[index + halfBoard + evenDownRight + oddDownRight].getPiece() == null &&
                                  !squareArray[index + halfBoard + evenDownRight + oddDownRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenDownRight + oddDownRight]);
                            }
                        }
                        else if (piece instanceof King && board == 2 &&
                            squareArray[index - halfBoard + evenDownRight].getPiece() != null &&
                            squareArray[index - halfBoard + evenDownRight].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index - halfBoard + evenDownRight].getSafe())
                        {
                            if (squareArray[index - halfBoard + evenDownRight + oddDownRight].getPiece() == null &&
                                  !squareArray[index - halfBoard + evenDownRight + oddDownRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenDownRight + oddDownRight]);
                            }
                        }
                    }
                }
            }
        }

        else //odd rows (square.getPosition().getRow()%2 == 1)
        {
            if (game.getVisitorTurn() == false || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move up
            {
                if (row > 2 && column > 2)//up left case. Makes sure checker won't jump off board
                {
                    //check if adjacent square is occupied
                    if (squareArray[index + oddUpLeft].getPiece() != null &&
                            squareArray[index + oddUpLeft].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + oddUpLeft].getSafe())//if up left square is the opposite color
                    {
                        //check if jump is available
                        if(squareArray[index + oddUpLeft + evenUpLeft].getPiece() == null &&
                              !squareArray[index + oddUpLeft + evenUpLeft].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + oddUpLeft + evenUpLeft]);

                            //check if jump then warp is available
                            if (piece instanceof King && board == 1 && !move.hasWarped() &&
                                    squareArray[index + halfBoard + evenUpLeft + oddUpLeft].getPiece() == null &&
                                    !squareArray[index + halfBoard + evenUpLeft + oddUpLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenUpLeft + oddUpLeft]);
                            }
                            else if (piece instanceof King && board == 2 && !move.hasWarped() &&
                                    squareArray[index - halfBoard + evenUpLeft + oddUpLeft].getPiece() == null &&
                                    !squareArray[index - halfBoard + evenUpLeft + oddUpLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenUpLeft + oddUpLeft]);
                            }
                        }
                    }

                    //if not blocked off check for available squares on other board
                    if (!warpBlocked && !move.hasWarped())
                    {
                        //check up and left on board other board
                        if (piece instanceof King && board == 1 &&
                            squareArray[index + halfBoard + oddUpLeft].getPiece() != null &&
                            squareArray[index + halfBoard + oddUpLeft].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index + halfBoard + oddUpLeft].getSafe())
                        {
                            if (squareArray[index + halfBoard + evenUpLeft + oddUpLeft].getPiece() == null &&
                                  !squareArray[index + halfBoard + evenUpLeft + oddUpLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenUpLeft + oddUpLeft]);
                            }
                        }
                        else if (piece instanceof King && board == 2 &&
                            squareArray[index - halfBoard + oddUpLeft].getPiece() != null &&
                            squareArray[index - halfBoard + oddUpLeft].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index - halfBoard + oddUpLeft].getSafe())
                        {
                            if (squareArray[index - halfBoard + evenUpLeft + oddUpLeft].getPiece() == null &&
                                  !squareArray[index - halfBoard + evenUpLeft + oddUpLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenUpLeft + oddUpLeft]);
                            }
                        }
                    }
                }
                if (row > 2 && column <= (width-2) )//up right case. Makes sure piece won't jump off board
                {
                    //check if adjacent square is occupied
                    if (squareArray[index + oddUpRight].getPiece() != null &&
                            squareArray[index + oddUpRight].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + oddUpRight].getSafe())//if up left square is the opposite color
                    {
                        //check if jump is available
                        if(squareArray[index + oddUpRight + evenUpRight].getPiece() == null &&
                              !squareArray[index + oddUpRight + evenUpRight].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + oddUpRight + evenUpRight]);

                            //check if jump then warp is available
                            if (piece instanceof King && board == 1 && !move.hasWarped() &&
                                    squareArray[index + halfBoard + evenUpRight + oddUpRight].getPiece() == null &&
                                    !squareArray[index + halfBoard + evenUpRight + oddUpRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenUpRight + oddUpRight]);
                            }
                            else if (piece instanceof King && board == 2 && !move.hasWarped() &&
                                    squareArray[index - halfBoard + evenUpRight + oddUpRight].getPiece() == null &&
                                    !squareArray[index - halfBoard + evenUpRight + oddUpRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenUpRight + oddUpRight]);
                            }
                        }
                    }

                    //if not blocked off check for available squares on other board
                    if (!warpBlocked && !move.hasWarped())
                    {
                        //check up and right on board other board
                        if (piece instanceof King && board == 1 &&
                            squareArray[index + halfBoard + oddUpRight].getPiece() != null &&
                            squareArray[index + halfBoard + oddUpRight].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index + halfBoard + oddUpRight].getSafe())
                        {
                            if (squareArray[index + halfBoard + evenUpRight + oddUpRight].getPiece() == null &&
                                  !squareArray[index + halfBoard + evenUpRight + oddUpRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenUpRight + oddUpRight]);
                            }
                        }
                        else if (piece instanceof King && board == 2 &&
                            squareArray[index - halfBoard + oddUpRight].getPiece() != null &&
                            squareArray[index - halfBoard + oddUpRight].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index - halfBoard + oddUpRight].getSafe())
                        {
                            if (squareArray[index - halfBoard + evenUpRight + oddUpRight].getPiece() == null &&
                                  !squareArray[index - halfBoard + evenUpRight + oddUpRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenUpRight + oddUpRight]);
                            }
                        }
                    }
                }
            }

            if (game.getVisitorTurn() == true || piece instanceof King) //check if it's home's turn or a king, i.e. the only two cases where you can move down
            {
                if (row <= (width-2) && column > 2)//down left case. Makes sure checker won't jump off board
                {
                    //check if adjacent square is occupied
                    if (squareArray[index + oddDownLeft].getPiece() != null &&
                            squareArray[index + oddDownLeft].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + oddDownLeft].getSafe())//if up left square is the opposite color
                    {
                        //check if jump is available
                        if(squareArray[index + oddDownLeft + evenDownLeft].getPiece() == null &&
                              !squareArray[index + oddDownLeft + evenDownLeft].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + oddDownLeft + evenDownLeft]);

                            //check if jump then warp is available
                            if (piece instanceof King && board == 1 && !move.hasWarped() &&
                                    squareArray[index + halfBoard + evenDownLeft + oddDownLeft].getPiece() == null &&
                                    !squareArray[index + halfBoard + evenDownLeft + oddDownLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenDownLeft + oddDownLeft]);
                            }
                            else if (piece instanceof King && board == 2 && !move.hasWarped() &&
                                    squareArray[index - halfBoard + evenDownLeft + oddDownLeft].getPiece() == null &&
                                    !squareArray[index - halfBoard + evenDownLeft + oddDownLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenDownLeft + oddDownLeft]);
                            }
                        }
                    }

                    //if not blocked off check for available squares on other board
                    if (!warpBlocked && !move.hasWarped())
                    {
                        //check down and left on board other board
                        if (piece instanceof King && board == 1 &&
                            squareArray[index + halfBoard + oddDownLeft].getPiece() != null &&
                            squareArray[index + halfBoard + oddDownLeft].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index + halfBoard + oddDownLeft].getSafe())
                        {
                            if (squareArray[index + halfBoard + evenDownLeft + oddDownLeft].getPiece() == null &&
                                  !squareArray[index + halfBoard + evenDownLeft + oddDownLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenDownLeft + oddDownLeft]);
                            }
                        }
                        else if (piece instanceof King && board == 2 &&
                            squareArray[index - halfBoard + oddDownLeft].getPiece() != null &&
                            squareArray[index - halfBoard + oddDownLeft].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index - halfBoard + oddDownLeft].getSafe())
                        {
                            if (squareArray[index - halfBoard + evenDownLeft + oddDownLeft].getPiece() == null &&
                                  !squareArray[index - halfBoard + evenDownLeft + oddDownLeft].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenDownLeft + oddDownLeft]);
                            }
                        }
                    }
                }
                if (row <= (width-2) && column <= (width-2) )//down right case. Makes sure piece won't jump off board
                {
                    //check if adjacent square is occupied
                    if (squareArray[index + oddDownRight].getPiece() != null &&
                            squareArray[index + oddDownRight].getPiece().getColor() != square.getPiece().getColor() &&
                            !squareArray[index + oddDownRight].getSafe())//if up left square is the opposite color
                    {
                        //check if jump is available
                        if(squareArray[index + oddDownRight + evenDownRight].getPiece() == null &&
                              !squareArray[index + oddDownRight + evenDownRight].getBlocked())//check the spot up and left of the piece
                        {
                            squareJumps.add(squareArray[index + oddDownRight + evenDownRight]);

                            //check if jump then warp is available
                            if (piece instanceof King && board == 1 && !move.hasWarped() &&
                                    squareArray[index + halfBoard + evenDownRight + oddDownRight].getPiece() == null &&
                                    !squareArray[index + halfBoard + evenDownRight + oddDownRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenDownRight + oddDownRight]);
                            }
                            else if (piece instanceof King && board == 2 && !move.hasWarped() &&
                                    squareArray[index - halfBoard + evenDownRight + oddDownRight].getPiece() == null &&
                                    !squareArray[index - halfBoard + evenDownRight + oddDownRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenDownRight + oddDownRight]);
                            }
                        }
                    }

                    //if not blocked off check for available squares on other board
                    if (!warpBlocked && !move.hasWarped())
                    {
                        //check down and right on board other board
                        if (piece instanceof King && board == 1 &&
                            squareArray[index + halfBoard + oddDownRight].getPiece() != null &&
                            squareArray[index + halfBoard + oddDownRight].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index + halfBoard + oddDownRight].getSafe())
                        {
                            if (squareArray[index + halfBoard + evenDownRight + oddDownRight].getPiece() == null &&
                                  !squareArray[index + halfBoard + evenDownRight + oddDownRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index + halfBoard + evenDownRight + oddDownRight]);
                            }
                        }
                        else if (piece instanceof King && board == 2 &&
                            squareArray[index - halfBoard + oddDownRight].getPiece() != null &&
                            squareArray[index - halfBoard + oddDownRight].getPiece().getColor() !=
                            square.getPiece().getColor() &&
                            !squareArray[index - halfBoard + oddDownRight].getSafe())
                        {
                            if (squareArray[index - halfBoard + evenDownRight + oddDownRight].getPiece() == null &&
                                  !squareArray[index - halfBoard + evenDownRight + oddDownRight].getBlocked())
                            {
                                squareJumps.add(squareArray[index - halfBoard + evenDownRight + oddDownRight]);
                            }
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