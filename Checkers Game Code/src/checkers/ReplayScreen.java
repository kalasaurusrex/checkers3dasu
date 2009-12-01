/*
 * The ReplayScreen class provides instant replay capabilities to a user
 *
 * Author: David Clark
 */

package checkers;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Frame;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class ReplayScreen extends javax.swing.JDialog
{
    int maxMoves;
    int currentMove;
    final int PIXELS = 40;
    private Square[] square;
    private int width = 0;
    private int boardSize = 0;
    ArrayList<int[]> movesList;

    //load images
    ImageIcon board8X8 = new ImageIcon(getClass().
            getResource("/checkers/images/Board8x8.png"));
    ImageIcon board10X10 = new ImageIcon(getClass().
            getResource("/checkers/images/Board10x10.png"));
    ImageIcon squareBlack = new ImageIcon(getClass().
            getResource("/checkers/images/SquareBlack.png"));
    ImageIcon checkerBlack = new ImageIcon(getClass().
            getResource("/checkers/images/CheckerBlack.png"));
    ImageIcon checkerRed = new ImageIcon(getClass().
            getResource("/checkers/images/CheckerRed.png"));
    ImageIcon kingBlack = new ImageIcon(getClass().
            getResource("/checkers/images/KingBlack.png"));
    ImageIcon kingRed = new ImageIcon(getClass().
            getResource("/checkers/images/KingRed.png"));
    ImageIcon squareSafe = new ImageIcon(getClass().
            getResource("/checkers/images/SquareSafe.png"));
    ImageIcon safeCheckerBlack = new ImageIcon(getClass().
            getResource("/checkers/images/SafeCheckerBlack.png"));
    ImageIcon safeCheckerRed = new ImageIcon(getClass().
            getResource("/checkers/images/SafeCheckerRed.png"));
    ImageIcon safeKingBlack = new ImageIcon(getClass().
            getResource("/checkers/images/SafeKingBlack.png"));
    ImageIcon safeKingRed = new ImageIcon(getClass().
            getResource("/checkers/images/SafeKingRed.png"));
    ImageIcon squareBlocked = new ImageIcon(getClass().
            getResource("/checkers/images/SquareBlocked.png"));
    ImageIcon displayLabel = new ImageIcon(getClass().
            getResource("/checkers/images/DisplayLabel.png"));
    ImageIcon background = new ImageIcon(getClass().
            getResource("/checkers/images/Background.png"));
    ImageIcon forwardIcon = new ImageIcon(getClass().
            getResource("/checkers/images/ForwardButton.png"));
    ImageIcon backIcon = new ImageIcon(getClass().
            getResource("/checkers/images/BackButton.png"));
    ImageIcon firstIcon = new ImageIcon(getClass().
            getResource("/checkers/images/FirstButton.png"));
    ImageIcon lastIcon = new ImageIcon(getClass().
            getResource("/checkers/images/LastButton.png"));
    ImageIcon exitSign = new ImageIcon(getClass().
            getResource("/checkers/images/ExitSign.png"));

    //load fonts
    Font oldEnglish_16 = loadFont(Font.PLAIN, 16);
    Font oldEnglish_14 = loadFont(Font.PLAIN, 14);

    /** Creates new form ReplayScreen */
    public ReplayScreen(Frame parent, boolean modal, int size, ArrayList ml)
    {
        super(parent, modal);

        width = size;
        boardSize = width * width;
        movesList = ml;
        maxMoves = currentMove = movesList.size() - 1;

        initComponents();
        initBoard();
        initExtras();

        refreshBoard(movesList.get(movesList.size() - 1));
    }

     private void initBoard()
    {
        int board = 1;
        int row = 1;
        int col = 2;
        boolean halfFlag = false;
        lMessagePane = new JLayeredPane();
        lMessageLabel = new JLabel();
        rMessagePane = new JLayeredPane();
        rMessageLabel = new JLabel();
        BackgroundPane = new JLayeredPane();
        BackgroundLabel = new JLabel();
        lBoardPane = new JLayeredPane();
        lBackgroundLabel = new JLabel();
        rBoardPane = new JLayeredPane();
        rBackgroundLabel = new JLabel();

        getContentPane().add(BackgroundPane);
        BackgroundPane.setBounds(0, 0, 888, 640);
        BackgroundPane.add(BackgroundLabel, -1);
        BackgroundLabel.setIcon(background);
        BackgroundLabel.setBounds(0, 0, 888, 640);

        getContentPane().add(lMessagePane, 0);
        lMessagePane.setBounds(40, 430, 340, 145);

        getContentPane().add(rMessagePane, 0);
        rMessagePane.setBounds(500, 20, 340, 145);

        lMessageLabel.setIcon(displayLabel);
        lMessageLabel.setBounds(0, 0, 340, 145);
        lMessagePane.add(lMessageLabel, -1);

        rMessageLabel.setIcon(displayLabel);
        rMessageLabel.setBounds(0, 0, 340, 145);
        rMessagePane.add(rMessageLabel, -1);

        //initialize the array of Squares
        square = new Square[boardSize];

        //set the size and location of the background labels and add them to
        //the layered pane.
        lBackgroundLabel.setBounds(0, 0, PIXELS * width, PIXELS * width);
        lBoardPane.add(lBackgroundLabel, JLayeredPane.DEFAULT_LAYER);
        BackgroundPane.add(lBoardPane, 0);
        rBackgroundLabel.setBounds(0, 0, PIXELS * width, PIXELS * width);
        rBoardPane.add(rBackgroundLabel, JLayeredPane.DEFAULT_LAYER);
        BackgroundPane.add(rBoardPane, 0);

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

     //display navigation buttons
    private void initExtras()
    {
        lastButton = new javax.swing.JButton();
        firstButton = new javax.swing.JButton();
        forwardButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        currentMoveText = new IntegerTextField();
        maxMoveLabel = new JLabel("of  " + maxMoves);

        //firstButton.setText("<<");
        firstButton.setIcon(firstIcon);
        firstButton.setContentAreaFilled(false);
        firstButton.setBorder(null);
        firstButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                firstButtonActionPerformed(evt);
            }
        });
        rMessagePane.add(firstButton, 0);
        firstButton.setBounds(20, 70, 55, 55);

        backButton.setIcon(backIcon);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(null);
        //backButton.setText("<");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        rMessagePane.add(backButton, 0);
        backButton.setBounds(85, 70, 55, 55);

        forwardButton.setIcon(forwardIcon);
        forwardButton.setContentAreaFilled(false);
        forwardButton.setBorder(null);
        //forwardButton.setText(">");
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                forwardButtonActionPerformed(evt);
            }
        });
        rMessagePane.add(forwardButton, 0);
        forwardButton.setBounds(200, 70, 55, 55);

        lastButton.setIcon(lastIcon);
        lastButton.setContentAreaFilled(false);
        lastButton.setBorder(null);
        //lastButton.setText(">>");
        lastButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });
        rMessagePane.add(lastButton, 0);
        lastButton.setBounds(265, 70, 55, 55);

        //exitButton.setText("X");
        exitButton.setIcon(exitSign);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        lMessagePane.add(exitButton, 0);
        exitButton.setBounds(25, 25, 290, 95);

        currentMoveText.setText(Integer.toString(currentMove));
        currentMoveText.setHorizontalAlignment(JTextField.CENTER);
        currentMoveText.setBorder(new LineBorder(new Color(0, 0, 0), 1, false));
        currentMoveText.setFont(oldEnglish_16);
        currentMoveText.setBounds(120, 25, 45, 20);
        rMessagePane.add(currentMoveText, 0);

        maxMoveLabel.setFont(oldEnglish_16);
        maxMoveLabel.setBounds(175, 25, 140, 20);
        rMessagePane.add(maxMoveLabel, 0);
    }

    //display the last move in the list
    private void lastButtonActionPerformed(ActionEvent evt)
    {
        if (movesList.size() > 0)
        {
            refreshBoard(movesList.get(movesList.size() - 1));
            currentMove = maxMoves;
        }

        currentMoveText.setText(Integer.toString(currentMove));
    }

    //display the first move in the list
    private void firstButtonActionPerformed(ActionEvent evt)
    {
        refreshBoard(movesList.get(0));
        currentMove = 0;
        currentMoveText.setText(Integer.toString(currentMove));
    }

    //display the next move in the list
    private void forwardButtonActionPerformed(ActionEvent evt)
    {
        if (currentMove < maxMoves)
        {
            currentMove++;
            refreshBoard(movesList.get(currentMove));
        }

        currentMoveText.setText(Integer.toString(currentMove));
    }

    //display the previous move in the list
    private void backButtonActionPerformed(ActionEvent evt)
    {
        if (currentMove > 0)
        {
            currentMove--;
            refreshBoard(movesList.get(currentMove));
        }

        currentMoveText.setText(Integer.toString(currentMove));
    }

    //exit the replay screen
    private void exitButtonActionPerformed(ActionEvent evt)
    {
        dispose();
    }

    //display the given move
    private void refreshBoard(int[] board)
    {
        for (int i = 0; i < square.length; i++)
        {
            switch (board[i])
            {
                case Main.SQ_BL: square[i].setIcon(squareBlack); break;
                case Main.CK_BL: square[i].setIcon(checkerBlack); break;
                case Main.CK_RD: square[i].setIcon(checkerRed); break;
                case Main.KG_BL: square[i].setIcon(kingBlack); break;
                case Main.KG_RD: square[i].setIcon(kingRed); break;
                case Main.SQ_SF: square[i].setIcon(squareSafe); break;
                case Main.SF_CK_BL: square[i].setIcon(safeCheckerBlack); break;
                case Main.SF_CK_RD: square[i].setIcon(safeCheckerRed); break;
                case Main.SF_KG_BL: square[i].setIcon(safeKingBlack); break;
                case Main.SF_KG_RD: square[i].setIcon(safeKingRed); break;
                case Main.SQ_BO: square[i].setIcon(squareBlocked); break;
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

        replayMenuBar = new javax.swing.JMenuBar();
        replayMenu = new javax.swing.JMenu();
        closeReplayMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("3D Checkers - Instant Replay");
        setMinimumSize(new java.awt.Dimension(888, 640));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(null);

        replayMenu.setText("Replay");
        replayMenu.setFont(oldEnglish_16);

        closeReplayMenuItem.setText("Close Replay");
        closeReplayMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeReplayMenuItemActionPerformed(evt);
            }
        });
        replayMenu.add(closeReplayMenuItem);
        closeReplayMenuItem.setFont(oldEnglish_14);

        replayMenuBar.add(replayMenu);

        setJMenuBar(replayMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeReplayMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeReplayMenuItemActionPerformed
        dispose();
}//GEN-LAST:event_closeReplayMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem closeReplayMenuItem;
    private javax.swing.JMenu replayMenu;
    private javax.swing.JMenuBar replayMenuBar;
    // End of variables declaration//GEN-END:variables

    //additional variables declaration
    JLayeredPane BackgroundPane;
    JLabel BackgroundLabel;
    JLayeredPane lMessagePane;
    JLabel lMessageLabel;
    JLayeredPane rMessagePane;
    JLabel rMessageLabel;
    JLayeredPane lBoardPane;
    JLabel lBackgroundLabel;
    JLayeredPane rBoardPane;
    JLabel rBackgroundLabel;
    JButton backButton;
    JButton firstButton;
    JButton forwardButton;
    JButton lastButton;
    JButton exitButton;
    IntegerTextField currentMoveText;
    JLabel maxMoveLabel;

    //restrict the values that a user can input in the text box
    class IntegerTextField extends JTextField
    {
        final static String invalid = "-`~!@#$%^&*()_+=\\|\"':;?/>.<, ";

        @Override
        public void processKeyEvent(KeyEvent ev)
        {
            char c = ev.getKeyChar();

            //prevent entry of letter or special character
            if (Character.isLetter(c) || invalid.indexOf(c) > -1)
            {
                ev.consume(); return;
            }
            //prevent entry of value greater than the total number of moves
            else if (Character.isDigit(c) && Integer.parseInt(getText() + c) > maxMoves)
            {
                ev.consume(); return;
            }
            //prevent entry of multple zero's or numbers with a leading zero
            else if (Character.isDigit(c) && getDocument().getLength() > 0 &&
                    Integer.parseInt(getText()) == 0)
            {
                ev.consume();
            }
            //display the move number entered in the text field
            else if (c == KeyEvent.VK_ENTER)
            {
                refreshBoard(movesList.get(Integer.parseInt(getText())));
                currentMove = Integer.parseInt(getText());
                return;
            }

            super.processKeyEvent(ev);
        }
    }
}
