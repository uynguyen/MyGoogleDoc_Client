/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class DeleteTask extends SwingWorker {

    String docCode;
    boolean result;
    JPanel myDocument;

    public DeleteTask(String docCode, JPanel myDocument) {
        this.docCode = docCode;
        this.myDocument = myDocument;
    }

    @Override
    protected Object doInBackground() throws Exception {
        result = Bus.Business.DeleteDocument(docCode);
        return result;
    }

    @Override
    protected void done() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                if (result) {
                }
            }        
        });
    }

}
