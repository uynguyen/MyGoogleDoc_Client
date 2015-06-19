/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import CustomComponents.StyledTextEditor;
import java.io.ObjectOutputStream;

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
        
    }
    
}
