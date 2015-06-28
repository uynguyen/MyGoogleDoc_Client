/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Bus.Global;
import CommunicatePackage.LoginReturnPackage;
import CustomComponents.CollaborationItem;
import CustomComponents.MyDocument;
import CustomComponents.WrapLayout;
import Pojo.Invite;
import Runnables.ReceivePushNotificationThread;

import java.awt.Dimension;

import SwingWorkers.CreateDocTask;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.Document;

/**
 *
 * @author UyNguyen.ITUS
 */
public class MyDocsForm extends javax.swing.JFrame {

    LoginReturnPackage _loginReturnPackage = null;
    private JDialog frame = new JDialog(this, "Collaboration", true);

    /**
     * Creates new form MyDocsForm
     */
    public MyDocsForm(LoginReturnPackage result) {
        initComponents();
        this._loginReturnPackage = result;

        txt_UserName.setText(result.user.getUsername());

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        int lstDocSize = result.documentList.length;

        ArrayList<Pojo.Document> returnListDocument = new ArrayList<Pojo.Document>();

        panel_MyDocs.setLayout(new WrapLayout(java.awt.FlowLayout.LEFT, 10, 10));
        for (int i = 0; i < lstDocSize; i++) {
            String name = result.documentList[i].getName();
            Date date = result.documentList[i].getDateCreate();
            String path = result.documentList[i].getPath();
            int idOwner = result.documentList[i].getIDOwner();

            int id = result.documentList[i].getID();
            String code = result.documentList[i].getCode();
            panel_MyDocs.add(new MyDocument(id, name, path, date, idOwner, code));
            returnListDocument.add(result.documentList[i]);
            // jScrollPane.add(panel_MyDocs);
        }

        ImageIcon icon = new ImageIcon("Resources\\avatar_default.png");
        lb_avatar.setLocation(150, 5);
        lb_avatar.setIcon(icon);

        lbl_countNoti.setText(String.valueOf(result.inviteList.length) + " NEWs");

        Global._currentAccount = result.user;
        Global._currentListDocument = returnListDocument;
        Global._currentListInvite = new ArrayList<Invite>(Arrays.asList(result.inviteList));

        this.myListConllaboration = new MyListCollaboration(Global._currentListInvite, panel_MyDocs);

       // frame.pack();
        // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // frame.setLocation(dim.width - frame.getSize().width / 2, dim.height - frame.getSize().height / 2);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(this.myListConllaboration);
        frame.setSize(new Dimension(900, 500));
        frame.setResizable(false);
        
        ReceivePushNotificationThread receivePushNotificationThread = new ReceivePushNotificationThread(this, panel_MyDocs, Global._currentAccount.getUsername());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lb_avatar = new javax.swing.JLabel();
        txt_UserName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        btn_logout = new javax.swing.JButton();
        btn_logout1 = new javax.swing.JButton();
        btn_logout2 = new javax.swing.JButton();
        btn_logout3 = new javax.swing.JButton();
        btn_createDoc = new javax.swing.JButton();
        btn_Collaboration = new javax.swing.JButton();
        lbl_countNoti = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panel_MyDocs = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));

        lb_avatar.setText("23");

        txt_UserName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_UserName.setForeground(new java.awt.Color(255, 255, 255));
        txt_UserName.setText("Nguyễn Long Uy");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Uynguyen1212505", "VinLe1212513", "ThanhTung499", "MyUsername", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        btn_logout.setText("Find people");

        btn_logout1.setText("Log out");
        btn_logout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logout1ActionPerformed(evt);
            }
        });

        btn_logout2.setText("Change password");
        btn_logout2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logout2ActionPerformed(evt);
            }
        });

        btn_logout3.setText("Activity log");
        btn_logout3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logout3ActionPerformed(evt);
            }
        });

        btn_createDoc.setText("Create Doc");
        btn_createDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createDocActionPerformed(evt);
            }
        });

        btn_Collaboration.setText("Collaboration");
        btn_Collaboration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CollaborationActionPerformed(evt);
            }
        });

        lbl_countNoti.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_countNoti.setForeground(new java.awt.Color(255, 255, 255));
        lbl_countNoti.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(txt_UserName))
                                    .addComponent(lb_avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_logout1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_logout3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_logout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_logout2)
                                    .addComponent(btn_Collaboration))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_countNoti)
                                    .addComponent(btn_createDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_UserName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_logout2)
                    .addComponent(btn_createDoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Collaboration)
                    .addComponent(lbl_countNoti))
                .addGap(18, 18, 18)
                .addComponent(btn_logout)
                .addGap(18, 18, 18)
                .addComponent(btn_logout3)
                .addGap(18, 18, 18)
                .addComponent(btn_logout1)
                .addGap(28, 28, 28))
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel_MyDocs.setBackground(new java.awt.Color(255, 204, 102));
        panel_MyDocs.setMinimumSize(new java.awt.Dimension(400, 400));
        panel_MyDocs.setPreferredSize(new java.awt.Dimension(400, 400));
        panel_MyDocs.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jScrollPane2.setViewportView(panel_MyDocs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btn_createDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createDocActionPerformed
        // TODO add your handling code here:
        String title = JOptionPane.showInputDialog("Please input document title: ");

        if (title != null) {
           
            CreateDocTask createDoc = new CreateDocTask(Global._currentAccount.getID(),Global._currentAccount.getUsername() , title, this);
            Global.HideLoading();
            createDoc.execute();
            Global.ShowLoading(this);
        }

    }//GEN-LAST:event_btn_createDocActionPerformed
    public static MyListCollaboration myListConllaboration;
    
    private void btn_CollaborationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CollaborationActionPerformed
        // TODO add your handling code here:

        frame.setVisible(true);
      
    }//GEN-LAST:event_btn_CollaborationActionPerformed

    private void btn_logout2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logout2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_logout2ActionPerformed

    private void btn_logout3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logout3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_logout3ActionPerformed

    private void btn_logout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logout1ActionPerformed
        Bus.Business.Logout(Global._currentAccount.getUsername());
        this.setVisible(false);
        this.dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_btn_logout1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MyDocsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MyDocsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MyDocsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MyDocsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MyDocsForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Collaboration;
    private javax.swing.JButton btn_createDoc;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_logout1;
    private javax.swing.JButton btn_logout2;
    private javax.swing.JButton btn_logout3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_avatar;
    private javax.swing.JLabel lbl_countNoti;
    private javax.swing.JPanel panel_MyDocs;
    private javax.swing.JLabel txt_UserName;
    // End of variables declaration//GEN-END:variables
}
