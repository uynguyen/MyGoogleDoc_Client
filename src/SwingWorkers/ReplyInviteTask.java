/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Global;
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
    JPanel panel;
    CollaborationItem item;
    
    public ReplyInviteTask(boolean reply, int idInvite, String docCode, int idClient, JPanel panel, CollaborationItem item){
        this.reply = reply;
        this.idInvite = idInvite;
        this.docCode = docCode;
        this.idClient = idClient;
        this.panel = panel;
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
                if(result.getID() < -1){
                    JOptionPane.showMessageDialog(item.getRootPane(), "Fail to reply invitation");
                    
                } else {
                    Container con =  item.getParent();
                    con.remove(item);
                    con.revalidate();
                    con.repaint();

                    if(reply){
                        panel.add(new MyDocument(result.getID(), result.getName(), result.getPath(),
                                result.getDateCreate(), result.getIDOwner(), result.getCode()));
                        panel.revalidate();
                        panel.repaint();
                    }
                    
                }
            }
        });
        Global.HideLoading();
    }
    
    
    
}
