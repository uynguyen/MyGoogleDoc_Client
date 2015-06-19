/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import CustomComponents.StyledTextEditor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author ThanhTung
 */
public class ReceiveThread implements Runnable{
    
    Thread t;
    ObjectInputStream objectInputStream;
    StyledTextEditor styledTextEditor;
    String initDocument;
    
    public ReceiveThread(ObjectInputStream ois, StyledTextEditor ste){
        this.objectInputStream = ois;
        this.styledTextEditor = ste;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            //Receive Document
            initDocument = objectInputStream.readUTF();
            
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    styledTextEditor.setHTMLString(initDocument);
                }
            });
            
            while(true){
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
