/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import GUI.RegisterForm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
            
            InputStream inputStream = server.getInputStream();
            OutputStream outputStream = server.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            
            //Send user register info
            bufferedWriter.write("register");
            bufferedWriter.newLine();
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.write(password);
            bufferedWriter.newLine();
            bufferedWriter.write(email);
            
            //Receive register result
            String result = bufferedReader.readLine();
            if(result.equalsIgnoreCase("true")){
                return true;
            }
            
            bufferedWriter.close();
            bufferedReader.close();
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            
        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return false;
    }
}
