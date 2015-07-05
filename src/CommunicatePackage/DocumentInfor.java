/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommunicatePackage;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author UyNguyen.ITUS
 */
public class DocumentInfor implements Serializable{
    private String _docName;
    private Date _lastAccess;
    private String[] _members;
    private String _owner;
    private Date _dateCreate;

    public DocumentInfor(String _docName, Date _lastAccess, String[] _members, String _owner, Date _dateCreate) {
        this._docName = _docName;
        this._lastAccess = _lastAccess;
        this._members = _members;
        this._owner = _owner;
        this._dateCreate = _dateCreate;
    }

    
    
    
    
    /**
     * @return the _docName
     */
    public String getDocName() {
        return _docName;
    }

    /**
     * @param _docName the _docName to set
     */
    public void setDocName(String _docName) {
        this._docName = _docName;
    }

    /**
     * @return the _lastAccess
     */
    public Date getLastAccess() {
        return _lastAccess;
    }

    /**
     * @param _lastAccess the _lastAccess to set
     */
    public void setLastAccess(Date _lastAccess) {
        this._lastAccess = _lastAccess;
    }

    /**
     * @return the _members
     */
    public String[] getMembers() {
        return _members;
    }

    /**
     * @param _members the _members to set
     */
    public void setMembers(String[] _members) {
        this._members = _members;
    }

    /**
     * @return the _owner
     */
    public String getOwner() {
        return _owner;
    }

    /**
     * @param _owner the _owner to set
     */
    public void setOwner(String _owner) {
        this._owner = _owner;
    }

    /**
     * @return the _dateCreate
     */
    public Date getDateCreate() {
        return _dateCreate;
    }

    /**
     * @param _dateCreate the _dateCreate to set
     */
    public void setDateCreate(Date _dateCreate) {
        this._dateCreate = _dateCreate;
    }
    
    
    
    
    
}
