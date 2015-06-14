/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import javax.swing.text.SimpleAttributeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vin
 */
public class StyledTextEditorIT {
    
    public StyledTextEditorIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of NewDocument method, of class StyledTextEditor.
     */
    @Test
    public void testNewDocument() {
        System.out.println("NewDocument");
        String contentType = "";
        StyledTextEditor instance = new StyledTextEditor();
        instance.NewDocument(contentType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of OpenDocument method, of class StyledTextEditor.
     */
    @Test
    public void testOpenDocument() {
        System.out.println("OpenDocument");
        StyledTextEditor instance = new StyledTextEditor();
        instance.OpenDocument();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SaveDocument method, of class StyledTextEditor.
     */
    @Test
    public void testSaveDocument() {
        System.out.println("SaveDocument");
        StyledTextEditor instance = new StyledTextEditor();
        instance.SaveDocument();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBindings method, of class StyledTextEditor.
     */
    @Test
    public void testAddBindings() {
        System.out.println("addBindings");
        StyledTextEditor instance = new StyledTextEditor();
        instance.addBindings();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHTMLString method, of class StyledTextEditor.
     */
    @Test
    public void testGetHTMLString() {
        System.out.println("getHTMLString");
        StyledTextEditor instance = new StyledTextEditor();
        String expResult = "";
        String result = instance.getHTMLString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHTMLString method, of class StyledTextEditor.
     */
    @Test
    public void testSetHTMLString() {
        System.out.println("setHTMLString");
        String src = "";
        StyledTextEditor instance = new StyledTextEditor();
        instance.setHTMLString(src);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initDocument method, of class StyledTextEditor.
     */
    @Test
    public void testInitDocument() {
        System.out.println("initDocument");
        StyledTextEditor instance = new StyledTextEditor();
        instance.initDocument();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initAttributes method, of class StyledTextEditor.
     */
    @Test
    public void testInitAttributes() {
        System.out.println("initAttributes");
        int length = 0;
        StyledTextEditor instance = new StyledTextEditor();
        SimpleAttributeSet[] expResult = null;
        SimpleAttributeSet[] result = instance.initAttributes(length);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
