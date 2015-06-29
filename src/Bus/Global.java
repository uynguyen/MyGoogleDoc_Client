/*
* To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import Actions.Action;
import CustomComponents.ProcessBar;
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
    
    private static final JDialog Loading = new ProcessBar();
    
    public static void ShowLoading(JFrame parent){
        Loading.setLocationRelativeTo(parent);
        Loading.setVisible(true);
    }
    
    public static void HideLoading(){
 
        Loading.setVisible(false);
    }
}
