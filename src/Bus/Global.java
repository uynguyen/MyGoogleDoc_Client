/*
* To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import Actions.Action;

import GUI.MyDocsForm;
import Pojo.Account;
import Pojo.Document;
import Pojo.Invite;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import sun.misc.Queue;






/**
 *
 * @author UyNguyen.ITUS
 */
public  class Global {
    
    public static Account _currentAccount;
    public static ArrayList<Document> _currentListDocument;
    public static ArrayList<Invite> _currentListInvite;
    public static String password = "";
   // public static boolean flag = true;
    public static String _IPServer = "localhost";
    public static int _ServerPort = 51399;
    public static int _DocsPort = 13599;
    
    public static Queue<Action> _myQueue = new Queue<>();
    

    public static MyDocsForm _MyDocForm;
    
 
}
