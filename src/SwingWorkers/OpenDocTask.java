/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Global;
import GUI.Main;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class OpenDocTask extends SwingWorker<Object, Object>{
    
    String docCode;
    String username;
    int result;
    JFrame myDocForm;

    public OpenDocTask(String docCode, String username, JFrame myDocForm){
        this.docCode = docCode;
        this.myDocForm = myDocForm;
        this.username = username;
    }

    @Override
    protected Object doInBackground() throws Exception {    
        result = Bus.Business.OpenDoc(docCode, username);
        System.out.println(result);
        
        return result;
    }

   
    @Override
    protected void done() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                if(result == -1){
                    JOptionPane.showMessageDialog(myDocForm, "Fail to open Document. Please try again later");
                    Global.HideLoading();
                } else {    
                   
                   // myDocForm.setVisible(false);
                   // myDocForm.dispose();
                    new Main(result, docCode).setVisible(true); 
                    
                }               
            }
        });
       
    }
    
}
