//----------------------------------------------------------------------------
//FileName: LoginScreen.java
//Project: 3D Checkers
//Author: Byron Lunt
//Description: This GUI loads a saved game
//----------------------------------------------------------------------------


package checkers;

import java.awt.Font;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class LoadScreen extends javax.swing.JFrame
{
    private FileNameExtensionFilter filter;
    private boolean hFound = false;
    private boolean vFound = false;

     //load the Old English font with a given size and type
    private Font loadFont(int type, float size)
    {
        Font font = null;
        try
        {
            InputStream input = this.getClass().getResourceAsStream("/OLDENGL.TTF");
            font = Font.createFont(Font.PLAIN, input).deriveFont(type, size);
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }
        return font;
    }
    //load fonts
    Font oldEnglish_12 = loadFont(Font.PLAIN, 12);
    Font oldEnglish_14 = loadFont(Font.PLAIN, 14);
    Font oldEnglish_16 = loadFont(Font.PLAIN, 16);
    Font oldEnglish_18 = loadFont(Font.PLAIN, 18);
    Font oldEnglish_36b = loadFont(Font.BOLD, 36);
    Font oldEnglish_48b = loadFont(Font.BOLD, 64);

    /** Creates new form LoadScreen */
    public LoadScreen()
    {
        initComponents();
        //filter the file types so that only files of type '.game'
        //are displayed.
        filter = new FileNameExtensionFilter("Saved Games","game");
        GameLoader.addChoosableFileFilter(filter);

        try
        {
        //set the current directory so that it is always where the saved games
        //are.
        File file = new File (new File(".").getCanonicalPath() + File.separator + "games");
        
        GameLoader.setCurrentDirectory(file);
        }
        
        //handle our exceptions
        catch(IOException e)
        {
            System.out.println("Cannot find game directory");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        GameLoader = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("3D Checkers - Load Game");
        setMinimumSize(new java.awt.Dimension(660, 445));
        getContentPane().setLayout(null);

        GameLoader.setAcceptAllFileFilterUsed(false);
        GameLoader.setApproveButtonMnemonic(1);
        GameLoader.setBorder(null);
        GameLoader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GameLoaderActionPerformed(evt);
            }
        });
        jPanel1.add(GameLoader);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 570, 390);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GameLoaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GameLoaderActionPerformed
        if(evt.getSource() == GameLoader)
        {
            if(JFileChooser.APPROVE_SELECTION.equals(evt.getActionCommand()))
            {
               //the Open button was pressed
               File file = GameLoader.getSelectedFile();
               try
               {
                    //retrieve our saved game from the hard drive.
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Game game = (Game) ois.readObject();

                    //check to ensure that both the home player and visitor
                    //player are still registered in the users list, make
                    //sure that one or both have not been deleted.
                    for(int i = 0; i < Main.storage.getUsers().size(); i++)
                    {
                        if(game.getHome().equals(Main.storage.getUsers().get(i).getUserName()))
                        {
                            hFound = true;
                            break;
                        }
                    }

                    for(int i = 0; i < Main.storage.getUsers().size(); i++)
                    {
                        if(game.getVisitor().equals(Main.storage.getUsers().get(i).getUserName()))
                        {
                            vFound = true;
                            break;
                        }
                    }
                    // if both players were found in the Users list, continue gameplay
                    // else go back to the welcome screen.
                    if(hFound && vFound)
                    {
                        new LoadGameLogin(game, game.getHome(), game.getVisitor()).setVisible(true);
                        dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "One or more players are no longer registered",
                                "Cannot Find PLayer", JOptionPane.WARNING_MESSAGE);
                        Main.restart();
                    }
                }

                //handle any exceptions that are thrown.
                catch (FileNotFoundException fne)
                {
                    System.out.println("File Not Found");
                    //storage = new Storage();
                }
                catch (IOException ioe)
                {
                    //System.exit(1);
                    ioe.printStackTrace();
                }
                catch (ClassNotFoundException cne)
                {
                    System.exit(2);
                }
            }
        
           else
           {
                //the Cancel button was pressed
                Main.restart();
                dispose();
           }
        }
    }//GEN-LAST:event_GameLoaderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser GameLoader;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    // End of variables declaration//GEN-END:variables

}
