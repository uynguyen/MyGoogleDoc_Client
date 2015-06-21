/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class ShareTask extends SwingWorker<Object, Object>{
    
    int idClient;
    String docCode;
    String username;
    boolean result;
    JFrame container;
    
    public ShareTask (int idClient, String docCode, String username, JFrame container){
        this.idClient = idClient;
        this.docCode = docCode;
        this.username = username;
        this.container = container;
    }

    @Override
    protected Object doInBackground() throws Exception {
        result = Bus.Business.Share(idClient, docCode, username);
        return result;
    }

    @Override
    protected void done() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                if(result){
                    JOptionPane.showMessageDialog(container, "Request sended");
                } else {
                    JOptionPane.showMessageDialog(container, "Username not found or something went wrong");
                }
            }
        });
    }
    
    
    
}
