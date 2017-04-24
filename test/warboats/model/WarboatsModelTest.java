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
* File: WarboatsModelTest
* Description: Tests the WarboatsModel class. The sending of a player's
* move is not tested, as this would simply test the functionality of
* the external library that we implemented.  Other than getters and setters,
* all other methods are tested.
*
* ****************************************
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
public class WarboatsModelTest extends TestCase {

    private WarboatsModel testModel;

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
        testModel = new WarboatsModel(null, null);
        //null: as we are not testing network functionality.
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of addShip method, of class WarboatsModel.
     */
    public void testAddShip() {
        System.out.println("addShip");

        int startX = 2;
        int endX = 3;
        int startY = 3;
        int endY = 3;

        testModel.addShip(1, startX, startY, endX, endY);

        Boat placedShip = testModel.getNavy().get(0);

        int placedShipXStart = placedShip.getStartX();
        int placedShipYStart = placedShip.getStartY();
        int placedShipXEnd = placedShip.getEndX();
        int placedShipYEnd = placedShip.getEndY();

        assertEquals(placedShipXStart, startX);
        assertEquals(placedShipYStart, startY);
        assertEquals(placedShipXEnd, endX);
        assertEquals(placedShipYEnd, endY);

        assertEquals(placedShip.getSize(), 2);

    }

    /**
     * Test of checkLoss method, of class WarboatsModel.
     */
    public void testCheckLoss() {
        System.out.println("checkLoss");

        int startX = 2;
        int endX = 3;
        int startY = 3;
        int endY = 3;
        testModel.addShip(1, startX, startY, endX, endY);

        testModel.checkLoss();
        boolean result = testModel.isLost();
        assertEquals(result, false);

        testModel.getNavy().get(0).toggleAlive();
        testModel.checkLoss();
        result = testModel.isLost();
        assertEquals(result, true);

    }

}
