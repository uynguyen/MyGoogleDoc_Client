/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class OpenDocTask extends SwingWorker<Object, Object>{
    
    String docID;
    int result;
    
    public OpenDocTask(String docID){
        this.docID = docID;
    }

    @Override
    protected Object doInBackground() throws Exception {
        result = Bus.Business.OpenDoc(docID);
        return result;
    }

    @Override
    protected void done() {
        super.done(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
