/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Actions.Action;
import Actions.ActionQuit;
import Bus.Global;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhTung
 */
public class SendThread implements Runnable {

    Thread t;
    ObjectOutputStream objectOutputStream;
    Actions.Action action;

    public SendThread(ObjectOutputStream oos, Actions.Action action) {
        this.objectOutputStream = oos;
        this.action = action;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Action ac = null;
                while (!Global._myQueue.isEmpty()) {
                    synchronized (Global._myQueue) {
                        
                        synchronized (objectOutputStream) {
                            ac = Global._myQueue.dequeue();
                            if(ac instanceof ActionQuit){
                                break;
                            }
                            objectOutputStream.writeObject(ac);
                            objectOutputStream.flush();

                            
                        }

                    }
                }
                if(ac instanceof ActionQuit){
                    break;
                }
            }
            
            objectOutputStream.flush();

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
