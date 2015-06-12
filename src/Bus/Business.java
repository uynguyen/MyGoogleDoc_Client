/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import CommunicatePackage.RegisterPackage;
import GUI.RegisterForm;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UyNguyen.ITUS
 */
public class Business {
    
    public static boolean Register(String username, String password, String email){
        try {
            Socket server = new Socket("localhost",51399);
            
            System.out.print(server.getPort());
            
            
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());
            
                        
            //Send user register info
            objectOutputStream.writeBoolean(false);
            objectOutputStream.flush();
            
            RegisterPackage message = new RegisterPackage(username, password, email);
            
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            //Receive register result
            boolean result = objectInputStream.readBoolean();
            
            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();
            
            return result;
            
        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return false;
    }
    
    
}
