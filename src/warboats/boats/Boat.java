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
 *
 * @author clo006
 */
public class Boat {

    private Board currentBoard;
    private boolean alive;
    private ArrayList<Marker> positionTiles;
    private int startX;
    private int startY; //converted from letter representation
    private int endX;
    private int endY; //converted from letter representation
    private int size;

    public Boat(int xStart, int yStart, int xEnd, int yEnd, Board curBoard) {
        alive = true;
        startX = xStart;
        startY = yStart;
        endX = xEnd;
        endY = yEnd;
        currentBoard = curBoard;

        positionTiles = new ArrayList<Marker>();
    }

    /**
     * Places a boat at the coordinates at which it was initialized and assigns
     * that boat object to every marker on which the boat is placed.
     *
     * @param boatObjectType type of boat being placed
     */
    public void placeBoat(Boat boatObject) throws ClassCastException {
        try {
            if ((this.startX == this.endX || this.startY == this.endY) && (abs(
                                                                           this.startX - this.endX) == this.size - 1 || abs(
                                                                           this.startY - this.endY) == this.size - 1)) {

                //Assign markers from board to Boat based off user input
                for (int i = startX; i <= endX; i++) {
                    for (int j = startY; j <= endY; j++) {
                        Marker temp = currentBoard.getBoard().get(i - 1).get(
                                j - 1);
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
     * are indicated to be hit. Sets alive field of Boat object appropriately
     * afterwards.
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
     * Sets size of boats. Set to be package private so people cannot make boats
     * of custom size. Since boats cannot be placed on a board without a valid
     * size this prevents tampering with the game.
     *
     * @param size
     */
    void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isAlive() {
        return alive;
    }

    public ArrayList<Marker> getPositionTiles() {
        return positionTiles;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public void toggleAlive() {
        this.alive = !this.alive;
    }

}
