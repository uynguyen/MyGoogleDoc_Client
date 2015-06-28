/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Business;
import Bus.Global;
import GUI.LoginForm;
import javax.swing.JButton;
import javax.swing.JFrame;
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
    JButton btn_Register;
    JButton btn_Back;
    JFrame registerForm;
    boolean result;
    
    public RegisterTask(String username, String password, String email, JLabel errorLabel,
            JButton btn_Register, JButton btn_Back, JFrame registerForm){
        this.username = username;
        this.password = password;
        this.email = email;
        this.errorLabel = errorLabel;
        this.btn_Register = btn_Register;
        this.btn_Back = btn_Back;
        this.registerForm = registerForm;
    }    
    
    @Override
    protected  void done(){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                if(result == true){
                    errorLabel.setText("Success");
                    registerForm.setVisible(false);
                    registerForm.dispose();
                    new LoginForm().setVisible(true);
                } else {
                    errorLabel.setText("Something went wrong");
                    btn_Register.setEnabled(true);
                    btn_Back.setEnabled(true);
                }
                
            }
        }
        );
        Global.HideLoading();
    }

    @Override
    protected Object doInBackground() throws Exception {
        result = Business.Register(username, password, email);
        return result;
    }
    
}
