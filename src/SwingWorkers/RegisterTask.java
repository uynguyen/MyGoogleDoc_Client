/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Business;
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
    boolean result;
    
    public RegisterTask(String username, String password, String email, JLabel errorLabel){
        this.username = username;
        this.password = password;
        this.email = email;
        this.errorLabel = errorLabel;
    }    
    
    @Override
    protected  void done(){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                if(result == true){
                    errorLabel.setText("Success");
                } else {
                    errorLabel.setText("Something went wrong");
                }
                
            }
        }
        );
    }

    @Override
    protected Object doInBackground() throws Exception {
        result = Business.Register(username, password, email);
        return result;
    }
    
}
