/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 10:37:26 AM
*
* Project: warboats
* Package: warboats.model
* File: BoardTest
* Description: Tests the functionality of Board class methods.
*
* ****************************************
 */
package warboats.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import warboats.network.Coordinates;

/**
 *
 * @author khc009
 */
public class BoardTest extends TestCase {

    /**
     * Instance of WarboatsModel to run tests on.
     */
    //private Board testBoard;
    private WarboatsModel testModel;

    public BoardTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BoardTest.class);
        return suite;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //testBoard = new Board();
        testModel = new WarboatsModel(null, null);
        testModel.addShip(5, 2, 3, 6, 3); //Adds a carrier for testing purposes
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of checkHit method, of class Board. DONE.
     */
    public void testCheckHit() {
        System.out.println("checkHit");

        Board testBoard = testModel.getMyBoard();
        boolean result = testBoard.checkHit(5, 5);
        assertEquals(false, result);

        result = testBoard.checkHit(4, 3);
        assertEquals(true, result);

    }

    /**
     * Test of hitMiss method, of class Board.
     */
    public void testHitMiss() {
        System.out.println("hitMiss");
        Board testBoard = testModel.getMyBoard();
        int x = 5;
        int y = 5;
        Coordinates testCoord = new Coordinates(x, y);

        testBoard.hitMiss(true, testCoord);
        Marker testTile = testBoard.getBoard().get(x - 1).get(y - 1);
        assertEquals("H", testTile.getConsoleRepresentation());

        testBoard.hitMiss(false, testCoord);
        assertEquals("M", testTile.getConsoleRepresentation());
    }

}
