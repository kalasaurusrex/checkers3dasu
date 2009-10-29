/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GameScreen.java
 *
 * Created on Oct 15, 2009, 9:29:36 AM
 */

package checkers;

import java.util.*;
import javax.swing.*;


public class GameScreen extends javax.swing.JFrame
{
    private static final int RED = 0;
    private static final int BLACK = 1;
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
    //private boolean first = false;
    //private boolean moveSelected = false;
    Square firstSelectMove;
    Square secondSelectMove;
    Square firstSelectSetup;
    Square secondSelectSetup;
    ArrayList<Square> availableMoves;
    Random random;
    
    //load images
    ImageIcon board8X8 = new ImageIcon(getClass().getResource("/checkers/images/Board_Base_8x8.gif"));
    ImageIcon board10X10 = new ImageIcon(getClass().getResource("/checkers/images/Board_Base_10x10.gif"));
    ImageIcon squareBlack = new ImageIcon(getClass().getResource("/checkers/images/Square_B.jpg"));
    ImageIcon hSquareBlack = new ImageIcon(getClass().getResource("/checkers/images/Highlight_B.jpg"));
    ImageIcon checkerBlack = new ImageIcon(getClass().getResource("/checkers/images/Square_B_w_B.jpg"));
    ImageIcon hCheckerBlack = new ImageIcon(getClass().getResource("/checkers/images/Highlight_B_w_B.jpg"));
    ImageIcon checkerRed = new ImageIcon(getClass().getResource("/checkers/images/Square_B_w_R.jpg"));
    ImageIcon hCheckerRed = new ImageIcon(getClass().getResource("/checkers/images/Highlight_B_w_R.jpg"));
    ImageIcon kingBlack = new ImageIcon(getClass().getResource("/checkers/images/Square_B_w_B_K.jpg"));
    ImageIcon hKingBlack = new ImageIcon(getClass().getResource("/checkers/images/Highlight_B_w_B_K.jpg"));
    ImageIcon kingRed = new ImageIcon(getClass().getResource("/checkers/images/Square_B_w_R_K.jpg"));
    ImageIcon hKingRed = new ImageIcon(getClass().getResource("/checkers/images/Highlight_B_w_R_K.jpg"));
    ImageIcon squareSafe = new ImageIcon(getClass().getResource("/checkers/images/Square_G.jpg"));
    ImageIcon hSquareSafe = new ImageIcon(getClass().getResource("/checkers/images/Highlight_G.jpg"));
    ImageIcon safeCheckerBlack = new ImageIcon(getClass().getResource("/checkers/images/Square_G_w_B.jpg"));
    ImageIcon hSafeCheckerBlack = new ImageIcon(getClass().getResource("/checkers/images/Highlight_G_w_B.jpg"));
    ImageIcon safeCheckerRed = new ImageIcon(getClass().getResource("/checkers/images/Square_G_w_R.jpg"));
    ImageIcon hSafeCheckerRed = new ImageIcon(getClass().getResource("/checkers/images/Highlight_G_w_R.jpg"));
    ImageIcon safeKingBlack = new ImageIcon(getClass().getResource("/checkers/images/Square_G_w_B_K.jpg"));
    ImageIcon hSafeKingBlack = new ImageIcon(getClass().getResource("/checkers/images/Highlight_G_w_B_K.jpg"));
    ImageIcon safeKingRed = new ImageIcon(getClass().getResource("/checkers/images/Square_G_w_R_K.jpg"));
    ImageIcon hSafeKingRed = new ImageIcon(getClass().getResource("/checkers/images/Highlight_G_w_R_K.jpg"));
    ImageIcon squareBlocked = new ImageIcon(getClass().getResource("/checkers/images/Square_Blocked.jpg"));
    ImageIcon hSquareBlocked = new ImageIcon(getClass().getResource("/checkers/images/Highlight_Blocked.jpg"));

    /** Creates new form GameScreen */
//    public GameScreen()
//    {
//
//    }
    
    public GameScreen(Game savedGame)
    {
        //In Build 2:
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
        
        coinToss(player1, player2);

        initBoardSetup();

        game = new Game(square, width, visitorPlayer, homePlayer);
        referee = new Referee(square, game);

    }

    //will be implemented in build 2
    //randomly place all pieces
    public void randomPlace ()
    {
        
    }

    //randomly determine the home and visitor player
    private void coinToss(String player1, String player2)
    {
        int headsTails;
        int homeVisitor;
        random = new Random();

        Object[] HTOptions = { "HEADS", "TAILS" };
        headsTails = JOptionPane.showOptionDialog(null, player1 + ", select heads or tails",
              "Coin Toss", JOptionPane.DEFAULT_OPTION,
              JOptionPane.QUESTION_MESSAGE, null, HTOptions, HTOptions[0]);

        Object[] HVOptions = { "HOME", "VISITOR" };

        if (random.nextInt(2) == headsTails)
        {
            if (headsTails == 0)
            {
                homeVisitor = JOptionPane.showOptionDialog(null,
                      "The coin landed on Heads!\n\n" + player1 +
                      ", do you want to be home or visitor?", "Coin Toss",
                      JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                      null, HVOptions, HVOptions[0]);

                if (homeVisitor == 0)
                {
                    homePlayer = player1;
                    visitorPlayer = player2;
                }
                else
                {
                    homePlayer = player2;
                    visitorPlayer = player1;
                }
            }
            else
            {
                homeVisitor = JOptionPane.showOptionDialog(null,
                      "The coin landed on tails!\n\n" + player1 +
                      ", do you want to be home or visitor?", "Coin Toss",
                      JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                      null, HVOptions, HVOptions[0]);
                
                if (homeVisitor == 0)
                {
                    homePlayer = player1;
                    visitorPlayer = player2;
                }
                else
                {
                    homePlayer = player2;
                    visitorPlayer = player1;
                }
            }
        }
        else
        {
            if (headsTails == 1)
            {
                homeVisitor = JOptionPane.showOptionDialog(null,
                      "The coin landed on Heads!\n\n" + player2 +
                      ", do you want to be home or visitor?", "Coin Toss",
                      JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                      null, HVOptions, HVOptions[0]);
                
                if (homeVisitor == 0)
                {
                    homePlayer = player2;
                    visitorPlayer = player1;
                }
                else
                {
                    homePlayer = player1;
                    visitorPlayer = player2;
                }
            }
            else
            {
                homeVisitor = JOptionPane.showOptionDialog(null,
                      "The coin landed on tails!\n\n" + player2 +
                      ", do you want to be home or visitor?", "Coin Toss",
                      JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                      null, HVOptions, HVOptions[0]);
                
                if (homeVisitor == 0)
                {
                    homePlayer = player2;
                    visitorPlayer = player1;
                }
                else
                {
                    homePlayer = player1;
                    visitorPlayer = player2;
                }
            }
        }
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
        rBackgroundLabel.setEnabled(false);  //implemented in build 2

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
            {
                rBoardPane.add(this.square[i], javax.swing.JLayeredPane.DEFAULT_LAYER);
                this.square[i].setEnabled(false);  //implemented in build 2
            }
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
                if (referee.executeMove(firstSelectMove, secondSelectMove) == 1)
                {
                    //NOTE: this logic will only work in build 1
                    secondSelectMove.setIcon(firstSelectMove.getIcon());
                    firstSelectMove.setIcon(squareBlack);

                    if (rMessageLabel.getText().equals("<html>" + visitorPlayer + ", it's your turn</html>"))
                        rMessageLabel.setText("<html>" + homePlayer + ", it's your turn</html>");
                    else
                        rMessageLabel.setText("<html>" + visitorPlayer + ", it's your turn</html>");
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
        rMessageLabel = new JLabel();

        lMessagePane.setBorder(BorderFactory.createTitledBorder
              (BorderFactory.createEtchedBorder
              (new java.awt.Color(255, 255, 0), null),
              homePlayer + ", place a piece",
              javax.swing.border.TitledBorder.RIGHT,
              javax.swing.border.TitledBorder.TOP,
              new java.awt.Font("Old English Text MT", 0, 16)));
        getContentPane().add(lMessagePane);
        lMessagePane.setBounds(40, 430, 340, 145);

        //visitor places first piece, set visible to false
        lMessagePane.setVisible(false);


        rMessagePane.setBorder(BorderFactory.createTitledBorder
              (BorderFactory.createEtchedBorder
              (new java.awt.Color(255, 255, 0), null),
              visitorPlayer + ", place a piece",
              javax.swing.border.TitledBorder.LEFT,
              javax.swing.border.TitledBorder.TOP,
              new java.awt.Font("Old English Text MT", 0, 16)));
        getContentPane().add(rMessagePane);
        rMessagePane.setBounds(500, 20, 340, 145);

        lMessageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lMessageLabel.setFont(new java.awt.Font("Old English Text MT", 0, 16));
        lMessageLabel.setBounds(5, 5, 335, 140);
        lMessagePane.add(lMessageLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lMessageLabel.setVisible(false);
        rMessageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rMessageLabel.setFont(new java.awt.Font("Old English Text MT", 0, 24));
        rMessageLabel.setBounds(5, 45, 335, 55);
        rMessagePane.add(rMessageLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rMessageLabel.setVisible(false);

        placeVisitorChecker = new Square(new Position(0, 1, 1), NIL);
        placeVisitorChecker.setIcon(checkerBlack);
        rMessagePane.add(placeVisitorChecker, JLayeredPane.DEFAULT_LAYER);
        placeVisitorChecker.setBounds(80, 25, 40, 40);
        placeVisitorChecker.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                Square mySquare =  (Square) evt.getSource();
                setupClicked(mySquare);
            }
        });
        
        visitorCheckerLabel = new JLabel();
        visitorCheckerLabel.setText("x " + visitorCheckers);
        rMessagePane.add(visitorCheckerLabel, JLayeredPane.DEFAULT_LAYER);
        visitorCheckerLabel.setBounds(125, 35, 30, 20);

        placeVisitorKing = new Square(new Position(0, 1, 2), NIL);
        placeVisitorKing.setIcon(kingBlack);
        rMessagePane.add(placeVisitorKing, JLayeredPane.DEFAULT_LAYER);
        placeVisitorKing.setBounds(200, 25, 40, 40);
        placeVisitorKing.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                Square myPiece =  (Square) evt.getSource();
                setupClicked(myPiece);
            }
        });

        visitorKingLabel = new JLabel();
        visitorKingLabel.setText("x " + visitorKings);
        rMessagePane.add(visitorKingLabel, JLayeredPane.DEFAULT_LAYER);
        visitorKingLabel.setBounds(245, 35, 30, 20);

        placeVisitorBlocked = new Square(new Position(0, 2, 1), NIL);
        placeVisitorBlocked.setIcon(squareBlocked);
        rMessagePane.add(placeVisitorBlocked, JLayeredPane.DEFAULT_LAYER);
        placeVisitorBlocked.setBounds(20, 85, 40, 40);

        visitorBlockedLabel = new JLabel();
        visitorBlockedLabel.setText("x " + visitorBlocked);
        rMessagePane.add(visitorBlockedLabel, JLayeredPane.DEFAULT_LAYER);
        visitorBlockedLabel.setBounds(65, 95, 30, 20);

        placeVisitorSafe = new Square(new Position(0, 2, 2), NIL);
        placeVisitorSafe.setIcon(squareSafe);
        rMessagePane.add(placeVisitorSafe, JLayeredPane.DEFAULT_LAYER);
        placeVisitorSafe.setBounds(140, 85, 40, 40);

        visitorSafeLabel = new JLabel();
        visitorSafeLabel.setText("x " + visitorSafe);
        rMessagePane.add(visitorSafeLabel, JLayeredPane.DEFAULT_LAYER);
        visitorSafeLabel.setBounds(185, 95, 30, 20);

        placeVisitorMine = new Square(new Position(0, 2, 3), NIL);
        placeVisitorMine.setIcon(squareBlack);
        rMessagePane.add(placeVisitorMine, JLayeredPane.DEFAULT_LAYER);
        placeVisitorMine.setBounds(260, 85, 40, 40);

        visitorMineLabel = new JLabel();
        visitorMineLabel.setText("x " + visitorMines);
        rMessagePane.add(visitorMineLabel, JLayeredPane.DEFAULT_LAYER);
        visitorMineLabel.setBounds(305, 95, 30, 20);

        placeHomeChecker = new Square(new Position(0, 3, 1), NIL);
        placeHomeChecker.setIcon(checkerRed);
        lMessagePane.add(placeHomeChecker, JLayeredPane.DEFAULT_LAYER);
        placeHomeChecker.setBounds(80, 25, 40, 40);
        placeHomeChecker.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                Square mySquare =  (Square) evt.getSource();
                setupClicked(mySquare);
            }
        });

        homeCheckerLabel = new JLabel();
        homeCheckerLabel.setText("x " + homeCheckers);
        lMessagePane.add(homeCheckerLabel, JLayeredPane.DEFAULT_LAYER);
        homeCheckerLabel.setBounds(125, 35, 30, 20);

        placeHomeKing = new Square(new Position(0, 3, 2), NIL);
        placeHomeKing.setIcon(kingRed);
        lMessagePane.add(placeHomeKing, JLayeredPane.DEFAULT_LAYER);
        placeHomeKing.setBounds(200, 25, 40, 40);
        placeHomeKing.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                Square mySquare =  (Square) evt.getSource();
                setupClicked(mySquare);
            }
        });

        homeKingLabel = new JLabel();
        homeKingLabel.setText("x " + homeKings);
        lMessagePane.add(homeKingLabel, JLayeredPane.DEFAULT_LAYER);
        homeKingLabel.setBounds(245, 35, 30, 20);

        placeHomeBlocked = new Square(new Position(0, 4, 1), NIL);
        placeHomeBlocked.setIcon(squareBlocked);
        lMessagePane.add(placeHomeBlocked, JLayeredPane.DEFAULT_LAYER);
        placeHomeBlocked.setBounds(20, 85, 40, 40);

        homeBlockedLabel = new JLabel();
        homeBlockedLabel.setText("x " + homeBlocked);
        lMessagePane.add(homeBlockedLabel, JLayeredPane.DEFAULT_LAYER);
        homeBlockedLabel.setBounds(65, 95, 30, 20);

        placeHomeSafe = new Square(new Position(0, 4, 2), NIL);
        placeHomeSafe.setIcon(squareSafe);
        lMessagePane.add(placeHomeSafe, JLayeredPane.DEFAULT_LAYER);
        placeHomeSafe.setBounds(140, 85, 40, 40);

        homeSafeLabel = new JLabel();
        homeSafeLabel.setText("x " + homeSafe);
        lMessagePane.add(homeSafeLabel, JLayeredPane.DEFAULT_LAYER);
        homeSafeLabel.setBounds(185, 95, 30, 20);

        placeHomeMine = new Square(new Position(0, 4, 3), NIL);
        placeHomeMine.setIcon(squareBlack);
        lMessagePane.add(placeHomeMine, JLayeredPane.DEFAULT_LAYER);
        placeHomeMine.setBounds(260, 85, 40, 40);

        homeMineLabel = new JLabel();
        homeMineLabel.setText("x " + homeMines);
        lMessagePane.add(homeMineLabel, JLayeredPane.DEFAULT_LAYER);
        homeMineLabel.setBounds(305, 95, 30, 20);

        //items to be implemented in build 2
        placeVisitorSafe.setEnabled(false);
        visitorSafeLabel.setEnabled(false);
        placeVisitorBlocked.setEnabled(false);
        visitorBlockedLabel.setEnabled(false);
        placeVisitorMine.setEnabled(false);
        visitorMineLabel.setEnabled(false);
        placeHomeSafe.setEnabled(false);
        homeSafeLabel.setEnabled(false);
        placeHomeBlocked.setEnabled(false);
        homeBlockedLabel.setEnabled(false);
        placeHomeMine.setEnabled(false);
        homeMineLabel.setEnabled(false);
    }

    //remove the board setup images (Squares)
    private void removeBoardSetup()
    {
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

        lMessagePane.setBorder(null);
        lMessagePane.setVisible(true);
        lMessageLabel.setVisible(true);
        rMessagePane.setBorder(null);
        rMessagePane.setVisible(true);
        rMessageLabel.setVisible(true);
        rMessageLabel.setText("<html>" + visitorPlayer + ", it's your turn</html>");
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
                //update the icons to reflect the piece placement
                square.setIcon(firstSelectSetup.getIcon());

                //decrease the piece count and refresh the label value
                if (firstSelectSetup.getIcon() == checkerBlack)
                {
                    visitorCheckers--;
                    visitorCheckerLabel.setText("x " + visitorCheckers);

                    if (visitorCheckers == 0)
                        placeVisitorChecker.setEnabled(false);
                }
                else if (firstSelectSetup.getIcon() == kingBlack)
                {
                    visitorKings--;
                    visitorKingLabel.setText("x " + visitorKings);

                    if (visitorKings == 0)
                        placeVisitorKing.setEnabled(false);
                }
                else if (firstSelectSetup.getIcon() == checkerRed)
                {
                    homeCheckers--;
                    homeCheckerLabel.setText("x " + homeCheckers);

                    if (homeCheckers == 0)
                        placeHomeChecker.setEnabled(false);
                }
                else if (firstSelectSetup.getIcon() == kingRed)
                {
                    homeKings--;
                    homeKingLabel.setText("x " + homeKings);

                    if (homeKings == 0)
                        placeHomeKing.setEnabled(false);
                }

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
                if(homeCheckers == 0 && homeKings == 0)
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
        gameMenu.setFont(new java.awt.Font("Old English Text MT", 0, 16));

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setFont(new java.awt.Font("Old English Text MT", 0, 14));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        gameMenu.add(exitMenuItem);

        boardMenuBar.add(gameMenu);

        helpMenu.setText("Help");
        helpMenu.setFont(new java.awt.Font("Old English Text MT", 0, 16));
        boardMenuBar.add(helpMenu);

        setJMenuBar(boardMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //exit the program
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
    * @param args the command line arguments
    */
//    public static void main(String args[])
//    {
//        java.awt.EventQueue.invokeLater(new Runnable()
//        {
//            public void run()
//            {
//                new GameScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar boardMenuBar;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu gameMenu;
    private javax.swing.JMenu helpMenu;
    // End of variables declaration//GEN-END:variables

    //additional variable declarations
    JLabel lMessageLabel;
    JLayeredPane lMessagePane;
    JLabel rMessageLabel;
    JLayeredPane rMessagePane;
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