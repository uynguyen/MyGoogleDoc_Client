/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Global;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class ForgotPasswordTask extends SwingWorker<Object, Object>{
    String username;
    JFrame loginForm;
    String result;
    
    public ForgotPasswordTask(String username, JFrame loginForm){
        this.username = username;
        this.loginForm = loginForm;
    }

    @Override
    protected Object doInBackground() throws Exception {
        result = Bus.Business.ResetPassword(username);
        return result;
    }

    @Override
    protected void done() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                loginForm.setEnabled(true);
                JOptionPane.showMessageDialog(loginForm, result);
            }
        });
        Global.HideLoading();
    }
       
}
