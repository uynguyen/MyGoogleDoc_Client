/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditorKits;


import com.itextpdf.text.html.simpleparser.HTMLWorker;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import javax.swing.text.BadLocationException;
import javax.swing.text.rtf.RTFEditorKit;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vin
 */
public class DocumentExporter {

    public static String ConvertToRTF(String htmlString) throws IOException {
        String result = "";

        OutputStream os = new ByteArrayOutputStream();
        AdvancedHTMLEditorKit htmlEditorKit = new AdvancedHTMLEditorKit();
        RTFEditorKit rtfEditorKit = new RTFEditorKit();

        htmlString = htmlString.replaceAll("<br.*?>", "#NEW_LINE#");
        htmlString = htmlString.replaceAll("</p>", "#NEW_LINE#");
        htmlString = htmlString.replaceAll("<p.*?>", "");
        InputStream is = new ByteArrayInputStream(htmlString.getBytes());
        try {
            javax.swing.text.Document doc = htmlEditorKit.createDefaultDocument();
            htmlEditorKit.read(is, doc, 0);
            rtfEditorKit.write(os, doc, 0, doc.getLength());
            result = os.toString();
            result = result.replaceAll("#NEW_LINE#", "\\\\par ");
        } catch (BadLocationException e) {
        }

        return result;
    }

    public static void ConvertToPDF(String htmlString, String output) {
        
       try {
		      Document document = new Document(PageSize.A4);
		      PdfWriter pdfWriter = PdfWriter.getInstance
		           (document, new FileOutputStream(output));
		      document.open();
		      document.addCreationDate();
	
		      XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

		     
		      worker.parseXHtml(pdfWriter, document, new StringReader(htmlString));
		      document.close();
		      System.out.println("Done.");
		      }
		    catch (FileNotFoundException | DocumentException e) {
		    } catch (IOException ex) {
            Logger.getLogger(DocumentExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
