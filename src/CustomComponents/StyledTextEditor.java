/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import Actions.ActionDelete;
import Actions.ActionFormat;
import Actions.ActionInsert;
import Actions.ActionSelect;
import EditorKits.AdvancedHTMLEditorKit;
import EditorKits.AdvancedRTFEditorKit;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputMap;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
    
    final long MAX_CHARACTERS = Long.MAX_VALUE;
    Color forcegroundColor = Color.black;
    public File currentFile = null;


    public StyledTextEditor() {
        initComponents();
        textPane.setMargin(new Insets(50, 50, 50, 50));
       // textPane.setCaretPosition(0);
        NewDocument();
        addBindings();
         
//        textPane.getStyledDocument().addUndoableEditListener(FormatToolbar.getUndoableEditLitener());
//        textPane.getStyledDocument().addDocumentListener(new MyDocumentListener());
//        textPane.addCaretListener(new MyCaretListener());
        FormatToolbar.setTextEditor(this);
        
    }

    // Tạo một tài liệu mới
    public void NewDocument() {
        textPane.setEditorKit(new AdvancedHTMLEditorKit());
        textPane.setContentType(textPane.getEditorKit().getContentType());
        textPane.setDocument(textPane.getEditorKit().createDefaultDocument()); 
        textPane.setStyledDocument(new HTMLDocument());
       
        textPane.setCaretPosition(0);
    }

    private void addChooseFileFilters(JFileChooser chooser) {
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Rich Text File (*.rtf)", "rtf"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("HTML (*.html, *.htm)", "html", "htm"));
    }

    private void addChooseImageFilters(JFileChooser chooser) {
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Bitmap (*.bmp)", "bmp"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG (*.jpg)", "jpg"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG (*.png)", "png"));

    }

    public void InsertImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        addChooseImageFilters(chooser);
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                AdvancedHTMLEditorKit kit = (AdvancedHTMLEditorKit)textPane.getEditorKit();
              String imgTagData = kit.insertImage(textPane.getStyledDocument(), textPane.getCaretPosition(), chooser.getSelectedFile());
              Actions.ActionInsertImage action = new Actions.ActionInsertImage(textPane.getCharacterAttributes());
              action.setStartPosition(textPane.getCaretPosition());
              action.setContent(imgTagData);
                sendAction(action);
            } catch (Exception ex) {

            }
        }
    }

    public void OpenDocument() {
        //addCaret(10, Color.BLUE);
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        addChooseFileFilters(chooser);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            try {
                byte[] buff = Files.readAllBytes(currentFile.toPath());
                String content = new String(buff);
                if (getExtensionFile().equals("HTML")) {
                    setHTMLString(content);
                } else {
                    setRTFString(content);
                }
                textPane.getStyledDocument().addUndoableEditListener(FormatToolbar.getUndoableEditLitener());
                textPane.getStyledDocument().addDocumentListener(new MyDocumentListener());
            } catch (IOException ex) {

            }
        }
    }

    private String getExtensionFile() {
        if (currentFile == null) {
            return "";
        }
        String filename = currentFile.getAbsolutePath();
        String extension = filename.substring(filename.lastIndexOf("."));
        switch (extension) {
            case ".html":
                return "HTML";
            case ".rtf":
                return "RTF";
            default:
                return "";
        }
    }

    private StyledEditorKit getChoosenEditorKit(JFileChooser chooser) {
        String filename = chooser.getSelectedFile().getName();
        String extension = filename.substring(filename.lastIndexOf("."));
        switch (extension) {
            case ".html":
                return new AdvancedHTMLEditorKit();
            case ".rtf":
                return new AdvancedRTFEditorKit();
            default:
                return null;
        }
    }

    /**
     *
     */
    public void SaveDocument() {
        if (currentFile == null) {
            SaveAsDocument();
            return;
        }
        if (currentFile == null) {
            return;
        }

        try {
            if (getExtensionFile().equals("HTML")) {
                Files.write(currentFile.toPath(), getHTMLString().getBytes());
            } else {
                Files.write(currentFile.toPath(), getRTFString().getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(StyledTextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public JTextPane getJTextPane() {
        return textPane;
    }


    public void SaveAsDocument() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        addChooseFileFilters(chooser);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);

        try {
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                currentFile = chooser.getSelectedFile();
                
                
                SaveDocument();
            }
        } catch (HeadlessException e) {
        }

    }

    //This listens for and reports caret movements.
    protected class MyCaretListener 
            implements CaretListener {

        public MyCaretListener() {
            
        }

        //Might not be invoked from the event dispatch thread.
        @Override
        public void caretUpdate(CaretEvent e) {
            sendActionCaretUpdate(e.getDot(), e.getMark());       
        }

        protected void sendActionCaretUpdate(final int dot, final int mark) {
            SwingUtilities.invokeLater(() -> {
                 ActionSelect action = new ActionSelect(textPane.getCharacterAttributes());
                if (dot == mark) {  // no selection
                    action.setStartPosition(dot);
                    action.setEndPosition(dot);
                } else {
                   
                    if (dot < mark) {
                        action.setStartPosition(dot);
                        action.setEndPosition(mark);
                    } else {
                        action.setStartPosition(mark);
                        action.setEndPosition(dot);
                    }
                   
                }
                 sendAction(action);
            });
        }
    }

    public void addDocumentChangeListener(FireChangeDocumentListener listener){
        if (listener != null)
             listeners.add(listener);
}
    
    //And this one listens for any changes to the document.
    protected class MyDocumentListener
            implements DocumentListener {

//        private void doSendAction(Actions.Action action) {
//                    Runnable doSend = () -> {
//                        sendAction(action);
//                    };
//                    SwingUtilities.invokeLater(doSend);
//                }
        @Override
        public void insertUpdate(DocumentEvent e) {
            ActionInsert action = new ActionInsert(textPane.getCharacterAttributes());

            try {
                action.setStartPosition(e.getOffset());
                action.setContent(e.getDocument().getText(e.getOffset(), e.getLength()));

            } catch (BadLocationException ex) {
                Logger.getLogger(StyledTextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            //doSendAction(action);
            sendAction(action);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            ActionDelete action = new ActionDelete(textPane.getCharacterAttributes());

            action.setStartPosition(e.getOffset());
            action.setEndPosition(e.getOffset() + e.getLength());
            //doSendAction(action);
             sendAction(action);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            ActionFormat action = new ActionFormat(textPane.getCharacterAttributes());
            action.setStartPosition(e.getOffset());
            action.setEndPosition(e.getOffset() + e.getLength());
            //doSendAction(action);
             sendAction(action);
        }
    }
    
    private void sendAction(Actions.Action action){
        
        listeners.stream().forEach((l) -> {
            l.FireChange(new ActionChangeEvent(action));
         });
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
        return strResult;
    }


    public void setHTMLString(String src) {
        NewDocument();
        textPane.setText(src);
        textPane.getStyledDocument().addUndoableEditListener(FormatToolbar.getUndoableEditLitener());
        textPane.getStyledDocument().addDocumentListener(new MyDocumentListener());
        textPane.addCaretListener(new MyCaretListener());
    }

    public String getRTFString() {
        String strResult = "";
        try {
            RTFEditorKit kit = new RTFEditorKit();
            Document d = textPane.getStyledDocument();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            kit.write(baos, d, d.getStartPosition().getOffset(), d.getLength());
            strResult = baos.toString(StandardCharsets.UTF_8.name());
        } catch (IOException | BadLocationException ex) {
        }
        return strResult;
    }

    public void setRTFString(String src) {
        RTFEditorKit kit = new AdvancedRTFEditorKit();
        textPane.setEditorKit(kit);
        textPane.setDocument(kit.createDefaultDocument());
        textPane.setText(src);
        textPane.getStyledDocument().addUndoableEditListener(FormatToolbar.getUndoableEditLitener());
        textPane.getStyledDocument().addDocumentListener(new MyDocumentListener());
        //  textPane.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    }

    
    public void ApplyActionChange(Actions.Action action){
        action.onDraw(textPane);
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
