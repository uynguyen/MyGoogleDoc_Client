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
                if(loginForm == null){
                    if(result != null && result.result == true){
                        new MyDocsForm(result).setVisible(true);
                    } else{
                        new LoginForm().setVisible(true);
                    }                    
                    return;
                }
                if ( result != null && result.result == true ) {
                    // Main Form to show after the Login Form..
                    loginForm.setVisible(false);
                    
//                    Pojo.Account user = new Pojo.Account(1234, "Vin", "abc", "xyz");
//                    
//                    result = new LoginReturnPackage(true, user, new Document[0]);
                    new MyDocsForm(result).setVisible(true);
                                    
                } else {                
                    btn_login.setEnabled(true);
                    JOptionPane.showMessageDialog(loginForm, "Username or password not correct", "Login Error", 0);
                  
                }
                
            }
        });
        Global.HideLoading();
    }

}
