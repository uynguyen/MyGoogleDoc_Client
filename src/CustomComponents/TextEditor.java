/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package CustomComponents;
/*
 * TextComponentDemo.java requires one additional file:
 *   DocumentSizeFilter.java
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.text.rtf.RTFEditorKit;
import javax.swing.undo.*;

public final class TextEditor extends JPanel {

    JTextPane textPane;
    JFrame parent;
    AbstractDocument doc;
    static final long MAX_CHARACTERS = Long.MAX_VALUE;
    JTextArea changeLog;
    String newline = "\n";
    HashMap<Object, Action> actions;
    JToolBar Toolbar;

    //undo helpers
    protected UndoAction undoAction;
    protected RedoAction redoAction;
    protected UndoManager undo = new UndoManager();

    public TextEditor() {
        super();

        //Create the text pane and configure it.
        textPane = new JTextPane();
        textPane.setCaretPosition(0);
        textPane.setMargin(new Insets(20, 20, 20, 20));
        
        StyledDocument styledDoc = textPane.getStyledDocument();
        if (styledDoc instanceof AbstractDocument) {
            doc = (AbstractDocument) styledDoc;
            doc.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
        } else {
            System.err.println("Text pane's document isn't an AbstractDocument!");
            System.exit(-1);
        }
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(200, 200));

        //Create the text area for the status log and configure it.
        changeLog = new JTextArea(5, 30);
        changeLog.setEditable(false);

        //Create the status area.
        JPanel statusPane = new JPanel(new GridLayout(1, 1));
        CaretListenerLabel caretListenerLabel
                = new CaretListenerLabel("Caret Status");
        statusPane.add(caretListenerLabel);
        this.setLayout(new BorderLayout());
        //Add the components.
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(statusPane, BorderLayout.PAGE_END);
        this.add(createToolBar(), BorderLayout.NORTH);
        //Set up the menu bar.
        actions = createActionTable(textPane);
        JMenu editMenu = createEditMenu();
        JMenu styleMenu = createStyleMenu();

        JMenuBar mb = new JMenuBar();
        mb.add(editMenu);
        mb.add(styleMenu);

        //Add some key bindings.
        addBindings();

        //Put the initial text into the text pane.
        initDocument();
        textPane.setCaretPosition(0);

        //Start watching for undoable edits and caret changes.
        doc.addUndoableEditListener(new MyUndoableEditListener());
        textPane.addCaretListener(caretListenerLabel);
        doc.addDocumentListener(new MyDocumentListener());

        this.setVisible(true);
    }

    private JToolBar createToolBar() {
        JToolBar tb = new FormatToolbar();
        tb.setFloatable(false);

        Action action = new RTFEditorKit.BoldAction();
        action.putValue(Action.NAME, "B");
        tb.add(action);

        action = new RTFEditorKit.ItalicAction();
        action.putValue(Action.NAME, "I");
        tb.add(action);

        action = new RTFEditorKit.UnderlineAction();
        action.putValue(Action.NAME, "U");
        tb.add(action);

        return tb;
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
                } else if (dot < mark) {
                    setText("selection from: " + dot
                            + " to " + mark + newline);
                } else {
                    setText("selection from: " + mark
                            + " to " + dot + newline);
                }
            });
        }
    }

    //This one listens for edits that can be undone.
    protected class MyUndoableEditListener
            implements UndoableEditListener {

        @Override
        public void undoableEditHappened(UndoableEditEvent e) {
            //Remember the edit and update the menus.
            undo.addEdit(e.getEdit());
            undoAction.updateUndoState();
            redoAction.updateRedoState();
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
            changeLog.append(e.getType().toString() + ": "
                    + changeLength + " character"
                    + ((changeLength == 1) ? ". " : "s. ")
                    + " Text length = " + document.getLength()
                    + "." + newline);
        }
    }

    //Add a couple of emacs key bindings for navigation.
    protected void addBindings() {
        InputMap inputMap = textPane.getInputMap();

        //Ctrl-b to bold
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK);
        inputMap.put(key, new RTFEditorKit.BoldAction());

//        //Ctrl-s to save
//        key = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK);
//        inputMap.put(key, DefaultEditorKit.downAction);

        //Ctrl-i to italic
        key = KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK);
        inputMap.put(key, new RTFEditorKit.ItalicAction());

        //Ctrl-u to underline
        key = KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK);
        inputMap.put(key, new RTFEditorKit.UnderlineAction());
    }

    //Create the edit menu.
    protected JMenu createEditMenu() {
        JMenu menu = new JMenu("Edit");

        //Undo and redo are actions of our own creation.
        undoAction = new UndoAction();
        menu.add(undoAction);

        redoAction = new RedoAction();
        menu.add(redoAction);

        menu.addSeparator();

        //These actions come from the default editor kit.
        //Get the ones we want and stick them in the menu.
        menu.add(getActionByName(DefaultEditorKit.cutAction));
        menu.add(getActionByName(DefaultEditorKit.copyAction));
        menu.add(getActionByName(DefaultEditorKit.pasteAction));

        menu.addSeparator();

        menu.add(getActionByName(DefaultEditorKit.selectAllAction));
        return menu;
    }

    //Create the style menu.
    protected JMenu createStyleMenu() {
        JMenu menu = new JMenu("Style");

        Action action = new RTFEditorKit.BoldAction();
        action.putValue(Action.NAME, "Bold");
        menu.add(action);

        action = new RTFEditorKit.ItalicAction();
        action.putValue(Action.NAME, "Italic");
        menu.add(action);

        action = new RTFEditorKit.UnderlineAction();
        action.putValue(Action.NAME, "Underline");
        menu.add(action);

        menu.addSeparator();

        menu.add(new RTFEditorKit.FontSizeAction("12", 12));
        menu.add(new RTFEditorKit.FontSizeAction("14", 14));
        menu.add(new RTFEditorKit.FontSizeAction("18", 18));

        menu.addSeparator();

        menu.add(new RTFEditorKit.FontFamilyAction("Serif",
                "Serif"));
        menu.add(new RTFEditorKit.FontFamilyAction("SansSerif",
                "SansSerif"));

        menu.addSeparator();

        menu.add(new RTFEditorKit.ForegroundAction("Red",
                Color.red));
        menu.add(new RTFEditorKit.ForegroundAction("Green",
                Color.green));
        menu.add(new RTFEditorKit.ForegroundAction("Blue",
                Color.blue));
        menu.add(new RTFEditorKit.ForegroundAction("Black",
                Color.black));

        return menu;
    }
    
    public void SaveDocument() {
        try{
             if (textPane.getText().length() > 0){

            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(false);

            int option = chooser.showSaveDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {

                RTFEditorKit kit = new RTFEditorKit();
                StyledDocument document = (StyledDocument)textPane.getDocument();
                BufferedOutputStream out;

                out = new BufferedOutputStream(new FileOutputStream(chooser.getSelectedFile().getAbsoluteFile()));
                kit.write(out, document, document.getStartPosition().getOffset(), document.getLength());
            }
        }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
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
                doc.insertString(doc.getLength(), initString[i] + newline,
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

    class UndoAction extends AbstractAction {

        public UndoAction() {
            super("Undo");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undo.undo();
            } catch (CannotUndoException ex) {
                System.out.println("Unable to undo: " + ex);
            }
            updateUndoState();
            redoAction.updateRedoState();
        }

        protected void updateUndoState() {
            if (undo.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }
    }

    class RedoAction extends AbstractAction {

        public RedoAction() {
            super("Redo");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undo.redo();
            } catch (CannotRedoException ex) {
                System.out.println("Unable to redo: " + ex);
            }
            updateRedoState();
            undoAction.updateUndoState();
        }

        protected void updateRedoState() {
            if (undo.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getRedoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }
}
