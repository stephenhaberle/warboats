/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import warboats.boats.Boat;

/**
 *
 * @author khc009
 */
public class MarkerTest extends TestCase {

    public MarkerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MarkerTest.class);
        return suite;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of toString method, of class Marker.
     */
    public void testToString() {
        System.out.println("toString");
        Marker instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosX method, of class Marker.
     */
    public void testGetPosX() {
        System.out.println("getPosX");
        Marker instance = null;
        int expResult = 0;
        int result = instance.getPosX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosY method, of class Marker.
     */
    public void testGetPosY() {
        System.out.println("getPosY");
        Marker instance = null;
        int expResult = 0;
        int result = instance.getPosY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor method, of class Marker.
     */
    public void testGetColor() {
        System.out.println("getColor");
        Marker instance = null;
        String expResult = "";
        String result = instance.getColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor method, of class Marker.
     */
    public void testSetColor() {
        System.out.println("setColor");
        String color = "";
        Marker instance = null;
        instance.setColor(color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isShipOn method, of class Marker.
     */
    public void testIsShipOn() {
        System.out.println("isShipOn");
        Marker instance = null;
        boolean expResult = false;
        boolean result = instance.isShipOn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toggleShipOn method, of class Marker.
     */
    public void testToggleShipOn() {
        System.out.println("toggleShipOn");
        Marker instance = null;
        instance.toggleShipOn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBoat method, of class Marker.
     */
    public void testSetBoat() {
        System.out.println("setBoat");
        Boat boatType = null;
        Marker instance = null;
        instance.setBoat(boatType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoat method, of class Marker.
     */
    public void testGetBoat() {
        System.out.println("getBoat");
        Marker instance = null;
        Boat expResult = null;
        Boat result = instance.getBoat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
