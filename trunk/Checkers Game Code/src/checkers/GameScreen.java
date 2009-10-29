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
    final int PIXELS = 40;
    private Square[] square;
    private Referee referee;
    private Game game;
    private int visitorCheckers = 0;
    private int visitorKings = 1;
    private int homeCheckers = 0;
    private int homeKings = 1;
    private int width = 0;
    private int boardSize = 0;
    private boolean boardSetup = false;
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
    public GameScreen()
    {

    }
    
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
            visitorCheckers = homeCheckers = 9;
        else if (size == 10)
            visitorCheckers = homeCheckers = 14;

        initComponents();
        initBoard();
        initBoardSetup();

        if (coinToss() == 0)
            game = new Game(square, width, player1, player2);
        else
            game = new Game(square, width, player2, player1);
        
        referee = new Referee(square, game);
    }

    //will be implemented in build 2
    //randomly place all pieces
    public void randomPlace ()
    {
        
    }

    //randomly determine the home and visitor player
    public int coinToss ()
    {
        random = new Random();

        return random.nextInt(2);
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
            rBoardPane.setBounds(468, 138, PIXELS * width, PIXELS * width);

            //add the background picture to the label
            lBackgroundLabel.setIcon(board10X10);
            rBackgroundLabel.setIcon(board10X10);
        }
        else if (width == 8)
        {
            //define the placement of the 8x8 board
            //the PIXELS offset is added in order to center the smaller board
            lBoardPane.setBounds(12 + PIXELS, 10 + PIXELS, PIXELS * width, PIXELS * width);
            rBoardPane.setBounds(468 + PIXELS, 138 + PIXELS, PIXELS * width, PIXELS * width);

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
            this.square[i] = new Square(new Position(board, row, col));
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

                referee.executeMove(firstSelectMove, secondSelectMove);

                firstSelectMove = null;
            }
            else
            {
                if (square.isEnabled())
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
        placeVisitorChecker = new Square(new Position(0, 1, 1));
        placeVisitorChecker.setIcon(checkerBlack);
        getContentPane().add(placeVisitorChecker);
        placeVisitorChecker.setBounds(590, 20, 40, 40);
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
        getContentPane().add(visitorCheckerLabel);
        visitorCheckerLabel.setBounds(635, 30, 30, 20);

        placeVisitorKing = new Square(new Position(0, 1, 2));
        placeVisitorKing.setIcon(kingBlack);
        getContentPane().add(placeVisitorKing);
        placeVisitorKing.setBounds(710, 20, 40, 40);
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
        getContentPane().add(visitorKingLabel);
        visitorKingLabel.setBounds(755, 30, 30, 20);

        placeVisitorBlocked = new Square(new Position(0, 2, 1));
        placeVisitorBlocked.setIcon(squareBlocked);
        getContentPane().add(placeVisitorBlocked);
        placeVisitorBlocked.setBounds(530, 80, 40, 40);

        placeVisitorSafe = new Square(new Position(0, 2, 2));
        placeVisitorSafe.setIcon(squareSafe);
        getContentPane().add(placeVisitorSafe);
        placeVisitorSafe.setBounds(650, 80, 40, 40);

        placeVisitorMine = new Square(new Position(0, 2, 3));
        placeVisitorMine.setIcon(squareBlack);
        getContentPane().add(placeVisitorMine);
        placeVisitorMine.setBounds(770, 80, 40, 40);

        placeHomeChecker = new Square(new Position(0, 3, 1));
        placeHomeChecker.setIcon(checkerRed);
        getContentPane().add(placeHomeChecker);
        placeHomeChecker.setBounds(130, 430, 40, 40);
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
        getContentPane().add(homeCheckerLabel);
        homeCheckerLabel.setBounds(175, 440, 30, 20);

        placeHomeKing = new Square(new Position(0, 3, 2));
        placeHomeKing.setIcon(kingRed);
        getContentPane().add(placeHomeKing);
        placeHomeKing.setBounds(250, 430, 40, 40);
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
        getContentPane().add(homeKingLabel);
        homeKingLabel.setBounds(295, 440, 30, 20);

        placeHomeBlocked = new Square(new Position(0, 4, 1));
        placeHomeBlocked.setIcon(squareBlocked);
        getContentPane().add(placeHomeBlocked);
        placeHomeBlocked.setBounds(70, 490, 40, 40);

        placeHomeSafe = new Square(new Position(0, 4, 2));
        placeHomeSafe.setIcon(squareSafe);
        getContentPane().add(placeHomeSafe);
        placeHomeSafe.setBounds(190, 490, 40, 40);

        placeHomeMine = new Square(new Position(0, 4, 3));
        placeHomeMine.setIcon(squareBlack);
        getContentPane().add(placeHomeMine);
        placeHomeMine.setBounds(310, 490, 40, 40);

        //items to be implemented in build 2
        placeVisitorSafe.setEnabled(false);
        placeVisitorBlocked.setEnabled(false);
        placeVisitorMine.setEnabled(false);
        placeHomeSafe.setEnabled(false);
        placeHomeBlocked.setEnabled(false);
        placeHomeMine.setEnabled(false);
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
        homeCheckerLabel.setVisible(false);
        homeKingLabel.setVisible(false);
    }

    //receive and manage "clicks" from the user during setup phase
    private void setupClicked(Square square)
    {
        //check if a first selection has been made
        if (firstSelectSetup != null)
        {
            secondSelectSetup = square;

            removeHighlight(firstSelectSetup);
            removeHighlight(secondSelectSetup);

            if (square.isEnabled() && referee.executePlacement(firstSelectSetup, secondSelectSetup))
            {
                square.setIcon(firstSelectSetup.getIcon());
                
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
        setMinimumSize(new java.awt.Dimension(880, 600));
        setName("boardFrame"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(null);

        gameMenu.setText("Game");
        gameMenu.setFont(new java.awt.Font("Old English Text MT", 0, 16)); // NOI18N

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setFont(new java.awt.Font("Old English Text MT", 0, 14)); // NOI18N
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        gameMenu.add(exitMenuItem);

        boardMenuBar.add(gameMenu);

        helpMenu.setText("Help");
        helpMenu.setFont(new java.awt.Font("Old English Text MT", 0, 16)); // NOI18N
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
    JLabel homeCheckerLabel;
    JLabel homeKingLabel;
}