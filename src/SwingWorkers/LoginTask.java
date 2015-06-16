/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Business;
import CommunicatePackage.LoginReturnPackage;
import GUI.MyDocsForm;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class LoginTask extends SwingWorker<Object, Object> {

    String username;
    String password;
    LoginReturnPackage result;
    JFrame loginForm;

    public LoginTask(String username, String password, JFrame loginForm) {
        this.username = username;
        this.password = password;
        this.loginForm = loginForm;
    }

    @Override
    protected Object doInBackground() throws Exception {
        result = Business.Login(username, password);
        return result;
    }

    @Override
    protected void done() {
        super.done();

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                if (result.result == true) {
                    // Main Form to show after the Login Form..
                    loginForm.setVisible(false);
                    new MyDocsForm(result).setVisible(true);
                                    
                } else {                       
                    JOptionPane.showMessageDialog(loginForm, "Username or password not correct", "Login Error", 0);
                  
                }
            }
        });
    }

}
