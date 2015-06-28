/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import CommunicatePackage.CreateDocPackage;
import CommunicatePackage.CreateDocReturnPackage;
import CommunicatePackage.LeavePackage;
import CommunicatePackage.LoginPackage;
import CommunicatePackage.LoginReturnPackage;

import CommunicatePackage.RegisterPackage;
import CommunicatePackage.ReplyInvitePackage;
import CommunicatePackage.SharePackage;
import GUI.RegisterForm;
import Pojo.Document;
import Pojo.EnumUserAction;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UyNguyen.ITUS
 */
public class Business {

    public static boolean Register(String username, String password, String email) {
        try {
            Socket server = new Socket(Global._IPServer, Global._ServerPort);

            System.out.print(server.getPort());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());

            //Send user register info
            objectOutputStream.writeInt(EnumUserAction.REGISTER.getValue());
            objectOutputStream.flush();

            RegisterPackage message = new RegisterPackage(username, password, email);

            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            //Receive register result
            boolean result = objectInputStream.readBoolean();

            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();

            return result;

        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static CreateDocReturnPackage CreateDoc(String title, int ID_Owner) {
        CreateDocReturnPackage result = new CreateDocReturnPackage("", -1);
        try {
            Socket server = new Socket(Global._IPServer, Global._DocsPort);

            System.out.print(server.getPort());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());

            //Send create doc info
            objectOutputStream.writeInt(EnumUserAction.CREATEDOC.getValue());
            objectOutputStream.flush();

            CreateDocPackage message = new CreateDocPackage(ID_Owner, title);

            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            //Receive return port (-1 mean fail to create)
            result = (CreateDocReturnPackage) objectInputStream.readObject();

            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Business.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int OpenDoc(String docID, String username) {
        int result = -1;
        try {
            Socket server = new Socket(Global._IPServer, Global._DocsPort);

            System.out.print(server.getPort());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());

            //Send doc id
            objectOutputStream.writeInt(EnumUserAction.OPENDOC.getValue());
            objectOutputStream.flush();
            
            objectOutputStream.writeUTF(docID);
            objectOutputStream.flush();
            
            //Receive return port (-1 mean fail to open)            
            result = objectInputStream.readInt();

            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static Document ReplyInvite(boolean reply, int idInvite, String docCode, int idClient) {        
        Document result = new Document(-1, null, null, null, -1, null);
        try {
            Socket server = new Socket(Global._IPServer, Global._DocsPort);

            System.out.print(server.getPort());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());

            //Send signal
            objectOutputStream.writeInt(EnumUserAction.REPLYINVITE.getValue());
            objectOutputStream.flush();
            
            ReplyInvitePackage replyInvitePackage = new ReplyInvitePackage(reply, idInvite, docCode, idClient);
            
            objectOutputStream.writeObject(replyInvitePackage);
            objectOutputStream.flush();
            //Receive result
            result = (Document)objectInputStream.readObject();

            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Business.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static boolean Share(int idClient, String docCode, String username) {        
        boolean result = false;
        try {
            Socket server = new Socket(Global._IPServer, Global._DocsPort);

            System.out.print(server.getPort());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());

            //Send signal
            objectOutputStream.writeInt(EnumUserAction.SHARE.getValue());
            objectOutputStream.flush();
            
            SharePackage sharePackage = new SharePackage(idClient, docCode, username);
            
            objectOutputStream.writeObject(sharePackage);
            objectOutputStream.flush();
            //Receive result
            result = objectInputStream.readBoolean();

            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static boolean LeaveDocument(int idClient, String docCode) {        
        boolean result = false;
        try {
            Socket server = new Socket(Global._IPServer, Global._DocsPort);

            System.out.print(server.getPort());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());

            //Send signal
            objectOutputStream.writeInt(EnumUserAction.SHARE.getValue());
            objectOutputStream.flush();
            
            LeavePackage message = new LeavePackage(idClient, docCode);
            
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            //Receive result
            result = objectInputStream.readBoolean();

            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static boolean DeleteDocument(String docCode) {        
        boolean result = false;
        try {
            Socket server = new Socket(Global._IPServer, Global._DocsPort);

            System.out.print(server.getPort());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());

            //Send signal
            objectOutputStream.writeInt(EnumUserAction.SHARE.getValue());
            objectOutputStream.flush();
            
            objectOutputStream.writeUTF(docCode);
            objectOutputStream.flush();
            //Receive result
            result = objectInputStream.readBoolean();

            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static boolean checkValidPassword(String pass) {

        if (pass.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$") && pass.length() >= 6) {
            return true;
        }

        return false;
    }

    public static boolean checkValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }

    public static LoginReturnPackage Login(String username, String password) {
        LoginReturnPackage result = null;
        try {

            Socket server = new Socket(Global._IPServer, Global._ServerPort);

            System.out.print(server.getPort());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());

            //Send login signal
            objectOutputStream.writeInt(EnumUserAction.LOGIN.getValue());
            objectOutputStream.flush();

            //Create Login package for sending
            LoginPackage message = new LoginPackage(username, password);

            //Sending Login information
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

            //Receive login result
            result = (LoginReturnPackage) objectInputStream.readObject();

            objectInputStream.close();
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Business.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static String ResetPassword(String username) {
        String result = "Reset password fail!\nPlease try again!";
        try {
            Socket server = new Socket(Global._IPServer, Global._ServerPort);

            System.out.print(server.getPort());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());

            //Send username
            objectOutputStream.writeInt(EnumUserAction.RESETPASS.getValue());
            objectOutputStream.flush();

            objectOutputStream.writeUTF(username);
            objectOutputStream.flush();
            //Receive resetpass result
            result = objectInputStream.readUTF();

            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            result = "Error when reset password!\nPlease try again!";
        }
        return result;
    }

}
