/*
* The WelcomeScreen class provides a simple Graphical User Interface
* that allows a user to select 1 of 4 options:
* 1. Play new game
* 2. Load game
* 3. View statistics
* 4. Perform administrative functions
* 
* Author:  David Clark
*/

package checkers;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class WelcomeScreen extends javax.swing.JFrame
{
    //load fonts
    Font oldEnglish_16 = loadFont(Font.PLAIN, 16);
    Font oldEnglish_40 = loadFont(Font.PLAIN, 40);
    
    /** Creates new form WelcomeScreen */
    public WelcomeScreen()
    {
        initComponents();
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

        buttonGroup = new javax.swing.ButtonGroup();
        WelcomeLabel = new javax.swing.JLabel();
        NewGameButton = new javax.swing.JRadioButton();
        LoadGameButton = new javax.swing.JRadioButton();
        StatsButton = new javax.swing.JRadioButton();
        AdminButton = new javax.swing.JRadioButton();
        NextButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("3D Checkers - Welcome");
        setMinimumSize(new java.awt.Dimension(440, 330));
        setResizable(false);
        getContentPane().setLayout(null);

        WelcomeLabel.setFont(oldEnglish_40);
        WelcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WelcomeLabel.setText("Welcome to 3D Checkers");
        WelcomeLabel.setPreferredSize(new java.awt.Dimension(404, 50));
        getContentPane().add(WelcomeLabel);
        WelcomeLabel.setBounds(18, 12, 404, 50);

        buttonGroup.add(NewGameButton);
        NewGameButton.setFont(oldEnglish_16);
        NewGameButton.setSelected(true);
        NewGameButton.setText("New Game");
        getContentPane().add(NewGameButton);
        NewGameButton.setBounds(160, 90, 140, 24);

        buttonGroup.add(LoadGameButton);
        LoadGameButton.setFont(oldEnglish_16);
        LoadGameButton.setText("Load Game");
        getContentPane().add(LoadGameButton);
        LoadGameButton.setBounds(160, 120, 140, 24);

        buttonGroup.add(StatsButton);
        StatsButton.setFont(oldEnglish_16);
        StatsButton.setText("Statistics");
        getContentPane().add(StatsButton);
        StatsButton.setBounds(160, 150, 140, 24);

        buttonGroup.add(AdminButton);
        AdminButton.setFont(oldEnglish_16);
        AdminButton.setText("Administration");
        getContentPane().add(AdminButton);
        AdminButton.setBounds(160, 180, 140, 24);

        NextButton.setFont(oldEnglish_16);
        NextButton.setText("Next");
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });
        getContentPane().add(NextButton);
        NextButton.setBounds(240, 242, 68, 28);
        getRootPane().setDefaultButton(NextButton);

        ExitButton.setFont(oldEnglish_16);
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ExitButton);
        ExitButton.setBounds(130, 242, 68, 28);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        if (NewGameButton.isSelected()) {
            //new GameScreen(10, "New Player 1", "New Player 2").setVisible(true);
            new LoginScreen().setVisible(true);
            setVisible(false);
        }

        //Go to load game screen
        if (LoadGameButton.isSelected()) {
            new LoadScreen().setVisible(true);
            setVisible(false);
        }

        //go to stat screen
        if (StatsButton.isSelected()) {
            new StatScreen().setVisible(true);
            setVisible(false);
        }
        if (AdminButton.isSelected()) {
            // create NewAdminForm to accept a new adminstrator
            if (!Main.storage.adminCreated) {
                NewAdminForm newForm = new NewAdminForm(this, true);
                newForm.setVisible(true);
                setVisible(false);
                // create AdminScreen to allow adminstrative functions
            } else {
                new AdminScreen().setVisible(true);
                setVisible(false);
            }
    }//GEN-LAST:event_NextButtonActionPerformed
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AdminButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JRadioButton LoadGameButton;
    private javax.swing.JRadioButton NewGameButton;
    private javax.swing.JButton NextButton;
    private javax.swing.JRadioButton StatsButton;
    private javax.swing.JLabel WelcomeLabel;
    private javax.swing.ButtonGroup buttonGroup;
    // End of variables declaration//GEN-END:variables

}
