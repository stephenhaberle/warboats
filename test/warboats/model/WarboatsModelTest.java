/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.model;

import java.util.ArrayList;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import warboats.boats.Boat;
import warboats.network.Coordinates;

/**
 *
 * @author khc009
 */
public class WarboatsModelTest extends TestCase {

    public WarboatsModelTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(WarboatsModelTest.class);
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
     * Test of sendPlayerMove method, of class WarboatsModel.
     */
    public void testSendPlayerMove() throws Exception {
        System.out.println("sendPlayerMove");
        int x = 0;
        int y = 0;
        WarboatsModel instance = null;
        instance.sendPlayerMove(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addShip method, of class WarboatsModel.
     */
    public void testAddShip() {
        System.out.println("addShip");
        int boatType = 0;
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        WarboatsModel instance = null;
        instance.addShip(boatType, x1, y1, x2, y2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConsolePlacements method, of class WarboatsModel.
     */
    public void testGetConsolePlacements() {
        System.out.println("getConsolePlacements");
        WarboatsModel instance = null;
        instance.getConsolePlacements();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkLoss method, of class WarboatsModel.
     */
    public void testCheckLoss() {
        System.out.println("checkLoss");
        WarboatsModel instance = null;
        instance.checkLoss();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMyBoard method, of class WarboatsModel.
     */
    public void testGetMyBoard() {
        System.out.println("getMyBoard");
        WarboatsModel instance = null;
        Board expResult = null;
        Board result = instance.getMyBoard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpponentBoard method, of class WarboatsModel.
     */
    public void testGetOpponentBoard() {
        System.out.println("getOpponentBoard");
        WarboatsModel instance = null;
        Board expResult = null;
        Board result = instance.getOpponentBoard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastShot method, of class WarboatsModel.
     */
    public void testGetLastShot() {
        System.out.println("getLastShot");
        WarboatsModel instance = null;
        Coordinates expResult = null;
        Coordinates result = instance.getLastShot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastShot method, of class WarboatsModel.
     */
    public void testSetLastShot() {
        System.out.println("setLastShot");
        Coordinates lastShot = null;
        WarboatsModel instance = null;
        instance.setLastShot(lastShot);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNavy method, of class WarboatsModel.
     */
    public void testGetNavy() {
        System.out.println("getNavy");
        WarboatsModel instance = null;
        ArrayList<Boat> expResult = null;
        ArrayList<Boat> result = instance.getNavy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLost method, of class WarboatsModel.
     */
    public void testIsLost() {
        System.out.println("isLost");
        WarboatsModel instance = null;
        boolean expResult = false;
        boolean result = instance.isLost();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPlayerTurn method, of class WarboatsModel.
     */
    public void testIsPlayerTurn() {
        System.out.println("isPlayerTurn");
        boolean expResult = false;
        boolean result = WarboatsModel.isPlayerTurn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of togglePlayerTurn method, of class WarboatsModel.
     */
    public void testTogglePlayerTurn() {
        System.out.println("togglePlayerTurn");
        WarboatsModel.togglePlayerTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isWon method, of class WarboatsModel.
     */
    public void testIsWon() {
        System.out.println("isWon");
        WarboatsModel instance = null;
        boolean expResult = false;
        boolean result = instance.isWon();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWon method, of class WarboatsModel.
     */
    public void testSetWon() {
        System.out.println("setWon");
        boolean won = false;
        WarboatsModel instance = null;
        instance.setWon(won);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
