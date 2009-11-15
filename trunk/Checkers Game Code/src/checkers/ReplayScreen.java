/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReplayScreen.java
 *
 * Created on Nov 11, 2009, 9:57:11 PM
 */

package checkers;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;

public class ReplayScreen extends javax.swing.JDialog
{
    final int PIXELS = 40;
    private Square[] square;
    private int width = 0;
    private int boardSize = 0;

    //load images
    ImageIcon board8X8 = new ImageIcon(getClass().getResource("/checkers/images/Board8x8.png"));
    ImageIcon board10X10 = new ImageIcon(getClass().getResource("/checkers/images/Board10x10.png"));
    ImageIcon squareBlack = new ImageIcon(getClass().getResource("/checkers/images/SquareBlack.png"));
    ImageIcon checkerBlack = new ImageIcon(getClass().getResource("/checkers/images/CheckerBlack.png"));
    ImageIcon checkerRed = new ImageIcon(getClass().getResource("/checkers/images/CheckerRed.png"));
    ImageIcon kingBlack = new ImageIcon(getClass().getResource("/checkers/images/KingBlack.png"));
    ImageIcon kingRed = new ImageIcon(getClass().getResource("/checkers/images/KingRed.png"));
    ImageIcon squareSafe = new ImageIcon(getClass().getResource("/checkers/images/SquareSafe.png"));
    ImageIcon safeCheckerBlack = new ImageIcon(getClass().getResource("/checkers/images/SafeCheckerBlack.png"));
    ImageIcon safeCheckerRed = new ImageIcon(getClass().getResource("/checkers/images/SafeCheckerRed.png"));
    ImageIcon safeKingBlack = new ImageIcon(getClass().getResource("/checkers/images/SafeKingBlack.png"));
    ImageIcon safeKingRed = new ImageIcon(getClass().getResource("/checkers/images/SafeKingRed.png"));
    ImageIcon squareBlocked = new ImageIcon(getClass().getResource("/checkers/images/SquareBlocked.png"));

    /** Creates new form ReplayScreen */
    public ReplayScreen(java.awt.Frame parent, boolean modal, int size)
    {
        super(parent, modal);

        width = size;
        boardSize = width * width;

        initComponents();
        initBoard();
    }

    public void getBoardInfo ()
    {

    }

    public void refreshBoard ()
    {

    }

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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Instant Replay");
        setMinimumSize(new java.awt.Dimension(888, 640));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ReplayScreen dialog = new ReplayScreen(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
