/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import CustomComponents.CollaborationItem;
import CustomComponents.MyDocument;
import GUI.MyListCollaboration;
import Pojo.Document;
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
    Document result;
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
                if(result.getID() >= 0){
                    //item.getParent().remove(item);
                  Container con =  item.getParent();
                  con.remove(item);
                  con.revalidate();
                  con.repaint();
                  
                  if(reply){
                      JPanel panel_MyDocs = ((MyListCollaboration)item.getParent()).getPanel_MyDocs();
                      panel_MyDocs.add(new MyDocument(result.getID(), result.getName(), result.getPath(),
                              result.getDateCreate(), result.getIDOwner(), result.getCode()));
                      panel_MyDocs.revalidate();
                      panel_MyDocs.repaint();
                  }
                  
                   
                } else {
                    JOptionPane.showMessageDialog(item.getRootPane(), "Fail to reply invitation");
                   
                }
            }
        });
    }
    
    
    
}
