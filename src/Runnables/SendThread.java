/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.Global;
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
    StyledTextEditor styledTextEditor;
    
    public SendThread(ObjectOutputStream oos, StyledTextEditor ste){
        this.objectOutputStream = oos;
        this.styledTextEditor = ste;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            //sending client information
            objectOutputStream.writeObject(Global._currentAccount);
            objectOutputStream.flush();
            
            
        } catch (IOException ex) {
            Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
