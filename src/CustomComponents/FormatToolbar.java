/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Vin
 */
public class FormatToolbar extends JToolBar {
    
    public FormatToolbar() {
        
        super();
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        this.setBorder(new LineBorder(Color.orange, 1, true));
        this.setFloatable(false);
    }
    
    private int getButtonFontStyle(String str) {
        switch (str) {
            case "B":
                return 1;
            case "I":
                return 2;
            case "U":
                return 0;
        }
        return 0;
    }

    @Override
    public JButton add(Action a) {
        JButton b = createActionComponent(a);
        b.setAction(a);
        b.setBorder(new LineBorder(Color.orange, 1, true));
        b.setBackground(Color.white);
        b.setFont(new Font("Times New Roman", getButtonFontStyle(b.getText()), 16));
        b.setPreferredSize(new Dimension(30, 30));
        b.setSize(new Dimension(30, 30));
        b.setFocusable(false);
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MouseClick(e);
                super.mousePressed(e);
            }
        });        
        
        add(b);
        return b;
    }
    
    public void MouseClick(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn.isSelected()) {
            btn.setSelected(false);
        } else {
            btn.setSelected(true);
        }
    }
    
}
