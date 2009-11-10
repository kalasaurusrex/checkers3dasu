/*
* The GameScreen class provides a Graphical User Interface and handles user
* actions relating to piece placement and movement in the 3D checkers game.
*
* Author:  David Clark
*/

package checkers;

import java.util.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class GameScreen extends javax.swing.JFrame
{
    private static final int NIL = 0;
    final int PIXELS = 40;
    private Square[] square;
    private Referee referee;
    private Game game;
    private int visitorCheckers = 0;
    private int visitorKings = 1;
    private int visitorBlocked = 2;
    private int visitorSafe = 1;
    private int visitorMines = 2;
    private int homeCheckers = 0;
    private int homeKings = 1;
    private int homeBlocked = 2;
    private int homeSafe = 1;
    private int homeMines = 2;
    private int width = 0;
    private int boardSize = 0;
    private boolean boardSetup = false;
    private String homePlayer;
    private String visitorPlayer;
    Square firstSelectMove  = null;
    Square secondSelectMove = null;
    Square firstSelectSetup;
    Square secondSelectSetup;
    ArrayList<Square> availableMoves;
    Random random;

    //load fonts
    Font oldEnglish_14 = loadFont(Font.BOLD, 14);
    Font oldEnglish_16b = loadFont(Font.BOLD, 16);
    Font oldEnglish_16 = loadFont(Font.PLAIN, 16);

    //load images
    ImageIcon board8X8 = new ImageIcon(getClass().getResource("/checkers/images/Board8x8.png"));
    ImageIcon board10X10 = new ImageIcon(getClass().getResource("/checkers/images/Board10x10.png"));
    ImageIcon squareBlack = new ImageIcon(getClass().getResource("/checkers/images/SquareBlack.png"));
    ImageIcon hSquareBlack = new ImageIcon(getClass().getResource("/checkers/images/hSquareBlack.png"));
    ImageIcon checkerBlack = new ImageIcon(getClass().getResource("/checkers/images/CheckerBlack.png"));
    ImageIcon hCheckerBlack = new ImageIcon(getClass().getResource("/checkers/images/hCheckerBlack.png"));
    ImageIcon checkerRed = new ImageIcon(getClass().getResource("/checkers/images/CheckerRed.png"));
    ImageIcon hCheckerRed = new ImageIcon(getClass().getResource("/checkers/images/hCheckerRed.png"));
    ImageIcon kingBlack = new ImageIcon(getClass().getResource("/checkers/images/KingBlack.png"));
    ImageIcon hKingBlack = new ImageIcon(getClass().getResource("/checkers/images/hKingBlack.png"));
    ImageIcon kingRed = new ImageIcon(getClass().getResource("/checkers/images/KingRed.png"));
    ImageIcon hKingRed = new ImageIcon(getClass().getResource("/checkers/images/hKingRed.png"));
    ImageIcon squareSafe = new ImageIcon(getClass().getResource("/checkers/images/SquareSafe.png"));
    ImageIcon hSquareSafe = new ImageIcon(getClass().getResource("/checkers/images/hSquareSafe.png"));
    ImageIcon safeCheckerBlack = new ImageIcon(getClass().getResource("/checkers/images/SafeCheckerBlack.png"));
    ImageIcon hSafeCheckerBlack = new ImageIcon(getClass().getResource("/checkers/images/hSafeCheckerBlack.png"));
    ImageIcon safeCheckerRed = new ImageIcon(getClass().getResource("/checkers/images/SafeCheckerRed.png"));
    ImageIcon hSafeCheckerRed = new ImageIcon(getClass().getResource("/checkers/images/hSafeCheckerRed.png"));
    ImageIcon safeKingBlack = new ImageIcon(getClass().getResource("/checkers/images/SafeKingBlack.png"));
    ImageIcon hSafeKingBlack = new ImageIcon(getClass().getResource("/checkers/images/hSafeKingBlack.png"));
    ImageIcon safeKingRed = new ImageIcon(getClass().getResource("/checkers/images/SafeKingRed.png"));
    ImageIcon hSafeKingRed = new ImageIcon(getClass().getResource("/checkers/images/hSafeKingRed.png"));
    ImageIcon squareBlocked = new ImageIcon(getClass().getResource("/checkers/images/SquareBlocked.png"));
    ImageIcon hSquareBlocked = new ImageIcon(getClass().getResource("/checkers/images/hSquareBlocked.png"));
    ImageIcon setupCheckerBlack = new ImageIcon(getClass().getResource("/checkers/images/PieceCheckerBlack.png"));
    ImageIcon hSetupCheckerBlack = new ImageIcon(getClass().getResource("/checkers/images/hPieceCheckerBlack.png"));
    ImageIcon setupKingBlack = new ImageIcon(getClass().getResource("/checkers/images/PieceKingBlack.png"));
    ImageIcon hSetupKingBlack = new ImageIcon(getClass().getResource("/checkers/images/hPieceKingBlack.png"));
    ImageIcon setupCheckerRed = new ImageIcon(getClass().getResource("/checkers/images/PieceCheckerRed.png"));
    ImageIcon hSetupCheckerRed = new ImageIcon(getClass().getResource("/checkers/images/hPieceCheckerRed.png"));
    ImageIcon setupKingRed = new ImageIcon(getClass().getResource("/checkers/images/PieceKingRed.png"));
    ImageIcon hSetupKingRed = new ImageIcon(getClass().getResource("/checkers/images/hPieceKingRed.png"));
    ImageIcon setupMine = new ImageIcon(getClass().getResource("/checkers/images/Mine.png"));
    ImageIcon hSetupMine = new ImageIcon(getClass().getResource("/checkers/images/hMine.png"));

    /** Creates new form GameScreen */
    public GameScreen(Game savedGame)
    {
        //In Build 3:
        //get saved game info and load it
        
        initComponents();
        initBoard();
        game = savedGame;
        referee = new Referee(square, game);
    }

    public GameScreen(int size, String player1, String player2)
    {
        width = size;
        boardSize = width * width;
        boardSetup = true;

        if (size == 8)
            visitorCheckers = homeCheckers = 1; //change back to 9
        else if (size == 10)
            visitorCheckers = homeCheckers = 1; //change back to 14

        initComponents();
        initBoard();

        setVisible(true);
        coinToss(player1, player2);
        
        initBoardSetup();

        game = new Game(square, width, visitorPlayer, homePlayer);
        referee = new Referee(square, game);
    }

    //randomly place all pieces
    public void randomPlace ()
    {
        random = new Random();
        //This is the randomPlace case for the 8x8 board
        if(width == 8)
        {
            game.setVisitorTurn(true);
            //populate an array with all of the possible squares
            //that a piece can be placed.
            Square[] randomList = new Square[24];
            for(int i = 0; i < 12; i++)
            {
                randomList[i] = square[i];
            }
               for(int j = 0; j < 12; j++)
            {
               randomList[j+12] = square[j+32];
            }

            //randomly place all visitor checkers.
            while(visitorCheckers != 0)
            {
                int tmp;
                tmp = random.nextInt() % 24;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all visitor kings.
            while(visitorKings != 0)
            {
                int tmp;
                tmp = random.nextInt() % 24;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setPiece(new King(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all visitor blocked squares.
            while(visitorBlocked != 0)
            {
                int tmp;
                tmp = random.nextInt() % 24;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setBlocked(true);
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all visitor safe zones.
            while(visitorSafe != 0)
            {
                int tmp;
                tmp = random.nextInt() % 24;

                if(randomList[tmp].getBlocked() != true)
                {
                    randomList[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all visitor mines.
            while(visitorMines != 0)
            {
                int tmp;
                tmp = random.nextInt() % 24;

                if(randomList[tmp].getBlocked() != true && randomList[tmp].getSafe() != true)
                {
                    randomList[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //populate the array with the available squares for the Home player
            for(int i = 0; i < 12; i++)
            {
                randomList[i] = square[i];
            }
               for(int j = 0; j < 12; j++)
            {
               randomList[j+12] = square[52+j];
            }

            game.setVisitorTurn(false);

            //randomly place all Home checkers.
            while(visitorCheckers != 0)
            {
                int tmp;
                tmp = random.nextInt() % 24;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all Home kings.
            while(visitorKings != 0)
            {
                int tmp;
                tmp = random.nextInt() % 24;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setPiece(new King(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all Home blocked squares.
            while(visitorBlocked != 0)
            {
                int tmp;
                tmp = random.nextInt() % 24;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setBlocked(true);
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all Home safe zones.
            while(visitorSafe != 0)
            {
                int tmp;
                tmp = random.nextInt() % 24;

                if(randomList[tmp].getBlocked() != true)
                {
                    randomList[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all Home mines.
            while(visitorMines != 0)
            {
                int tmp;
                tmp = random.nextInt() % 24;

                if(randomList[tmp].getBlocked() != true && randomList[tmp].getSafe() != true)
                {
                    randomList[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }
        }

        //This is the randomPlace case for the 10x10
        if(width == 10)
        {
            game.setVisitorTurn(true);
            //populate an array with all of the possible squares
            //that a piece can be placed.
            Square[] randomList = new Square[30];
            for(int i = 0; i < 15; i++)
            {
                randomList[i] = square[i];
            }
               for(int j = 0; j < 15; j++)
            {
               randomList[j+15] = square[50+j];
            }

            //randomly place all visitor checkers.
            while(visitorCheckers != 0)
            {
                int tmp;
                tmp = random.nextInt() % 30;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all visitor kings.
            while(visitorKings != 0)
            {
                int tmp;
                tmp = random.nextInt() % 30;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setPiece(new King(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all visitor blocked squares.
            while(visitorBlocked != 0)
            {
                int tmp;
                tmp = random.nextInt() % 30;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setBlocked(true);
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all visitor safe zones.
            while(visitorSafe != 0)
            {
                int tmp;
                tmp = random.nextInt() % 30;

                if(randomList[tmp].getBlocked() != true)
                {
                    randomList[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all visitor mines.
            while(visitorMines != 0)
            {
                int tmp;
                tmp = random.nextInt() % 30;

                if(randomList[tmp].getBlocked() != true && randomList[tmp].getSafe() != true)
                {
                    randomList[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //populate the array with the available squares for the Home player
            for(int i = 0; i < 15; i++)
            {
                randomList[i] = square[i+35];
            }
               for(int j = 0; j < 15; j++)
            {
               randomList[j+15] = square[85+j];
            }

            game.setVisitorTurn(false);

            //randomly place all Home checkers.
            while(visitorCheckers != 0)
            {
                int tmp;
                tmp = random.nextInt() % 30;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all Home kings.
            while(visitorKings != 0)
            {
                int tmp;
                tmp = random.nextInt() % 30;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setPiece(new King(game.getVisitorTurn()));
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all Home blocked squares.
            while(visitorBlocked != 0)
            {
                int tmp;
                tmp = random.nextInt() % 30;

                if(randomList[tmp].getPiece() == null)
                {
                    randomList[tmp].setBlocked(true);
                    updateIcon(randomList[tmp]);
                }
            }

            //randomly place all Home safe zones.
            while(visitorSafe != 0)
            {
                int tmp;
                tmp = random.nextInt() % 30;

                if(square[tmp].getBlocked() != true)
                {
                    square[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(square[tmp]);
                }
            }

            //randomly place all Home mines.
            while(visitorMines != 0)
            {
                int tmp;
                tmp = random.nextInt() % 30;

                if(square[tmp].getBlocked() != true && square[tmp].getSafe() != true)
                {
                    square[tmp].setPiece(new Checker(game.getVisitorTurn()));
                    updateIcon(square[tmp]);
                }
            }
        }
    }

    //randomly determine the home and visitor player
    private void coinToss(String player1, String player2)
    {
        CoinToss toss = new CoinToss(null, true, player1, player2);
        toss.setLocationRelativeTo(this);
        toss.setVisible(true);

        //use the information from the coin toss to determine the home and
        //visitor player
        if (toss.player1Home())
        {
            homePlayer = player1;
            visitorPlayer = player2;
        }
        else
        {
            homePlayer = player2;
            visitorPlayer = player1;
        }
        
        //give the players the option to randomize
       /* Object[] options = {"Yes", "No",};
        int selection = -1;
        while (selection == -1)
        { // force the user to make a selection
            selection = JOptionPane.showOptionDialog(jFrame1,
            "Would you like to place your own pieces?", "Player 1 Login",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                options[1]);

        if (selection == 1)  // Sign In selected
        {
            randomPlace();
        }
        }*/
    }
    
    //************************ initialize the board ***********************//
    // The board is initialized as an array of Square objects (a Square is //
    // an extension of the JLabel object).  The Square objects always have //
    // a black background and are placed in every other spot on the game   //
    // board.                                                              //
    // Each Square is given a 3-Dimensional position in the format         //
    // (board, row, column), with board referring to the board number      //
    // (1 or 2), row referring to the row number (1 through height of      //
    // board) and column referring to the column number (1 through the     //
    // width of the board).                                                //
    /////////////////////////////////////////////////////////////////////////
    private void initBoard()
    {
        int board = 1;
        int row = 1;
        int col = 2;
        boolean halfFlag = false;
        JLayeredPane lBoardPane = new JLayeredPane();
        JLabel lBackgroundLabel = new JLabel();
        JLayeredPane rBoardPane = new JLayeredPane();
        JLabel rBackgroundLabel = new JLabel();

        //initialize the array of Squares
        square = new Square[boardSize];

        //set the size and location of the background labels and add them to
        //the layered pane.
        lBackgroundLabel.setBounds(0, 0, PIXELS * width, PIXELS * width);
        lBoardPane.add(lBackgroundLabel, JLayeredPane.DEFAULT_LAYER);
        getContentPane().add(lBoardPane);
        rBackgroundLabel.setBounds(0, 0, PIXELS * width, PIXELS * width);
        rBoardPane.add(rBackgroundLabel, JLayeredPane.DEFAULT_LAYER);
        getContentPane().add(rBoardPane);

        if (width == 10)
        {
            //define the placement of the 10x10 board
            lBoardPane.setBounds(12, 10, PIXELS * width, PIXELS * width);
            rBoardPane.setBounds(470, 180, PIXELS * width, PIXELS * width);

            //add the background picture to the label
            lBackgroundLabel.setIcon(board10X10);
            rBackgroundLabel.setIcon(board10X10);
        }
        else if (width == 8)
        {
            //define the placement of the 8x8 board
            //the PIXELS offset is added in order to center the smaller board
            lBoardPane.setBounds(12 + PIXELS, 10 + PIXELS, PIXELS * width, PIXELS * width);
            rBoardPane.setBounds(470 + PIXELS, 180 + PIXELS, PIXELS * width, PIXELS * width);

            //add the background picture to the label
            lBackgroundLabel.setIcon(board8X8);
            rBackgroundLabel.setIcon(board8X8);
        }

        //the vert and horiz values are manipulated in the 'for' loop to place
        //the black squares between the red squares on the board
        int vert = 0;
        int horiz = PIXELS;

        for (int i = 0; i < boardSize; i++)
        {
            //once half the squares have been placed, start adding squares to
            //the second board
            if (i == boardSize/2)
            {
                board = 2;
                row = 1;
                col = 2;
                vert = 0;
                horiz = PIXELS;
                halfFlag = true;
            }

            //initialize a 3D Position, give the position to the square, give
            //the square its location on the board and give it a mouse listener
            this.square[i] = new Square(new Position(board, row, col), i);
            this.square[i].setIcon(squareBlack);
            this.square[i].setBounds(horiz, vert, PIXELS, PIXELS);
            this.square[i].addMouseListener(new java.awt.event.MouseAdapter()
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt)
                {
                    Square mySquare =  (Square) evt.getSource();
                    squareClicked(mySquare);
                }
            });

            //if half the squares have been placed, add the square to board 2,
            //otherwise add the square to board 1
            if (halfFlag)
                rBoardPane.add(this.square[i], javax.swing.JLayeredPane.DEFAULT_LAYER);
            else
                lBoardPane.add(this.square[i], javax.swing.JLayeredPane.DEFAULT_LAYER);

            //There are two cases for reaching the end of a row:
            //case 1 - the square has been added to the last spot on the row
            //case 2 - the square has been added to the second to the last
            //spot on the row (a red square occupies the last spot)
            if (col % width == 0)  //case 1
            {
                row++;
                col = 1;
                vert = vert + PIXELS;
                horiz = 0;
            }
            else if (col % width == width - 1)  //case 2
            {
                row++;
                col = 2;
                vert = vert + PIXELS;
                horiz = PIXELS;
            }
            //if the end of the row has not been reached, increment the column
            //and the horizontal location
            else
            {
                col = col + 2;
                horiz = horiz + (PIXELS * 2);
            }
        }
    }

    //recieve and manage "clicks" from the user on the GUI
    private void squareClicked(Square square)
    {
        //check if the game is in the board setup phase
        if (boardSetup)
            setupClicked(square);
        else
        {
            if (firstSelectMove != null)
            {
                secondSelectMove = square;

                removeHighlights(availableMoves);

                //will return 0 for error and 1 for valid move
                if (referee.executeMove(firstSelectMove, secondSelectMove, availableMoves) == 1)
                {
                    updateIcon(firstSelectMove);
                    updateIcon(secondSelectMove);

                    //toggle highlight of player whose turn it is
                    if (visitorIconLabel.getBorder() == null)
                    {
                        visitorIconLabel.setBorder(BorderFactory.createEtchedBorder(
                            javax.swing.border.EtchedBorder.RAISED,
                            new java.awt.Color(255, 255, 0), null));
                        visitorNameLabel.setFont(oldEnglish_16b);
                        homeIconLabel.setBorder(null);
                        homeNameLabel.setFont(oldEnglish_16);
                    }
                    else
                    {
                        visitorIconLabel.setBorder(null);
                        visitorNameLabel.setFont(oldEnglish_16);
                        homeIconLabel.setBorder(BorderFactory.createEtchedBorder(
                            javax.swing.border.EtchedBorder.RAISED,
                            new java.awt.Color(255, 255, 0), null));
                        homeNameLabel.setFont(oldEnglish_16b);
                    }
                }

                firstSelectMove = null;
            }
            else
            {
                if (square.isEnabled() && referee.validateTurn(square.getPiece()))
                {
                    firstSelectMove = square;

                    availableMoves = referee.showMoves(square);

                    highlightMoves(availableMoves);
                }
            }
        }
    }

    //update the icon on a square that has been manipulated by the referee
    private void updateIcon(Square square)
    {
        if (square.getSafe()) //is safe zone
        {
            if (square.getPiece() == null) //empty safe
                square.setIcon(squareSafe);
            else if (square.getPiece() instanceof Checker)
            {
                if (square.getPiece().getColor() == Main.BLACK)  //safe black checker
                    square.setIcon(safeCheckerBlack);
                else  //safe red checker
                    square.setIcon(safeCheckerRed);
            }
            else if (square.getPiece() instanceof King)
            {
                if (square.getPiece().getColor() == Main.BLACK) //safe black king
                    square.setIcon(safeKingBlack);
                else //safe red king
                    square.setIcon(safeKingRed);
            }
        }
        else //not safe zone
        {
            if (square.getPiece() == null) //empty square
                square.setIcon(squareBlack);
            else if (square.getPiece() instanceof Checker)
            {
                if (square.getPiece().getColor() == Main.BLACK)  //black checker
                    square.setIcon(checkerBlack);
                else  //red checker
                    square.setIcon(checkerRed);
            }
            else if (square.getPiece() instanceof King)
            {
                if (square.getPiece().getColor() == Main.BLACK) //black king
                    square.setIcon(kingBlack);
                else //red king
                    square.setIcon(kingRed);
            }
        }
    }

    //highlight a list of moves that are provided by the referee
    private void highlightMoves(ArrayList<Square> moves)
    {
        if (moves != null)
            for (int i = 0; i < moves.size(); i++)
                setHighlight(moves.get(i));
    }

    //remove the highlights from a list of moves provided by the referee
    private void removeHighlights(ArrayList<Square> moves)
    {
        if (moves != null)
            for (int i = 0; i < moves.size(); i++)
                removeHighlight(moves.get(i));
    }

    //highlight a given square
    private void setHighlight(Square square)
    {
        if(square.getIcon() == squareBlack)
            square.setIcon(hSquareBlack);
        else if (square.getIcon() == checkerBlack)
            square.setIcon(hCheckerBlack);
        else if (square.getIcon() == kingBlack)
            square.setIcon(hKingBlack);
        else if (square.getIcon() == checkerRed)
            square.setIcon(hCheckerRed);
        else if (square.getIcon() == kingRed)
            square.setIcon(hKingRed);
        else if (square.getIcon() == squareSafe)
            square.setIcon(hSquareSafe);
        else if (square.getIcon() == safeCheckerBlack)
            square.setIcon(hSafeCheckerBlack);
        else if (square.getIcon() == safeKingBlack)
            square.setIcon(hSafeKingBlack);
        else if (square.getIcon() == safeCheckerRed)
            square.setIcon(hSafeCheckerRed);
        else if (square.getIcon() == safeKingRed)
            square.setIcon(hSafeKingRed);
        else if (square.getIcon() == squareBlocked)
            square.setIcon(hSquareBlocked);
        else if (square.getIcon() == setupCheckerBlack)
            square.setIcon(hSetupCheckerBlack);
        else if (square.getIcon() == setupKingBlack)
            square.setIcon(hSetupKingBlack);
        else if (square.getIcon() == setupCheckerRed)
            square.setIcon(hSetupCheckerRed);
        else if (square.getIcon() == setupKingRed)
            square.setIcon(hSetupKingRed);
        else if (square.getIcon() == setupMine)
            square.setIcon(hSetupMine);

    }

    //remove the highlight from a given square
    private void removeHighlight(Square square)
    {
        if(square.getIcon() == hSquareBlack)
            square.setIcon(squareBlack);
        else if (square.getIcon() == hCheckerBlack)
            square.setIcon(checkerBlack);
        else if (square.getIcon() == hKingBlack)
            square.setIcon(kingBlack);
        else if (square.getIcon() == hCheckerRed)
            square.setIcon(checkerRed);
        else if (square.getIcon() == hKingRed)
            square.setIcon(kingRed);
        else if (square.getIcon() == hSquareSafe)
            square.setIcon(squareSafe);
        else if (square.getIcon() == hSafeCheckerBlack)
            square.setIcon(safeCheckerBlack);
        else if (square.getIcon() == hSafeKingBlack)
            square.setIcon(safeKingBlack);
        else if (square.getIcon() == hSafeCheckerRed)
            square.setIcon(safeCheckerRed);
        else if (square.getIcon() == hSafeKingRed)
            square.setIcon(safeKingRed);
        else if (square.getIcon() == hSquareBlocked)
            square.setIcon(squareBlocked);
        else if (square.getIcon() == hSetupCheckerBlack)
            square.setIcon(setupCheckerBlack);
        else if (square.getIcon() == hSetupKingBlack)
            square.setIcon(setupKingBlack);
        else if (square.getIcon() == hSetupCheckerRed)
            square.setIcon(setupCheckerRed);
        else if (square.getIcon() == hSetupKingRed)
            square.setIcon(setupKingRed);
        else if (square.getIcon() == hSetupMine)
            square.setIcon(setupMine);
    }

    //*********************** initialize board setup **********************//
    // When the board is in the board setup phase (as determined by the    //
    // boardSetup flag), the user will be provided with additional "setup" //
    // icons (implemented as Squares) that will aid in the board setup     //
    // process.                                                            //
    // The "setup" Squares will be given a Position object in the format   //
    // (board, row, column), with the board equal to 0, row referring      //
    // to the row of "setup" icons, and column referring to the column of  //
    // "setup" icons.                                                      //
    /////////////////////////////////////////////////////////////////////////
    private void initBoardSetup()
    {
        lMessagePane = new JLayeredPane();
        lMessageLabel = new JLabel();
        rMessagePane = new JLayeredPane();
        homeIconLabel = new JLabel(setupCheckerRed, JLabel.CENTER);
        homeNameLabel = new JLabel(homePlayer);
        visitorIconLabel = new JLabel(setupCheckerBlack, JLabel.CENTER);
        visitorNameLabel = new JLabel(visitorPlayer);

        lMessagePane.setBorder(BorderFactory.createTitledBorder
              (BorderFactory.createEtchedBorder
              (new java.awt.Color(255, 255, 0), null),
              homePlayer + ", place a piece",
              javax.swing.border.TitledBorder.RIGHT,
              javax.swing.border.TitledBorder.TOP, oldEnglish_16));
        getContentPane().add(lMessagePane);
        lMessagePane.setBounds(40, 430, 340, 145);

        //since the visitor places the first piece, hide home's options
        lMessagePane.setVisible(false);

        rMessagePane.setBorder(BorderFactory.createTitledBorder
              (BorderFactory.createEtchedBorder
              (new java.awt.Color(255, 255, 0), null),
              visitorPlayer + ", place a piece",
              javax.swing.border.TitledBorder.LEFT,
              javax.swing.border.TitledBorder.TOP, oldEnglish_16));
        getContentPane().add(rMessagePane);
        rMessagePane.setBounds(500, 20, 340, 145);

        lMessageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lMessageLabel.setFont(oldEnglish_16);
        lMessageLabel.setBounds(5, 5, 335, 140);
        lMessagePane.add(lMessageLabel, JLayeredPane.DEFAULT_LAYER);
        lMessageLabel.setVisible(false);

        visitorIconLabel.setBounds(20, 20, 40, 40);
        rMessagePane.add(visitorIconLabel, JLayeredPane.DEFAULT_LAYER);
        visitorIconLabel.setVisible(false);
        visitorNameLabel.setBounds(65, 20, 275, 40);
        rMessagePane.add(visitorNameLabel, JLayeredPane.DEFAULT_LAYER);
        visitorNameLabel.setVisible(false);

        homeIconLabel.setBounds(20, 75, 40, 40);
        rMessagePane.add(homeIconLabel, JLayeredPane.DEFAULT_LAYER);
        homeIconLabel.setVisible(false);
        homeNameLabel.setBounds(65, 75, 275, 40);
        rMessagePane.add(homeNameLabel, JLayeredPane.DEFAULT_LAYER);
        homeNameLabel.setVisible(false);

        placeVisitorChecker = new Square(new Position(0, 1, 1), NIL);
        placeVisitorChecker.setIcon(setupCheckerBlack);
        rMessagePane.add(placeVisitorChecker, JLayeredPane.DEFAULT_LAYER);
        placeVisitorChecker.setBounds(80, 25, 40, 40);
        placeVisitorChecker.setToolTipText("Checker");
        placeVisitorChecker.addMouseListener(mouseAdapter);
        
        visitorCheckerLabel = new JLabel();
        visitorCheckerLabel.setText("x " + visitorCheckers);
        rMessagePane.add(visitorCheckerLabel, JLayeredPane.DEFAULT_LAYER);
        visitorCheckerLabel.setBounds(125, 35, 30, 20);

        placeVisitorKing = new Square(new Position(0, 1, 2), NIL);
        placeVisitorKing.setIcon(setupKingBlack);
        rMessagePane.add(placeVisitorKing, JLayeredPane.DEFAULT_LAYER);
        placeVisitorKing.setBounds(200, 25, 40, 40);
        placeVisitorKing.setToolTipText("King");
        placeVisitorKing.addMouseListener(mouseAdapter);

        visitorKingLabel = new JLabel();
        visitorKingLabel.setText("x " + visitorKings);
        rMessagePane.add(visitorKingLabel, JLayeredPane.DEFAULT_LAYER);
        visitorKingLabel.setBounds(245, 35, 30, 20);

        placeVisitorBlocked = new Square(new Position(0, 2, 1), NIL);
        placeVisitorBlocked.setIcon(squareBlocked);
        rMessagePane.add(placeVisitorBlocked, JLayeredPane.DEFAULT_LAYER);
        placeVisitorBlocked.setBounds(20, 85, 40, 40);
        placeVisitorBlocked.setToolTipText("Blocked Square");
        placeVisitorBlocked.addMouseListener(mouseAdapter);

        visitorBlockedLabel = new JLabel();
        visitorBlockedLabel.setText("x " + visitorBlocked);
        rMessagePane.add(visitorBlockedLabel, JLayeredPane.DEFAULT_LAYER);
        visitorBlockedLabel.setBounds(65, 95, 30, 20);

        placeVisitorSafe = new Square(new Position(0, 2, 2), NIL);
        placeVisitorSafe.setIcon(squareSafe);
        rMessagePane.add(placeVisitorSafe, JLayeredPane.DEFAULT_LAYER);
        placeVisitorSafe.setBounds(140, 85, 40, 40);
        placeVisitorSafe.setToolTipText("Safe Zone");
        placeVisitorSafe.addMouseListener(mouseAdapter);

        visitorSafeLabel = new JLabel();
        visitorSafeLabel.setText("x " + visitorSafe);
        rMessagePane.add(visitorSafeLabel, JLayeredPane.DEFAULT_LAYER);
        visitorSafeLabel.setBounds(185, 95, 30, 20);

        placeVisitorMine = new Square(new Position(0, 2, 3), NIL);
        placeVisitorMine.setIcon(setupMine);
        rMessagePane.add(placeVisitorMine, JLayeredPane.DEFAULT_LAYER);
        placeVisitorMine.setBounds(260, 85, 40, 40);
        placeVisitorMine.setToolTipText("Smart Mine");
        placeVisitorMine.addMouseListener(mouseAdapter);

        visitorMineLabel = new JLabel();
        visitorMineLabel.setText("x " + visitorMines);
        rMessagePane.add(visitorMineLabel, JLayeredPane.DEFAULT_LAYER);
        visitorMineLabel.setBounds(305, 95, 30, 20);

        placeHomeChecker = new Square(new Position(0, 3, 1), NIL);
        placeHomeChecker.setIcon(setupCheckerRed);
        lMessagePane.add(placeHomeChecker, JLayeredPane.DEFAULT_LAYER);
        placeHomeChecker.setBounds(80, 25, 40, 40);
        placeHomeChecker.setToolTipText("Checker");
        placeHomeChecker.addMouseListener(mouseAdapter);

        homeCheckerLabel = new JLabel();
        homeCheckerLabel.setText("x " + homeCheckers);
        lMessagePane.add(homeCheckerLabel, JLayeredPane.DEFAULT_LAYER);
        homeCheckerLabel.setBounds(125, 35, 30, 20);

        placeHomeKing = new Square(new Position(0, 3, 2), NIL);
        placeHomeKing.setIcon(setupKingRed);
        lMessagePane.add(placeHomeKing, JLayeredPane.DEFAULT_LAYER);
        placeHomeKing.setBounds(200, 25, 40, 40);
        placeHomeKing.setToolTipText("King");
        placeHomeKing.addMouseListener(mouseAdapter);

        homeKingLabel = new JLabel();
        homeKingLabel.setText("x " + homeKings);
        lMessagePane.add(homeKingLabel, JLayeredPane.DEFAULT_LAYER);
        homeKingLabel.setBounds(245, 35, 30, 20);

        placeHomeBlocked = new Square(new Position(0, 4, 1), NIL);
        placeHomeBlocked.setIcon(squareBlocked);
        lMessagePane.add(placeHomeBlocked, JLayeredPane.DEFAULT_LAYER);
        placeHomeBlocked.setBounds(20, 85, 40, 40);
        placeHomeBlocked.setToolTipText("Blocked Square");
        placeHomeBlocked.addMouseListener(mouseAdapter);

        homeBlockedLabel = new JLabel();
        homeBlockedLabel.setText("x " + homeBlocked);
        lMessagePane.add(homeBlockedLabel, JLayeredPane.DEFAULT_LAYER);
        homeBlockedLabel.setBounds(65, 95, 30, 20);

        placeHomeSafe = new Square(new Position(0, 4, 2), NIL);
        placeHomeSafe.setIcon(squareSafe);
        lMessagePane.add(placeHomeSafe, JLayeredPane.DEFAULT_LAYER);
        placeHomeSafe.setBounds(140, 85, 40, 40);
        placeHomeSafe.setToolTipText("Safe Zone");
        placeHomeSafe.addMouseListener(mouseAdapter);

        homeSafeLabel = new JLabel();
        homeSafeLabel.setText("x " + homeSafe);
        lMessagePane.add(homeSafeLabel, JLayeredPane.DEFAULT_LAYER);
        homeSafeLabel.setBounds(185, 95, 30, 20);

        placeHomeMine = new Square(new Position(0, 4, 3), NIL);
        placeHomeMine.setIcon(setupMine);
        lMessagePane.add(placeHomeMine, JLayeredPane.DEFAULT_LAYER);
        placeHomeMine.setBounds(260, 85, 40, 40);
        placeHomeMine.setToolTipText("Smart Mine");
        placeHomeMine.addMouseListener(mouseAdapter);

        homeMineLabel = new JLabel();
        homeMineLabel.setText("x " + homeMines);
        lMessagePane.add(homeMineLabel, JLayeredPane.DEFAULT_LAYER);
        homeMineLabel.setBounds(305, 95, 30, 20);
    }

    //remove the board setup images (Squares)
    private void removeBoardSetup()
    {
        //remove the setup icons
        placeVisitorChecker.setVisible(false);
        placeVisitorKing.setVisible(false);
        placeVisitorSafe.setVisible(false);
        placeVisitorBlocked.setVisible(false);
        placeVisitorMine.setVisible(false);
        placeHomeChecker.setVisible(false);
        placeHomeKing.setVisible(false);
        placeHomeSafe.setVisible(false);
        placeHomeBlocked.setVisible(false);
        placeHomeMine.setVisible(false);
        visitorCheckerLabel.setVisible(false);
        visitorKingLabel.setVisible(false);
        visitorBlockedLabel.setVisible(false);
        visitorSafeLabel.setVisible(false);
        visitorMineLabel.setVisible(false);
        homeCheckerLabel.setVisible(false);
        homeKingLabel.setVisible(false);
        homeBlockedLabel.setVisible(false);
        homeSafeLabel.setVisible(false);
        homeMineLabel.setVisible(false);

        //enable the labels that will be used during game play
        lMessagePane.setBorder(null);
        lMessagePane.setVisible(true);
        lMessageLabel.setVisible(true);
        rMessagePane.setBorder(null);
        rMessagePane.setVisible(true);

        visitorIconLabel.setVisible(true);
        visitorIconLabel.setBorder(BorderFactory.createEtchedBorder(
              javax.swing.border.EtchedBorder.RAISED,
              new java.awt.Color(255, 255, 0), null));
        visitorNameLabel.setVisible(true);
        visitorNameLabel.setFont(oldEnglish_16b);
        homeIconLabel.setVisible(true);
        homeNameLabel.setVisible(true);
        homeNameLabel.setFont(oldEnglish_16);

        //inform the user that the board setup phase is over
        javax.swing.UIManager.put("OptionPane.messageFont", oldEnglish_16);
        JOptionPane.showMessageDialog(this, "Board setup is complete!\n" +
              visitorPlayer + " has the first move.", "Board Setup",
              JOptionPane.INFORMATION_MESSAGE);
    }

    //handle mouse click on Squares
    MouseAdapter mouseAdapter = new MouseAdapter()
    {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt)
        {
            Square mySquare =  (Square) evt.getSource();
            setupClicked(mySquare);
        }
    };

    private void updateSetupIcons(Position setup, Square destination)
    {
        //decrease the piece count and refresh the label value
        if (setup.getRow() == 1 && setup.getColumn() == 1) //visitor checker
        {
            visitorCheckers--;
            visitorCheckerLabel.setText("x " + visitorCheckers);

            if (visitorCheckers == 0)
                placeVisitorChecker.setEnabled(false);

            if (destination.getSafe())
                destination.setIcon(safeCheckerBlack);
            else
                destination.setIcon(checkerBlack);
        }
        else if (setup.getRow() == 1 && setup.getColumn() == 2) //visitor king
        {
            visitorKings--;
            visitorKingLabel.setText("x " + visitorKings);

            if (visitorKings == 0)
                placeVisitorKing.setEnabled(false);

            if (destination.getSafe())
                destination.setIcon(safeKingBlack);
            else
                destination.setIcon(kingBlack);
        }
        else if (setup.getRow() == 3 && setup.getColumn() == 1) //home checker
        {
            homeCheckers--;
            homeCheckerLabel.setText("x " + homeCheckers);

            if (homeCheckers == 0)
                placeHomeChecker.setEnabled(false);

            if (destination.getSafe())
                destination.setIcon(safeCheckerRed);
            else
                destination.setIcon(checkerRed);
        }
        else if (setup.getRow() == 3 && setup.getColumn() == 2) //home king
        {
            homeKings--;
            homeKingLabel.setText("x " + homeKings);

            if (homeKings == 0)
                placeHomeKing.setEnabled(false);

            if (destination.getSafe())
                destination.setIcon(safeKingRed);
            else
                destination.setIcon(kingRed);
        }
        else if (setup.getRow() == 2 && setup.getColumn() == 1) //visitor blocked
        {
            visitorBlocked--;
            visitorBlockedLabel.setText("x " + visitorBlocked);

            if (visitorBlocked == 0)
                placeVisitorBlocked.setEnabled(false);

            destination.setIcon(squareBlocked);
        }
        else if (setup.getRow() == 4 && setup.getColumn() == 1) //home blocked
        {
            homeBlocked--;
            homeBlockedLabel.setText("x " + homeBlocked);

            if (homeBlocked == 0)
                placeHomeBlocked.setEnabled(false);

            destination.setIcon(squareBlocked);
        }
        else if (setup.getRow() == 2 && setup.getColumn() == 2) //visitor safe
        {
            visitorSafe--;
            visitorSafeLabel.setText("x " + visitorSafe);

            if (visitorSafe == 0)
                placeVisitorSafe.setEnabled(false);

            if (destination.getPiece() == null)
                destination.setIcon(squareSafe);
            else
            {
                if (destination.getPiece() instanceof Checker)
                    destination.setIcon(safeCheckerBlack);
                else if (destination.getPiece() instanceof King)
                    destination.setIcon(safeKingBlack);
            }
        }
        else if (setup.getRow() == 4 && setup.getColumn() == 2) //home safe
        {
            homeSafe--;
            homeSafeLabel.setText("x " + homeSafe);

            if (homeSafe == 0)
                placeHomeSafe.setEnabled(false);

            if (destination.getPiece() == null)
                destination.setIcon(squareSafe);
            else
            {
                if (destination.getPiece() instanceof Checker)
                    destination.setIcon(safeCheckerRed);
                else if (destination.getPiece() instanceof King)
                    destination.setIcon(safeKingRed);
            }
        }
        else if (setup.getRow() == 2 && setup.getColumn() == 3) //visitor mine
        {
            visitorMines--;
            visitorMineLabel.setText("x " + visitorMines);

            if (visitorMines == 0)
                placeVisitorMine.setEnabled(false);
        }
        else if (setup.getRow() == 4 && setup.getColumn() == 3) //home mine
        {
            homeMines--;
            homeMineLabel.setText("x " + homeMines);

            if (homeMines == 0)
                placeHomeMine.setEnabled(false);
        }
    }

    //receive and manage "clicks" from the user during setup phase
    private void setupClicked(Square square)
    {
        //check if a first selection has been made
        if (firstSelectSetup != null)
        {
            secondSelectSetup = square;

            //remove the highlight after every selection, regardless of whether
            //or not it is valid
            removeHighlight(firstSelectSetup);
            removeHighlight(secondSelectSetup);

            if (square.isEnabled() && referee.executePlacement(firstSelectSetup, secondSelectSetup))
            {
                updateSetupIcons(firstSelectSetup.getPosition(), secondSelectSetup);

                //alternate visibility of the home and visitor setup
                if (rMessagePane.isVisible())
                {
                    rMessagePane.setVisible(false);
                    lMessagePane.setVisible(true);
                }
                else
                {
                    rMessagePane.setVisible(true);
                    lMessagePane.setVisible(false);
                }

                //once all pieces have been placed, end the board setup phase
                if(homeCheckers == 0 && homeKings == 0 && homeBlocked == 0 &&
                        homeSafe == 0 && homeMines == 0)
                {
                    boardSetup = false;
                    removeBoardSetup();
                }
            }

            firstSelectSetup = null;
        }
        else
        {
            if (square.isEnabled() && referee.verifySelection(square))
            {
                firstSelectSetup = square;

                setHighlight(square);
            }
        }
    }

    //load the Old English font with a given size and type
    private Font loadFont(int type, float size)
    {
        Font font = null;
        try
        {
            InputStream input = this.getClass().getResourceAsStream("/OLDENGL.TTF");
            font = Font.createFont(Font.PLAIN, input).deriveFont(type, size);
        }
        catch (IOException ioe)
        {
            System.err.println(ioe);
            System.exit(1);
        }
        catch (FontFormatException ffe)
        {
            System.err.println(ffe);
            System.exit(1);
        }
        return font;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardMenuBar = new javax.swing.JMenuBar();
        gameMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("3D Checkers");
        setMinimumSize(new java.awt.Dimension(888, 640));
        setName("boardFrame"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(null);

        gameMenu.setText("Game");
        gameMenu.setFont(oldEnglish_16);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setFont(oldEnglish_14);
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        gameMenu.add(exitMenuItem);

        boardMenuBar.add(gameMenu);

        helpMenu.setText("Help");
        helpMenu.setFont(oldEnglish_16);
        boardMenuBar.add(helpMenu);

        setJMenuBar(boardMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //exit the program
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar boardMenuBar;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu gameMenu;
    private javax.swing.JMenu helpMenu;
    // End of variables declaration//GEN-END:variables

    //additional variable declarations
    JLabel lMessageLabel;
    JLayeredPane lMessagePane;
    JLayeredPane rMessagePane;
    JLabel homeIconLabel;
    JLabel homeNameLabel;
    JLabel visitorIconLabel;
    JLabel visitorNameLabel;
    Square placeVisitorChecker;
    Square placeVisitorKing;
    Square placeVisitorBlocked;
    Square placeVisitorSafe;
    Square placeVisitorMine;
    Square placeHomeChecker;
    Square placeHomeKing;
    Square placeHomeBlocked;
    Square placeHomeSafe;
    Square placeHomeMine;
    JLabel visitorCheckerLabel;
    JLabel visitorKingLabel;
    JLabel visitorBlockedLabel;
    JLabel visitorSafeLabel;
    JLabel visitorMineLabel;
    JLabel homeCheckerLabel;
    JLabel homeKingLabel;
    JLabel homeBlockedLabel;
    JLabel homeSafeLabel;
    JLabel homeMineLabel;
}