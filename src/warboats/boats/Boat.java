/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 1:38:29 PM
*
* Project: warboats
* Package: warboats.boats
* File: Boat
* Description: Generic parent class to represent boats and their attributes.
*
* ****************************************
 */
package warboats.boats;

import static java.lang.Math.abs;
import java.util.ArrayList;
import warboats.model.Board;
import warboats.model.Marker;

/**
 * Generic boat class containing methods specific to all boats
 *
 * @author clo006
 */
public class Boat {

    private Board currentBoard;
    private boolean alive; //is the boat sunk or not
    private ArrayList<Marker> positionTiles;
    private int startX;
    private int startY; //converted from letter representation
    private int endX;
    private int endY; //converted from letter representation
    private int size; // predetermined size of the boat

    /**
     *
     * @param xStart Start x coordinate of the gridpane that the boat will be
     * placed on
     * @param yStart Start y coordinate of the gridpane that the boat will be
     * placed on
     * @param xEnd End x coordinate of the gridpane that the boat will be placed
     * on
     * @param yEnd End y coordinate of the gridpane that the boat will be placed
     * on
     * @param curBoard The board (player/opponent) that the ship will be a part
     * of
     */
    public Boat(int xStart, int yStart, int xEnd, int yEnd, Board curBoard) {
        alive = true;
        startX = xStart;
        startY = yStart;
        endX = xEnd;
        endY = yEnd;
        currentBoard = curBoard;

        positionTiles = new ArrayList<Marker>(); //Array of tiles in gridpane that the boat is placed on
    }

    /**
     * Places a boat at the coordinates at which it was initialized and assigns
     * that boat object to every marker on which the boat is placed.
     *
     * @param boatObject a boat that is to be placed
     */
    public void placeBoat(Boat boatObject) throws ClassCastException {
        try {
            // Check that a boat is on a single axis and is the correct size. Used mainly for entering in coordinates manually
            if ((this.startX == this.endX || this.startY == this.endY) && (abs(
                                                                           this.startX - this.endX) == this.size - 1 || abs(
                                                                           this.startY - this.endY) == this.size - 1)) {

                //Assign markers from board to Boat based off user input
                for (int i = startX; i <= endX; i++) {
                    for (int j = startY; j <= endY; j++) {
                        Marker temp = currentBoard.getBoard().get(i - 1).get(
                                j - 1);
                        //Check to see if a boat is already on the tile the user is trying to place a boat on
                        if (temp.isShipOn()) {
                            throw new ClassCastException("SHIP ALREADY ON TILE");
                        }
                        else {
                            positionTiles.add(temp);
                            temp.toggleShipOn();
                            temp.setBoat(boatObject);
                        }
                    }
                }
            }
            else {
                throw new IllegalArgumentException(
                        "BOATS MUST BE ALONG A SINGLE AXIS");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("INVALID BOAT PLACEMENT COORDINATES");
            System.out.println(e);
        }
    }

    /**
     * Updates the position of a boat on the board TODO: CHECK TO SEE IF THIS IS
     * USED AT ALL
     *
     * @param x1 Start x coordinate of the gridpane that the boat will be placed
     * on
     * @param y1 Start y coordinate of the gridpane that the boat will be placed
     * on
     * @param x2 End x coordinate of the gridpane that the boat will be placed
     * on
     * @param y2 End y coordinate of the gridpane that the boat will be placed
     * on
     */
    public void updatePosition(int x1, int y1, int x2, int y2) {
        for (int i = this.positionTiles.size() - 1; i >= 0; i--) {
            Marker m = this.positionTiles.get(i);
            m.toggleShipOn();
            m.setConsoleRepresentation("o");
            this.positionTiles.remove(m);
        }
        this.startX = x1;
        this.startY = y1;
        this.endX = x2;
        this.endY = y2;
        this.placeBoat(this);
    }

    /**
     * Checks if a boat is sunk by seeing if all Markers on which that boat sits
     * are indicated to be hit
     *
     * @return boolean of whether or not the boat has been sunk
     */
    public boolean checkSunk() {
        int hitCount = 0;
        for (Marker temp : positionTiles) {
            if (temp.getConsoleRepresentation().equals("H")) {
                hitCount++;
            }
        }

        if (hitCount == this.size) {
            this.alive = false;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Sets size of boats Set to be package private so people cannot make boats
     * of custom size since boats cannot be placed on a board without a valid
     * size this prevents tampering with the game.
     *
     * @param size - An int
     */
    void setSize(int size) {
        this.size = size;
    }

    /**
     * Getter for the size of a boat
     *
     * @return and int representing the size of the boat
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Getter for the alive attribute
     *
     * @return a boolean representing the sunk status of a boat
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Getter for the position tiles
     *
     * @return an ArrayList of Markers representing the tiles that the ship
     * occupies on the gridpane
     */
    public ArrayList<Marker> getPositionTiles() {
        return positionTiles;
    }

    /**
     * Getter for the start x coordinate of a boat
     *
     * @return an int representing the x coordinate of the gridpane
     */
    public int getStartX() {
        return startX;
    }

    /**
     * Getter for the start y coordinate of a boat
     *
     * @return an int representing the y coordinate of the gridpane
     */
    public int getStartY() {
        return startY;
    }

    /**
     * Getter for the end x coordinate of a boat
     *
     * @return an int representing the x coordinate of the gridpane
     */
    public int getEndX() {
        return endX;
    }

    /**
     * Getter for the end y coordinate of a boat
     *
     * @return an int representing the y coordinate of the gridpane
     */
    public int getEndY() {
        return endY;
    }

    /**
     * Flips the sunk status of a ship. Can revive or kill off a ship
     */
    public void toggleAlive() {
        this.alive = !this.alive;
    }

}
