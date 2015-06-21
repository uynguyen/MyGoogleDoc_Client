/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Bus.Global;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicTextUI;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

/**
 *
 * @author UyNguyen.ITUS
 */
public class ActionSelect extends Action{

    public ActionSelect(AttributeSet attributeset) {
        super(attributeset);
    }  
    
    @Override
    public void onDraw(JTextPane textPane) {
       System.err.println("Select: " + _startPosition + "->" + _endPosition);
       int selection_start = textPane.getSelectionStart();
       int selection_end = textPane.getSelectionEnd();
       if (_startPosition == _endPosition){         
           textPane.getHighlighter().removeAllHighlights();
          // textPane.setCaretPosition(_endPosition);
           try {
               textPane.getHighlighter().addHighlight(_startPosition, _endPosition,
                       new DefaultHighlighter.DefaultHighlightPainter(Color.red));
            
              
           } catch (BadLocationException ex) {
               Logger.getLogger(ActionSelect.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       else{
           try {
               textPane.getHighlighter().removeAllHighlights();
               textPane.getHighlighter().addHighlight(_startPosition, _endPosition,
                       new DefaultHighlighter.DefaultHighlightPainter(Color.green));
               textPane.select(selection_start, selection_end);
           } catch (BadLocationException ex) {
               Logger.getLogger(ActionSelect.class.getName()).log(Level.SEVERE, null, ex);
           }
       }

      
       textPane.invalidate();
       //textPane.setSelectionStart(_startPosition);
      // textPane.setSelectionEnd(_endPosition);
       
    }
    
}
