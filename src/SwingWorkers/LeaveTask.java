/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Global;
import CustomComponents.MyDocument;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class LeaveTask extends SwingWorker{
    String docCode;
    int clientID;
    JPanel myDocument;
    boolean result;

    public LeaveTask(String _Code, int clientID, JPanel myDocument) {
        this.docCode = _Code;
        this.clientID = clientID;
        this.myDocument = myDocument;
    }
    

    @Override
    protected Object doInBackground() throws Exception {
        result = Bus.Business.LeaveDocument(clientID, docCode);
        return result;
    }

    @Override
    protected void done() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                if(result){
                    
                }
            }
        });
    }
    
    
}
