/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Business;
import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class CreateDocTask extends SwingWorker<Object, Object>{
    
    int idOwner;
    String docTitle;
    int result;
    
    public CreateDocTask(int idOwner, String docTitle){
        this.idOwner = idOwner;
        this.docTitle = docTitle;
    }

    @Override
    protected Object doInBackground() throws Exception {
        result = Business.CreateDoc(docTitle, idOwner);
        return result;
    }

    @Override
    protected void done() {
        if(result == -1){
            System.out.print("fail");
        } else {
            System.out.print("success");
        }
    }
       
}
