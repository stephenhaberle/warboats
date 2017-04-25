/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 10:40:05 AM
*
* Project: warboats
* Package: warboats
* File: Marker
* Description: Class meant to represent tiles on the boards.
*
* ****************************************
 */
package warboats.model;

import warboats.boats.Boat;

/**
 *
 * @author clo006
 */
public class Marker {

    private int posX;
    private int posY;
    private String consoleRepresentation = "o"; //can determine if tile has been shot at based on consoleRepresentation

    private boolean isHit = false;
    //simple boolean value to determine if a boat is placed on the tile
    private boolean isShipOn = false;

    //field pointing to the instance of boat placed on this marker, if one exists
    private Boat boatOn;

    public Marker(int x, int y) {
        posX = x;
        posY = y;
        consoleRepresentation = "o";
    }

    @Override
    public String toString() {
        return this.consoleRepresentation;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getConsoleRepresentation() {
        return consoleRepresentation;
    }

    public void setConsoleRepresentation(String consoleRepresentation) {
        this.consoleRepresentation = consoleRepresentation;
    }

    public boolean isShipOn() {
        return isShipOn;
    }

    public void toggleShipOn() {
        this.isShipOn = !(this.isShipOn);
        this.consoleRepresentation = "B";
    }

    public void setBoat(Boat boatType) {
        this.boatOn = boatType;
    }

    public Boat getBoat() {
        return boatOn;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        this.isHit = hit;
    }

}
