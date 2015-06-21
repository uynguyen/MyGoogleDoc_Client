/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import CustomComponents.CollaborationItem;
import java.awt.Container;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Thanh Tung
 */
public class ReplyInviteTask extends SwingWorker<Object, Object>{
    boolean reply;
    int idInvite;
    String docCode;
    int idClient;
    boolean result;
    CollaborationItem item;
    
    public ReplyInviteTask(boolean reply, int idInvite, String docCode, int idClient, CollaborationItem item){
        this.reply = reply;
        this.idInvite = idInvite;
        this.docCode = docCode;
        this.idClient = idClient;
        this.item = item;
    }

    @Override
    protected Object doInBackground() throws Exception {
        result = Bus.Business.ReplyInvite(reply, idInvite, docCode, idClient);
        return result;
    }

    @Override
    protected void done() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                if(result){
                    //item.getParent().remove(item);
                  Container con =  item.getParent();
                  con.remove(item);
                  con.revalidate();
                  con.repaint();
                  
                   
                } else {
                    JOptionPane.showMessageDialog(item.getRootPane(), "Fail to reply invitation");
                   
                }
            }
        });
    }
    
    
    
}
