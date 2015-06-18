/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditorKits;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.GlyphView;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 *
 * @author Vin
 */
public class AdvancedStyledEditorKit extends StyledEditorKit {
    public AdvancedStyledEditorKit()
    {
        super();
    }
     ViewFactory defaultFactory=new WrapColumnFactory();
    @Override
    public ViewFactory getViewFactory() {
        return defaultFactory;
    }

    @Override
    public MutableAttributeSet getInputAttributes() {
        MutableAttributeSet mAttrs=super.getInputAttributes();
        mAttrs.removeAttribute("line_break_attribute");
        return mAttrs;
    }

    private static class WrapLabelView extends LabelView {

        public WrapLabelView(Element elem) {
            super(elem);
        }
        @Override
       public int getBreakWeight(int axis, float pos, float len) {
        if (axis == View.X_AXIS) {
            checkPainter();
            int p0 = getStartOffset();
            int p1 = getGlyphPainter().getBoundedPosition(this, p0, pos, len);
            if (p1 == p0) {
                // can't even fit a single character
                return View.BadBreakWeight;
            }
            try {
                //if the view contains line break char return forced break
                if (getDocument().getText(p0, p1 - p0).contains("\r")) {
                    return View.ForcedBreakWeight;
                }
            }
            catch (BadLocationException ex) {
                //should never happen
            }
        }
        return super.getBreakWeight(axis, pos, len);
    }

        @Override
    public View breakView(int axis, int p0, float pos, float len) {
        if (axis == View.X_AXIS) {
            checkPainter();
            int p1 = getGlyphPainter().getBoundedPosition(this, p0, pos, len);
            try {
                //if the view contains line break char break the view
                int index = getDocument().getText(p0, p1 - p0).indexOf("\r");
                if (index >= 0) {
                    GlyphView v = (GlyphView) createFragment(p0, p0 + index + 1);
                    return v;
                }
            }
            catch (BadLocationException ex) {
                //should never happen
            }
        }
        return super.breakView(axis, p0, pos, len);
    }
    }

    private static class NoWrapParagraphView extends ParagraphView {

        public NoWrapParagraphView(Element elem) {
            super(elem);
        }
        @Override
  public void layout(int width, int height) {
        super.layout(Short.MAX_VALUE, height);
    }

        @Override
    public float getMinimumSpan(int axis) {
        return super.getPreferredSpan(axis);
    }
        
    }
    
    
    class WrapColumnFactory implements ViewFactory {
    @Override
    public View create(Element elem) {
        String kind = elem.getName();
        if (kind != null) {
            switch (kind) {
                case AbstractDocument.ContentElementName:
                    return new WrapLabelView(elem);
                case AbstractDocument.ParagraphElementName:
                    return new NoWrapParagraphView(elem);
                case AbstractDocument.SectionElementName:
                    return new BoxView(elem, View.Y_AXIS);
                case StyleConstants.ComponentElementName:
                    return new ComponentView(elem);
                case StyleConstants.IconElementName:
                    return new IconView(elem);
            }
        }

        // default to text display
        return new LabelView(elem);
    }
}

}
