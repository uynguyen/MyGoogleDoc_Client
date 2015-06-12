/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorker;

import Bus.Business;
import GUI.RegisterForm;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author ThanhTung
 */
public class RegisterTask extends SwingWorker<Object, Object>{    

    String username;
    String password;
    String email;
    JLabel errorLabel;
    
    public RegisterTask(String username, String password, String email, JLabel errorLabel){
        this.username = username;
        this.password = password;
        this.email = email;
        this.errorLabel = errorLabel;
    }    
    
    @Override
    protected  void done(){
    }

    @Override
    protected Object doInBackground() throws Exception {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                errorLabel.setText("wating...");
            }
        }
        );
        return Business.Register(username, password, email);
    }
}
