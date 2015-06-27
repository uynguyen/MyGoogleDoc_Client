/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Actions.Action;
import Actions.ActionChat;
import Actions.ActionJoin;
import Bus.Global;
import CustomComponents.StyledTextEditor;
import java.awt.Container;
import java.awt.TextArea;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author ThanhTung
 */
public class ReceiveThread implements Runnable {

    Thread t;
    ObjectInputStream objectInputStream;
    StyledTextEditor styledTextEditor;
    JTextArea textArea_ChatRoom;
    String initDocument = "";
    Action action;

    public ReceiveThread(ObjectInputStream ois, StyledTextEditor ste, JTextArea chatRoom) {
        this.objectInputStream = ois;
        this.styledTextEditor = ste;
        this.textArea_ChatRoom = chatRoom;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            //Receive Document
            initDocument = objectInputStream.readUTF();

        } catch (IOException ex) {
            Logger.getLogger(ReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        SwingUtilities.invokeLater(() -> {
            styledTextEditor.setHTMLString(initDocument);
        });

        while (true) {
            try {
                action = (Action) objectInputStream.readObject();

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                
                continue;
            }
            SwingUtilities.invokeLater(() -> {
              //  Global.flag = false;
                if (action instanceof ActionChat) {

                    textArea_ChatRoom.append(((ActionChat) action).getUsername() + " : " + ((ActionChat) action).getContent());
                    textArea_ChatRoom.append("\n");

                } else {
                    if (action instanceof ActionJoin) {
                          
                        Container con = styledTextEditor.getParent();
                        JOptionPane.showMessageDialog(con, ((ActionJoin)action).getUsername() + " was joined to the document");
                      

                    } else {
                        styledTextEditor.ApplyActionChange(action);
                    }
                }
            });
        }

    }

}
