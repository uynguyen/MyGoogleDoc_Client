/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Global;
import GUI.Main;
import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class LoadMainFormTask extends SwingWorker<Void, Void>{
    
    public int workingServerPort;
    public String docCode;
    
    public LoadMainFormTask(int port, String code){
        this.workingServerPort = port;
        this.docCode = code;
    }

    @Override
    protected Void doInBackground() throws Exception {
        new Main(workingServerPort, docCode).setVisible(true);
        return null;
    }

    @Override
    protected void done() {
        
    }
    
    
    
}
