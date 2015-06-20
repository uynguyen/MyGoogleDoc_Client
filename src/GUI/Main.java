/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Bus.Global;
import CustomComponents.ActionChangeEvent;
import Runnables.ReceiveThread;
import Runnables.SendThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

/**
 *
 * @author Vin
 */
public class Main extends javax.swing.JFrame {

    private Socket Server;
    private ObjectOutputStream objectOutputStream;
    private  int WorkingServerPort;
    /**
     * Creates new form Main
     * @param workingServerPort
     */
    public Main(int workingServerPort) {
        initComponents();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        styledTextEditor1.addDocumentChangeListener(this::performSendActionChangeEvent);

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
            Server = new Socket("localhost", workingServerPort);
            objectOutputStream = new ObjectOutputStream(Server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(Server.getInputStream());
            
            //Sending client info
            objectOutputStream.writeObject(Global._currentAccount);
            objectOutputStream.flush();
            
            //Create receive thread
            ReceiveThread receiveThread = new ReceiveThread(objectInputStream, styledTextEditor1);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    // TODO: Gửi action tới server
    public final void performSendActionChangeEvent(ActionChangeEvent evt) {
        Actions.Action action = evt.getAction();
        
        if(Global.flag == false){
            Global.flag = true;
            return;
        }
        try {
            Thread.sleep(100);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 59));

        btn_Share.setText("Share");
        btn_Share.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ShareActionPerformed(evt);
            }
        });

        styledTextEditor1.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(styledTextEditor1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, Short.MAX_VALUE)
                .addGap(240, 240, 240))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Share)
                .addGap(94, 94, 94))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Share)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(styledTextEditor1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ShareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ShareActionPerformed
        // TODO add your handling code here:

        String title = JOptionPane.showInputDialog("Input username: ");

        if (title != null) {

        }


    }//GEN-LAST:event_btn_ShareActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Share;
    private CustomComponents.StyledTextEditor styledTextEditor1;
    // End of variables declaration//GEN-END:variables
}
