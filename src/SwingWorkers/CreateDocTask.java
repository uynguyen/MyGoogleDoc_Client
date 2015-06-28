/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorkers;

import Bus.Business;
import CommunicatePackage.CreateDocReturnPackage;
import GUI.Main;
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
public class CreateDocTask extends SwingWorker<Object, Object> {

    int idOwner;
    String docTitle;
    CreateDocReturnPackage result;
    JFrame myDocForm;
    JDialog notiDialog;

    private JDialog CreateModalDialog(String message) {
        JDialog dialog = new JDialog(myDocForm);
        JLabel label = new JLabel(message);
        dialog.setLocationRelativeTo(null);
        dialog.add(label);
        dialog.pack();
        return dialog;
    }

    public CreateDocTask(int idOwner, String docTitle, JFrame myDocForm) {
        this.idOwner = idOwner;
        this.docTitle = docTitle;
        this.myDocForm = myDocForm;
        notiDialog = CreateModalDialog("Please wait...");
    }

    @Override
    protected Object doInBackground() throws Exception {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                notiDialog.setVisible(true);
            }
        });
        result = Business.CreateDoc(docTitle, idOwner);
        return result;
    }

    @Override
    protected void done() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                notiDialog.setVisible(false);
                notiDialog.dispose();
                if (result.port == -1) {
                    System.out.print("fail");
                    JOptionPane.showMessageDialog(myDocForm, "Fail to create document");                    
                } else {
                    myDocForm.setVisible(false);
                    myDocForm.dispose();
                    new Main(result.port,result.docCode).setVisible(true);
                }
            }
        });

    }

}
