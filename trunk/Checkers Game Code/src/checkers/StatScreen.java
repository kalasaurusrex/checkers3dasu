//-----------------------------------------------------------------------------
//FileName: StatScreen.java
//Project: 3D Checkers
//Author: Byron Lunt
//Description: This is the GUI representation of our stat screen
//-----------------------------------------------------------------------------

package checkers;

import java.awt.Font;
import java.io.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


public class StatScreen extends javax.swing.JFrame
{
    User selected;
    User opponent;

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

    public StatScreen()
    {
        initComponents();
        UserList.setModel(new DefaultComboBoxModel(Main.storage.getUsers()));
        UserList.setVisible(true);
        CompareList.setModel(new DefaultComboBoxModel(Main.storage.getUsers()));
        CompareList.setVisible(true);
        CompareLabel.setVisible(false);
        CompareList.setVisible(false);
        OverallButton.setVisible(false);
        PvPButton.setVisible(false);

    }
   
    public void indStats (User player)
    {
        WinsLabel.setText("" + player.getStats().getWins());
        LossesLabel.setText("" + player.getStats().getLosses());
        TiesLabel.setText("" + player.getStats().getTies());

    }

    public void pvpStats (User selected, User opponent)
    {
        if (selected != null) {
            // PvP stats for a player vs. himself do not make sense
            if (selected.equals(opponent)) {
                WinsLabel.setText("N/A");
                LossesLabel.setText("N/A");
                TiesLabel.setText("N/A");
            } else if (selected.getStats().pvpRecords.containsKey(opponent)) {
                WinsLabel.setText("" + selected.getStats().pvpRecords.get(opponent).getWins());
                LossesLabel.setText("" + selected.getStats().pvpRecords.get(opponent).getLosses());
                TiesLabel.setText("" + selected.getStats().pvpRecords.get(opponent).getTies());
            } else {
                WinsLabel.setText("0");
                LossesLabel.setText("0");
                TiesLabel.setText("0");
            }
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        WinLossTieLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        WinsLabel = new javax.swing.JLabel();
        LossesLabel = new javax.swing.JLabel();
        TiesLabel = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        OKButton = new javax.swing.JButton();
        UserList = new javax.swing.JComboBox();
        CompareLabel = new javax.swing.JLabel();
        CompareList = new javax.swing.JComboBox();
        OverallButton = new javax.swing.JButton();
        PvPButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("3D Checkers - Statistics");
        setMinimumSize(new java.awt.Dimension(660, 350));
        getContentPane().setLayout(null);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(0, 70, 730, 20);

        jLabel1.setFont(oldEnglish_36b);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Statistics");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(130, 20, 320, 60);

        BackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/checkers/images/Back_Button.png"))); // NOI18N
        BackButton.setBorder(null);
        BackButton.setBorderPainted(false);
        BackButton.setContentAreaFilled(false);
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(BackButton);
        BackButton.setBounds(10, 10, 55, 55);
        BackButton.getAccessibleContext().setAccessibleDescription("");

        WinLossTieLabel.setFont(oldEnglish_16);
        WinLossTieLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WinLossTieLabel.setText("Wins              Losses              Ties");
        WinLossTieLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(WinLossTieLabel);
        WinLossTieLabel.setBounds(250, 94, 300, 30);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(280, 120, 240, 10);

        WinsLabel.setFont(oldEnglish_16);
        WinsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(WinsLabel);
        WinsLabel.setBounds(290, 130, 40, 20);

        LossesLabel.setFont(oldEnglish_16);
        LossesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(LossesLabel);
        LossesLabel.setBounds(380, 130, 50, 20);

        TiesLabel.setFont(oldEnglish_16);
        TiesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(TiesLabel);
        TiesLabel.setBounds(480, 130, 40, 20);
        getContentPane().add(PasswordField);
        PasswordField.setBounds(20, 140, 100, 20);

        OKButton.setText("OK");
        OKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });
        getContentPane().add(OKButton);
        OKButton.setBounds(130, 140, 60, 20);

        UserList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        UserList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserListActionPerformed(evt);
            }
        });
        getContentPane().add(UserList);
        UserList.setBounds(20, 100, 170, 30);

        CompareLabel.setFont(oldEnglish_14);
        CompareLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CompareLabel.setText("Select a player for PvP Stats");
        getContentPane().add(CompareLabel);
        CompareLabel.setBounds(20, 190, 170, 30);

        CompareList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CompareList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompareListActionPerformed(evt);
            }
        });
        getContentPane().add(CompareList);
        CompareList.setBounds(20, 230, 170, 30);

        OverallButton.setText("Overall Stats");
        OverallButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OverallButtonActionPerformed(evt);
            }
        });
        getContentPane().add(OverallButton);
        OverallButton.setBounds(290, 240, 110, 23);

        PvPButton.setText("PvP Stats");
        PvPButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PvPButtonActionPerformed(evt);
            }
        });
        getContentPane().add(PvPButton);
        PvPButton.setBounds(419, 240, 100, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        Main.restart();
        dispose();
}//GEN-LAST:event_BackButtonActionPerformed

    private void OKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButtonActionPerformed
        selected = (User)UserList.getSelectedItem();
        String passwordEntry = PasswordField.getText();
        if (!(passwordEntry == null)) {
            if (!passwordEntry.equals(selected.getPassword())) {
                JOptionPane.showMessageDialog(this, "Incorrect Password!", "Password Incorrect", JOptionPane.WARNING_MESSAGE);
                selected = null;
                PasswordField.setText(null);
                return;
            }
            indStats(selected);
            OverallButton.setVisible(true);
            PvPButton.setVisible(true);
        }
}//GEN-LAST:event_OKButtonActionPerformed

    private void UserListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserListActionPerformed
        PasswordField.setText(null);
        PasswordField.setVisible(true);
        OKButton.setVisible(true);
        WinsLabel.setText(null);
        LossesLabel.setText(null);
        TiesLabel.setText(null);
        CompareList.setVisible(false);
        CompareLabel.setVisible(false);
}//GEN-LAST:event_UserListActionPerformed

    private void CompareListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompareListActionPerformed
        opponent = (User)CompareList.getSelectedItem();
        selected = (User)UserList.getSelectedItem();
        pvpStats(selected, opponent);
        
        

    }//GEN-LAST:event_CompareListActionPerformed

    private void OverallButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OverallButtonActionPerformed
        CompareList.setVisible(false);
        CompareLabel.setVisible(false);
        selected = (User)UserList.getSelectedItem();
        indStats(selected);
    }//GEN-LAST:event_OverallButtonActionPerformed

    private void PvPButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PvPButtonActionPerformed
        CompareList.setVisible(true);
        CompareLabel.setVisible(true);
        pvpStats(selected, (User)CompareList.getItemAt(0));
    }//GEN-LAST:event_PvPButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel CompareLabel;
    private javax.swing.JComboBox CompareList;
    private javax.swing.JLabel LossesLabel;
    private javax.swing.JButton OKButton;
    private javax.swing.JButton OverallButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton PvPButton;
    private javax.swing.JLabel TiesLabel;
    private javax.swing.JComboBox UserList;
    private javax.swing.JLabel WinLossTieLabel;
    private javax.swing.JLabel WinsLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

}
