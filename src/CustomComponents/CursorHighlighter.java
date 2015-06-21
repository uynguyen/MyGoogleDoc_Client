/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Vector;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;

/**
 *
 * @author Vin
 */
public class CursorHighlighter extends DefaultHighlighter {

    protected JTextComponent component;

    protected final Vector<UserHighlightInfo> highlights = new Vector<>();

    public void addUserHighlight(UserHighlightInfo userHighlightInfo) {
        highlights.add(userHighlightInfo);
    }

    public void removeUserHighlight(UserHighlightInfo userHighlightInfo) {
        highlights.remove(userHighlightInfo);
    }

    @Override
    public final void paint(final Graphics g) {
        highlights.forEach((info) -> {
            // Avoid allocing unless we need it.
            final Rectangle a = this.component.getBounds();
            final Insets insets = this.component.getInsets();
            a.x = insets.left;
            a.y = insets.top;
            // a.width -= insets.left + insets.right + 100;
            a.height -= insets.top + insets.bottom;
            final Highlighter.HighlightPainter p = info
                    .getPainter();
            p.paint(g, info.getStartOffset(), info
                    .getEndOffset(), a, this.component);

        });
    }

    @Override
    public Highlight[] getHighlights() {
        int size = highlights.size();
        if (size == 0) {
            return new UserHighlightInfo[0];
        }
        Highlight[] h = new Highlight[size];
        highlights.copyInto(h);
        return h;
    }

    public class UserHighlightInfo implements Highlighter.Highlight {

        public UserHighlightInfo(String _UserName, String _UserID, Position _p0, Position _p1, Color _color){
            UserName = _UserName;
            UserID = _UserID;
            p0 = _p0;
            p1 = _p1;
            painter = new DefaultHighlightPainter(_color);            
        }
        
        @Override
        public int getStartOffset() {
            return p0.getOffset();
        }

        @Override
        public int getEndOffset() {
            return p1.getOffset();
        }

        @Override
        public Highlighter.HighlightPainter getPainter() {
            return painter;
        }

        protected  Position p0;
        protected Position p1;
        protected String UserName;
        protected String UserID;
        protected Highlighter.HighlightPainter painter;

        /**
         * @return the UserName
         */
        public String getUserName() {
            return UserName;
        }

        /**
         * @param UserName the UserName to set
         */
        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        /**
         * @return the UserID
         */
        public String getUserID() {
            return UserID;
        }

        /**
         * @param UserID the UserID to set
         */
        public void setUserID(String UserID) {
            this.UserID = UserID;
        }
    }
}
