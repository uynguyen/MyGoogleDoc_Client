/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Actions.ActionChat;
import Actions.ActionQuit;
import Bus.Global;
import CustomComponents.ActionChangeEvent;
import Runnables.ReceiveThread;
import Runnables.SendThread;
import SwingWorkers.LoginTask;

import java.awt.event.KeyEvent;

import SwingWorkers.ShareTask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Vin
 */
public class Main extends javax.swing.JFrame {

    String docCode;

    private Socket Server;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    private int WorkingServerPort;
    private Object e;
    
    ReceiveThread receiveThread;

    /**
     * Creates new form Main
     *
     * @param workingServerPort
     */
    public Main() {
        initComponents();
    }

    public Main(int workingServerPort, String docCode) {
        initComponents();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.docCode = docCode;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        styledTextEditor1.addDocumentChangeListener(this::performSendActionChangeEvent);

        
      
        
        
        initChatRoom();
//        String result = "";
//
//        try {
//            String path = "C:\\Users\\UyNguyen.ITUS\\Desktop\\uy.html";
//
//            FileReader fr = new FileReader(path);
//            BufferedReader br = new BufferedReader(fr);
//
//            String aline;
//            while ((aline = br.readLine()) != null) {
//                result += aline;
//            }
//
//            br.close();
//            
//            
//            styledTextEditor1.setHTMLString(result);
//
//        } catch (Exception e) {
//
//        }
        //Thread test = new SuperServerThread(styledTextEditor1.getJTextPane());
        //  test.start();
        WorkingServerPort = workingServerPort;
        try {
            //Connect to workingServer            
            Server = new Socket(Global._IPServer, workingServerPort);
            objectOutputStream = new ObjectOutputStream(Server.getOutputStream());
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(Server.getInputStream());

            //Sending client info
            objectOutputStream.writeObject(Global._currentAccount);
            objectOutputStream.flush();

            //Create receive thread
            receiveThread = new ReceiveThread(objectInputStream, styledTextEditor1, jTextArea_Room, Global._currentAccount.getUsername());

            SendThread sendThread = new SendThread(objectOutputStream, null);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    // TODO: Gửi action tới server
    public final void performSendActionChangeEvent(ActionChangeEvent evt) {
        Actions.Action action = evt.getAction();

//        if (Global.flag == false) {
//            Global.flag = true;
//            return;
//        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Create send thread
        SendThread sendThread = new SendThread(objectOutputStream, action);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Share = new javax.swing.JButton();
        styledTextEditor1 = new CustomComponents.StyledTextEditor();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Room = new javax.swing.JTextArea();
        jTextField_Input = new javax.swing.JTextField();
        btn_Send = new javax.swing.JButton();
        lbl_Docname1 = new javax.swing.JLabel();
        lbl_Docname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(600, 59));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btn_Share.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Share.setForeground(new java.awt.Color(255, 0, 0));
        btn_Share.setText("Share this document");
        btn_Share.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ShareActionPerformed(evt);
            }
        });

        styledTextEditor1.setBackground(new java.awt.Color(102, 204, 255));
        styledTextEditor1.setBorder(null);

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));

        jTextArea_Room.setColumns(20);
        jTextArea_Room.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Room);

        jTextField_Input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_InputKeyPressed(evt);
            }
        });

        btn_Send.setText("Send");
        btn_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SendActionPerformed(evt);
            }
        });

        lbl_Docname1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lbl_Docname1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Docname1.setText("Chat room:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Docname1)
                            .addComponent(jTextField_Input, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(btn_Send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Docname1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_Input)
                    .addComponent(btn_Send, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap())
        );

        lbl_Docname.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lbl_Docname.setForeground(new java.awt.Color(0, 153, 255));
        lbl_Docname.setText("Document: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(styledTextEditor1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_Docname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Share)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Share)
                    .addComponent(lbl_Docname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(styledTextEditor1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ShareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ShareActionPerformed

        String username = JOptionPane.showInputDialog("Input username: ");

        if (username != null) {
            ShareTask shareTask = new ShareTask(Global._currentAccount.getID(), docCode, username, this);
            shareTask.execute();
           
        }


    }//GEN-LAST:event_btn_ShareActionPerformed

    private void jTextField_InputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_InputKeyPressed
        // TODO add your handling code here:

//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            sendMessage(jTextField_Input.getText());
//
//        }
    }//GEN-LAST:event_jTextField_InputKeyPressed

    private void btn_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SendActionPerformed
        // TODO add your handling code here:
        sendMessage(jTextField_Input.getText());
    }//GEN-LAST:event_btn_SendActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
     //   receiveThread.stopThread();
    }//GEN-LAST:event_formWindowClosed

    private void closeConnectionDocument()
    {
        Global._myQueue.enqueue(new ActionQuit(null, Global._currentAccount.getUsername()));
        try {
            Thread.sleep(1000);
            objectOutputStream.flush();
            objectOutputStream.close();
            LoginTask loginTask = new LoginTask(Global._currentAccount.getUsername(), Global.password, this, null);            
            loginTask.execute();
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeConnectionDocument();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        //Global.HideLoading();
      
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
            //  }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Send;
    private javax.swing.JButton btn_Share;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Room;
    private javax.swing.JTextField jTextField_Input;
    private javax.swing.JLabel lbl_Docname;
    private javax.swing.JLabel lbl_Docname1;
    private CustomComponents.StyledTextEditor styledTextEditor1;
    // End of variables declaration//GEN-END:variables

    private void initChatRoom() {
        this.getRootPane().setDefaultButton(btn_Send);
        jTextArea_Room.setEditable(false);
        jTextArea_Room.setLineWrap(true);
    }

    private void sendMessage(String text) {

        ActionChat actionChat = new ActionChat(null);
        actionChat.setUsername(Global._currentAccount.getUsername());
        actionChat.setContent(text);

        synchronized (Global._myQueue) {
            Global._myQueue.enqueue(actionChat);
        }
        //SendThread sendThread = new SendThread(objectOutputStream, actionChat);

        UpdateChatRoom(text);

    }

    private void UpdateChatRoom(String text) {

        jTextArea_Room.append("You: " + text.toString());
        jTextArea_Room.append("\n");
        jTextField_Input.selectAll();
        jTextField_Input.requestFocus();
        jTextField_Input.setText("");
    }
}
