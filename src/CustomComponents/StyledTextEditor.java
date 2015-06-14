/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import EditorKits.AdvancedHTMLEditorKit;
import EditorKits.AdvancedRTFEditorKit;
import java.awt.Color;
import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author Vin
 */
public final class StyledTextEditor extends javax.swing.JPanel {

    Color forcegroundColor = Color.black;

    File currentFile = null;
    String newline = "\n";
    HashMap<Object, Action> actions;

    //undo helpers
    /**
     * Creates new form StyledTextEditor
     */
    public StyledTextEditor() {
        initComponents();

        textPane.setCaretPosition(0);
        textPane.setMargin(new Insets(20, 20, 20, 20));

        //Set up the menu bar.
        actions = createActionTable(textPane);
        //Add some key bindings.
        addBindings();

        //Start watching for undoable edits and caret changes.
        textPane.getStyledDocument().addUndoableEditListener(FormatToolbar.getUndoableEditLitener());

        textPane.getStyledDocument().addDocumentListener(new StyledTextEditor.MyDocumentListener());
        FormatToolbar.setTextEditor(this);
    }

    public void NewDocument() {
        textPane.setEditorKit(new HTMLEditorKit());
        textPane.setDocument(textPane.getEditorKit().createDefaultDocument());
          //Put the initial text into the text pane.
        // initDocument();
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
                textPane.insertIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));

            } catch (Exception ex) {

            }
        }

    }

    public void OpenDocument() {
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

                textPane.getStyledDocument().addDocumentListener(new StyledTextEditor.MyDocumentListener());
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
    protected class CaretListenerLabel extends JLabel
            implements CaretListener {

        public CaretListenerLabel(String label) {
            super(label);
        }

        //Might not be invoked from the event dispatch thread.
        @Override
        public void caretUpdate(CaretEvent e) {
            displaySelectionInfo(e.getDot(), e.getMark());
        }

        //This method can be invoked from any thread.  It 
        //invokes the setText and modelToView methods, which 
        //must run on the event dispatch thread. We use
        //invokeLater to schedule the code for execution
        //on the event dispatch thread.
        protected void displaySelectionInfo(final int dot,
                final int mark) {
            SwingUtilities.invokeLater(() -> {
                if (dot == mark) {  // no selection
                    try {
                        Rectangle caretCoords = textPane.modelToView(dot);
                        //Convert it to view coordinates.
                        setText("caret: text position: " + dot
                                + ", view location = ["
                                + caretCoords.x + ", "
                                + caretCoords.y + "]"
                                + newline);
                    } catch (BadLocationException ble) {
                        setText("caret: text position: " + dot + newline);
                    }
                } else {
                    if (dot < mark) {
                        setText("selection from: " + dot
                                + " to " + mark + newline);
                    } else {
                        setText("selection from: " + mark
                                + " to " + dot + newline);
                    }
                }
                try {
                    AttributeSet atts = ((StyledEditorKit) textPane.getEditorKit()).getInputAttributes();
                    System.out.println(textPane.getStyledDocument().getText(dot, dot - mark));

                    System.out.println("isBold :" + StyleConstants.isBold(atts));
                    System.out.println("isItalic :" + StyleConstants.isItalic(atts));
                    System.out.println("isUnderline :" + StyleConstants.isUnderline(atts));
                } catch (Exception ex) {

                }
            });
        }
    }

    //And this one listens for any changes to the document.
    protected class MyDocumentListener
            implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }

        private void displayEditInfo(DocumentEvent e) {
            Document document = e.getDocument();
            int changeLength = e.getLength();

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

    public String getHTMLString() {
        String strResult = "";
        try {
            HTMLEditorKit kit = new HTMLEditorKit();
            Document d = textPane.getStyledDocument();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            kit.write(baos, d, d.getStartPosition().getOffset(), d.getLength());
            strResult = baos.toString(StandardCharsets.UTF_8.name());
        } catch (IOException | BadLocationException ex) {
        }
        return strResult;
    }

    protected String addUTF8Charset(String src) {
        return "";
    }

    public void setHTMLString(String src) {
        HTMLEditorKit kit = new HTMLEditorKit();
        textPane.setEditorKit(kit);
        textPane.setDocument(kit.createDefaultDocument());
        textPane.setCaret(kit.createCaret());

        textPane.setText(src);
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
        RTFEditorKit kit = new RTFEditorKit();
        textPane.setEditorKit(kit);
        textPane.setDocument(kit.createDefaultDocument());
        textPane.setText(src);
    }

    protected void initDocument() {
        String initString[]
                = {"Use the mouse to place the caret.",
                    "Use the edit menu to cut, copy, paste, and select text.",
                    "Also to undo and redo changes.",
                    "Use the style menu to change the style of the text.",
                    "Use the arrow keys on the keyboard or these emacs key bindings to move the caret:",
                    "Ctrl-f, Ctrl-b, Ctrl-n, Ctrl-p."};

        SimpleAttributeSet[] attrs = initAttributes(initString.length);

        try {
            for (int i = 0; i < initString.length; i++) {
                textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(), initString[i] + newline,
                        attrs[i]);
            }
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text.");
        }
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

    //The following two methods allow us to find an
    //action provided by the editor kit by its name.
    private HashMap<Object, Action> createActionTable(JTextComponent textComponent) {
        HashMap<Object, Action> actionlisteners = new HashMap<>();
        Action[] actionsArray = textComponent.getActions();
        for (Action a : actionsArray) {
            actionlisteners.put(a.getValue(Action.NAME), a);
        }
        return actionlisteners;
    }

    private Action getActionByName(String name) {
        return actions.get(name);
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

        textPane.setContentType("text/html"); // NOI18N
        textPane.setComponentPopupMenu(jPopupMenu1);
        textPane.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(textPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                    .addComponent(FormatToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(FormatToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponents.AdvancedFormatToolBar FormatToolbar;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables
}