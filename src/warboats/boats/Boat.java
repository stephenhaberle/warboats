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
* Description:
*
* ****************************************
 */
package warboats.boats;

import static java.lang.Math.abs;
import java.util.ArrayList;
import warboats.Board;
import warboats.Marker;

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
    private int boatType;

    public Boat(int xStart, int yStart, int xEnd, int yEnd, Board curBoard) {
        alive = true;
        startX = xStart - 1;
        startY = yStart - 1;
        endX = xEnd - 1;
        endY = yEnd - 1;
        currentBoard = curBoard;

        positionTiles = new ArrayList<Marker>();
    }

    public void placeBoat() {
        try {
            if ((this.startX == this.endX || this.startY == this.endY) && (abs(
                                                                           this.startX - this.endX) == this.size - 1 || abs(
                                                                           this.startY - this.endY) == this.size - 1)) {

                //Assign markers from board to Boat based off user input
                System.out.println("START X " + this.startX);
                System.out.println("END X " + this.endX);
                System.out.println("START Y " + this.startY);
                System.out.println("END Y " + this.endY);
                for (int i = startX; i <= endX; i++) {
                    for (int j = startY; j <= endY; j++) {
                        Marker temp = currentBoard.getBoard().get(i).get(j);
                        System.out.println("MARKER X" + temp.getPosX());
                        System.out.println("MARKER Y" + temp.getPosY());
                        if (temp.isShipOn()) {
                            throw new Exception("SHIP ALREADY ON TILE");
                        }
                        else {
                            positionTiles.add(temp);
                            temp.toggleShipOn();
                            temp.setBoatType(boatType);
                        }
                    }
                }
            }
            else {
                throw new Exception("BOATS MUST BE ALONG A SINGLE AXIS");
            }
        } catch (Exception e) {
            System.out.println("INVALID BOAT PLACEMENT COORDINATES");
            System.out.println(e);
        }
    }

    public void setSize(int size) {
        this.size = size;
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

    public void setType(int boatType) {
        this.boatType = boatType;
    }

}
