/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import CustomComponents.StyledTextEditor;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhTung
 */
public class SendThread implements Runnable{
    
    Thread t;
    ObjectOutputStream objectOutputStream;
    Actions.Action action;
    
    public SendThread(ObjectOutputStream oos, Actions.Action _action){
        this.objectOutputStream = oos;
        this.action = _action;
        t = new Thread(this);
        t.start();
       
    }

    @Override
    public void run() {
        System.err.println("Sending...");
        try {
            objectOutputStream.writeObject(this.action);
        } catch (IOException ex) {
            Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.err.println("Sended!");
    }
    
}
