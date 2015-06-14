/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommunicatePackage;

import java.io.Serializable;

/**
 *
 * @author UyNguyen.ITUS
 */
public class DocumentPackage implements Serializable {
    String _title;
    int _ID_Owner;
    public DocumentPackage(String title, int id_owner){
        this._title = title;
        this._ID_Owner =id_owner;
    }
}
