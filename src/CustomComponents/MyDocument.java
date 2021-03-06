/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import Bus.Global;
import SwingWorkers.DeleteTask;
import SwingWorkers.LeaveTask;
import SwingWorkers.OpenDocTask;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 *
 * @author UyNguyen.ITUS
 */
public class MyDocument extends javax.swing.JPanel {

    private int _ID;
    private String _Name;
    private String _Path;
    private Date _DateCreate;
    private int _IDOwner;

    private String _Code;

    /**
     * Creates new form MyDocument
     */
    public MyDocument(int id, String name, String path, Date date, int idOwner, String code) {
        initComponents();
        initControl();
        this._ID = id;
        this._Name = name;
        this._Path = path;
        this._DateCreate = date;
        this._IDOwner = idOwner;

        this._Code = code;
        txt_Name.setText(name);
        txt_CreatedDate.setText(date.toLocaleString());
        ImageIcon icon = new ImageIcon("Resources\\Icon-Document.png");

        image.setIcon(icon);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txt_Name = new javax.swing.JLabel();
        txt_CreatedDate = new javax.swing.JLabel();
        btn_Delete = new javax.swing.JButton();
        image = new javax.swing.JLabel();
        btn_Leave = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.MatteBorder(null));
        setMaximumSize(new java.awt.Dimension(200, 220));
        setMinimumSize(new java.awt.Dimension(200, 220));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(200, 220));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        txt_Name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Name.setText("Simple text");

        txt_CreatedDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_CreatedDate.setText("19/09/1994");

        btn_Delete.setBackground(new java.awt.Color(255, 255, 255));
        btn_Delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Delete.setText("Delete");
        btn_Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DeleteMouseExited(evt);
            }
        });
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imageMouseExited(evt);
            }
        });

        btn_Leave.setBackground(new java.awt.Color(255, 255, 255));
        btn_Leave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Leave.setText("Leave");
        btn_Leave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_LeaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_LeaveMouseExited(evt);
            }
        });
        btn_Leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LeaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_Leave, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btn_Delete))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_CreatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_CreatedDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Leave)
                    .addComponent(btn_Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:

        //  System.out.println(this._Name +  "Hover");
        this.setBorder(BorderFactory.createMatteBorder(
                2, 2, 2, 2, Color.GREEN));

        txt_Name.setFont(new Font("Serif", Font.BOLD, 14));
        txt_CreatedDate.setFont(new Font("Serif", Font.BOLD, 14));

        if (isOwner(Global._currentAccount.getID())) {
            btn_Delete.setVisible(true);
            btn_Leave.setVisible(false);
        } else {
            btn_Delete.setVisible(false);
            btn_Leave.setVisible(true);
        }

     // System.out.print(this._IDOwner);

    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
        initControl();
    }//GEN-LAST:event_formMouseExited

    private void btn_DeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DeleteMouseEntered
        // TODO add your handling code here:

        formMouseEntered(evt);

    }//GEN-LAST:event_btn_DeleteMouseEntered

    private void btn_DeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DeleteMouseExited
        // TODO add your handling code here:

        formMouseExited(evt);
    }//GEN-LAST:event_btn_DeleteMouseExited


    private void imageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageMouseEntered
        // TODO add your handling code here:

        image.setToolTipText("<html>" + this._Name + "<br>" + this._DateCreate.toLocaleString() + "</html>");
        formMouseEntered(evt);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }//GEN-LAST:event_imageMouseEntered

    private void imageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageMouseExited
        // TODO add your handling code here:
        formMouseExited(evt);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_imageMouseExited

    private void imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageMouseClicked
        // TODO add your handling code here:
        image.setEnabled(false); //Khỏi click nhiều lần
        OpenDocTask openDocTask = new OpenDocTask(_Code, Global._currentAccount.getUsername(), (JFrame) SwingUtilities.getWindowAncestor(this), image);
        openDocTask.execute();
     

    }//GEN-LAST:event_imageMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void btn_LeaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LeaveMouseEntered
        // TODO add your handling code here:
        formMouseEntered(evt);
    }//GEN-LAST:event_btn_LeaveMouseEntered

    private void btn_LeaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LeaveMouseExited
        // TODO add your handling code here:
        formMouseExited(evt);
    }//GEN-LAST:event_btn_LeaveMouseExited

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        int result = JOptionPane.showConfirmDialog(this.getRootPane(), "Are you sure you want to delete this document?");
        if (result == 0) {
            DeleteTask deleteTask = new DeleteTask(_Code, this);
            deleteTask.execute();
        }

    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_LeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LeaveActionPerformed
        int result = JOptionPane.showConfirmDialog(this.getRootPane(), "Are you sure you want to leave this document?");
        if (result == 0) {
            LeaveTask leaveTask = new LeaveTask(_Code, Global._currentAccount.getID(), this);
            leaveTask.execute();
        }
    }//GEN-LAST:event_btn_LeaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Leave;
    private javax.swing.JLabel image;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel txt_CreatedDate;
    private javax.swing.JLabel txt_Name;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the _ID
     */
    public int getID() {
        return _ID;
    }

    /**
     * @param _ID the _ID to set
     */
    public void setID(int _ID) {
        this._ID = _ID;
    }

    /**
     * @return the _Name
     */
    public String getName() {
        return _Name;
    }

    /**
     * @param _Name the _Name to set
     */
    public void setName(String _Name) {
        this._Name = _Name;
    }

    /**
     * @return the _Path
     */
    public String getPath() {
        return _Path;
    }

    /**
     * @param _Path the _Path to set
     */
    public void setPath(String _Path) {
        this._Path = _Path;
    }

    /**
     * @return the _DateCreate
     */
    public Date getDateCreate() {
        return _DateCreate;
    }

    /**
     * @param _DateCreate the _DateCreate to set
     */
    public void setDateCreate(Date _DateCreate) {
        this._DateCreate = _DateCreate;
    }

    /**
     * @return the _IDOwner
     */
    public int getIDOwner() {
        return _IDOwner;
    }

    /**
     * @param _IDOwner the _IDOwner to set
     */
    public void setIDOwner(int _IDOwner) {
        this._IDOwner = _IDOwner;
    }

    private void initControl() {
        this.setBorder(BorderFactory.createMatteBorder(
                2, 2, 2, 2, Color.WHITE));
        txt_Name.setFont(new Font("Serif", Font.PLAIN, 14));
        txt_CreatedDate.setFont(new Font("Serif", Font.PLAIN, 14));

        btn_Delete.setVisible(false);
        btn_Leave.setVisible(false);
    }

    public boolean isOwner(int idOwner) {
        return this._IDOwner == idOwner;
    }
}
