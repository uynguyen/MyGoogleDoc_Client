/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 *
 * @author Vin
 */
public class ProcessBar extends JDialog {

    /**
     * Creates new form ProcessBar
     */
    public ProcessBar() {
        initComponents();
        
        // Setting for this component
     //  this.setAlwaysOnTop(true);
      //  this.setOpacity(0.55f);
    //   this.setUndecorated(true);
        this.setMaximumSize(new Dimension(200, 200));
        this.setMinimumSize(new Dimension(200, 200));
      //  this.setResizable(false);
      //  this.getRootPane().setOpaque(false);
        this.imageLabel.setIcon(new ImageIcon("src\\Resources\\loading.gif"));
       // this.setModal(true);
     //   this.imageLabel.setOpaque(false);
      //  this.imageLabel.getRootPane().setOpaque(false);
      //  this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        
    }

   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageLabel = new javax.swing.JLabel();

        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(200, 200));
        setMinimumSize(new java.awt.Dimension(200, 200));
        setModal(true);
        setUndecorated(true);
        setOpacity(0.7F);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageLabel.setIconTextGap(0);
        getContentPane().add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    // End of variables declaration//GEN-END:variables
}