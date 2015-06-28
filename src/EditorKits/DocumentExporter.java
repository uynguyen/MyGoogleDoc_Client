/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditorKits;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author Vin
 */
public class DocumentExporter {
    
    
    public static String ConvertToRTF(String htmlString) throws IOException{
        String result = "";
        
        OutputStream os = new ByteArrayOutputStream();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        RTFEditorKit rtfEditorKit = new RTFEditorKit();
        

        htmlString = htmlString.replaceAll("<br.*?>", "#NEW_LINE#");
        htmlString = htmlString.replaceAll("</p>", "#NEW_LINE#");
        htmlString = htmlString.replaceAll("<p.*?>", "");
        InputStream is = new ByteArrayInputStream(htmlString.getBytes());
        try {
            Document doc = htmlEditorKit.createDefaultDocument();
            htmlEditorKit.read(is, doc, 0);
            rtfEditorKit.write(os, doc, 0, doc.getLength());
            result = os.toString();
            result = result.replaceAll("#NEW_LINE#", "\\\\par ");
        } catch (BadLocationException e) {
        }
        
        return result;
    }

    public static String ConvertToPDF(String htmlString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
