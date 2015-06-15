/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import java.util.Date;
import javax.swing.ImageIcon;

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
    private int _IDPartners;
    private String _Code;
    /**
     * Creates new form MyDocument
     */
    public MyDocument(int id,String name, String path, Date date, int idOwner, int idPartners, String code) {
       initComponents();
       
       this._ID = id;
       this._Path = name;
       this._Path = path;
       this._DateCreate = date;
       this._IDOwner = idOwner;
       this._IDPartners = idPartners;
       this._Code = code;
       txt_Name.setText(name);
       txt_CreatedDate.setText(date.toLocaleString());
       ImageIcon icon = new ImageIcon("src\\Resources\\Icon-Document.png"); 
     
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
        btn_Open = new javax.swing.JButton();
        btn_Details = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        image = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setPreferredSize(new java.awt.Dimension(200, 220));

        txt_Name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Name.setText("Simple text");

        txt_CreatedDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_CreatedDate.setText("19/09/1994");

        btn_Open.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        btn_Open.setText("Open");

        btn_Details.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        btn_Details.setText("Details");

        btn_Delete.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        btn_Delete.setText("Delete");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Open)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Details)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Delete))
                            .addComponent(txt_CreatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_CreatedDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Open)
                    .addComponent(btn_Details)
                    .addComponent(btn_Delete))
                .addGap(0, 10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Details;
    private javax.swing.JButton btn_Open;
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

    /**
     * @return the _IDPartners
     */
    public int getIDPartners() {
        return _IDPartners;
    }

    /**
     * @param _IDPartners the _IDPartners to set
     */
    public void setIDPartners(int _IDPartners) {
        this._IDPartners = _IDPartners;
    }
}
