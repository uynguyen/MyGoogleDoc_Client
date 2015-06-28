/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.Global;
import CommunicatePackage.InvitePackage;
import CustomComponents.MyDocument;
import Pojo.Document;
import Pojo.EnumUserAction;
import SwingWorkers.ReplyInviteTask;
import com.sun.corba.se.impl.io.IIOPOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Thanh Tung
 */
public class ReceivePushNotificationThread implements Runnable {

    Thread t;
    JFrame myDocForm;
    JPanel panel_Docs;
    InvitePackage invitePackage;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    String username;

    public ReceivePushNotificationThread(JFrame myDocForm, JPanel panel, String username) {
        this.myDocForm = myDocForm;
        this.panel_Docs = panel;
        this.username = username;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            Socket server = new Socket(Global._IPServer, Global._DocsPort);

            oos = new ObjectOutputStream(server.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(server.getInputStream());

            //send signal
            oos.writeInt(EnumUserAction.REGISTER_PUSH_SERVICE.getValue());
            oos.flush();
            
            oos.writeUTF(username);
            oos.flush();

            while (true) {
                invitePackage = (InvitePackage) ois.readObject();

                if (invitePackage.quit == true) {
                    break;
                }

                SwingUtilities.invokeLater(() -> {
                    int rs = JOptionPane.showConfirmDialog(myDocForm, invitePackage.senderName
                            + " want to invite you to working with him/her on document " + invitePackage.document.getName());
                    
                    Document doc = invitePackage.document;
                    try {
                        if (rs > 0) {
                            oos.writeBoolean(true);
                            oos.flush();
                            panel_Docs.add(new MyDocument(doc.getID(), doc.getName(), doc.getPath(),
                                    doc.getDateCreate(), doc.getIDOwner(), doc.getCode()));
                            panel_Docs.revalidate();
                            panel_Docs.repaint();
                        } else {
                            oos.writeBoolean(false);
                            oos.flush();
                        }
                        
                    }catch (IOException ex) {
                        Logger.getLogger(ReceivePushNotificationThread.class.getName()).log(Level.SEVERE, null, ex);                        
                    }
                });
            }

            ois.close();
            oos.flush();
            oos.close();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ReceivePushNotificationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
