/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import Actions.ActionDelete;
import Actions.ActionFormat;
import Actions.ActionInsert;

import Bus.Global;
import EditorKits.AdvancedHTMLEditorKit;
import EditorKits.DocumentExporter;
import com.sun.java.swing.SwingUtilities3;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputMap;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author Vin
 */
public final class StyledTextEditor extends javax.swing.JPanel {

    private final List<FireChangeDocumentListener> listeners = new ArrayList<>();

    protected MyCaretListener caretListener = null;
    protected MyDocumentListener myDocumentListener;

    public StyledTextEditor() {
        initComponents();
        initFileFilters();
        FormatToolbar.addFormatDocumentListener((AdvancedFormatToolBar.ActionFormatEvent evt) -> {
            performToolbarAction(evt);
        });
        myDocumentListener = new MyDocumentListener();
        caretListener = new MyCaretListener();
        textPane.setMargin(new Insets(50, 50, 50, 50));

        NewDocument();
        addBindings();
    }

    public void performToolbarAction(AdvancedFormatToolBar.ActionFormatEvent evt) {
        switch (evt.getTypeAction()) {
            case AdvancedFormatToolBar.ActionFormatEvent.FormatAction:
                doFormatAction(evt);
                break;
            case AdvancedFormatToolBar.ActionFormatEvent.ExportDocument: {
                 ExportDocument();
                break;
            }
            case AdvancedFormatToolBar.ActionFormatEvent.UndoAction: {

                break;
            }
            case AdvancedFormatToolBar.ActionFormatEvent.RedoAction: {

                break;
            }
            case AdvancedFormatToolBar.ActionFormatEvent.InsertImageAction: {
                InsertImage();
                break;
            }
            default:
                break;
        }
    }

    // Tạo một tài liệu mới
    public void NewDocument() {
        textPane.setEditorKit(new AdvancedHTMLEditorKit());
        textPane.setContentType(textPane.getEditorKit().getContentType());
        textPane.setDocument(textPane.getEditorKit().createDefaultDocument());
        textPane.setStyledDocument(new HTMLDocument());
        textPane.setCaretPosition(0);
    }

    private HashMap<String, FileNameExtensionFilter> mapFileFilter = new HashMap<>();
    
    private void initFileFilters(){
        mapFileFilter = new HashMap<>();
        mapFileFilter.put("RTF", new FileNameExtensionFilter("Rich Text File (*.rtf)", "rtf"));
        mapFileFilter.put("HTML", new FileNameExtensionFilter("HTML (*.html, *.htm)", "html", "htm"));
        mapFileFilter.put("PDF", new FileNameExtensionFilter("Portalble Document Format (*.pdf)", "pdf"));
         mapFileFilter.put("DOCX", new FileNameExtensionFilter("Microsoft Office Document (*.docx)", "docx"));
        
    }
    
    private void addChooseFileFilters(JFileChooser chooser) {
        mapFileFilter.keySet().stream().forEach((key)->{
            chooser.addChoosableFileFilter(mapFileFilter.get(key));
        });
    }

    private void addChooseImageFilters(JFileChooser chooser) {
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG (*.png)", "png"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Bitmap (*.bmp)", "bmp"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG (*.jpg)", "jpg"));
    }

    public void InsertImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        addChooseImageFilters(chooser);
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                AdvancedHTMLEditorKit kit = (AdvancedHTMLEditorKit) textPane.getEditorKit();
                String imgTagData = kit.insertImage(textPane.getStyledDocument(), 
                        textPane.getCaretPosition(), chooser.getSelectedFile());

            } catch (Exception ex) {

            }
        }
    }

    /**
     *
     * @return
     */
    public JTextPane getJTextPane() {
        return textPane;
    }

    private void doFormatAction(AdvancedFormatToolBar.ActionFormatEvent evt) {
        if (textPane.getSelectedText() != null && textPane.getSelectedText().length() > 0) {
            textPane.setCharacterAttributes(evt.getAttributeSet(), false);
        } else {
            textPane.getInputAttributes().addAttributes(evt.getAttributeSet());
            textPane.setCharacterAttributes(evt.getAttributeSet(), false);
            
        }
    }

    private void ExportDocument() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);
        addChooseFileFilters(chooser);     
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int saveResult = chooser.showSaveDialog(this);
        if (saveResult == JFileChooser.APPROVE_OPTION){
            try {
                if (chooser.getFileFilter() == mapFileFilter.get("RTF"))
                
                Files.write(getFileWithExtesion(chooser.getSelectedFile(), ".rtf").toPath(),
                        DocumentExporter.ConvertToRTF(getHTMLString()).getBytes());
                
                else
                
                    if (chooser.getFileFilter() == mapFileFilter.get("HTML"))
                        Files.write(getFileWithExtesion(chooser.getSelectedFile(), ".html").toPath(),
                                getHTMLString().getBytes());
                
                else
                
                        if (chooser.getFileFilter() == mapFileFilter.get("PDF"))
                                    DocumentExporter.ConvertToPDF(getHTMLString(),
                                       getFileWithExtesion(chooser.getSelectedFile(), ".pdf").getAbsolutePath());
                        else
                              if (chooser.getFileFilter() == mapFileFilter.get("DOCX"))
                                    DocumentExporter.ConvertToDocx(getHTMLString(),
                                       getFileWithExtesion(chooser.getSelectedFile(), ".docx").getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(StyledTextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private File getFileWithExtesion(File f, String extension){
        if (!f.getAbsolutePath().endsWith(extension)){
            return new File(f.toPath() + extension);
        }
        return f;
    }
    
    //This listens for and reports caret movements.
    protected class MyCaretListener implements CaretListener {

        public MyCaretListener() {
        }
        public boolean isListening = true;

        //Might not be invoked from the event dispatch thread.

        @Override
        public void caretUpdate(CaretEvent e) {
            //  FormatToolbar.setAttributeSets((SimpleAttributeSet) textPane.getCharacterAttributes());
            //  textPane.setCharacterAttributes(textPane.getCharacterAttributes(), true);
//            if (isListening) {
//                sendActionCaretUpdate(e.getDot(), e.getMark());
//            }
        }

        protected void sendActionCaretUpdate(final int dot, final int mark) {
//            SwingUtilities.invokeLater(() -> {
//                try {
//                    ActionSelect action = new ActionSelect(textPane.getCharacterAttributes());
//                    if (dot == mark) {  // no selection
//                        action.setStartPosition(dot);
//                        action.setEndPosition(dot);
//                    } else {
//
//                        if (dot < mark) {
//                            action.setStartPosition(dot);
//                            action.setEndPosition(mark);
//                        } else {
//                            action.setStartPosition(mark);
//                            action.setEndPosition(dot);
//                        }
//                    }
//                    sendAction(action);
//                } catch (Exception ex) {
//                    Logger.getLogger(ActionSelect.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            });

        }

    }

    public void addDocumentChangeListener(FireChangeDocumentListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    //And this one listens for any changes to the document.
    protected class MyDocumentListener
            implements DocumentListener {

        public boolean isListening = true;

        private void doSendAction(Actions.Action action) {
            if (isListening) {
                Runnable doSend = () -> {
                    sendAction(action);
                };
                SwingUtilities.invokeLater(doSend);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            ActionInsert action = new ActionInsert(textPane.getCharacterAttributes());

            try {
                action.setStartPosition(e.getOffset());
                action.setContent(textPane.getStyledDocument().getText(e.getOffset(), e.getLength()));

            } catch (BadLocationException ex) {
                Logger.getLogger(StyledTextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            //doSendAction(action);
            doSendAction(action);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            ActionDelete action = new ActionDelete(textPane.getCharacterAttributes());

            action.setStartPosition(e.getOffset());
            action.setEndPosition(e.getOffset() + e.getLength());
            //doSendAction(action);
            doSendAction(action);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            ActionFormat action = new ActionFormat(textPane.getCharacterAttributes());
            action.setStartPosition(e.getOffset());
            action.setEndPosition(e.getOffset() + e.getLength());
            //doSendAction(action);
            doSendAction(action);
        }
    }

    private void sendAction(Actions.Action action) {

//        listeners.stream().forEach((l) -> {
//            l.FireChange(new ActionChangeEvent(action));
//        });
        synchronized (Global._myQueue) {
            Global._myQueue.enqueue(action);
        }

    }

    //Add a couple of emacs key bindings for navigation.
    protected void addBindings() {
        InputMap inputMap = textPane.getInputMap();

        //Ctrl-b to bold
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK);
        inputMap.put(key, new StyledEditorKit.BoldAction());

        //Ctrl-i to italic
        key = KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK);
        inputMap.put(key, new StyledEditorKit.ItalicAction());

        //Ctrl-u to underline
        key = KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK);
        inputMap.put(key, new StyledEditorKit.UnderlineAction());
    }

    // Lấy chuỗi HTML của tài liệu
    public String getHTMLString() {
        String strResult = "";
        try {
            AdvancedHTMLEditorKit kit = new AdvancedHTMLEditorKit();
            Document d = textPane.getStyledDocument();
            StringWriter wt = new StringWriter();
            kit.write(wt, d, d.getStartPosition().getOffset(), d.getLength());
            strResult = wt.getBuffer().toString();
        } catch (IOException | BadLocationException ex) {
        }
        strResult = strResult.replaceAll("\n", "<br>");
         strResult =  strResult.replaceAll("\r", "<br>");
        return strResult.replaceAll("\r\n", "<br>");
    }

    public void setHTMLString(String src) {
        NewDocument();
        textPane.setText(src);
        textPane.setCaretPosition(0);
        textPane.getStyledDocument().addUndoableEditListener(FormatToolbar.getUndoableEditLitener());
        textPane.getStyledDocument().addDocumentListener(myDocumentListener);
        textPane.addCaretListener(caretListener);
    }

    

    public void ApplyActionChange(Actions.Action action) {

        caretListener.isListening = false;
        myDocumentListener.isListening = false;

        action.onDraw(textPane);
        caretListener.isListening = true;
        myDocumentListener.isListening = true;
    }

    protected SimpleAttributeSet[] initAttributes(int length) {
        //Hard-code some attributes.
        SimpleAttributeSet[] attrs = new SimpleAttributeSet[length];

        attrs[0] = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attrs[0], "SansSerif");
        StyleConstants.setFontSize(attrs[0], 16);

        attrs[1] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setBold(attrs[1], true);

        attrs[2] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setItalic(attrs[2], true);

        attrs[3] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setFontSize(attrs[3], 20);

        attrs[4] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setFontSize(attrs[4], 12);

        attrs[5] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setForeground(attrs[5], Color.red);

        return attrs;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        FormatToolbar = new CustomComponents.AdvancedFormatToolBar();

        jPopupMenu1.setLabel("popup");

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        setMinimumSize(new java.awt.Dimension(720, 470));
        setPreferredSize(new java.awt.Dimension(720, 470));

        textPane.setContentType("text/html"); // NOI18N
        textPane.setAutoscrolls(false);
        textPane.setComponentPopupMenu(jPopupMenu1);
        textPane.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textPane.setMaximumSize(jScrollPane1.getPreferredSize());
        textPane.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                textPaneMouseMoved(evt);
            }
        });
        textPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                textPaneMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                textPaneMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(textPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FormatToolbar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(FormatToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textPaneMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textPaneMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_textPaneMouseMoved

    private void textPaneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textPaneMouseEntered
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.TEXT_CURSOR));
    }//GEN-LAST:event_textPaneMouseEntered

    private void textPaneMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textPaneMouseExited
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_textPaneMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponents.AdvancedFormatToolBar FormatToolbar;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables
}
