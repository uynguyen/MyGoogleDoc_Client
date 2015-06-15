/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CommunicatePackage.LoginReturnPackage;
import CustomComponents.MyDocument;
<<<<<<< HEAD
import java.awt.Dimension;
=======
import SwingWorkers.CreateDocTask;
>>>>>>> ff616c1975eb4e4654f787f7f17b513e8f11379d
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.ImageIcon;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * @author UyNguyen.ITUS
 */
public class MyDocsForm extends javax.swing.JFrame {

    LoginReturnPackage _loginReturnPackage = null;
    
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
        int temp1 = lstDocSize % 5;
        int temp2 = lstDocSize / 5;
        
        if(lstDocSize % 5 != 0){
            temp2++;
        }
        
<<<<<<< HEAD
        
       // JScrollPane jScrollPane = new JScrollPane();
       // jScrollPane.setLocation(300, 5);
       // jScrollPane.setPreferredSize(new Dimension(180,3 * 220));
        
      //  JPanel panel_MyDocs = new JPanel();
      //  panel_MyDocs.setLocation(300, 5);
      //  panel_MyDocs.setSize(5 * 180 , temp2 * 220); //180-220
        
        
       
        
=======
        JPanel panel_MyDocs = new JPanel();
        panel_MyDocs.setLocation(300, 5);
        panel_MyDocs.setSize(5 * 150 , temp2 * 220); //180-220
>>>>>>> ff616c1975eb4e4654f787f7f17b513e8f11379d
        GridLayout grid = new GridLayout();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setColumns(5);
        grid.setRows(temp2);
        panel_MyDocs.setLayout(grid);
        for (int i = 0; i < lstDocSize; i++) {
            String name = result.documentList[i].getName();
            Date date = result.documentList[i].getDateCreate();
            String path = result.documentList[i].getPath();
            int idOwner = result.documentList[i].getIDOwner();
            int idPartners = result.documentList[i].getIDPartners();
            int id = result.documentList[i].getID();
            String code = result.documentList[i].getCode();
            panel_MyDocs.add(new MyDocument(id,name,path,date,idOwner,idPartners,code));
           // jScrollPane.add(panel_MyDocs);
        }
        //jScrollPane.add(panel_MyDocs);
        //this.add(jScrollPane);
        ImageIcon icon = new ImageIcon("src\\Resources\\avatar_default.png"); 
         lb_avatar.setLocation(150,5);
       lb_avatar.setIcon(icon); 
      
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
        jScrollPane2 = new javax.swing.JScrollPane();
        panel_MyDocs = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lb_avatar.setText("23");

        txt_UserName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_UserName.setText("Nguyễn Long Uy");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        btn_logout.setText("Find people");

        btn_logout1.setText("Log out");

        btn_logout2.setText("Change password");

        btn_logout3.setText("Activity log");

        btn_createDoc.setText("Create Doc");
        btn_createDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createDocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_logout2)
                        .addGap(18, 18, 18)
                        .addComponent(btn_createDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_logout1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_logout)
                                    .addComponent(btn_logout3)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(txt_UserName))
                                    .addComponent(lb_avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGap(18, 18, 18)
                .addComponent(btn_logout)
                .addGap(18, 18, 18)
                .addComponent(btn_logout3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_logout1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout panel_MyDocsLayout = new javax.swing.GroupLayout(panel_MyDocs);
        panel_MyDocs.setLayout(panel_MyDocsLayout);
        panel_MyDocsLayout.setHorizontalGroup(
            panel_MyDocsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2347, Short.MAX_VALUE)
        );
        panel_MyDocsLayout.setVerticalGroup(
            panel_MyDocsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(panel_MyDocs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2)
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
        
        if(title != null){
            CreateDocTask createDoc = new CreateDocTask(_loginReturnPackage.user.getID(), title, this);
            createDoc.execute();
        }
        
    }//GEN-LAST:event_btn_createDocActionPerformed

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
    private javax.swing.JPanel panel_MyDocs;
    private javax.swing.JLabel txt_UserName;
    // End of variables declaration//GEN-END:variables
}
