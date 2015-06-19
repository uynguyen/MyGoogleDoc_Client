/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import GUI.Main;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class OpenDocTask extends SwingWorker<Object, Object>{
    
    String docID;
    int result;
    JFrame myDocForm;
    
    public OpenDocTask(String docID, JFrame myDocForm){
        this.docID = docID;
        this.myDocForm = myDocForm;
    }

    @Override
    protected Object doInBackground() throws Exception {
        result = Bus.Business.OpenDoc(docID);
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
                } else {
                  //  JOptionPane.showMessageDialog(myDocForm, "Open success. Port " + result);
                    myDocForm.setVisible(false);
                    myDocForm.dispose();
                    new Main(result).setVisible(true);
                }
                
            }
        });
    }
    
}
