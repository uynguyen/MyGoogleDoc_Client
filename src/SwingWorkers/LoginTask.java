/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Business;
import Bus.Global;
import CommunicatePackage.LoginReturnPackage;
import GUI.LoginForm;
import GUI.MyDocsForm;
import javax.swing.JButton;
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
    JButton btn_login;

    public LoginTask(String username, String password, JFrame loginForm, JButton btnlogin) {
        this.username = username;
        this.password = password;
        this.loginForm = loginForm;
        this.btn_login = btnlogin;
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
                if (btn_login == null) {
                    if (result != null && result.result == true) {
                        loginForm.setVisible(false);
                        loginForm.dispose();

                        new MyDocsForm(result).setVisible(true);;

                    } else {
                        loginForm.setVisible(false);
                        loginForm.dispose();
                        new LoginForm().setVisible(true);
                    }
                    return;
                }
                if (result != null && result.result == true) {
                    // Main Form to show after the Login Form..
                    loginForm.setVisible(false);
                    loginForm.dispose();
                    Global.password = password;

                    new MyDocsForm(result).setVisible(true);

                } else {
                    btn_login.setEnabled(true);
                    JOptionPane.showMessageDialog(loginForm, "Username or password not correct", "Login Error", 0);

                }
                
            }
        });
       
    }

}
